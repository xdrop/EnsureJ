package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

import java.util.List;

public class ListHandler<E, T extends ParamCheckFailedException> extends Handler<T, ListHandler<E, T>> {

    private List<E> lst;

    ListHandler(List<E> lst) {
        this.lst = lst;
    }

    public ResultEval<T, ListHandler<E, T>> all(Predicate<E> p){
        for(E e : lst){
            if(!p.pass(e)){
                return result(false, "all() check failed");
            }
        }

        return result(true);
    }

    public ResultEval<T, ListHandler<E, T>> any(Predicate<E> p){
        for(E e : lst){
            if(p.pass(e)){
                return result(true);
            }
        }

        return result(false, "any() check failed");
    }
}
