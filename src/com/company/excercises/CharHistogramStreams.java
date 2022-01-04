package com.company.excercises;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharHistogramStreams {

    public static void main(String[] args) {
        String words[] = {"hello", "world", "more", "words", "that", "come", "to", "my", "mind"};

        // take the above list of words and flatmap them to a list of letters
        List<String> letters =
                Arrays.stream(words)
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList());

        Integer[] freqs = new Integer[26];
        IntStream.rangeClosed(0,25).forEach(i -> freqs[i] = i);

        List<Integer> result = Arrays.stream(freqs)
                .map(i -> (int)
                        letters.stream().map(l -> l.toLowerCase(Locale.ROOT).charAt(0))
                                .filter(l -> Character.getNumericValue(l) == i+10)
                                .count())
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}
