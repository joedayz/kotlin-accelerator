package com.josediaz.kotlinaccelerator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Basic Testing with JUnit 5
 * 
 * Key Learning Points:
 * 1. Basic test structure
 * 2. Assertions
 * 3. Test lifecycle
 * 4. Parameterized tests
 * 5. Nested tests
 * 6. Test tags
 * 7. Timeout tests
 */
class BasicTesting {
    
    private lateinit var calculator: Calculator
    
    @BeforeEach
    fun setUp() {
        calculator = Calculator()
    }
    
    @Test
    @DisplayName("Should add two numbers correctly")
    fun testAddition() {
        // Given
        val a = 5
        val b = 3

        // When
        val result = calculator.add(a, b)
        
        // Then
        assertEquals(8, result)
    }
    
    @Test
    @DisplayName("Should subtract two numbers correctly")
    fun testSubtraction() {
        val result = calculator.subtract(10, 3)
        assertEquals(7, result)
    }
    
    @Test
    @DisplayName("Should multiply two numbers correctly")
    fun testMultiplication() {
        val result = calculator.multiply(4, 5)
        assertEquals(20, result)
    }
    
    @Test
    @DisplayName("Should divide two numbers correctly")
    fun testDivision() {
        val result = calculator.divide(15, 3)
        assertEquals(5.0, result, 0.001)
    }
    
    @Test
    @DisplayName("Should throw exception when dividing by zero")
    fun testDivisionByZero() {
        assertThrows(ArithmeticException::class.java) {
            calculator.divide(10, 0)
        }
    }
    
    @Test
    @DisplayName("Should handle multiple assertions")
    fun testMultipleAssertions() {
        val result = calculator.add(2, 3)
        
        assertAll("Calculator operations",
            { assertEquals(5, result) },
            { assertTrue(result > 0) },
            { assertFalse(result < 0) },
            { assertNotNull(result) }
        )
    }
    
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5])
    @DisplayName("Should return positive result for positive inputs")
    fun testPositiveInputs(input: Int) {
        val result = calculator.add(input, 1)
        assertTrue(result > 0)
    }
    
    @ParameterizedTest
    @CsvSource(
        "2, 3, 5",
        "10, 20, 30",
        "0, 5, 5",
        "-5, 5, 0"
    )
    @DisplayName("Should add numbers correctly with CSV data")
    fun testAdditionWithCsv(a: Int, b: Int, expected: Int) {
        val result = calculator.add(a, b)
        assertEquals(expected, result)
    }
    
    @ParameterizedTest
    @MethodSource("provideTestData")
    @DisplayName("Should multiply numbers correctly with method source")
    fun testMultiplicationWithMethodSource(a: Int, b: Int, expected: Int) {
        val result = calculator.multiply(a, b)
        assertEquals(expected, result)
    }
    
    companion object {
        @JvmStatic
        fun provideTestData(): Stream<Array<Any>> {
            return Stream.of(
                arrayOf(2, 3, 6),
                arrayOf(4, 5, 20),
                arrayOf(0, 10, 0),
                arrayOf(-2, 3, -6)
            )
        }
    }
    
    @Nested
    @DisplayName("Advanced Calculator Tests")
    inner class AdvancedCalculatorTests {
        
        @Test
        @DisplayName("Should calculate power correctly")
        fun testPower() {
            val result = calculator.power(2, 3)
            assertEquals(8, result)
        }
        
        @Test
        @DisplayName("Should calculate square root correctly")
        fun testSquareRoot() {
            val result = calculator.squareRoot(16.0)
            assertEquals(4.0, result, 0.001)
        }
        
        @Test
        @DisplayName("Should handle negative square root")
        fun testNegativeSquareRoot() {
            assertThrows(IllegalArgumentException::class.java) {
                calculator.squareRoot(-1.0)
            }
        }
    }
    
    @Test
    @Tag("slow")
    @DisplayName("Should complete within timeout")
    fun testTimeout() {
        assertTimeout(java.time.Duration.ofSeconds(2)) {
            calculator.slowOperation()
        }
    }
    
    @Test
    @DisplayName("Should handle null values")
    fun testNullHandling() {
        val result = calculator.add(5, 0)
        assertNotNull(result)
    }
    
    @Test
    @DisplayName("Should handle edge cases")
    fun testEdgeCases() {
        // Test with zero
        assertEquals(0, calculator.add(0, 0))
        
        // Test with negative numbers
        assertEquals(-2, calculator.add(-5, 3))
        
        // Test with large numbers
        assertEquals(2000000000, calculator.add(1000000000, 1000000000))
    }
}

/**
 * Simple Calculator class for testing
 */
class Calculator {
    fun add(a: Int, b: Int): Int = a + b
    
    fun subtract(a: Int, b: Int): Int = a - b
    
    fun multiply(a: Int, b: Int): Int = a * b
    
    fun divide(a: Int, b: Int): Double {
        if (b == 0) throw ArithmeticException("Division by zero")
        return a.toDouble() / b.toDouble()
    }
    
    fun power(base: Int, exponent: Int): Int {
        return Math.pow(base.toDouble(), exponent.toDouble()).toInt()
    }
    
    fun squareRoot(number: Double): Double {
        if (number < 0) throw IllegalArgumentException("Cannot calculate square root of negative number")
        return Math.sqrt(number)
    }
    
    fun slowOperation() {
        Thread.sleep(1000) // Simulate slow operation
    }
}
