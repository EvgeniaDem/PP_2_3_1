package application.service;

import application.dao.UserDao;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(Long id) throws SQLException {
        return userDao.getById(id);
    }

    @Override
    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public User update(Long id, User updatedUser) {
        return userDao.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}