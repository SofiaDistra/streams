package recommender.movie;

import common.Combo;
import common.Movie;
import common.Pair;
import common.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecommenderUtils {


    /**
     * Computes the cooccurrences of a given movie with
     * the rest of the movies in the dataset
     * @param users a list of all the users in the dataset
     * @param u the user for whom the recommendations are to be generated
     * @param m the movie for which the cooccurrences are computed
     * @return a Map with movies as keys and their corresponding cooccurrences as values
     */
    public static Map<Movie, Long> computeCoOccurrences(List<User> users, User u, Movie m) {

        Map<Movie, Double> userRatings = u.getRatings();

        // contains the co-occurrences
        List<Map<Movie, Long>> freqsMap = userRatings.keySet().stream()
                .parallel()
                .map(rating -> users.stream().filter(user ->
                                !user.equals(u) && user.getRatings().containsKey(rating) && user.getRatings().containsKey(m)).collect(Collectors.toList())
                        .stream().map(user -> new Combo(1L, rating))
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.counting())))
                .collect(Collectors.toList());

        // the frequencies list flattened
        return freqsMap
                .parallelStream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Movie, Double> normalizeCoOccurrences(Map<Movie, Long> freqs) {

        Optional<Long> totalFreqs = freqs.values().stream()
                .parallel()
                .reduce(Long::sum);

        return freqs.entrySet().stream()
                .parallel()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / Double.valueOf(totalFreqs.get())));
    }

    /**
     * Normalizes the ratings of a user,
     * by subtracting from each rating the user's average rating
     * @param ratings the ratings to be normalized
     * @return the normalized ratings
     */
    public static Map<Movie, Double> normalizeUserRatings(Map<Movie, Double> ratings) {

        // sum of all user's ratings
        Double ratingsSum = ratings.values().stream().reduce(Double::sum).get();
        Double ratingAvg = ratingsSum / ratings.size();

        // subtract user's rating average from each user's rating
        return ratings.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() - ratingAvg));

    }

    /**
     * Adds missing movies to user, with rating 0.0
     * @param allMovies the list of all available movies from the data
     * @param user the user to whom the ratings should be filled
     */
    public static void fillUserRatings(List<Movie> allMovies, User user) {
        for(Movie m : allMovies) {
            if(!user.getRatings().containsKey(m)) {
                user.addRating(m, 0.0);
            }
        }
    }

    /**
     * Collects ratings of a given movie to a map,
     * having the user as key and the rating as value
     * @param users the list of all available users
     * @param movie the movie for which to collect the ratings
     * @return a Map with the user as key and the corresponding rating as value
     */
    public static Map<User, Double> collectMovieRatings(List<User> users, Movie movie) {

        return users.stream().parallel()
                .map(u -> u.getRatings().containsKey(movie) ? new Pair<>(u, u.getRatings().get(movie)) :
                        new Pair<>(u, 0.0))
                .collect(Collectors.toMap(Pair::getKey, Pair::getRating));
    }
}
