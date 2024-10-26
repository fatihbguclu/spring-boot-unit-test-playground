package org.ft;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DemoUtilsTestLifeCycles {

    DemoUtils demoUtils;

    @BeforeAll
    static void setupBeforeEachClass() {
        System.out.println("@BeforeAll executes only once before all test methods execution in the class");
    }

    @AfterAll
    static void setupAfterAll() {
        System.out.println("@AfterAll executes only once after all test methods execution in the class");
    }

    @BeforeEach
    void setupBeforeEachTest() {
        System.out.println("@BeforeEach executes before the execution of each test method");
        demoUtils = new DemoUtils();
    }

    @BeforeEach
    void setupAfterEachTest() {
        System.out.println("@AfterEach running after each test");
    }

    @Test
    void testEqualsAndNotEquals() {
        DemoUtils demoUtils = new DemoUtils();
        assertEquals(10, demoUtils.add(5, 5), "5+5 must be equals 10");
    }

    @Test
    void testNullAndNotNull() {
        DemoUtils demoUtils = new DemoUtils();
        Integer number = null;
        Integer number2 = 2;
        assertNull(demoUtils.checkNull(number), "Object should null");
        assertNotNull(demoUtils.checkNull(number2), "Object should not to be null");
    }
}
