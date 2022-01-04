package com.company.excercises;

import java.util.stream.LongStream;

public class PiCalcStreams {

    public static void main(String[] args) {

        long numSteps = 10000;
        double sum;

        double step = 1.0 / (double)numSteps;

        // calculate sum using streams
        LongStream stream = LongStream.range(0L, numSteps);
        sum = stream.mapToDouble(i -> ((double)i+0.5)*step).reduce(0, (a,b) -> {
            a += 4.0/(1.0+b*b);
            return a;
        });

        double pi = sum * step;

        System.out.println("Computed pi = " + pi);
        System.out.println("Difference between computed pi and Math.PI = " + Math.abs(pi - Math.PI));
    }
}
