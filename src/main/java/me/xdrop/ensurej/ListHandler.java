package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class ListHandler<T> extends Handler<ListHandler<T>> {

    private List<T> arg;
    private List<Predicate<T>> checks = new ArrayList<>(2);

    public ListHandler(List<T> arg) {
        this.arg = arg;
    }

    public Chain<T, ListHandler<T>> all(final Predicate<T> pred){

        return create(new Predicate<T>() {
            @Override
            public boolean eval(T in) {
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

    public ListHandler<T> add(Predicate<T> pred){
        checks.add(pred);
        return this;
    }


    public Chain<Object, ListHandler<T>> any() {

        return create(new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                for(T t: arg){
                    for(Predicate<T> pred: checks){
                        if(pred.eval(t))
                            return true;
                    }
                }

                return false;
            }
        },null, "");

    }

    public Chain<Object, ListHandler<T>> all() {

        return create(new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                for(T t: arg){
                    for(Predicate<T> pred: checks){
                        if(!pred.eval(t))
                            return false;
                    }
                }

                return true;
            }
        },null, "");

    }
}
