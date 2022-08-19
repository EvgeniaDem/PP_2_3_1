package application.controller;

import application.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/users")                       // все адреса в контроллере будут начинаться со /users
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()                                // автоматом при наборе /users будем пападать в этот метод
    public String index(Model model) {             // Model передаем, чтобы Spring Framework внедрил эту модель со списком
        try {
            model.addAttribute("users", userDao.index());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userDao.show(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/show";
    }
}
