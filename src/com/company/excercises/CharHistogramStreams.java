package com.company.excercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharHistogramStreams {

    public static void main(String[] args) throws IOException {

        List<String> words = Files.lines(Path.of("resources/text.txt"))// read file line by line to a stream
                .map(s -> s.split("\\s+")) // split each line to its words, space separated
                .flatMap(Arrays::stream) // flatmap previous stream<String[]> to stream<String>
                .map(w -> w.replaceAll("[,.]*", "")) // remove commas and dots from each word
                .collect(Collectors.toList());

        List<Character> chars = words
                .parallelStream()
                .flatMap(x -> x.chars().mapToObj(i -> (char) i))
                .collect(Collectors.toList());

        Integer[] freqs = new Integer[26];
        IntStream.rangeClosed(0,25).forEach(i -> freqs[i] = i);

        List<Integer> result = Arrays.stream(freqs)
                .parallel()
                .map(i -> (int)
                        chars.stream()
                                .filter(c -> Character.getNumericValue(c) == i+10)
                                .count())
                .collect(Collectors.toList());


        result.forEach(r -> System.out.print(r + " "));


    }
}
