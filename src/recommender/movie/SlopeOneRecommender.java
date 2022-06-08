package recommender.movie;

import common.Combo;
import common.Movie;
import common.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements the slope one algorithm for recommendations.
 * It receives a specific user and a specific movie for which
 * to calculate the prediction.
 */
public class SlopeOneRecommender {

    private User user;
    private Movie movie;
    private List<User> users;
    Map<Movie, Double> userRatings;
    Map<Movie, Long> freqsFlat;
    List<Combo> diffsFinal;

    /**
     * Constructor for the SlopeOneRecommender class
     * @param user The user for whom to produce the prediction
     * @param movie The movie for which to produce the prediction
     * @param users The list of all users
     */
    public SlopeOneRecommender(User user, Movie movie, List<User> users) {
        this.user = user;
        this.movie = movie;
        this.userRatings = user.getRatings();
        this.users = users;
    }

    /**
     * Calculates and prints the predicted rating of a user for a specific movie
     */
    public void predict() {

        if(user.getRatings().containsKey(movie)) {
            System.out.println("User with id " + user.getId() + ", already rated movie with id " + movie.getId());
            System.out.println("Rating = " + user.getRatings().get(movie));
            return;
        }

        preProcess();

        // calculate the prediction sum
        Optional<Double> pred = diffsFinal.stream().map(combo -> {
            double orig = userRatings.get(combo.getMovie());
            Long freq = combo.getFreq();
            Double differ = combo.getDiff();
            return (differ + orig) * freq;
        }).reduce(Double::sum);

        // sum the frequencies
        Optional<Long> totalFreqs = freqsFlat.values().stream().reduce(Long::sum);

        // final prediction
        Double prediction = pred.get()/totalFreqs.get();

        System.out.println("Prediction for User " + user.getId() + " and movie " + movie.getId() + " = " + prediction);
    }

    /**
     * Preprocesses the data, so as to calculate the differences and co-occurrences matrices
     */
    private void preProcess() {
        freqsFlat = RecommenderUtils.computeCoOccurrences(users, user, movie);

        // contains the differences
        List<Map<Movie, Double>> diffs = userRatings.keySet().stream()
                .map(rating -> users.stream().filter(u ->
                                !u.equals(user) && u.getRatings().containsKey(rating)
                                        && u.getRatings().containsKey(movie)).collect(Collectors.toList())
                        .stream().map(user -> {
                            double differ = user.getRatings().get(movie) - user.getRatings().get(rating);
                            return new Combo(differ, rating);
                        })
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.summingDouble(Combo::getDiff))))
                .collect(Collectors.toList());

        // the differences list flattened
        Map<Movie, Double> diffsFlat = diffs.stream().flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // the list of final differences
        diffsFinal = diffsFlat.entrySet().stream()
                .map(d -> {
                    Long freq = freqsFlat.get(d.getKey());
                    return new Combo(d.getValue() / freq, freq, d.getKey());
                }).collect(Collectors.toList());

    }
}
