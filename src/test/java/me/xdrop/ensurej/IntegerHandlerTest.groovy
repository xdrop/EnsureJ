package me.xdrop.ensurej

import static Ensure.val

class IntegerHandlerTest extends GroovyTestCase {

    void testInRange() {
        assertTrue Ensure.that(3).inRange(0, 4).andEval()
        assertTrue val(3).inRange(0,4).e()
        assertFalse Ensure.that(3).inRange(0, 3).andEval()
        assertTrue Ensure.that(3).inRange(0, 2).or().inRange(0,1).or().inRange(0,10).andEval()

    }

    void testIsPositive() {
        assertTrue Ensure.that(10).isPositive().andEval()
        assertFalse Ensure.that(-10).isPositive().andEval()
        assertTrue Ensure.that(0).isPositive().andEval()

    }

    void testIsNegative() {
        assertFalse Ensure.that(10).isNegative().andEval()
        assertTrue Ensure.that(-10).isNegative().andEval()
        assertFalse Ensure.that(0).isNegative().andEval()
    }
}
