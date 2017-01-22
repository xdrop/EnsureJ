package me.xdrop.ensurej.checks;

import me.xdrop.ensurej.Chain;
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

    public static Predicate<String> lengthBetween(final int lower, final int upper){

        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                int length = in.length();
                return length >= lower && length < upper;
            }
        };

    }

    public static Predicate<String> allLetters(final Character c){
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                for (int i = 0; i < in.length(); i++){
                    if(in.charAt(i) != c){
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static Predicate<String> allEqual() {
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                return allLetters(in.charAt(0)).eval(in);
            }
        };
    }

    public static Predicate<String> isInteger(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                try {
                    Integer.parseInt(in);
                    return true;
                } catch (NumberFormatException e){
                    return false;
                }
            }
        };
    }

    public static Predicate<String> isDecimal(){
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                try {
                    Double.parseDouble(in);
                    Float.parseFloat(in);
                    return true;
                } catch (NumberFormatException e){
                    return false;
                }
            }
        };
    }

    public static Predicate<String> isNumber() {
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                return isInteger().eval(in) || isDecimal().eval(in);
            }
        };
    }

    public static Predicate<String> fromSet(final String ... set){
        return new Predicate<String>() {
            @Override
            public boolean eval(String in) {
                for (String s : set){
                    if (s.equals(in)){
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
