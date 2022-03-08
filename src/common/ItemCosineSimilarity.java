package common;

import recommender.movie.RecommenderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCosineSimilarity {


    public static Double itemsSimilarity(Map<User, Double> ratings1, Map<User, Double> ratings2) {
        /*
         TODO etsi xanw merikous xristes pou mporei na yparxoun sto ratings2
         prepei sta maps na yparxoun oloi oi koinoi xristes, me rating estw 0
         fillUserRatings: na tin xrisimopoiisw
        */
        Double sum = ratings1.entrySet().stream()
                .map(e -> ratings2.get(e.getKey()) != null ? e.getValue() * ratings2.get(e.getKey()) : 0.0)
                .reduce(Double::sum)
                .get();

        Double r1 = Math.sqrt(ratings1.values().stream()
                .map(v -> v*v)
                .reduce(Double::sum).get());

        Double r2 = Math.sqrt(ratings2.values().stream()
                .map(v -> v * v)
                .reduce(Double::sum).get());

        return sum / (r1*r2);
    }

    public static Map<Movie, Double> findSimilarMovies(Movie movie, List<User> users, List<Movie> movies) {

        Map<Movie, Double> similarities = new HashMap<>();

        Map<User, Double> r1 = RecommenderUtils.collectMovieRatings(users, movie);

        movies.forEach(m -> {
            Map<User, Double> r2 = RecommenderUtils.collectMovieRatings(users, m);
            Double sim = itemsSimilarity(r1, r2);
            similarities.put(m, sim);
        });

        return similarities;
    }

    public static Double usersSimilarity(Map<Movie, Double> ratings1, Map<Movie, Double> ratings2) {
        Double sum = ratings1.entrySet().stream()
                .map(e -> ratings2.get(e.getKey()) != null ? e.getValue() * ratings2.get(e.getKey()) : 0.0)
                .reduce(Double::sum)
                .get();

        Double r1 = Math.sqrt(ratings1.values().stream()
                .map(v -> v*v)
                .reduce(Double::sum).get());

        Double r2 = Math.sqrt(ratings2.values().stream()
                .map(v -> v*v)
                .reduce(Double::sum).get());

        return sum / (r1*r2);
    }


}
