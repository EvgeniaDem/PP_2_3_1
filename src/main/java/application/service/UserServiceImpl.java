package application.service;

import application.dao.UserDao;
import application.dao.UserDaoImpl;
import application.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> index() throws SQLException {
        return userDao.index();
    }

    @Override
    public User show(Long id) throws SQLException {
        return userDao.show(id);
    }

    @Override
    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}