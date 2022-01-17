package recommender.movie;

import common.Movie;
import common.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides a single static method to
 * read data from a file and load them to a list of users
 */
public class MovieDataLoader {


    /**
     * Reads data from a text file and loads them to a list
     * of users with movie ratings
     *
     * @param path the path to the file containing the data
     * @return a list of users with their ratings for each movie item
     */
    public static List<User> loadUserRatings(Path path) {

        List<User> users;

        try {
             users = Files.lines(path)
                            .skip(1) // skip the header line
                            .map(line -> line.split(",")) // transform each line to an array
                            .map(splitted -> {
                                User u = new User(splitted[0]);
                                Movie m = new Movie(splitted[1]);
                                // TODO try catch for parsing
                                Double rating = Double.parseDouble(splitted[2]);
                                u.addRating(m,rating);
                                return u;
                            }) // transform each array to an entity
                     .collect(Collectors.toList())
                     .stream()
                     .map(new UserMovieMapper())
                     .distinct()
                     .collect(Collectors.toList());

        } catch (IOException x) {
            throw new UncheckedIOException(x);
        }

        return users;
    }

    public static List<Movie> loadMovies(Path path) {
        List<Movie> movies;

        try {
            movies = Files.lines(path)
                    .skip(1) // skip the header line
                    .map(new MovieMapper()) // transform each line to an array
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            movies = new ArrayList<>();
        }

        return movies;
    }
}
