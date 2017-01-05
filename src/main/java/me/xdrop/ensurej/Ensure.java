package me.xdrop.ensurej;

import java.util.List;

public class Ensure {

    public static IntegerHandler that(int a){
        return new IntegerHandler(a);
    }

    public static IntegerHandler val(int a){
        return new IntegerHandler(a);
    }

    public static <T> ListHandler<T> that(List<T> a){
        return new ListHandler<T>(a);
    }

    public static <T> ListHandler<T> val(List<T> a){
        return new ListHandler<T>(a);
    }

    public static StringHandler val(String a) {
        return new StringHandler(a);
    }

    public static StringHandler that(String a) {
        return new StringHandler(a);
    }

}
