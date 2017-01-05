package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        public Tuple(Predicate<E> p, E arg) {
            this.p = p;
            this.arg = arg;
        }
    }

    private List<Tuple> chainedConditions;
    private Stack<Operation> ops;
    private F chain;
    private String msg;

    Chain(Predicate<E> p, E arg, F chain, String msg) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new Stack<>();
        this.chain = chain;
        this.msg = msg;
        chainedConditions.add(new Tuple(p, arg));
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

    public void visit(Predicate<E> p, E arg){
        chainedConditions.add(new Tuple(p, arg));
    }

    private boolean _eval(){
        if (ops.size() < 1){
            Predicate<E> p = chainedConditions.get(0).p;
            E arg = chainedConditions.get(0).arg;
            return p.eval(arg);
        } else{
            int i = 1;
            boolean lhs;

            Tuple t1 = chainedConditions.get(0);
            Predicate<E> p1 = t1.p;
            E arg1 = t1.arg;

            Tuple t2 = chainedConditions.get(1);
            Predicate<E> p2 = t2.p;
            E arg2 = t2.arg;

            lhs = ops.pop().apply(p1.eval(arg1), p2.eval(arg2));

            // Pop operations and evaluate predicates until something evaluates to true
            // or all to false
            while(i < ops.size()){
                t1 = chainedConditions.get(i+1);
                p1 = t1.p;
                arg1 = t1.arg;

                Operation op = ops.pop();

                lhs = op.apply(lhs, p1.eval(arg1));

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


}
