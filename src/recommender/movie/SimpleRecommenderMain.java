package recommender.movie;

import common.Movie;
import common.User;

import java.nio.file.Path;
import java.util.List;

public class SimpleRecommenderMain {

    public static void main(String[] args) {

        List<User> users = MovieDataLoader.loadUserRatings(Path.of("resources/ratings.csv"));
        List<Movie> movies = MovieDataLoader.loadMovies(Path.of("resources/movies.csv"));

        if(args.length != 2) {
            System.out.println("Usage: <user_id> <movie_id>");
            return;
        }

        // the user u and the movie m for which we calculate the predicted rating
        User u = new User(args[0]);
        if(users.contains(u)) u = users.get(users.indexOf(u));
        else {
            System.out.println("No User with id " + u.getId() + " exists");
            return;
        }

        Movie m = new Movie(args[1]);
        if(!movies.contains(m)) {
            System.out.println("Invalid movie id. Exiting...");
            return;
        }

        SimpleRecommender sr = new SimpleRecommender(movies, u, m, users);
        sr.predict();

    }

}
