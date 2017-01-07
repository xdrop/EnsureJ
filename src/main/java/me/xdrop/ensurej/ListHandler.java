package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class ListHandler<T> extends AggregateHandler<T, ListHandler<T>> {

    private List<Predicate<T>> checks = new ArrayList<>(2);

    ListHandler(List<T> arg) {
        super(arg);
    }


    public Chain<Object, ListHandler<T>> all(final Predicate<T> pred){

        return create(new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                for(T t: arg){
                    if(!pred.eval(t))
                        return false;

                }

                return true;
            }
        },null, "");


    }

    public Chain<T, ListHandler<T>> any(final Predicate<T> pred){

        return create(new Predicate<T>() {
            @Override
            public boolean eval(T in) {
                for(T t: arg){
                    if(pred.eval(t))
                        return true;

                }

                return false;
            }
        },null, "");


    }



}
