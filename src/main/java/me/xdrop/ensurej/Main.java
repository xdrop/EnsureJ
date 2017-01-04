package me.xdrop.ensurej;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParamCheckFailedException {
        List<Float> lst = new ArrayList<Float>();
        Ensure.that(-2f).isPositive();
//        boolean s2 = Ensure.that(lst).any(FloatingPointHandler.allPositive()).e();

//        System.out.println(s2);


    }
}
