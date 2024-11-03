package org.ft;

import org.junit.jupiter.api.*;

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
}