package me.xdrop.ensurej

import static Ensure.val;

class StringHandlerTest extends GroovyTestCase {

    void testIr(){
        def lst = [new ArrayList<>(), new LinkedList<>()]
        def a = lst.get(0)
        print(a)
        print(lst.getClass())
    }

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
        assertTrue val("AAAA").isAllUppercase(true).e()
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

    void testLengthBetween(){
        assertTrue val("111").lengthBetween(0,4).e()
        assertFalse val("111").lengthBetween(0,3).e()
        assertTrue  val("111").lengthBetween(-100,4).e()
    }

    void testAllEqual(){
        assertTrue val("aaaaa").allEqual('a' as Character).e()
        assertTrue  val("1111").allEqual('1' as Character).e()
        assertFalse val("aaaaab").allEqual('a' as Character).e()
        assertTrue val("aaa").allEqual().e()
        assertFalse val("aab").allEqual().e()
    }
}
