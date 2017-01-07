package me.xdrop.ensurej;

import me.xdrop.ensurej.checks.CheckInt;

public class IntegerHandler extends Handler<IntegerHandler> {

    private int a;

    public IntegerHandler(int a) {
        this.a = a;
    }

    public Chain<Integer, IntegerHandler> isPositive(){
        return create(CheckInt.isPositive(), a, "Positive number expected");
    }

    public Chain<Integer, IntegerHandler> isNegative(){
        return create(CheckInt.isNegative(), a, "Negative number expected");
    }

    public Chain<Integer, IntegerHandler> inRange(int lower, int upper){
        return create(CheckInt.inRange(lower, upper), a, "Number was not in range");
    }

}
