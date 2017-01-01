package me.xdrop.ensurej;

public class Main {

    public static void main(String[] args) throws ParamCheckFailedException {
        Ensure.that(5).inRange(0,3).or().inRange(0,3).andThrow();
    }
}
