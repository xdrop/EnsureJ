package me.xdrop.ensurej;

@SuppressWarnings("unchecked")
abstract class Handler<E extends Handler<E>> {

    private boolean not;
    private Chain<Object, E> toReturn;


    private E self(){
        return (E) this;
    }

    E not(){
        not = true;
        return self();
    }

    <T> Chain<T, E> create(Predicate<T> p, T arg, String msg){

        boolean _not = not;
        not = false;

        if(toReturn != null){
            toReturn.visit((Predicate<Object>) p, arg, _not);
            return (Chain<T, E>) toReturn;
        }
        return new Chain<>(p, arg, self(), msg, _not);

    }

    void visit(Chain<Object,E> toReturn){
        this.toReturn = toReturn;
    }
}
