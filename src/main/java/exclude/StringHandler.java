package exclude;


import me.xdrop.ensurej.Handler;
import me.xdrop.ensurej.ParamCheckFailedException;
import me.xdrop.ensurej.ResultEval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler<T extends ParamCheckFailedException> extends Handler<T> {

    private String s;

    public StringHandler(String s) {
        this.s = s;
    }

    public ResultEval<T> matches(String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);
        return result(matcher.matches(), String.format("Pattern %s doesn't match %s", pattern, s));
    }
}
