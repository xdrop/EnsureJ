package me.xdrop.ensurej;

public class ParamCheckFailedException extends Throwable {

    public ParamCheckFailedException() {
    }

    public ParamCheckFailedException(String message) {
        super(message);
    }

    public ParamCheckFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamCheckFailedException(Throwable cause) {
        super(cause);
    }

    public ParamCheckFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static ParamCheckFailedException create(){
        return new ParamCheckFailedException();
    }

    public static ParamCheckFailedException create(String message){
        return new ParamCheckFailedException(message);
    }

    public static ParamCheckFailedException create(String message, Throwable cause){
        return new ParamCheckFailedException(message, cause);
    }

    public static ParamCheckFailedException create(Throwable cause){
        return new ParamCheckFailedException(cause);
    }

    public static ParamCheckFailedException create(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        return new ParamCheckFailedException(message, cause, enableSuppression, writableStackTrace);
    }



}
