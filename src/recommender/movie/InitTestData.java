package recommender.movie;


import common.Movie;
import common.User;

import java.util.ArrayList;
import java.util.List;

public class InitTestData {

    public static List<User> initData() {
        User u0 = new User("u0");
        User u1 = new User("u1");
        User u2 = new User("u2");
        User u3 = new User("u3");

        Movie m0 = new Movie("Candy");
        Movie m1 = new Movie("Drink");
        Movie m2 = new Movie("Soda");
        Movie m3 = new Movie("Popcorn");
        Movie m4 = new Movie("Snacks");

        u0.addRating(m1,1.0);
        u0.addRating(m3,4.0);
        u0.addRating(m4,6.0);

        u1.addRating(m0,5.0);
        u1.addRating(m2,6.0);

        u2.addRating(m0,9.0);
        u2.addRating(m2,1.0);
        u2.addRating(m4,6.0);

        u3.addRating(m0,7.0);
        u3.addRating(m1,8.0);
        u3.addRating(m3,9.0);

        List<User> users = new ArrayList<>();
        users.add(u0);
        users.add(u1);
        users.add(u2);
        users.add(u3);

        return users;
    }

    public static List<Movie> getMovies() {
        Movie m0 = new Movie("Candy");
        Movie m1 = new Movie("Drink");
        Movie m2 = new Movie("Soda");
        Movie m3 = new Movie("Popcorn");
        Movie m4 = new Movie("Snacks");

        List<Movie> movies = new ArrayList<>();
        movies.add(m0);
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);

        return movies;
    }

    public static List<User> initData2() {
        User u1 = new User("u1");
        User u2 = new User("u2");
        User u3 = new User("u3");
        User u4 = new User("u4");
        User u5 = new User("u5");

        Movie m1 = new Movie("1001");
        Movie m2 = new Movie("1002");
        Movie m3 = new Movie("1003");
        Movie m4 = new Movie("1004");
        Movie m5 = new Movie("1005");
        Movie m6 = new Movie("1006");
        Movie m7 = new Movie("1007");

        u1.addRating(m1,5.0);
        u1.addRating(m2,3.0);
        u1.addRating(m3,2.5);

        u2.addRating(m1,2.0);
        u2.addRating(m2,2.5);
        u2.addRating(m3,5.0);
        u2.addRating(m4,2.0);

        u3.addRating(m1,2.0);
        u3.addRating(m4,4.0);
        u3.addRating(m5,4.5);
        u3.addRating(m7,5.0);

        u4.addRating(m1,5.0);
        u4.addRating(m3,3.0);
        u4.addRating(m4,4.5);
        u4.addRating(m6,4.0);

        u5.addRating(m1,4.0);
        u5.addRating(m2,3.0);
        u5.addRating(m3,2.0);
        u5.addRating(m4,4.0);
        u5.addRating(m5,3.5);
        u5.addRating(m6,4.0);


        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        return users;
    }

    public static List<Movie> getMovies2() {
        Movie m1 = new Movie("1001");
        Movie m2 = new Movie("1002");
        Movie m3 = new Movie("1003");
        Movie m4 = new Movie("1004");
        Movie m5 = new Movie("1005");
        Movie m6 = new Movie("1006");
        Movie m7 = new Movie("1007");

        List<Movie> movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);
        movies.add(m5);
        movies.add(m6);
        movies.add(m7);

        return movies;
    }
}
