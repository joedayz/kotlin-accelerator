package com.josediaz.kotlinaccelerator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * 🧪 SIMPLE KOTLIN TESTS - LEARNING TESTING WITH KOTLIN
 * 
 * This file demonstrates basic testing concepts in Kotlin.
 * Testing is crucial for learning - it helps you understand how code works!
 * 
 * 🎯 KEY LEARNING POINTS:
 * 1. JUnit 5 testing framework
 * 2. Kotlin test syntax
 * 3. Assertions and expectations
 * 4. Testing data classes
 * 5. Testing functions
 * 
 * 🚀 WHY TESTING MATTERS:
 * - Tests verify your code works correctly
 * - Tests serve as documentation
 * - Tests catch bugs early
 * - Tests help you understand the code
 */
class SimpleTest {
    
    @Test
    fun testBasicKotlinFeatures() {
        // Test variables
        val name = "John"
        val age = 30
        assertNotNull(name)
        assertTrue(age > 0)
        
        // Test null safety
        val nullableString: String? = null
        val safeLength = nullableString?.length ?: 0
        assertEquals(0, safeLength)
        
        // Test data class
        val person = Person("Alice", 25, "Engineering")
        assertEquals("Alice", person.name)
        assertEquals(25, person.age)
        assertEquals("Engineering", person.department)
        
        // Test collections
        val numbers = listOf(1, 2, 3, 4, 5)
        val doubled = numbers.map { it * 2 }
        assertEquals(listOf(2, 4, 6, 8, 10), doubled)
        
        // Test functions
        val result = add(5, 3)
        assertEquals(8, result)
    }
    
    private fun add(a: Int, b: Int): Int = a + b
}
