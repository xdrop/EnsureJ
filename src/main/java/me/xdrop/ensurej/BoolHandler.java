package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

public class BoolHandler<T extends ParamCheckFailedException> extends Handler<T, BoolHandler<T>> {

    private boolean b;

    public BoolHandler(boolean b) {
        this.b = b;
    }

    public ResultEval<T, BoolHandler<T>> isFalse() throws T {
        return result(!b, "Expected false got true");
    }


    public ResultEval<T, BoolHandler<T>> isTrue() throws T {
        return result(!b, "Expected true got false");
    }
}
