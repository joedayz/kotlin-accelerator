package com.josediaz.kotlinaccelerator

/**
 * CollectionsExamples - comprehensive showcase of Kotlin collection types and operations.
 * All functions are pure and return values so they can be asserted from tests.
 */
object CollectionsExamples {

    // Immutable vs mutable collections
    fun immutableList(): List<Int> = listOf(1, 2, 3)
    fun mutableList(): MutableList<Int> = mutableListOf(1, 2, 3)

    fun immutableSet(): Set<String> = setOf("a", "b", "a") // deduped
    fun mutableSet(): MutableSet<String> = mutableSetOf("a", "b")

    fun immutableMap(): Map<String, Int> = mapOf("one" to 1, "two" to 2)
    fun mutableMap(): MutableMap<String, Int> = mutableMapOf("one" to 1, "two" to 2)

    // Arrays
    fun intArray(): IntArray = intArrayOf(1, 2, 3)
    fun genericArray(): Array<String> = arrayOf("x", "y")

    // Ranges
    fun intRangeInclusive(): IntRange = 1..5
    fun intRangeExclusive(): IntProgression = 1 until 5
    fun intRangeStep(): IntProgression = 0..10 step 2
    fun charRange(): CharRange = 'a'..'e'

    // Sequences (lazy)
    fun squaresSequence(n: Int): List<Int> = (1..n).asSequence().map { it * it }.toList()

    // Pairs/Triples
    fun pairAndTriple(): Pair<Pair<Int, String>, Triple<Int, Int, Int>> =
        (1 to "one") to Triple(1, 2, 3)

    // Common operations: map, filter, reduce, fold, groupBy, associate, zip, flatten/flatMap
    fun mapFilter(numbers: List<Int>): List<Int> = numbers.filter { it % 2 == 0 }.map { it * 10 }

    fun reduceSum(numbers: List<Int>): Int = numbers.reduce { acc, n -> acc + n }
    fun foldProduct(numbers: List<Int>): Int = numbers.fold(1) { acc, n -> acc * n }

    fun groupByParity(numbers: List<Int>): Map<String, List<Int>> =
        numbers.groupBy { if (it % 2 == 0) "even" else "odd" }

    fun associateByLength(words: List<String>): Map<Int, List<String>> =
        words.groupBy { it.length }

    fun zipLists(a: List<Int>, b: List<String>): List<String> = a.zip(b) { x, y -> "$x-$y" }

    fun flattenLists(matrix: List<List<Int>>): List<Int> = matrix.flatten()

    fun flatMapTokens(lines: List<String>): List<String> =
        lines.flatMap { it.split(Regex("\\s+")) }.filter { it.isNotBlank() }

    // Distinct, sorted, take/drop, chunked/windowed
    fun distinctSorted(numbers: List<Int>): List<Int> = numbers.distinct().sorted()

    fun takeDrop(numbers: List<Int>): Pair<List<Int>, List<Int>> = numbers.take(3) to numbers.drop(3)

    fun chunkedWindowed(numbers: List<Int>): Pair<List<List<Int>>, List<List<Int>>> =
        numbers.chunked(3) to numbers.windowed(size = 3, step = 2, partialWindows = false)

    // Mutable ops return copies to keep functions pure
    fun addToMutableList(list: List<Int>, value: Int): List<Int> {
        val m = list.toMutableList()
        m.add(value)
        return m
    }

    fun putInMutableMap(map: Map<String, Int>, key: String, value: Int): Map<String, Int> {
        val m = map.toMutableMap()
        m[key] = value
        return m
    }
}
