package me.xdrop.ensurej;

public abstract class Handler<E extends Handler<E>> {

    private boolean not;
    private Chain<Object, E> toReturn;


    E self(){
        return (E) this;
    }

    E not(){
        not = true;
        return self();
    }

    public <T> Chain<T, E> create(Predicate<T> p, T arg, String msg){
        if(toReturn != null){
            toReturn.visit((Predicate<Object>) p, arg);
            return (Chain<T, E>) toReturn;
        }
        return new Chain<>(p, arg, self(), msg);
    }

    void visit(Chain<Object,E> toReturn){
        this.toReturn = toReturn;
    }
}
