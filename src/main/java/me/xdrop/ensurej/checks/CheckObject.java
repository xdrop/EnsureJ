package me.xdrop.ensurej.checks;

import me.xdrop.ensurej.Predicate;

public class CheckObject {

    public static Predicate<Object> isNull(){
        return new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                return in == null;
            }
        };
    }

    public static Predicate<Object> isNotNull(){
        return new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                return in != null;
            }
        };
    }

    public static Predicate<Object> equal(final Object other){
        return new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                return in.equals(other);
            }
        };
    }

}
