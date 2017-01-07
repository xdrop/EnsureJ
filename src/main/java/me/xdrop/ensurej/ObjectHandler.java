package me.xdrop.ensurej;

import me.xdrop.ensurej.checks.CheckObject;

public class ObjectHandler extends Handler<ObjectHandler> {

    private Object a;

    public ObjectHandler(Object o) {
        this.a = o;
    }

    public Chain<Object, ObjectHandler> isNull(){
        return create(CheckObject.isNull(), a, "Object was not null");
    }

    public Chain<Object, ObjectHandler> isNotNull(){
        return create(CheckObject.isNotNull(), a, "Object was null");
    }

    public Chain<Object, ObjectHandler> equal(Object other){
        return create(CheckObject.equal(other), a, "Object was not equal");
    }
}
