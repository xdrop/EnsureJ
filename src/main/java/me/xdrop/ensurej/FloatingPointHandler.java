package me.xdrop.ensurej;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

public class FloatingPointHandler<T extends ParamCheckFailedException> extends Handler<T, FloatingPointHandler<T>> {

    private double d;

    FloatingPointHandler(double d) {
        this.d = d;
    }

    public ResultEval<T, FloatingPointHandler<T>> inRange(double lower, double upper) throws T {
        return result(d >= lower && d < upper, String.format("Range check failed: %d <= %d <%d", lower, d, upper));
    }

    public ResultEval<T, FloatingPointHandler<T>> isPositive(){
        return result(d >= 0, String.format("Positive check failed: %f was not positive", d));
    }

}
