package me.xdrop.ensurej.checks;

import me.xdrop.ensurej.Predicate;

public class CheckInt {


    public static Predicate<Integer> isPositive(){
        return new Predicate<Integer>() {
            @Override
            public boolean eval(Integer in) {
                return in >= 0;
            }
        };
    }

    public static Predicate<Integer> isNegative(){
        return new Predicate<Integer>() {
            @Override
            public boolean eval(Integer in) {
                return in < 0;
            }
        };
    }

    public static Predicate<Integer> inRange(final int lower, final int upper){
        return new Predicate<Integer>() {
            @Override
            public boolean eval(Integer in) {
                return in >= lower && in < upper;
            }
        };
    }

    public static Predicate<Integer> greaterThan(final int limit){
        return new Predicate<Integer>() {
            @Override
            public boolean eval(Integer in) {
                return in > limit;
            }
        };
    }

    public static Predicate<Integer> lessThan(final int limit){
        return new Predicate<Integer>() {
            @Override
            public boolean eval(Integer in) {
                return in < limit;
            }
        };
    }



}
