package me.xdrop.ensurej;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParamCheckFailedException, UnsupportedEncodingException {
        if ("aa/../aaa.ll".matches("^[\\w\\d]+(\\.[\\w\\d]+)?$")) {
            System.out.println("pass");
        }
    }
}
