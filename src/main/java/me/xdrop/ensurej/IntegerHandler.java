package me.xdrop.ensurej;

public class IntegerHandler extends Handler<IntegerHandler> {

    private int a;

    public IntegerHandler(int a) {
        this.a = a;
    }

    public Chain<Integer, IntegerHandler> isPositive(){
        return new Chain<>(CheckInt.isPositive(), a, self());
    }

}
