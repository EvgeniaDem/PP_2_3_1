package application.dao;

import application.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> index() throws SQLException;

    User show (Long id) throws SQLException;
}
