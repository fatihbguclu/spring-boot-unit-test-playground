package org.ft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    @Test
    @DisplayName("Divisible By Three")
    @Order(1)
    void testForDivisibleByThree() {
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.computeFizzBuzz(3), "Should be Fizz");
    }

    @Test
    @DisplayName("Divisible By Five")
    @Order(2)
    void testForDivisibleByFive() {
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.computeFizzBuzz(5), "Should be Buzz");
    }

    @Test
    @DisplayName("Divisible By Three and Five")
    @Order(3)
    void testForDivisibleByThreeAndFive() {
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.computeFizzBuzz(15), "Should be FizzBuzz");
    }

    @Test
    @DisplayName("Not Divisible By Three or Five")
    @Order(4)
    void testForNotDivisibleByThreeOrFive() {
        String expected = "112";
        assertEquals(expected, FizzBuzz.computeFizzBuzz(112), "Should return input number");
    }

    @ParameterizedTest(name = "value={0} expected={1}")
    @DisplayName("Testing FizzBuzz with small test data")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    void testFizzBuzzWithSmallCsv(int value, String expected) {
        assertEquals(expected, FizzBuzz.computeFizzBuzz(value));
    }

    @ParameterizedTest(name = "value={0} expected={1}")
    @DisplayName("Testing FizzBuzz with medium test data")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(6)
    void testFizzBuzzWithMediumCsv(int value, String expected) {
        assertEquals(expected, FizzBuzz.computeFizzBuzz(value));
    }

    @ParameterizedTest(name = "value={0} expected={1}")
    @DisplayName("Testing FizzBuzz with large test data")
    @CsvFileSource(resources = "/large-test-data.csv")
    @Order(7)
    void testFizzBuzzWithLargeCsv(int value, String expected) {
        assertEquals(expected, FizzBuzz.computeFizzBuzz(value));
    }
}