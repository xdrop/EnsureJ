package me.xdrop.ensurej;


import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler<T extends ParamCheckFailedException> extends Handler<T, StringHandler<T>> {

    private String s;

    StringHandler(String s) {
        this.s = s;
    }

    public ResultEval<T, StringHandler<T>> matches(String pattern){
        if(shortCircuit()) return getShortCircuit();

        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);
        return result(matcher.matches(), String.format("Pattern %s doesn't match %s", pattern, s));
    }
}
