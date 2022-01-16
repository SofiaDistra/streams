package recommender.movie;

import common.Movie;
import common.User;
import java.util.*;

public class MovieRecommenderMain {

    public static void main(String[] args) {

        List<User> users = InitTestData.initData();

        // the user u and the movie m for which we calculate the predicted rating
        User u = users.get(2);
        Movie m = new Movie("Popcorn");

        SlopeOneRecommender so = new SlopeOneRecommender(u, m, users);
        so.predict();

    }
}
