package me.xdrop.ensurej;

@SuppressWarnings("unchecked")
public abstract class Handler <T extends ParamCheckFailedException, E extends Handler<T,E>> {

    public enum State{
        ACTIVE,
        SHORTCIRCUIT;
    }

    private Class<T> exceptionClass;
    private ResultEval<T, E> resultRoot;
    private State state = State.ACTIVE;
    private ResultEval<T,E> shortCircuit;
    private boolean not;

    Handler() {
    }

    public Handler(ResultEval<T, E> shortCircuit) {
        this.shortCircuit = shortCircuit;
    }

    private E self(){
        return (E) this;
    }

    E not(){
        not = true;
        return self();
    }

    void setExceptionClass(Class<T> clazz){
        this.exceptionClass = clazz;
    }

    ResultEval<T,E> result(boolean b, String msg){

        if(not){
            b = !b;
            not = false;
        }

        if(resultRoot != null){
            resultRoot.bool_eval(b);
            return resultRoot;
        }
        return new ResultEval<T,E>(b, msg, exceptionClass, (E) self());
    }


    ResultEval<T, E> result(boolean b){

        if(not){
            b = !b;
            not = false;
        }

        if(resultRoot != null){
            resultRoot.bool_eval(b);
            return resultRoot;
        }

        return new ResultEval<T,E>(b, exceptionClass, self());
    }

    public ResultEval<T, E> getResultRoot() {
        return resultRoot;
    }

    void setResultRoot(ResultEval<T, E> resultRoot) {
        this.resultRoot = resultRoot;
    }

    ResultEval<T, E> getShortCircuit() {
        return shortCircuit;
    }

    boolean shortCircuit(){ return state.equals(State.SHORTCIRCUIT); }

    void setToShortCircuit(ResultEval<T, E> shortCircuit) {
        state = State.SHORTCIRCUIT;
        this.shortCircuit = shortCircuit;
    }
}
