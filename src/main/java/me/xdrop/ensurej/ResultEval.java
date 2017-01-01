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

    void eval(boolean rhs){
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

    public boolean get(){
        return result;
    }

    public void t() throws T{
        andThrow();
    }

    public void andThrow() throws T {
        if(!result && msg!=null) throwGeneric(msg);
        if(!result && msg==null) throwGeneric();
    }

    public <E extends Exception> void andThrow(Class<E> ex) throws E{
        if(!result && msg!=null) throwCustom(ex, msg);
        if(!result && msg==null) throwCustom(ex);
    }

    private <E extends Throwable> void throwCustom(Class<E> ex, String message) throws E{
        throwGeneric(ex, message);
    }

    private void throwGeneric(String message) throws T {
        this.throwGeneric(exceptionClass, message);
    }

    private <E extends Exception> void throwCustom(Class<E> ex) throws E{
        try {
            throw ex.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private <E extends Throwable> void throwGeneric(Class<E> ex, String message) throws E {
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
