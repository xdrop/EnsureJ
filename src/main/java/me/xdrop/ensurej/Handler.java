package me.xdrop.ensurej;

@SuppressWarnings("unchecked")
public abstract class Handler <T extends ParamCheckFailedException, E extends Handler<T,E>> {

    private Class<T> exceptionClass;
    private ResultEval<T, E> resultRoot;
    private ResultEval<T,E> shortCircuit;

    <F extends Handler<T, F>> F self(){
        return (F) this;
    }

    public Handler(ResultEval<T, E> shortCircuit) {
        this.shortCircuit = shortCircuit;
    }

    public Handler() {
    }

    void setExceptionClass(Class<T> clazz){
        this.exceptionClass = clazz;
    }

    ResultEval<T,E> result(boolean b, String msg){
        return new ResultEval<T,E>(b, msg, exceptionClass, (E) self());
    }

    ResultEval<T, E> result(boolean b){
        return new ResultEval<T,E>(b, exceptionClass, (E) self());
    }


    public ResultEval<T, E> getResultRoot() {
        return resultRoot;
    }

    public void setResultRoot(ResultEval<T, E> resultRoot) {
        this.resultRoot = resultRoot;
    }

    public ResultEval<T, E> getShortCircuit() {
        return shortCircuit;
    }

    public boolean shortCircuit(){ return shortCircuit != null; }
}
