package application.service;

import application.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> index() throws SQLException;

    User show (Long id) throws SQLException;

    public void save (User user) throws SQLException;

    public void update (Long id, User updatedUser);

    public void delete(Long id);
}
