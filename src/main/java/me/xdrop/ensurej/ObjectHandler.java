package me.xdrop.ensurej;

public class ObjectHandler<T extends ParamCheckFailedException> extends Handler<T, ObjectHandler<T>> {

    private Object obj;

    public ObjectHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * Checks whether the object is not null
     *
     * @return
     */
    public ResultEval<T, ObjectHandler<T>> isNotNull(){
        if (shortCircuit()) return getShortCircuit();

        return result(obj != null, "Object was null");

    }

    /**
     * Checks whether the object is null
     * @return
     */
    public ResultEval<T, ObjectHandler<T>> isNull(){
        if (shortCircuit()) return getShortCircuit();

        return result(obj == null, "Object wasn't null");

    }

    /**
     * Checks whether the source object equals() the other object
     *
     * @param other Tested for equality using {@code obj.equals(other)}
     * @return
     */
    public ResultEval<T, ObjectHandler<T>> equalsTo(Object other){
        if (shortCircuit()) return getShortCircuit();

        return result(obj.equals(other), "Objects not equal");

    }

    /**
     * Checks whether the source object is an instance of the class,
     * described by the received class argument.
     *
     * @param clazz The class argument to check against
     * @return
     */
    public ResultEval<T, ObjectHandler<T>> instanceOf(Class<?> clazz){
        if (shortCircuit()) return getShortCircuit();

        return result(obj.getClass().isInstance(clazz), "Object not instance of other.");

    }

}
