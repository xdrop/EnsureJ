package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class ListHandler<T> extends Handler<ListHandler<T>>{

    private List<T> arg;
    private List<Predicate<T>> checks = new ArrayList<>(2);

    public ListHandler(List<T> arg) {
        this.arg = arg;
    }

    public boolean all(Predicate<T> pred){

        for(T t: arg){
            if(!pred.eval(t))
                return false;
        }

        return true;


    }

    public ListHandler<T> add(Predicate<T> pred){
        checks.add(pred);
        return this;
    }

    public Chain<Object, ListHandler<T>> all() {

        return new Chain<>(new Predicate<Object>() {
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
        },null, self());

    }
}
