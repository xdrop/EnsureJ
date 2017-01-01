package me.xdrop.ensurej;

import java.lang.reflect.InvocationTargetException;

public class ResultEval<T extends ParamCheckFailedException, E extends Handler<T,E>> {

    private boolean res;
    private String msg;
    private E chain;

    private Class<T> exceptionClass;

    public ResultEval(boolean res, String msg, Class<T> exceptionClass, E chain) {
        this.res = res;
        this.msg = msg;
        this.exceptionClass = exceptionClass;
        this.chain = chain;
    }

    public ResultEval(boolean res, Class<T> exceptionClass, E chain) {
        this.res = res;
        this.exceptionClass = exceptionClass;
        this.chain = chain;
    }

    private void throwGeneric(String message) throws T {
        try {
            throw exceptionClass.getConstructor(String.class).newInstance(message);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void throwGeneric() throws T {
        try {
            throw exceptionClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void andThrow() throws T {
        if(!res && msg!=null) throwGeneric(msg);
        if(!res && msg==null) throwGeneric();
    }

    public boolean andEval(){
        return res;
    }

    public E and(){
        chain.setResultRoot(this);
        return chain;
    }

}
