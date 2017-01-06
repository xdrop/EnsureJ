package me.xdrop.ensurej;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Chain<E, F extends Handler> {

    class Condition {
        Predicate<E> p;
        E arg;
        boolean invert;

        Condition(Predicate<E> p, E arg) {
            this.p = p;
            this.arg = arg;
        }

        Condition(Predicate<E> p, E arg, boolean invert) {
            this.p = p;
            this.arg = arg;
            this.invert = invert;
        }

    }

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

    private List<Condition> chainedConditions;
    private List<Operation> ops;
    private F chain;
    private String msg;

    Chain(Predicate<E> p, E arg, F chain, String msg) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new ArrayList<>();
        this.chain = chain;
        this.msg = msg;
        chainedConditions.add(new Condition(p, arg));
    }

    Chain(Predicate<E> p, E arg, F chain, String msg, boolean invert) {
        this.chainedConditions = new ArrayList<>(4);
        this.ops = new ArrayList<>();
        this.chain = chain;
        this.msg = msg;
        chainedConditions.add(new Condition(p, arg, invert));
    }

    /**
     * Evaluate a condition, ie. apply the argument to the predicate
     * and return the result
     *
     * @param t Condition to be checked
     * @return The boolean evaluation of the predicate/argument pair
     */
    private static boolean eval(Chain.Condition t) {
        if (t.invert)
            return !t.p.eval(t.arg);
        else
            return t.p.eval(t.arg);
    }

    /**
     * Combine conditions using AND
     * @return
     */
    public F and() {
        this.ops.add(Operation.AND);
        chain.visit(this);
        return chain;
    }

    /**
     * Combine conditions using OR
     * @return
     */
    public F or() {
        this.ops.add(Operation.OR);
        chain.visit(this);
        return chain;
    }

    void visit(Predicate<E> p, E arg) {
        chainedConditions.add(new Condition(p, arg));
    }

    void visit(Predicate<E> p, E arg, boolean invert) {
        chainedConditions.add(new Condition(p, arg, invert));
    }

    private boolean _eval() {
        if (ops.size() < 1) {
            return eval(chainedConditions.get(0));
        } else {
            int i = 1;
            boolean lhs;

            boolean r1 = eval(chainedConditions.get(0));
            boolean r2 = eval(chainedConditions.get(1));

            lhs = ops.get(0).apply(r1, r2);

            // evaluate stored operations
            while (i < ops.size()) {
                boolean r = eval(chainedConditions.get(i + 1));
                Operation op = ops.get(i);

                lhs = op.apply(lhs, r);
                i++;
            }

            return lhs;
        }
    }

    /**
     * Evaluate this validation test
     * @return True if the validation passes, false otherwise
     */
    public boolean andEval() {
        return _eval();
    }

    /**
     * Evaluate this validation test
     * @return True if the validation passes, false otherwise
     */
    public boolean e() {
        return _eval();
    }

    /**
     * Evaluate this validation test
     * @return True if the validation passes, false otherwise
     */
    public boolean eval() {
        return _eval();
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @return
     */
    public void t() throws ParamCheckFailedException {
        andThrow();
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @return
     */
    public void t(String msg) throws ParamCheckFailedException {
        andThrow(msg);
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @param ex Custom exception to be thrown
     * @param message The exception message
     */
    public <L extends Throwable> void t(Class<L> ex, String message) throws L {
        andThrow(ex, message);
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @param ex Custom exception to be thrown
     * @throws L Exception when validation fails
     */
    public <L extends Throwable> void t(Class<L> ex) throws L {
        andThrow(ex);
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @param ex Custom exception to be thrown
     * @throws L Exception when validation fails
     */
    public <L extends Throwable> void andThrow(Class<L> ex) throws L {
        if (!_eval() && msg != null) throwCustom(ex, msg);
        if (!_eval() && msg == null) throwCustom(ex);
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @param ex Custom exception to be thrown
     * @param message The exception message
     * @throws L Exception when validation fails
     */
    public <L extends Throwable> void andThrow(Class<L> ex, String message) throws L {
        if (!_eval() && msg != null) throwCustom(ex, msg);
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @throws ParamCheckFailedException Exception when validation fails
     */
    public void andThrow() throws ParamCheckFailedException {
        boolean b = _eval();
        if (!b && msg != null) throw new ParamCheckFailedException(msg);
        if (!b) throw new ParamCheckFailedException();
    }

    /**
     * Evaluate this validation test and throw an exception if the validation
     * fails.
     *
     * @param message The exception message
     * @throws ParamCheckFailedException Exception when validation fails
     */
    public void andThrow(String message) throws ParamCheckFailedException {
        if (!_eval() && message != null) throw new ParamCheckFailedException(message);
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
