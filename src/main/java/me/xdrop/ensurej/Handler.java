package me.xdrop.ensurej;

public abstract class Handler<E extends Handler<E>> {

    private boolean not;


    E self(){
        return (E) this;
    }

    E not(){
        not = true;
        return self();
    }
}
