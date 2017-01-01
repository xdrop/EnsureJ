package me.xdrop.ensurej;

public class ObjectHandler<T extends ParamCheckFailedException> extends Handler<T, ObjectHandler<T>> {

    private Object obj;

    public ObjectHandler(Object obj) {
        this.obj = obj;
    }

    public ResultEval<T, ObjectHandler<T>> isNotNull(){
        if (shortCircuit()) return getShortCircuit();

        return result(obj != null, "Object was null");

    }

    public ResultEval<T, ObjectHandler<T>> isNull(){
        if (shortCircuit()) return getShortCircuit();

        return result(obj == null, "Object wasn't null");

    }

    public ResultEval<T, ObjectHandler<T>> equalsTo(Object other){
        if (shortCircuit()) return getShortCircuit();

        return result(obj.equals(other), "Objects not equal");

    }

    public ResultEval<T, ObjectHandler<T>> instanceOf(Class<?> clazz){
        if (shortCircuit()) return getShortCircuit();

        return result(obj.getClass().isInstance(clazz), "Object not instance of other.");

    }

}
