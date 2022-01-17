package recommender.movie;

import common.Movie;
import common.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleRecommender {

    Map<Movie, Double> coOccurrences;
    List<Movie> allMovies;
    List<User> allUsers;
    User user;
    Movie movieToPredict;

    public SimpleRecommender(List<Movie> movies, User user, Movie movieToPredict, List<User> allUsers) {
        this.allMovies = movies;
        this.user = user;
        this.movieToPredict = movieToPredict;
        this.allUsers = allUsers;
    }

    public void predict() {
        if(user.getRatings().containsKey(movieToPredict)) {
            System.out.println("User with id " + user.getName() + ", already rated movie with id " + movieToPredict.getId());
            System.out.println("Rating = " + user.getRatings().get(movieToPredict));
            return;
        }

        setUpRating();

        // compute co-occurrences and normalize them
        Map<Movie, Long> freqs = RecommenderUtils.computeCoOccurrences(allUsers, user, movieToPredict);
        coOccurrences = RecommenderUtils.normalizeCoOccurrences(freqs);

        // multiply co-occurrences with user's original ratings
        Map<Movie, Double> userRatings = user.getRatings();

        Optional<Double> prediction = userRatings.entrySet().stream()
                .map(entry -> {
                    Double original = entry.getValue();
                    Double co = coOccurrences.get(entry.getKey());
                    if(co == null) co = 0.0;
                    return original * co;
                }).reduce(Double::sum);

        System.out.println("Prediction for User " + user.getName() + " and movie " + movieToPredict.getId() + " = " + prediction.get());
    }

    private void setUpRating() {

        // for the given user, set up missing ratings with avg rating of the user
        Optional<Double> userSumRating = user.getRatings().values().stream().reduce(Double::sum);
        Double userAvg = userSumRating.get()/user.getRatings().size();

        // movies not rated by the user should have the user's avg rating
        Map<Movie, Double> collect = allMovies.stream()
                .filter(movie -> !user.getRatings().containsKey(movie))
                .collect(Collectors.toMap(Function.identity(), value -> userAvg));

        // add missing movie ratings to the user
        collect.forEach((m,v) -> user.getRatings().put(m, v));

    }


}
