package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class AggregateHandler<E, L extends AggregateHandler<E, L>> extends  Handler<L> {

    List<E> arg;
    private List<Predicate<E>> checks = new ArrayList<>(4);

    AggregateHandler(List<E> arg){
        this.arg = arg;
    }

    public Chain<Object, L> any(){

        return create(new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                for(E t: arg){
                    for(Predicate<E> pred: checks){
                        if(pred.eval(t))
                            return true;
                    }
                }

                return false;
            }
        },null, "");
    }


    public Chain<Object, L> all(){

        return create(new Predicate<Object>() {
            @Override
            public boolean eval(Object in) {
                for(E t: arg){
                    for(Predicate<E> pred: checks){
                        if(!pred.eval(t))
                            return false;
                    }
                }

                return true;
            }
        },null, "");
    }

    public L add(Predicate<E> pred){
        checks.add(pred);
        return self();
    }



}
