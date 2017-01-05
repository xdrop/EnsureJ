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
        return create(CheckString.isAllLowercase(true), s, "");
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
        return create(CheckString.isAllLowercase(allowNonChar), s, "");
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     *
     * @return
     */
    public Chain<String, StringHandler> isAllUppercase(){
        return create(CheckString.isAllUppercase(true), s, "");
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
        return create(CheckString.isAllUppercase(allowNonChar), s, "");
    }


    /**
     * Checks whether the String matches the given pattern.
     *
     * @param regex Regex pattern
     * @return
     */
    public Chain<String, StringHandler> matches(String regex){
        return create(CheckString.matches(regex), s, "");
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
        return create(CheckString.hasOnlyLetters(unicode), s, "");
    }

    /**
     * Checks whether the String contains only letters
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyLetters(){
        return create(CheckString.hasOnlyLetters(true), s, "");
    }

    /**
     * Checks whether the String contains only digits
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyDigits(){
        return create(CheckString.hasOnlyDigits(), s, "");
    }

    /**
     * Checks whether the String is not null or and contains at least
     * one non-space character.
     *
     * @return
     */
    public Chain<String, StringHandler> isNotEmpty(){
        return create(CheckString.isNotEmpty(), s, "");
    }


    /**
     * Checks whether the String does not start with whitespace
     * @return
     */
    public Chain<String, StringHandler> hasStartWhitespace(){
        return create(CheckString.noWhitespaceStart(), s, "");
    }


    /**
     * Checks whether the String does not end with whitespace
     * @return
     */
    public Chain<String, StringHandler> hasEndWhitespace(){
        return create(CheckString.noWhitespaceEnd(), s, "");
    }

    /**
     * Checks whether the String does not contain extra whitespace at start or end.
     * This is effectively {@code hasEndWhitespace()} and {@code hasStartWhitespace()}
     * combined together.
     *
     * @return
     */
        public Chain<String, StringHandler> isTrimmed(){
            return create(CheckString.isTrimmed(), s, "");
        }

}
