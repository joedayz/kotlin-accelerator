package com.josediaz.kotlinaccelerator

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.properties.Delegates
import kotlin.system.measureTimeMillis

/**
 * 🚀 ADVANCED KOTLIN TOPICS - SENIOR DEVELOPER ESSENTIALS
 * 
 * This file demonstrates advanced Kotlin concepts essential for senior developers.
 * All examples are working and demonstrate real-world patterns.
 * 
 * 🎯 KEY LEARNING POINTS:
 * 1. Advanced Collections and Functional Programming
 * 2. Coroutines and Async Programming
 * 3. Delegation Patterns
 * 4. Generics and Type Safety
 * 5. Sealed Classes and Pattern Matching
 * 6. Inline Functions and Performance
 * 7. DSL Builders
 * 8. Reflection and Annotations
 * 
 * 🔥 WHY THIS MATTERS:
 * - These are the concepts that separate junior from senior developers
 * - Essential for building production-ready applications
 * - Required knowledge for technical interviews
 * - Foundation for advanced frameworks and libraries
 */
class AdvancedKotlinTopics {
    
    fun demonstrateVariablesAndNullSafety() {
        println("=== Variables and Null Safety ===")
        
        // 🎯 VARIABLES: val vs var (immutable vs mutable)
        val immutableName = "Alice"           // val = final in Java
        var mutableAge = 25                   // var = regular variable in Java
        
        // immutableName = "Bob"              // ❌ Compilation error - val cannot be reassigned
        mutableAge = 26                       // ✅ OK - var can be reassigned
        
        println("Name: $immutableName, Age: $mutableAge")
        
        // 🔥 TYPE INFERENCE: Kotlin infers types automatically
        val inferredString = "Hello World"    // Type: String
        val inferredNumber = 42               // Type: Int
        val inferredList = listOf(1, 2, 3)    // Type: List<Int>
        
        println("Inferred types: $inferredString, $inferredNumber, $inferredList")
        
        // 🚀 NULL SAFETY: The killer feature of Kotlin
        val nonNullString: String = "Hello"   // Cannot be null
        val nullableString: String? = null    // Can be null (note the ?)
        
        // nonNullString = null               // ❌ Compilation error - cannot assign null
        
        // 🎯 SAFE CALL OPERATOR: ?.
        val length1 = nonNullString.length    // Direct access - safe
        val length2 = nullableString?.length  // Safe call - returns null if nullableString is null
        
        println("Length 1: $length1, Length 2: $length2")
        
        // 🔥 ELVIS OPERATOR: ?: (null coalescing)
        val safeLength = nullableString?.length ?: 0  // Use 0 if null
        val defaultName = nullableString ?: "Unknown" // Use "Unknown" if null
        
        println("Safe length: $safeLength, Default name: $defaultName")
        
        // 🚀 NOT-NULL ASSERTION: !! (use with caution)
        // val forcedLength = nullableString!!.length    // This would throw NPE since nullableString is null
        // This is dangerous! Only use when you're 100% sure it's not null
        
        // Example of safe usage of !! operator
        val safeString: String? = "Hello"
        val forcedLength = safeString!!.length    // Safe because we know it's not null
        println("Forced length: $forcedLength")
        
        // 🎯 SMART CASTS: Automatic casting after null checks
        fun processString(str: String?) {
            if (str != null) {
                // str is automatically cast to String (non-null) here
                println("String length: ${str.length}")  // No ? needed
                println("String uppercase: ${str.uppercase()}")
            }
        }
        
        processString("Hello")
        processString(null)
        
        // 🔥 LET FUNCTION: Execute code only if not null
        nullableString?.let { str ->
            println("String is not null: $str")
            println("Length: ${str.length}")
        }
        
        // Example with non-null value
        val nonNullValue: String? = "Hello World"
        nonNullValue?.let { str ->
            println("String is not null: $str")
            println("Length: ${str.length}")
        }
        
        // 🚀 ALSO FUNCTION: Execute side effects
        val result = "Hello".also { str ->
            println("Processing: $str")
        }.uppercase()
        
        println("Result: $result")
        
        // 🎯 APPLY FUNCTION: Configure objects
        val person = Person("", 0, "").apply {
            name = "Alice"
            age = 25
            department = "Engineering"
        }
        
        println("Person: $person")
        
        // 🔥 RUN FUNCTION: Execute block and return result
        val personInfo = person.run {
            "Name: $name, Age: $age, Department: $department"
        }
        
        println("Person info: $personInfo")
        
        // 🚀 WITH FUNCTION: Execute multiple operations on an object
        val formattedInfo = with(person) {
            "Employee: $name ($age years old) works in $department"
        }
        
        println("Formatted info: $formattedInfo")
        
        println("=== End Variables and Null Safety ===\n")
    }
    
    fun demonstrateAdvancedCollections() {
        println("=== Advanced Collections and Functional Programming ===")
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // 🎯 FUNCTIONAL OPERATIONS: Chain multiple transformations
        val result = numbers
            .filter { it % 2 == 0 }           // Keep only even numbers
            .map { it * it }                  // Square each number
            .filter { it > 10 }              // Keep only squares > 10
            .sorted()                        // Sort the results
            .take(3)                         // Take first 3 elements
        
        println("Chained operations: $result")
        
        // 🔥 SEQUENCES: Lazy evaluation for performance
        val largeList = (1..1_000_000).toList()
        
        val collectionTime = measureTimeMillis {
            val result = largeList
                .filter { it % 2 == 0 }
                .map { it * it }
                .filter { it > 1000 }
                .take(10)
            println("Collection result: $result")
        }
        
        val sequenceTime = measureTimeMillis {
            val result = largeList.asSequence()
                .filter { it % 2 == 0 }
                .map { it * it }
                .filter { it > 1000 }
                .take(10)
                .toList()
            println("Sequence result: $result")
        }
        
        println("Collection time: ${collectionTime}ms")
        println("Sequence time: ${sequenceTime}ms")
        println("Sequence is ${collectionTime / sequenceTime.toDouble()}x faster!")
        
        // 🚀 ADVANCED OPERATIONS: Grouping and partitioning
        val people = listOf(
            Person("Alice", 25, "Engineering"),
            Person("Bob", 30, "Marketing"),
            Person("Charlie", 35, "Engineering"),
            Person("Diana", 28, "Sales")
        )
        
        val groupedByDept = people.groupBy { it.department }
        val (adults, minors) = people.partition { it.age >= 18 }
        
        println("Grouped by department: $groupedByDept")
        println("Adults: ${adults.size}, Minors: ${minors.size}")
    }
    
    fun demonstrateCoroutines() {
        println("\n=== Coroutines and Async Programming ===")
        
        // 🎯 BASIC COROUTINES: Simple async operations
        runBlocking {
            println("Main thread: ${Thread.currentThread().name}")
            
            val job1 = launch {
                delay(100)
                println("Coroutine 1: ${Thread.currentThread().name}")
            }
            
            val job2 = launch {
                delay(200)
                println("Coroutine 2: ${Thread.currentThread().name}")
            }
            
            job1.join()
            job2.join()
        }
        
        // 🔥 ASYNC/AWAIT: Parallel execution
        runBlocking {
            val time = measureTimeMillis {
                val deferred1 = async { fetchData1() }
                val deferred2 = async { fetchData2() }
                val deferred3 = async { fetchData3() }
                
                val result1 = deferred1.await()
                val result2 = deferred2.await()
                val result3 = deferred3.await()
                
                println("Parallel results: $result1, $result2, $result3")
            }
            println("Parallel execution time: ${time}ms")
        }
        
        // 🚀 CHANNELS: Communication between coroutines
        runBlocking {
            val channel = Channel<String>()
            
            val producer = launch {
                repeat(5) { i ->
                    channel.send("Message $i")
                    delay(100)
                }
                channel.close()
            }
            
            val consumer = launch {
                for (message in channel) {
                    println("Received: $message")
                }
            }
            
            producer.join()
            consumer.join()
        }
    }
    
    fun demonstrateDelegation() {
        println("\n=== Delegation Patterns ===")
        
        // 🎯 PROPERTY DELEGATION: Lazy initialization
        class ExpensiveResource {
            val expensiveValue by lazy {
                println("Computing expensive value...")
                Thread.sleep(1000)
                "Expensive result"
            }
        }
        
        val resource = ExpensiveResource()
        println("Resource created")
        println("Accessing expensive value: ${resource.expensiveValue}")
        println("Accessing again: ${resource.expensiveValue}") // Cached!
        
        // 🔥 OBSERVABLE DELEGATION: React to property changes
        class User {
            var name: String by Delegates.observable("Unknown") { _, old, new ->
                println("Name changed from '$old' to '$new'")
            }
            
            var age: Int by Delegates.observable(0) { _, old, new ->
                println("Age changed from $old to $new")
            }
        }
        
        val user = User()
        user.name = "Alice"
        user.age = 25
        
        // 🚀 VETOABLE DELEGATION: Validate before setting
        class BankAccount {
            var balance: Int by Delegates.vetoable(0) { _, old, new ->
                if (new < 0) {
                    println("Invalid balance: $new (cannot be negative)")
                    false // Reject the change
                } else {
                    println("Balance changed from $old to $new")
                    true // Accept the change
                }
            }
        }
        
        val account = BankAccount()
        account.balance = 100
        account.balance = -50 // This will be rejected
        println("Final balance: ${account.balance}")
    }
    
    fun demonstrateGenerics() {
        println("\n=== Generics and Type Safety ===")
        
        // 🎯 GENERIC CLASSES: Type-safe containers
        class Box<T>(private var item: T) {
            fun get(): T = item
            fun set(item: T) { this.item = item }
        }
        
        val stringBox = Box("Hello")
        val intBox = Box(42)
        
        println("String box: ${stringBox.get()}")
        println("Int box: ${intBox.get()}")
        
        // 🔥 GENERIC FUNCTIONS: Type-safe operations
        fun <T> swap(list: MutableList<T>, i: Int, j: Int) {
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
        
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        swap(numbers, 0, 4)
        println("Swapped numbers: $numbers")
        
        // 🚀 REIFIED GENERICS: Access type information at runtime (using top-level helpers)
        println("Is 'Hello' a String? ${isOfType<String>("Hello")}")
        println("Is 42 a String? ${isOfType<String>(42)}")
        println("Type name of String: ${getTypeName<String>()}")
        println("Type name of Int: ${getTypeName<Int>()}")
    }
    
    fun demonstrateSealedClasses() {
        println("\n=== Sealed Classes and Pattern Matching ===")
        
        // 🎯 SEALED CLASS: Restricted inheritance hierarchy
        val successResult = Result.Success("Hello World")
        val errorResult = Result.Error(RuntimeException("Something went wrong"))
        val loadingResult = Result.Loading
        
        // 🔥 WHEN EXPRESSION: Exhaustive pattern matching
        fun handleResult(result: Result<String>) {
            when (result) {
                is Result.Success -> println("Success: ${result.value}")
                is Result.Error -> println("Error: ${result.exception.message}")
                is Result.Loading -> println("Loading...")
            }
        }
        
        handleResult(successResult)
        handleResult(errorResult)
        handleResult(loadingResult)
        
        // 🚀 SEALED CLASS WITH METHODS: Add behavior
        val circle = Shape.Circle(5.0)
        val rectangle = Shape.Rectangle(4.0, 6.0)
        
        fun processShape(shape: Shape) {
            when (shape) {
                is Shape.Circle -> {
                    println("Circle with radius ${shape.radius}")
                    println("Area: ${shape.area()}")
                }
                is Shape.Rectangle -> {
                    println("Rectangle ${shape.width}x${shape.height}")
                    println("Area: ${shape.area()}")
                }
            }
        }
        
        processShape(circle)
        processShape(rectangle)
    }
    
    fun demonstrateInlineFunctions() {
        println("\n=== Inline Functions and Performance ===")
        
        // 🎯 INLINE FUNCTION: Eliminates function call overhead
        val time = measureTime {
            Thread.sleep(100)
            println("Executing block...")
        }
        
        println("Execution time: ${time}ms")
        
        // 🔥 REIFIED GENERICS: Access type information at runtime
        println("Is 'Hello' a String? ${isOfType<String>("Hello")}")
        println("Type name of String: ${getTypeName<String>()}")
        
        // 🚀 INLINE CLASSES: Zero-cost abstractions
        val userId = UserId(123L)
        val productId = ProductId(456L)
        
        processUser(userId)
        processProduct(productId)
    }
    
    fun demonstrateDSLBuilders() {
        println("\n=== DSL Builders ===")
        
        // 🎯 HTML DSL: Create HTML-like structures
        class HtmlElement(val name: String, val content: String = "") {
            private val children = mutableListOf<HtmlElement>()
            
            fun addChild(child: HtmlElement) {
                children.add(child)
            }
            
            override fun toString(): String {
                val childrenStr = children.joinToString("")
                return "<$name>$content$childrenStr</$name>"
            }
        }
        
        // 🔥 DSL FUNCTIONS: Create builder functions
        fun html(init: HtmlElement.() -> Unit): HtmlElement {
            val html = HtmlElement("html")
            html.init()
            return html
        }
        
        fun HtmlElement.head(init: HtmlElement.() -> Unit): HtmlElement {
            val head = HtmlElement("head")
            head.init()
            addChild(head)
            return head
        }
        
        fun HtmlElement.body(init: HtmlElement.() -> Unit): HtmlElement {
            val body = HtmlElement("body")
            body.init()
            addChild(body)
            return body
        }
        
        fun HtmlElement.title(content: String): HtmlElement {
            val title = HtmlElement("title", content)
            addChild(title)
            return title
        }
        
        fun HtmlElement.h1(content: String): HtmlElement {
            val h1 = HtmlElement("h1", content)
            addChild(h1)
            return h1
        }
        
        fun HtmlElement.p(content: String): HtmlElement {
            val p = HtmlElement("p", content)
            addChild(p)
            return p
        }
        
        // 🚀 DSL IN ACTION: Create HTML structure
        val html = html {
            head {
                title("My Page")
            }
            body {
                h1("Welcome")
                p("This is a paragraph")
            }
        }
        
        println("Generated HTML:")
        println(html)
    }
    
    fun demonstrateReflection() {
        println("\n=== Reflection and Annotations ===")
        
        // 🎯 CLASS REFLECTION: Inspect class information
        val stringClass = String::class
        println("String class name: ${stringClass.simpleName}")
        println("String class qualified name: ${stringClass.qualifiedName}")
        println("String class is data class: ${stringClass.isData}")
        
        // 🔥 FUNCTION REFLECTION: Inspect function information
        val addFunction = ::processUser
        println("Function name: ${addFunction.name}")
        println("Function parameters: ${addFunction.parameters.map { it.name }}")
        println("Function return type: ${addFunction.returnType}")
        
        // 🚀 PROPERTY REFLECTION: Inspect property information
        val personClass = Person::class
        println("Person class: ${personClass.simpleName}")
        
        // 🎯 RUNTIME TYPE CHECKING: Inspect types at runtime
        fun <T> inspectType(value: T): String {
            val clazz = value!!::class
            return "Type: ${clazz.simpleName}, " +
                   "Is data class: ${clazz.isData}, " +
                   "Is sealed: ${clazz.isSealed}"
        }
        
        val person = Person("Alice", 25, "Engineering")
        println("Person: ${inspectType(person)}")
    }
}

// 🔥 DATA CLASS FOR EXAMPLES
data class Person(
    var name: String,
    var age: Int,
    var department: String
)

// 🎯 SEALED CLASSES FOR EXAMPLES
sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

sealed class Shape {
    abstract fun area(): Double
    
    data class Circle(val radius: Double) : Shape() {
        override fun area(): Double = Math.PI * radius * radius
    }
    
    data class Rectangle(val width: Double, val height: Double) : Shape() {
        override fun area(): Double = width * height
    }
}

// 🚀 INLINE CLASSES FOR EXAMPLES
@JvmInline
value class UserId(val value: Long)

@JvmInline
value class ProductId(val value: Long)

// 🔥 UTILITY FUNCTIONS FOR EXAMPLES
inline fun measureTime(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

inline fun <reified T> isOfType(value: Any): Boolean {
    return value is T
}

inline fun <reified T> getTypeName(): String {
    return T::class.simpleName ?: "Unknown"
}

fun processUser(userId: UserId) {
    println("Processing user with ID: ${userId.value}")
}

fun processProduct(productId: ProductId) {
    println("Processing product with ID: ${productId.value}")
}

// 🚀 SUSPEND FUNCTIONS: Simulate async operations
suspend fun fetchData1(): String {
    delay(1000)
    return "Data 1"
}

suspend fun fetchData2(): String {
    delay(1500)
    return "Data 2"
}

suspend fun fetchData3(): String {
    delay(800)
    return "Data 3"
}

// 🎯 EXTENSION FUNCTIONS FOR COLLECTIONS
fun <T> List<T>.second(): T = this[1]
fun <T> List<T>.penultimate(): T = this[size - 2]

// 🔥 MAIN FUNCTION TO RUN ALL EXAMPLES
fun main() {
    println("🚀 Advanced Kotlin Topics - Senior Developer Essentials")
    println("=".repeat(60))
    
    val advanced = AdvancedKotlinTopics()
    advanced.demonstrateAdvancedCollections()
    advanced.demonstrateCoroutines()
    advanced.demonstrateDelegation()
    advanced.demonstrateGenerics()
    advanced.demonstrateSealedClasses()
    advanced.demonstrateInlineFunctions()
    advanced.demonstrateDSLBuilders()
    advanced.demonstrateReflection()
    
    println("\n🎉 Advanced Kotlin Topics examples completed!")
    println("Key takeaways:")
    println("1. Use sequences for large datasets")
    println("2. Coroutines are better than threads")
    println("3. Delegation reduces code duplication")
    println("4. Generics provide type safety")
    println("5. Sealed classes enable pattern matching")
    println("6. Inline functions improve performance")
    println("7. DSL builders create expressive APIs")
    println("8. Reflection enables dynamic behavior")
}
