package sample;

import org.uncommons.maths.Maths;

public class SampleClass {

    public static double foo(double a, double b) {
        return Math.hypot(a, b);
    }

    public static double bar(double a, double b) {
        return Maths.log(a, b);
    }

}