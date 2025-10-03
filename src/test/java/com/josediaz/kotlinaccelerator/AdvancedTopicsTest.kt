package com.josediaz.kotlinaccelerator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlinx.coroutines.runBlocking

class AdvancedTopicsTest {

    @Test
    fun `variables and null safety`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateVariablesAndNullSafety()
        // If no exception and prints, we consider it demonstrated
        assertTrue(true)
    }

    @Test
    fun `advanced collections`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateAdvancedCollections()
        // If no exception and prints, we consider it demonstrated
        assertTrue(true)
    }

    @Test
    fun `coroutines basics`() = runBlocking {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateCoroutines()
        assertTrue(true)
    }

    @Test
    fun `delegation patterns`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateDelegation()
        assertTrue(true)
    }

    @Test
    fun `generics basics`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateGenerics()
        assertTrue(true)
    }

    @Test
    fun `sealed classes`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateSealedClasses()
        assertTrue(true)
    }

    @Test
    fun `inline and performance`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateInlineFunctions()
        assertTrue(true)
    }

    @Test
    fun `dsl builders`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateDSLBuilders()
        assertTrue(true)
    }

    @Test
    fun `reflection basics`() {
        val topics = AdvancedKotlinTopics()
        topics.demonstrateReflection()
        assertTrue(true)
    }
}
