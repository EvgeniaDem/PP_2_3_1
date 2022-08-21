package application.service;

import application.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id) throws SQLException;

    void save(User user) throws SQLException;

    User update(Long id, User updatedUser);

    void delete(Long id);
}
