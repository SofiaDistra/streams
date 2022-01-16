package recommender.movie;

import common.User;
import java.util.*;
import java.util.function.Function;

public class UserMovieMapper implements Function<User, User> {

    List<User> users = new ArrayList<>();

    @Override
    public User apply(User user) {
        if(users.contains(user)) {
            User old = users.get(users.indexOf(user));
            old.getRatings().putAll(user.getRatings());
            return old;
        } else {
            users.add(user);
            return user;
        }
    }
}
