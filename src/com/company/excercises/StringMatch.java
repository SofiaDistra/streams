package com.company.excercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class StringMatch {

    public static void main(String[] args) throws IOException {

        String pattern = "lorem";

        long matches = Files.lines(Path.of("resources/text.txt")) // read file line by line to a stream
                .parallel() // parallelize the stream
                .map(s -> s.split("\\s+")) // split each line to its words, space separated
                .flatMap(Arrays::stream) // flatmap previous stream<String[]> to stream<String>
                .map(w -> w.replaceAll("[,.]*", "")) // remove commas and dots from each word
                .filter(w -> w.equals(pattern)) // filter out words that don't match the pattern
                .count(); // count the words that match the pattern

        System.out.println("Matches = " + matches);
    }
}
