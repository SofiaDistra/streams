package recommender;

import common.Movie;
import common.User;
import recommender.movie.ItemKNeighborsRecommender;
import recommender.movie.MovieDataLoader;
import recommender.movie.SimpleRecommender;
import recommender.sequential.ItemKNeighborsRecommenderSeq;
import recommender.sequential.SimpleRecommenderSeq;

import java.nio.file.Path;
import java.util.List;

public class ItemKNeighborsRecommenderTestTime {

    public static void main(String[] args) {
        List<User> users = MovieDataLoader.loadUserRatings(Path.of("resources/ratings.csv"));
        List<Movie> movies = MovieDataLoader.loadMovies(Path.of("resources/movies.csv"));

        int[][] toRate = {
                {1,2}, {1,4}, {1,5}, {1,7}, {1,8}, {1,9}, {1,10}, {1,11}, {1,12}, {1,13},
                {1,14}, {1,15}, {1,16}, {1,17}, {1,18}, {1,19}, {1,20}, {1,21}, {1,22}, {1,23},
                {1,24}, {1,25}, {1,26}, {1,27}, {1,28}, {1,29}, {1,30}, {1,31}, {1,32}, {1,34},
        };

        int k = 10;

        int rows = toRate.length;

        long seqTimeSum = 0;
        for(int i=0; i<rows; i++) {
            User u = new User(String.valueOf(toRate[i][0]));
            Movie m = new Movie(String.valueOf(toRate[i][1]));

            users = MovieDataLoader.loadUserRatings(Path.of("resources/ratings.csv"));
            if(users.contains(u)) u = users.get(users.indexOf(u));
            ItemKNeighborsRecommenderSeq knnSeq = new ItemKNeighborsRecommenderSeq(k, m, u, users, movies);
            seqTimeSum += knnSeq.predict();
        }

        long parTimeSum = 0;
        for(int i=0; i<rows; i++) {
            User u = new User(String.valueOf(toRate[i][0]));
            Movie m = new Movie(String.valueOf(toRate[i][1]));

            users = MovieDataLoader.loadUserRatings(Path.of("resources/ratings.csv"));
            if(users.contains(u)) u = users.get(users.indexOf(u));
            ItemKNeighborsRecommender knnPar = new ItemKNeighborsRecommender(k, m, u, users, movies);
            parTimeSum += knnPar.predict();
        }

        double seqAvg = seqTimeSum / (double) rows;
        System.out.println("Sequential calculation average time = " + seqAvg);

        double parAvg = parTimeSum / (double) rows;
        System.out.println("Parallel calculation average time = " + parAvg);
    }
}
