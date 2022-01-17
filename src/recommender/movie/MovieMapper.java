package recommender.movie;

import common.Movie;

import java.util.function.Function;

public class MovieMapper implements Function<String, Movie> {

    @Override
    public Movie apply(String line) {
        Movie m = new Movie();

        String[] tokens = line.split(",");
        m.setId(tokens[0]);
        m.setTitle(tokens[1]);

        String[] genres = tokens[2].split("\\|");
        for(String g : genres) {
            m.addGenre(g);
        }

        return m;
    }
}
