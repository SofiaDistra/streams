package recommender.movie;

import common.Combo;
import common.Movie;
import common.User;

import java.util.*;
import java.util.stream.Collectors;

public class MovieRecommenderMain {

    public static void main(String[] args) {

        List<User> users = InitTestData.initData();

        // the user u and the movie m for which we calculate the predicted rating
        User u = users.get(2);
        Movie m = new Movie("Drink");

        // a map with all the movies the user has rated
        Map<Movie, Double> userRatings = u.getRatings();

        // contains the frequencies
        List<Map<Movie, Long>> freqsMap = userRatings.keySet().stream()
                .map(rating -> users.stream().filter(user ->
                                !user.equals(u) && user.getRatings().containsKey(rating) && user.getRatings().containsKey(m)).collect(Collectors.toList())
                        .stream().map(user -> new Combo(1L, rating))
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.counting())))
                .collect(Collectors.toList());

        // the frequencies list flattened
        Map<Movie, Long> freqsFlat = freqsMap.stream().flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        // contains the differences
        List<Map<Movie, Double>> diffs = userRatings.keySet().stream()
                .map(rating -> users.stream().filter(user ->
                                !user.equals(u) && user.getRatings().containsKey(rating) && user.getRatings().containsKey(m)).collect(Collectors.toList())
                        .stream().map(user -> {
                            double differ = user.getRatings().get(m) - user.getRatings().get(rating);
                            return new Combo(differ, rating);
                        })
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.summingDouble(Combo::getDiff))))
                .collect(Collectors.toList());

        // the differences list flattened
        Map<Movie, Double> diffsFlat = diffs.stream().flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // the list of final differences
        List<Combo> diffsFinal = diffsFlat.entrySet().stream()
                .map(d -> {
                    Long freq = freqsFlat.get(d.getKey());
                    return new Combo(d.getValue() / freq, freq, d.getKey());
                }).collect(Collectors.toList());

        // calculate the prediction sum
        Optional<Double> pred = diffsFinal.stream().map(combo -> {
            double orig = userRatings.get(combo.getMovie());
            Long freq = combo.getFreq();
            Double differ = combo.getDiff();
            return (differ + orig) * freq;
        }).reduce(Double::sum);

        // sum the frequencies
        Optional<Long> totalFreqs = freqsFlat.entrySet().stream().map(f -> f.getValue()).reduce(Long::sum);

        // final prediction
        Double prediction = pred.get()/totalFreqs.get();

        System.out.println("Prediction for User " + u.getName() + " and movie " + m.getTitle() + " = " + prediction);

    }
}
