package com.company.excercises;

import java.util.Random;
import java.util.stream.IntStream;

public class VectorSum {

    public static void main(String args[]) {
        Random random = new Random();

        // create a stream of random integers
        IntStream intStream = IntStream.rangeClosed(1,1000);
        // summing in parallel
        int parSum = intStream.parallel().sum();

        IntStream intStream2 = IntStream.rangeClosed(1,1000);
        // summing sequential
        int sum = intStream2.sum();

        System.out.println("Parallel sum=" + parSum);
        System.out.println("Sum=" + sum);
    }


}
