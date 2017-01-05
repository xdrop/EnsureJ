package me.xdrop.ensurej

class HandlerTest extends GroovyTestCase {
    void testNot() {
        assertTrue Ensure.that(2).not().isNegative().e()
        assertFalse  Ensure.that(2).not().inRange(0, 3).e()
    }
}
