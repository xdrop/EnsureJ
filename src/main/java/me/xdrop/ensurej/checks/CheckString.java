package me.xdrop.ensurej.checks;

import me.xdrop.ensurej.Predicate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckString {

    public static Predicate<String> isAllUppercase(final boolean allowNonChar) {
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if (Character.isLowerCase(ch) || (!allowNonChar && !Character.isUpperCase(ch))) {
                        return false;
                    }
                }

                return true;

            }
        };
    }

    public static Predicate<String> isAllLowercase(final boolean allowNonChar){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {

                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if ((Character.isUpperCase(ch)) || !allowNonChar && !Character.isLowerCase(ch)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static Predicate<String> matches(final String pattern){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                Pattern p = Pattern.compile(pattern);
                Matcher matcher = p.matcher(s);
                return matcher.matches();
            }
        };
    }

    public static Predicate<String> hasOnlyLetters(final boolean unicode){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                if (unicode) {
                    for (int i = 0; i < s.length(); i++) {
                        char ch = s.charAt(i);
                        if (!Character.isLetter(ch)) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return matches("^[a-zA-Z]+$").eval(s);
                }

            }
        };
    }

    public static Predicate<String> hasOnlyDigits(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {

                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if (!Character.isDigit(ch)) {
                        return false;
                    }
                }
                return true;

            }
        };
    }

    public static Predicate<String> isNotEmpty(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return s != null && matches("[^\\s+]").eval(s);
            }
        };
    }

    public static Predicate<String> isEmpty(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return !isNotEmpty().eval(s);
            }
        };
    }

    public static Predicate<String> isNotNull(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return s != null;
            }
        };
    }

    public static Predicate<String> hasWhitespaceStart(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return matches("\\s+.*").eval(s);
            }
        };
    }

    public static Predicate<String> hasWhitespaceEnd(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return matches(".*\\s").eval(s);
            }
        };
    }

    public static Predicate<String> isTrimmed(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String s) {
                return !hasWhitespaceStart().eval(s) && !hasWhitespaceEnd().eval(s);
            }
        };
    }
}
