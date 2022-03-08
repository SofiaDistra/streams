package common;

public class Pair<T, Double> {

    private T key;
    private Double rating;

    public Pair(T key, Double rating) {
        this.key = key;
        this.rating = rating;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
