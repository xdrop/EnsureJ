package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;

public class NumberHandler<T extends ParamCheckFailedException> extends Handler<T, NumberHandler<T>> {

    private Number n;

    NumberHandler(Number n) {
        this.n = n;
    }

}
