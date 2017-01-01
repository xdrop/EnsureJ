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

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     *
     * @return
     */
    public ResultEval<T, StringHandler<T>> isAllUppercase() {
        return isAllUppercase(true);
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public ResultEval<T, StringHandler<T>> isAllUppercase(boolean allowNonChar) {
        if (shortCircuit()) return getShortCircuit();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch) || (!allowNonChar && !Character.isUpperCase(ch))) {
                return result(false, "String not all uppercase");
            }
        }
        return result(true);
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that hello1 is passes this test.
     * See {@param allowNonChar}
     *
     * @return
     */
    public ResultEval<T, StringHandler<T>> isAllLowercase() {
        return isAllLowercase(true);
    }

    /**
     * Checks whether the String contains all lowercase letters.
     * Please note that hello1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public ResultEval<T, StringHandler<T>> isAllLowercase(boolean allowNonChar) {
        if (shortCircuit()) return getShortCircuit();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((Character.isUpperCase(ch)) || !allowNonChar && !Character.isLowerCase(ch)) {
                return result(false, "String not all uppercase");
            }
        }
        return result(true);
    }

    /**
     * Checks whether the String matches the given pattern.
     *
     * @param pattern Regex pattern
     * @return
     */
    public ResultEval<T, StringHandler<T>> matches(String pattern) {
        if (shortCircuit()) return getShortCircuit();

        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);
        return result(matcher.matches(), String.format("Pattern %s doesn't match %s", pattern, s));
    }

    /**
     * Checks whether the String contains only letters
     *
     * @return
     */
    public ResultEval<T, StringHandler<T>> hasOnlyLetters() {
        return hasOnlyLetters(true);
    }

    /**
     * Checks whether the String contains only letters
     *
     * @param unicode If false then we only check for latin a-zA-Z, otherwise
     *                {@code Character.isLetter()} is used which works with
     *                unicode letters too.
     * @return
     */
    public ResultEval<T, StringHandler<T>> hasOnlyLetters(boolean unicode) {
        if (shortCircuit()) return getShortCircuit();

        if (unicode) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!Character.isLetter(ch)) {
                    return result(false, "String is not letter only");
                }
            }
            return result(true);
        } else {
            return matches("\\w+");
        }
    }


    /**
     * Checks whether the String contains only letters
     *
     * @return
     */
    public ResultEval<T, StringHandler<T>> hasOnlyDigits() {
        if (shortCircuit()) return getShortCircuit();


        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch)) {
                return result(false, "String is not digit only");
            }
        }
        return result(true);

    }
}
