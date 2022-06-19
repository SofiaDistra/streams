package recommender.sequential;

import common.Movie;
import common.Pair;
import common.User;

import java.util.*;
import java.util.stream.Collectors;

public class ItemKNeighborsRecommenderSeq {

    private int k;
    // list with similar items + similarity indicator (double)
    private Map<Movie, Double> similarMovies;
    // item to predict
    private Movie movie;
    // the user for whom to calculate the predicted rating
    private User user;

    private List<User> allUsers;
    private List<Movie> allMovies;


    /**
     * Constructor for the ItemKNeighborsRecommender class
     * @param k The number of neighbors
     * @param movie The movie for which to produce the prediction
     * @param user The user for whom to produce the prediction
     * @param allUsers a list of all the users
     * @param allMovies a list of all the movies
     */
    public ItemKNeighborsRecommenderSeq(int k, Movie movie, User user, List<User> allUsers, List<Movie> allMovies) {
        this.k = k;
        this.movie = movie;
        this.user = user;
        this.allUsers = allUsers;
        this.allMovies = allMovies;
        this.similarMovies = new HashMap<>();
    }

    /**
     * Calculates and prints the predicted rating of a user for a specific movie
     */
    public long predict() {

        long start = System.currentTimeMillis();

        // calculate similarity of this movie with every other available movie
        Map<User, Double> movieRatings = RecommenderUtilsSeq.collectMovieRatings(allUsers, movie);

        RecommenderUtilsSeq.fillUserRatings(allMovies, user);
        for (User u : allUsers) RecommenderUtilsSeq.fillUserRatings(allMovies, u);

        similarMovies = allMovies.stream().map(m ->
                        new Pair(m, ItemCosineSimilaritySeq.itemsSimilarity(movieRatings, RecommenderUtilsSeq.collectMovieRatings(allUsers, m))))
                .collect(Collectors.toMap(t -> (Movie) t.getKey(), t -> (Double) t.getRating()));

        // sort similar movies according to similarity (descending)
        Map<Movie, Double> similarMoviesSorted = similarMovies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        // find top K similar movies
        int count = 0;
        Map<Movie, Double> topKSimilarMovies = new LinkedHashMap<>();
        for (Map.Entry<Movie, Double> e : similarMoviesSorted.entrySet()) {
            if (count == k) break;
            if (e.getValue().isNaN()) continue;
            topKSimilarMovies.put(e.getKey(), e.getValue());
            count++;
        }

        Double userAvgRating = user.getAvgRating();
        Double denominator = topKSimilarMovies.values().stream().reduce(Double::sum).get();
        Double numerator = topKSimilarMovies.entrySet().stream()
                .map(e -> e.getValue() * user.getRatings().get(e.getKey()))
                .reduce(Double::sum)
                .get();

        Double prediction = userAvgRating + (numerator / denominator);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("Prediction for User " + user.getId() + " and movie " + movie.getId() + " = " + prediction);
        return time;
    }
}
