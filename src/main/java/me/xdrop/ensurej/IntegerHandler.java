package me.xdrop.ensurej;

import me.xdrop.ensurej.checks.CheckInt;

public class IntegerHandler extends Handler<IntegerHandler> {

    private int a;

    public IntegerHandler(int a) {
        this.a = a;
    }

    public Chain<Integer, IntegerHandler> isPositive(){
        return new Chain<>(CheckInt.isPositive(), a, self());
    }

    public Chain<Integer, IntegerHandler> isNegative(){
        return new Chain<>(CheckInt.isNegative(), a, self());
    }

    public Chain<Integer, IntegerHandler> inRange(int lower, int upper){
        return new Chain<>(CheckInt.inRange(lower, upper), a, self());
    }

}
