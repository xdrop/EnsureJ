package me.xdrop.ensurej;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Chain<E, F extends Handler> {

    enum Operation {
        AND {
            boolean apply(boolean lhs, boolean rhs) {
                return lhs && rhs;
            }
        },
        OR {
            boolean apply(boolean lhs, boolean rhs) {
                return lhs || rhs;
            }
        };

        abstract boolean apply(boolean lhs, boolean rhs);
    }

    class Tuple{
        Predicate<E> p;
        E arg;
        boolean invert;

        Tuple(Predicate<E> p, E arg) {
            this.p = p;
            this.arg = arg;
        }

        Tuple(Predicate<E> p, E arg, boolean invert) {
            this.p = p;
            this.arg = arg;
            this.invert = invert;
        }

    }



    private List<Tuple> chainedConditions;
    private List<Operation> ops;
    private F chain;
    private String msg;

    Chain(Predicate<E> p, E arg, F chain, String msg) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new ArrayList<>();
        this.chain = chain;
        this.msg = msg;
        chainedConditions.add(new Tuple(p, arg));
    }

    Chain(Predicate<E> p, E arg, F chain, String msg, boolean invert) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new ArrayList<>();
        this.chain = chain;
        this.msg = msg;
        chainedConditions.add(new Tuple(p, arg, invert));
    }

    private static boolean eval(Chain.Tuple t){
        if(t.invert)
            return !t.p.eval(t.arg);
        else
            return t.p.eval(t.arg);
    }

    public F and(){
        this.ops.add(Operation.AND);
        chain.visit(this);
        return chain;
    }

    public F or(){
        this.ops.add(Operation.OR);
        chain.visit(this);
        return chain;
    }

    void visit(Predicate<E> p, E arg){
        chainedConditions.add(new Tuple(p, arg));
    }
    void visit(Predicate<E> p, E arg, boolean invert){
        chainedConditions.add(new Tuple(p, arg, invert));
    }


    private boolean _eval(){
        if (ops.size() < 1){
            return eval(chainedConditions.get(0));
        } else{
            int i = 1;
            boolean lhs;

            boolean r1 = eval(chainedConditions.get(0));
            boolean r2 = eval(chainedConditions.get(1));


            lhs = ops.get(0).apply(r1, r2);

            // Pop operations and evaluate predicates until something evaluates to true
            // or all to false
            while(i < ops.size()){
                boolean r = eval(chainedConditions.get(i+1));
                Operation op = ops.get(i);

                lhs = op.apply(lhs, r);

//                if (op == Operation.AND && !lhs){
//                    return false;
//                }
//
//                if (op == Operation.OR && lhs){
//                    return true;
//                }

                i++;

            }

            return lhs;
        }
    }

    public boolean andEval(){
        return _eval();
    }

    public boolean e(){
        return _eval();
    }

    public boolean eval(){
        return _eval();
    }

    public void t() throws ParamCheckFailedException {
        andThrow();
    }

    public void t(String msg) throws ParamCheckFailedException{
        andThrow(msg);
    }

    public <L extends Throwable> void t(Class<L> ex, String message) throws L {
        andThrow(ex, message);
    }

    public <L extends Throwable> void t(Class<L> ex) throws L {
        andThrow(ex);
    }

    public <L extends Throwable> void andThrow(Class<L> ex) throws L {
        if(!_eval() && msg!=null) throwCustom(ex, msg);
        if(!_eval() && msg==null) throwCustom(ex);
    }

    public <L extends Throwable> void andThrow(Class<L> ex, String message) throws L {
        if(!_eval() && msg!=null) throwCustom(ex, msg);
    }

    public void andThrow() throws ParamCheckFailedException {
        boolean b = _eval();
        if(!b && msg!=null) throw new ParamCheckFailedException(msg);
        if(!b) throw new ParamCheckFailedException();
    }

    public void andThrow(String message) throws ParamCheckFailedException {
        if(!_eval() && message!=null) throw new ParamCheckFailedException(message);
    }

    private <L extends Throwable> void throwCustom(Class<L> ex, String message) throws L {
        throwGeneric(ex, message);
    }


    private <L extends Throwable> void throwCustom(Class<L> ex) throws L {
        try {
            throw ex.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private <L extends Throwable> void throwGeneric(Class<L> ex, String message) throws L {
        try {
            throw ex.getConstructor(String.class).newInstance(message);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
