package me.xdrop.ensurej;

public class IntegerHandler<T extends ParamCheckFailedException> extends Handler<T, IntegerHandler<T>> {

    private int i;

    public IntegerHandler(int i) {
        this.i = i;
    }

    public IntegerHandler(ResultEval<T, IntegerHandler<T>> shortCircuit, int i) {
        super(shortCircuit);
        this.i = i;
    }

    /**
     * Asserts that the given number is within the specified range
     * @param lower The lower <b>inclusive</b> range
     * @param upper The upper <b>non-inclusive</b> range
     * @return
     */
    public ResultEval<T, IntegerHandler<T>> inRange(int lower, int upper){
        if(shortCircuit()) return getShortCircuit();

        return result(i >= lower && i < upper, String.format("Range check failed: %d <= %d <%d", lower, i, upper));
    }

    public ResultEval<T, IntegerHandler<T>> isPositive(){
        return result(i >= 0, String.format("Positive check failed: %d was not positive",i));
    }

    public ResultEval<T, IntegerHandler<T>> isNegative(){
        return result(i < 0, String.format("Negative check failed: %d was not negative", i));
    }

}
