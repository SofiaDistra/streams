package common;

import java.util.*;

public class User {

    private String id;
    private Map<Movie, Double> ratings;

    public User(String id) {
        this.id = id;
        this.ratings = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Movie, Double> getRatings() {
        return ratings;
    }

    public Double getAvgRating() {
        Double userRatingsSum = ratings.values().stream()
                .reduce(Double::sum).get();

        return userRatingsSum/ratings.size();
    }

    public void addRating(Movie m, Double value) {
        this.ratings.put(m,value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + id + '\'' +
                '}';
    }
}
