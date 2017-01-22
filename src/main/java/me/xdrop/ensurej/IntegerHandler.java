package me.xdrop.ensurej;

import me.xdrop.ensurej.checks.CheckInt;

public class IntegerHandler extends Handler<IntegerHandler> {

    private int a;

    public IntegerHandler(int a) {
        this.a = a;
    }

    public Chain<Integer, IntegerHandler> isPositive(){
        return create(CheckInt.isPositive(), a, "Positive number check failed");
    }

    public Chain<Integer, IntegerHandler> isNegative(){
        return create(CheckInt.isNegative(), a, "Negative number check failed");
    }

    public Chain<Integer, IntegerHandler> inRange(int lower, int upper){
        return create(CheckInt.inRange(lower, upper), a, "Number in range check failed");
    }

    public Chain<Integer, IntegerHandler> greaterThan(int limit){
        return create(CheckInt.greaterThan(limit), a, "Number greater than " + limit + " check failed");
    }

    public Chain<Integer, IntegerHandler> lessThan(int limit){
        return create(CheckInt.lessThan(limit), a, "Number less than " + limit + " check failed");
    }

}
