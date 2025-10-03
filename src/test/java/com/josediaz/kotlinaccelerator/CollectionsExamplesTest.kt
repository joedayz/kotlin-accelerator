package com.josediaz.kotlinaccelerator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CollectionsExamplesTest {

    @Test
    fun `immutable and mutable basics`() {
        assertEquals(listOf(1,2,3), CollectionsExamples.immutableList())
        assertEquals(setOf("a","b"), CollectionsExamples.immutableSet())
        assertEquals(mapOf("one" to 1, "two" to 2), CollectionsExamples.immutableMap())

        val mlist = CollectionsExamples.mutableList()
        mlist.add(4)
        assertEquals(listOf(1,2,3,4), mlist)

        val mset = CollectionsExamples.mutableSet()
        mset.add("c")
        assertEquals(setOf("a","b","c"), mset)

        val mmap = CollectionsExamples.mutableMap()
        mmap["three"] = 3
        assertEquals(mapOf("one" to 1, "two" to 2, "three" to 3), mmap)
    }

    @Test
    fun `arrays and ranges`() {
        assertArrayEquals(intArrayOf(1,2,3), CollectionsExamples.intArray())
        assertArrayEquals(arrayOf("x","y"), CollectionsExamples.genericArray())

        assertEquals(listOf(1,2,3,4,5), CollectionsExamples.intRangeInclusive().toList())
        assertEquals(listOf(1,2,3,4), CollectionsExamples.intRangeExclusive().toList())
        assertEquals(listOf(0,2,4,6,8,10), CollectionsExamples.intRangeStep().toList())
        assertEquals(listOf('a','b','c','d','e'), CollectionsExamples.charRange().toList())
    }

    @Test
    fun `sequences and pairs`() {
        assertEquals(listOf(1,4,9,16,25), CollectionsExamples.squaresSequence(5))
        val (p, t) = CollectionsExamples.pairAndTriple()
        assertEquals(1 to "one", p)
        assertEquals(Triple(1,2,3), t)
    }

    @Test
    fun `core operations`() {
        assertEquals(listOf(20,40), CollectionsExamples.mapFilter(listOf(1,2,3,4)))
        assertEquals(15, CollectionsExamples.reduceSum(listOf(1,2,3,4,5)))
        assertEquals(120, CollectionsExamples.foldProduct(listOf(1,2,3,4,5)))

        val grouped = CollectionsExamples.groupByParity(listOf(1,2,3,4,5))
        assertEquals(listOf(2,4), grouped["even"])
        assertEquals(listOf(1,3,5), grouped["odd"])

        val assoc = CollectionsExamples.associateByLength(listOf("a","bb","cc","ddd"))
        assertEquals(listOf("a"), assoc[1])
        assertEquals(setOf("bb","cc"), assoc[2]?.toSet())
        assertEquals(listOf("ddd"), assoc[3])

        assertEquals(listOf("1-a","2-b","3-c"), CollectionsExamples.zipLists(listOf(1,2,3), listOf("a","b","c")))

        assertEquals(listOf(1,2,3,4,5,6), CollectionsExamples.flattenLists(listOf(listOf(1,2,3), listOf(4,5,6))))

        val tokens = CollectionsExamples.flatMapTokens(listOf("hello world", "kotlin  collections"))
        assertEquals(listOf("hello","world","kotlin","collections"), tokens)
    }

    @Test
    fun `distinct sorted and slicing`() {
        assertEquals(listOf(1,2,3,4), CollectionsExamples.distinctSorted(listOf(3,1,2,3,4,2)))

        val (take3, drop3) = CollectionsExamples.takeDrop(listOf(1,2,3,4,5))
        assertEquals(listOf(1,2,3), take3)
        assertEquals(listOf(4,5), drop3)

        val (chunked, windowed) = CollectionsExamples.chunkedWindowed(listOf(1,2,3,4,5,6,7))
        assertEquals(listOf(listOf(1,2,3), listOf(4,5,6), listOf(7)), chunked)
        assertEquals(listOf(listOf(1,2,3), listOf(3,4,5), listOf(5,6,7)), windowed)
    }

    @Test
    fun `pure mutable helpers`() {
        assertEquals(listOf(1,2,3,9), CollectionsExamples.addToMutableList(listOf(1,2,3), 9))
        assertEquals(mapOf("one" to 1, "two" to 2, "x" to 99), CollectionsExamples.putInMutableMap(mapOf("one" to 1, "two" to 2), "x", 99))
    }
}

