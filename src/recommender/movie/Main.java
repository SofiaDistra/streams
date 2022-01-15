package movie.recommender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<User> users = InitTestData.initData();
        List<Movie> movies = InitTestData.getMovies();

        // the user u and the movie m for which we calculate the predicted rating
        User u = users.get(2);
        Movie m = new Movie("Drink");

        setUpRating(u, movies);

    }

    public static void setUpRating(User user, List<Movie> movies) {

        // for the given user, set up missing ratings with avg rating of the user
        Optional<Double> userSumRating = user.getRatings().values().stream().reduce(Double::sum);
        Double userAvg = userSumRating.get()/user.getRatings().size();

        // movies not rated by the user should have the user's avg rating
        Map<Movie, Double> collect = movies.stream()
                .filter(movie -> !user.getRatings().containsKey(movie))
                .collect(Collectors.toMap(Function.identity(), value -> userAvg));

        // add missing movie ratings to the user
        collect.forEach((m,v) -> user.getRatings().put(m, v));

    }

    public static void computeCoOccurences(List<User> users, User u, Movie m) {
        // contains the frequencies
        Map<Movie, Double> userRatings = u.getRatings();

        List<Map<Movie, Long>> freqsMap = userRatings.keySet().stream()
                .map(rating -> users.stream().filter(user ->
                                !user.equals(u) && user.getRatings().containsKey(rating) && user.getRatings().containsKey(m)).collect(Collectors.toList())
                        .stream().map(user -> new Combo(1L, rating))
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.counting())))
                .collect(Collectors.toList());

        // the frequencies list flattened
        Map<Movie, Long> freqsFlat = freqsMap.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // normalize
        int totalFreqs = freqsFlat.size();

    }

    public static void calculatePrediction(Map<Movie, Double> coOccurences, Map<Movie,Double> userRatings, Movie movieToPredict) {

    }


}
