package me.xdrop.ensurej

import static me.xdrop.ensurej.Ensure.val;

class StringHandlerTest extends GroovyTestCase {

    void testMatches() {
        assertTrue val("aaaaa").matches("\\w+").e()
        assertFalse val("aaaaa").matches("\\d+").e()
        assertTrue val("aaaaa").matches("[\\d|\\w]+").e()
    }

}
