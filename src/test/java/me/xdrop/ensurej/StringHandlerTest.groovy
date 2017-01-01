package me.xdrop.ensurej

import static Ensure.val;

class StringHandlerTest extends GroovyTestCase {

    void testMatches() {
        assertTrue val("aaaaa").matches("\\w+").e()
        assertFalse val("aaaaa").matches("\\d+").e()
        assertTrue val("aaaaa").matches("[\\d|\\w]+").e()
    }

    void testLowercase(){
        assertTrue val("aaaa").isAllLowercase().e()
        assertTrue val("aaaa1").isAllLowercase(true).e()
        assertFalse val("aaaa1").isAllLowercase(false).e()
        assertFalse val("aaaaA").isAllLowercase(false).e()
        assertFalse val("aaaaA").isAllLowercase(true).e()
    }

    void testUppercase(){
        assertTrue val("AAAA").isAllUppercase().e()
        assertTrue val("AAAA1").isAllUppercase(true).e()
        assertFalse val("aaaa").isAllUppercase(true).e()
        assertFalse val("AAAA1").isAllUppercase(false).e()
    }

    void testHasOnlyLetters() {
        assertTrue val("aaa").hasOnlyLetters(false).e()
        assertTrue val("aaa").hasOnlyLetters(true).e()
        assertTrue val("aaaα").hasOnlyLetters(true).e()
        assertFalse val("aaaα").hasOnlyLetters(false).e()
        assertFalse val("aaa1").hasOnlyLetters(true).e()
        assertFalse val("aaa1").hasOnlyLetters(false).e()

    }

    void testHasOnlyDigits() {
        assertTrue val("111").hasOnlyDigits().e()
        assertFalse val("111a").hasOnlyDigits().e()
    }
}
