package me.xdrop.ensurej;

import me.xdrop.ensurej.checks.CheckString;

public class StringHandler extends Handler<StringHandler> {

    private String s;

    public StringHandler(String s) {
        this.s = s;
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that hello1 is passes this test.
     * See {@param allowNonChar}
     *
     * @return
     */
    public Chain<String, StringHandler> isAllLowercase(){
        return new Chain<>(CheckString.isAllLowercase(true), s, self());
    }

    /**
     * Checks whether the String contains all lowercase letters.
     * Please note that hello1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public Chain<String, StringHandler> isAllLowercase(boolean allowNonChar){
        return new Chain<>(CheckString.isAllLowercase(allowNonChar), s, self());
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     *
     * @return
     */
    public Chain<String, StringHandler> isAllUppercase(){
        return new Chain<>(CheckString.isAllUppercase(true), s, self());
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public Chain<String, StringHandler> isAllUppercase(boolean allowNonChar){
        return new Chain<>(CheckString.isAllUppercase(allowNonChar), s, self());
    }


    /**
     * Checks whether the String matches the given pattern.
     *
     * @param pattern Regex pattern
     * @return
     */
    public Chain<String, StringHandler> matches(String regex){
        return new Chain<>(CheckString.matches(regex), s, self());
    }


    /**
     * Checks whether the String contains only letters
     *
     * @param unicode If false then we only check for latin a-zA-Z, otherwise
     *                {@code Character.isLetter()} is used which works with
     *                unicode letters too.
     * @return
     */
    public Chain<String, StringHandler> hasOnlyLetters(boolean unicode){
        return new Chain<>(CheckString.hasOnlyLetters(unicode), s, self());
    }

    /**
     * Checks whether the String contains only letters
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyLetters(){
        return new Chain<>(CheckString.hasOnlyLetters(true), s, self());
    }

    /**
     * Checks whether the String contains only digits
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyDigits(){
        return new Chain<>(CheckString.hasOnlyDigits(), s, self());
    }

    /**
     * Checks whether the String is not null or and contains at least
     * one non-space character.
     *
     * @return
     */
    public Chain<String, StringHandler> isNotEmpty(){
        return new Chain<>(CheckString.isNotEmpty(), s, self());
    }


    /**
     * Checks whether the String does not start with whitespace
     * @return
     */
    public Chain<String, StringHandler> hasStartWhitespace(){
        return new Chain<>(CheckString.noWhitespaceStart(), s, self());
    }


    /**
     * Checks whether the String does not end with whitespace
     * @return
     */
    public Chain<String, StringHandler> hasEndWhitespace(){
        return new Chain<>(CheckString.noWhitespaceEnd(), s, self());
    }

    /**
     * Checks whether the String does not contain extra whitespace at start or end.
     * This is effectively {@code hasEndWhitespace()} and {@code hasStartWhitespace()}
     * combined together.
     *
     * @return
     */
        public Chain<String, StringHandler> isTrimmed(){
            return new Chain<>(CheckString.isTrimmed(), s, self());
        }

}
