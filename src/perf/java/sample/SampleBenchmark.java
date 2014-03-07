package sample;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;

public class SampleBenchmark {

    @State(Scope.Thread)
    public static class Point {

        private static final double MAX_VALUE = 10_000;
        public final double a, b;

        public Point() {
            a = ThreadLocalRandom.current().nextDouble(MAX_VALUE);
            b = ThreadLocalRandom.current().nextDouble(MAX_VALUE);
        }
    }

    @GenerateMicroBenchmark
     public double measureBaseline(Point p) {
        return Math.sqrt(p.a * p.a + p.b * p.b);
    }

    @GenerateMicroBenchmark
    public double measureDirect(Point p) {
        return Math.hypot(p.a, p.b);
    }

    @GenerateMicroBenchmark
    public double measureSample(Point p) {
        return SampleClass.foo(p.a, p.b);
    }

}