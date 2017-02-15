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

    void testIsInteger(){
        assertTrue val("11").isInteger().e()
        assertFalse val("11x").isInteger().e()
        assertFalse val("1.1").isInteger().e()
        assertTrue val("000000").isInteger().e()
    }

    void testIsDecimal(){
        assertTrue val("1.1").isDecimal().e()
        assertTrue val("0.0").isDecimal().e()
        assertTrue val("10000000.1").isDecimal().e()
        assertFalse val("10000000.1s").isDecimal().e()
        assertFalse val("10000000").isDecimal().e()
    }

    void testIsNumber(){
        assertTrue val("111").isNumber().e();
        assertTrue val("111.33").isNumber().e();
        assertFalse val("111.33s").isNumber().e();
        assertFalse val("").isNumber().e();
    }

    void testFromSet(){
        Set<String> set = new HashSet<>(["abc","edf"])
        assertTrue val("abc").fromSet(set).e()
        assertTrue val("edf").fromSet(set).e()
        assertFalse val("abcd").fromSet(set).e()
        assertFalse val("").fromSet(set).e()
    }
}
