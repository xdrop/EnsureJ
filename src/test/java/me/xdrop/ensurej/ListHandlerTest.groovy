package me.xdrop.ensurej

import static me.xdrop.ensurej.checks.CheckInt.inRange
import static me.xdrop.ensurej.checks.CheckInt.isNegative
import static me.xdrop.ensurej.checks.CheckInt.isPositive

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
        assertFalse Ensure.that(lst).all(isPositive()).andEval()
        assertTrue Ensure.that(lst).any(isNegative()).andEval()
        assertTrue Ensure.that(lst).add(inRange(-100,100))
                .add(inRange(-4,10))
                .all().eval()
        assertFalse  Ensure.that(lst).add(inRange(-100,100))
                .add(inRange(0,10))
                .all().eval()
        assertTrue Ensure.that(lst).add(isPositive())
                                    .add(isNegative())
                                    .any().eval()
    }
}
