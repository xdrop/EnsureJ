package me.xdrop.ensurej;

import com.sun.tools.javac.comp.Check;
import me.xdrop.ensurej.checks.CheckString;

import java.util.ArrayList;
import java.util.List;

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
    public Chain<String, StringHandler> isAllLowercase() {
        return create(CheckString.isAllLowercase(true),
                s, "String not all lowercase");
    }

    /**
     * Checks whether the String contains all lowercase letters.
     * Please note that hello1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public Chain<String, StringHandler> isAllLowercase(boolean allowNonChar) {
        return create(CheckString.isAllLowercase(allowNonChar), s, "Lowercase check failed");
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     *
     * @return
     */
    public Chain<String, StringHandler> isAllUppercase() {
        return create(CheckString.isAllUppercase(true), s,
                "Uppercase check failed");
    }

    /**
     * Checks whether the String contains all uppercase letters.
     * Please note that HELLO1 is passes this test.
     * See {@param allowNonChar}
     *
     * @param allowNonChar Non-character entries are allowed
     * @return
     */
    public Chain<String, StringHandler> isAllUppercase(boolean allowNonChar) {
        return create(CheckString.isAllUppercase(allowNonChar), s, "Uppercase check failed");
    }


    /**
     * Checks whether the String matches the given pattern.
     *
     * @param regex Regex pattern
     * @return
     */
    public Chain<String, StringHandler> matches(String regex) {
        return create(CheckString.matches(regex), s, "Pattern check failed");
    }


    /**
     * Checks whether the String contains only letters
     *
     * @param unicode If false then we only check for latin a-zA-Z, otherwise
     *                {@code Character.isLetter()} is used which works with
     *                unicode letters too.
     * @return
     */
    public Chain<String, StringHandler> hasOnlyLetters(boolean unicode) {
        return create(CheckString.hasOnlyLetters(unicode), s, "Letter only check failed");
    }

    /**
     * Checks whether the String contains only letters
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyLetters() {
        return create(CheckString.hasOnlyLetters(true), s, "Letter only check failed");
    }

    /**
     * Checks whether the String contains only digits
     *
     * @return
     */
    public Chain<String, StringHandler> hasOnlyDigits() {
        return create(CheckString.hasOnlyDigits(), s, "Digit only check failed");
    }

    /**
     * Checks whether the String is not null or and contains at least
     * one non-space character.
     *
     * @return
     */
    public Chain<String, StringHandler> isNotEmpty() {
        return create(CheckString.isNotEmpty(), s, "Empty check failed");
    }


    /**
     * Checks whether the String does not start with whitespace
     *
     * @return
     */
    public Chain<String, StringHandler> hasStartWhitespace() {
        return create(CheckString.hasWhitespaceStart(), s, "Start whitespace check failed");
    }


    /**
     * Checks whether the String does not end with whitespace
     *
     * @return
     */
    public Chain<String, StringHandler> hasEndWhitespace() {
        return create(CheckString.hasWhitespaceEnd(), s, "End whitespace check failed");
    }

    /**
     * Checks whether the String does not contain extra whitespace at start or end.
     * This is effectively {@code hasEndWhitespace()} and {@code hasStartWhitespace()}
     * combined together.
     *
     * @return
     */
    public Chain<String, StringHandler> isTrimmed() {
        return create(CheckString.isTrimmed(), s, "Trimmed check failed");
    }

    /**
     * Checks whether the string is of length between {@param lower} and {@param upper}.
     *
     * @param lower The <strong>inclusive</strong> lower bound
     * @param upper The <strong>non-inclusive</strong> upper bound
     * @return
     */
    public Chain<String, StringHandler> lengthBetween(int lower, int upper){
        return create(CheckString.lengthBetween(lower, upper), s, "Number of characters check failed");
    }

    /**
     * Checks whether all characters in the string are equal to the given character.
     *
     * @param c The character that all letters must equal
     * @return
     */
    public Chain<String, StringHandler> allEqual(Character c){
        return create(CheckString.allLetters(c), s, "All characters equal to '" + c + "' check failed");
    }

    /**
     * Checks whether all characters in the string are all equal to each other.
     *
     * @return
     */
    public Chain<String, StringHandler> allEqual(){
        return create(CheckString.allEqual(), s, "All characters equal check failed");
    }

    /**
     * Checks whether the string represents a valid number. Note that
     * this includes integers, as well as floating point types
     *
     * @return
     */
    public Chain<String, StringHandler> isNumber(){
        return create(CheckString.isNumber(), s, "Number check failed");
    }

    /**
     * Checks whether the string is part of the set provided
     *
     * @param set
     * @return
     */
    public Chain<String, StringHandler> fromSet(String ... set){
        return create(CheckString.fromSet(set), s, "String in set check failed");
    }

}
