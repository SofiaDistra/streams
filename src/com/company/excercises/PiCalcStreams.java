package com.company.excercises;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PiCalcStreams {

    public static void main(String[] args) {

        long sections = 10000;
        double sum;

        double step = 1.0 / (double)sections;

        // calculate sum using streams
        sum = Stream
                .iterate(0, i -> i+1)
                .limit(sections)
                .parallel()
                .mapToDouble(i -> mapFunction(i,step))
                .reduce(0.0, Double::sum);

        double pi = sum * step;

        System.out.println("Computed pi = " + pi);
        System.out.println("Difference between computed pi and Math.PI = " + Math.abs(pi - Math.PI));
    }

    public static double mapFunction(int i, double step)
    {
        double x = ((double)i+0.5)*step;
        return 4.0/(1.0+x*x);
    }
}
