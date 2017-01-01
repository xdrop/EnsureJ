package me.xdrop.ensurej;

import java.lang.reflect.InvocationTargetException;

public class ResultEval<T extends ParamCheckFailedException, E extends Handler<T,E>> {

    enum Operation {
        AND {
            boolean op(boolean lhs, boolean rhs) {
                return lhs && rhs;
            }
        },
        OR {
            boolean op(boolean lhs, boolean rhs) {
                return lhs || rhs;
            }
        };

        abstract boolean op(boolean lhs, boolean rhs);
    }

    private Boolean result = null;
    private Operation op;
    private String msg;
    private E chain;

    private Class<T> exceptionClass;

    ResultEval(boolean res, String msg, Class<T> exceptionClass, E chain) {
        this.result = res;
        this.msg = msg;
        this.exceptionClass = exceptionClass;
        this.chain = chain;
    }

    ResultEval(boolean res, Class<T> exceptionClass, E chain) {
        this.result = res;
        this.exceptionClass = exceptionClass;
        this.chain = chain;
    }

    void bool_eval(boolean rhs){
        result = op.op(result, rhs);
        msg = "Aggregate condition " + op.toString() + " failed.";
    }



    public E and(){
        if (!result){
            chain.setToShortCircuit(this);
            result = false;
        }
        op = Operation.AND;
        chain.setResultRoot(this);
        return chain;
    }

    public E or(){
        if (result){
            chain.setToShortCircuit(this);
            result = true;
        }
        op = Operation.OR;
        chain.setResultRoot(this);
        return chain;
    }

    public boolean andEval(){
        return result;
    }

    public boolean e(){
        return result;
    }

    public boolean eval(){
        return result;
    }

    public void t() throws T{
        andThrow();
    }

    public void t(String msg) throws T{
        andThrow(msg);
    }

    public <E extends Exception> void t(Class<E> ex, String message) throws E{
        andThrow(ex, message);
    }

    public <E extends Exception> void t(Class<E> ex) throws E{
        andThrow(ex);
    }

    public void andThrow() throws T {
        if(!result && msg!=null) throwGeneric(msg);
        if(!result && msg==null) throwGeneric();
    }

    public void andThrow(String message) throws T {
        if(!result && msg!=null) throwGeneric(msg);
    }

    public <F extends Exception> void andThrow(Class<F> ex) throws F {
        if(!result && msg!=null) throwCustom(ex, msg);
        if(!result && msg==null) throwCustom(ex);
    }

    public <F extends Exception> void andThrow(Class<F> ex, String message) throws F {
        if(!result && msg!=null) throwCustom(ex, msg);
    }

    private <F extends Throwable> void throwCustom(Class<F> ex, String message) throws F {
        throwGeneric(ex, message);
    }

    private void throwGeneric(String message) throws T {
        this.throwGeneric(exceptionClass, message);
    }

    private <F extends Exception> void throwCustom(Class<F> ex) throws F {
        try {
            throw ex.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private <F extends Throwable> void throwGeneric(Class<F> ex, String message) throws F {
        try {
            throw ex.getConstructor(String.class).newInstance(message);
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

}
