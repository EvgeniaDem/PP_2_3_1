package application.dao;

import application.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {                                     //вытаскиванием пользователя по id
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User updateUserById(Long id, User updatedUser) {
        return entityManager.merge(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }
}
