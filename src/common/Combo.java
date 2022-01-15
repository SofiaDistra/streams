package common;

public class Combo {

    private Double diff;
    private Long freq;
    private Movie movie;

    public Combo() {
    }

    public Combo(Double diff, Long freq) {
        this.diff = diff;
        this.freq = freq;
    }

    public Combo(Double diff, Movie movie) {
        this.diff = diff;
        this.movie = movie;
    }

    public Combo(Long freq, Movie movie) {
        this.freq = freq;
        this.movie = movie;
    }

    public Combo(Double diff, Long freq, Movie movie) {
        this.diff = diff;
        this.freq = freq;
        this.movie = movie;
    }

    public Long getFreq() {
        return freq;
    }

    public void setFreq(Long freq) {
        this.freq = freq;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
