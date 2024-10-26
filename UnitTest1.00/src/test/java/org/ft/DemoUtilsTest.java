package org.ft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

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