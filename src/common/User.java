package common;

import java.util.*;

public class User {

    private String name;
    private Map<Movie, Double> ratings;
    private Double avgRating;

    public User(String name) {
        this.name = name;
        this.ratings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Movie, Double> getRatings() {
        return ratings;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public void setRatings(Map<Movie, Double> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Movie m, Double value) {
        this.ratings.put(m,value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
