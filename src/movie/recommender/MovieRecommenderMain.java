package movie.recommender;

import java.util.*;
import java.util.stream.Collectors;

public class MovieRecommenderMain {

    public static void main(String[] args) {

        List<User> users = initData();

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

    public static List<User> initData() {
        User u0 = new User("u0");
        User u1 = new User("u1");
        User u2 = new User("u2");
        User u3 = new User("u3");

        Movie m0 = new Movie("Candy");
        Movie m1 = new Movie("Drink");
        Movie m2 = new Movie("Soda");
        Movie m3 = new Movie("Popcorn");
        Movie m4 = new Movie("Snacks");

        u0.addRating(m1,1.0);
        u0.addRating(m3,4.0);
        u0.addRating(m4,6.0);

        u1.addRating(m0,5.0);
        u1.addRating(m2,6.0);

        u2.addRating(m0,9.0);
        u2.addRating(m2,1.0);
        u2.addRating(m4,6.0);

        u3.addRating(m0,7.0);
        u3.addRating(m1,8.0);
        u3.addRating(m3,9.0);

        List<User> users = new ArrayList<>();
        users.add(u0);
        users.add(u1);
        users.add(u2);
        users.add(u3);

        return users;
    }
}
