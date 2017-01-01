package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

import java.util.List;

public class ListHandler<E, T extends ParamCheckFailedException> extends Handler<T, ListHandler<E, T>> {

    private List<E> lst;

    ListHandler(List<E> lst) {
        this.lst = lst;
    }
}
