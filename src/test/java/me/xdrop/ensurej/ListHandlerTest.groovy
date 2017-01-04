package me.xdrop.ensurej

class ListHandlerTest extends GroovyTestCase {
    void testAll() {
        List<Integer> lst = [1,2,-3,4]
        assertTrue Ensure.that(lst).all({x -> x > -4} as Predicate<Integer>).e()
        assertFalse  Ensure.that(lst).all({x -> x > -3} as Predicate<Integer>).e()
    }

    void testAny() {
        List<Integer> lst = [1,2,-3,4]
        assertTrue Ensure.that(lst).any({ x -> x == 1} as Predicate<Integer>).e()
        assertTrue Ensure.that(lst).any({ x -> x == 4} as Predicate<Integer>).e()
        assertFalse Ensure.that(lst).any({ x -> x == 10} as Predicate<Integer>).e()
    }

    void testPositive(){
        List<Integer> lst = [1,2,-3,4]
        List<Double> lst2 = [1.0, 2.0, 3.0, 4.0] as List<Double>
    }
}
