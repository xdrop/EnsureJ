package me.xdrop.ensurej

class IntegerHandlerTest extends GroovyTestCase {

    void testInRange() {
        assertTrue Ensure.that(3).inRange(0, 4).andEval();
        assertFalse Ensure.that(3).inRange(0, 3).andEval();
        assertFalse Ensure.that(3).inRange(0, 10).and().inRange(0,2).andEval()

    }

    void testIsPositive() {

    }

    void testIsNegative() {

    }
}
