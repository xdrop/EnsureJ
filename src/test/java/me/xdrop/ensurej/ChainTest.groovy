package me.xdrop.ensurej
import static me.xdrop.ensurej.Ensure.val

class ChainTest extends GroovyTestCase {


    void testAnd() {
        assertTrue val("aaa").isAllLowercase().and().hasOnlyLetters().e()
        assertTrue val("aaa").isAllLowercase().and().isAllLowercase().and().isAllLowercase().e()
    }

    void testOr() {
        assertTrue val("aaa").isAllLowercase().or().isAllLowercase().e()
        assertFalse val("aaa").isAllUppercase().or().hasEndWhitespace().e()
    }

    void testCombined(){
        assertTrue val("aaa").isAllLowercase().or().isAllUppercase().and().isTrimmed().e()
    }


}
