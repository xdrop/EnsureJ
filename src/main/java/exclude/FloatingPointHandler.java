package exclude;

import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

public class FloatingPointHandler<T extends ParamCheckFailedException> extends Handler<T> {

    private double d;

    public FloatingPointHandler(double d) {
        this.d = d;
    }

    public ResultEval<T> inRange(double lower, double upper) throws T {
        return result(d >= lower && d < upper, String.format("Range check failed: %d <= %d <%d", lower, d, upper));
    }

    public ResultEval<T> isPositive(){
        return result(d >= 0, String.format("Positive check failed: %f was not positive", d));
    }

}
