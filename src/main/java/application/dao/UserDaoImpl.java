package application.dao;

import application.model.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
public class UserDaoImpl implements UserDao {
    private static Long PEOPLE_COUNT;
    private List<User> users;           // пока работаю со списком по видео-уроку, потом будет БД

    {
        users = new ArrayList<>();

        users.add(new User(++PEOPLE_COUNT, "Tom", 56));
        users.add(new User(++PEOPLE_COUNT, "Ann", 24));
        users.add(new User(++PEOPLE_COUNT, "Peter", 74));
        users.add(new User(++PEOPLE_COUNT, "Kate", 16));
    }

    @Override
    public List<User> index() {
        return users;
    }

    @Override
    public User show(Long id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
