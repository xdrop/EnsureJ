package me.xdrop.ensurej;

public class Main {

    public static void main(String[] args) {
        Foo foo = new Foo();
        ((Foo) foo.self()).hey();
    }
}
