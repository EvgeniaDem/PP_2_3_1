package application.dao;

import application.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Repository
@Component
public class UserDaoImpl implements UserDao {
    private static Long PEOPLE_COUNT = 0L;
    private List<User> users;           // пока работаю со списком по видео-уроку, потом будет БД

    {
        users = new ArrayList<>();

        users.add(new User(++PEOPLE_COUNT, "Tom", "Taylor"));
        users.add(new User(++PEOPLE_COUNT, "Ann", "Hields"));
        users.add(new User(++PEOPLE_COUNT, "Peter", "Glover"));
        users.add(new User(++PEOPLE_COUNT, "Kate", "Harrison"));
    }

    @Override
    public List<User> index() {
        return users;
    }

    @Override
    public User show(Long id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(++PEOPLE_COUNT);
        users.add(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        User userToBeUpdated = show(id);              // по id находим человека. которого хотим update и помещаем его в переменную toBeUpdated
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
    }

    @Override
    public void delete(Long id) {
        users.removeIf(p -> p.getId() == id);     // если сравнение == true, то удаляем этого человека из списка
    }
}
