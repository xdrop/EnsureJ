package me.xdrop.ensurej;

public class Base<E extends Base> {

    public E self(){
        return (E) this;
    }
}
