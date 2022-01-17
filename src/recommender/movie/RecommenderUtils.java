package recommender.movie;

import common.Combo;
import common.Movie;
import common.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecommenderUtils {

    public static Map<Movie, Long> computeCoOccurrences(List<User> users, User u, Movie m) {

        Map<Movie, Double> userRatings = u.getRatings();

        // contains the co-occurrences
        List<Map<Movie, Long>> freqsMap = userRatings.keySet().stream()
                .map(rating -> users.stream().filter(user ->
                                !user.equals(u) && user.getRatings().containsKey(rating) && user.getRatings().containsKey(m)).collect(Collectors.toList())
                        .stream().map(user -> new Combo(1L, rating))
                        .collect(Collectors.groupingBy(Combo::getMovie, Collectors.counting())))
                .collect(Collectors.toList());

        // the frequencies list flattened
        return freqsMap.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Movie, Double> normalizeCoOccurrences(Map<Movie, Long> freqs) {

        Optional<Long> totalFreqs = freqs.values().stream().reduce(Long::sum);

        return freqs.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / Double.valueOf(totalFreqs.get())));
    }
}
