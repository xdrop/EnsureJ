package me.xdrop.ensurej;

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


}
