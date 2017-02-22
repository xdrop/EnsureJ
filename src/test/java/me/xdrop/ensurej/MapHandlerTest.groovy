package me.xdrop.ensurej

import me.xdrop.ensurej.checks.CheckString

class MapHandlerTest extends GroovyTestCase {

    Map<String, String> lowToCaps = new HashMap<>();

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        lowToCaps.put("abc", "EFG")
        lowToCaps.put("def", "SSG")
        lowToCaps.put("defeee", "SKKKSG")
    }

    void testAllKeys(){
        assertFalse Ensure.that(lowToCaps).keys(CheckString.isAllUppercase()).all().e()
        assertTrue Ensure.that(lowToCaps).keys(CheckString.isAllLowercase()).all().e();
    }

    void testAllValues(){
        assertTrue  Ensure.that(lowToCaps).values(CheckString.isAllUppercase()).all().e()
        assertFalse  Ensure.that(lowToCaps).values(CheckString.isAllLowercase()).all().e();
    }

    void testAnyKeys(){
        assertTrue Ensure.that(lowToCaps).keys(CheckString.lengthBetween(0,4)).any().e()
        assertFalse Ensure.that(lowToCaps).keys(CheckString.lengthBetween(0,1)).any().e()
    }

    void testAnyValues(){
        assertTrue Ensure.that(lowToCaps).values(CheckString.lengthBetween(0,4)).any().e()
        assertFalse Ensure.that(lowToCaps).values(CheckString.lengthBetween(0,1)).any().e()
    }

}
