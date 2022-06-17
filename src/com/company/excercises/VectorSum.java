package com.company.excercises;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class VectorSum {

    public static void main(String args[]) {

        IntStream intStream2 = IntStream.rangeClosed(1,100000);
        // summing sequential
        long start2 = System.currentTimeMillis();
        int sum = intStream2.sum();
        long end2 = System.currentTimeMillis();

        // create a stream of random integers
        IntStream intStream = IntStream.rangeClosed(1,100000);
        // summing in parallel
        long start = System.currentTimeMillis();
        int parSum = intStream.parallel().sum();
        long end = System.currentTimeMillis();

        int[] ints = {7,8,3,5,9,12,45,34,67};

        // Vector parallel sum using sum()
        int sum1 = Arrays.stream(ints).parallel().sum();

        // Vector parallel sum using reduce and lambda
        int sum2 = intStream.parallel().reduce((i,j) -> i+j).getAsInt();

        // Vector parallel sum using reduce and method reference
        int sum3 = Arrays.stream(ints).parallel().reduce(Integer::sum).getAsInt();



        long total1 = end-start;
        System.out.println("Parallel sum=" + parSum + ", ready in " + total1 + " ms");
        long total2 = end2 - start2;
        System.out.println("Sum=" + sum + ", ready in " + total2 + " ms");

    }


}
