package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {

    private String id;
    private String title;
    private List<String> genres;

    public Movie(String id) {
        this.id = id;
        this.genres = new ArrayList<>();
    }

    public Movie() {
        this.genres = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void addGenre(String genre) {
        this.genres.add(genre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
