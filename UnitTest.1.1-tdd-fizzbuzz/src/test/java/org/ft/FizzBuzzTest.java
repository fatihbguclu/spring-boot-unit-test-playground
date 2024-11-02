package org.ft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    @Test
    @DisplayName("Divisible By Three")
    @Order(1)
    void testForDivisibleByThree() {
        fail("fail");
    }

}