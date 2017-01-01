package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParamCheckFailedException {
        Ensure.that(5).inRange(0,3).or().inRange(0,3).andThrow();
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(1);
        lst.add(2);
        Ensure.that(lst).all(new Predicate<Integer>() {
            public boolean pass(Integer in) {
                return in > 1;
            }
        });

    }
}
