package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

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
    private List<Operation> ops;
    private F chain;

    Chain(Predicate<E> p, E arg, F chain) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new ArrayList<>();
        this.chain = chain;
        chainedConditions.add(new Tuple(p, arg));
    }

    public F and(){
        this.ops.add(Operation.AND);
        return chain;
    }

    public F or(){
        this.ops.add(Operation.OR);
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
            int i = 0;
            // Pop operations and evaluate predicates until something evaluates to true
            // or all to false
            while(ops.size() > 0){
                Tuple t1 = chainedConditions.get(i);
                Predicate<E> p1 = t1.p;
                E arg1 = t1.arg;

                Tuple t2 = chainedConditions.get(i + 1);
                Predicate<E> p2 = t2.p;
                E arg2 = t2.arg;

                Operation op = ops.get(i);

                boolean result = op.apply(p1.eval(arg1), p2.eval(arg2));

                if (op == Operation.AND && !result){
                    return false;
                }

                if (op == Operation.OR && result){
                    return true;
                }

            }

            return true;
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
