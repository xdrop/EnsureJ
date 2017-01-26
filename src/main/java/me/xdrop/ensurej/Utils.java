package me.xdrop.ensurej;

import java.util.List;

public class Utils {

    public static <T> Predicate<Void> all(final List<Predicate<T>> preds, final T arg){
        return new Predicate<Void>() {
            @Override
            public boolean eval(Void in) {

                for (Predicate<T> p : preds){
                    if (!p.eval(arg)){
                        return false;
                    }
                }

                return true;
            }
        };
    }

}
