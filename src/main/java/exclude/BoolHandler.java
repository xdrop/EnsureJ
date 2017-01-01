package exclude;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

public class BoolHandler<T extends ParamCheckFailedException> extends Handler<T> {

    private boolean b;

    public BoolHandler(boolean b) {
        this.b = b;
    }

    public ResultEval<T> isFalse() throws T {
        return result(!b, "Expected false got true");
    }


    public ResultEval<T> isTrue() throws T {
        return result(!b, "Expected true got false");
    }
}
