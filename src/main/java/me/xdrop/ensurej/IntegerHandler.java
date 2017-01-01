package me.xdrop.ensurej;

public class IntegerHandler<T extends ParamCheckFailedException> extends Handler<T, IntegerHandler<T>> {

    private int i;

    IntegerHandler(int i) {
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

    /**
     * Asserts that the number is positive
     * @return
     */
    public ResultEval<T, IntegerHandler<T>> isPositive(){
        if(shortCircuit()) return getShortCircuit();

        return result(i >= 0, String.format("Positive check failed: %d was not positive",i));
    }

    /**
     * Asserts that the number is negative
     * @return
     */
    public ResultEval<T, IntegerHandler<T>> isNegative(){
        if(shortCircuit()) return getShortCircuit();

        return result(i < 0, String.format("Negative check failed: %d was not negative", i));
    }

}
