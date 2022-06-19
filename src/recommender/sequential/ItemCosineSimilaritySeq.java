package recommender.sequential;

import common.Movie;
import common.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCosineSimilaritySeq {
    /** Calculates and returns the cosine similarity between two items
     * @param ratings1 a map with all the ratings for a given movie
     * @param ratings2 a map with all the ratings for the other movie
     * @return the similarity between two items, which ratings are given as parameters
     */
    public static Double itemsSimilarity(Map<User, Double> ratings1, Map<User, Double> ratings2) {

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

        Map<User, Double> r1 = RecommenderUtilsSeq.collectMovieRatings(users, movie);

        movies.forEach(m -> {
            Map<User, Double> r2 = RecommenderUtilsSeq.collectMovieRatings(users, m);
            Double sim = itemsSimilarity(r1, r2);
            similarities.put(m, sim);
        });

        return similarities;
    }
}
