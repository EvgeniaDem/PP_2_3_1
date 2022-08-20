package application.controller;

import application.dao.UserDao;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("user", userDao.show(id));   // "user" - это ключ
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/show";
    }

    @GetMapping("/new")                                           // по запросу "/new" в браузер вернется форма для создания нового юзера
    public String newUser(@ModelAttribute("user") User user) {       // используем Get-запрос для получения новой формы
        return "users/new";                                          // возвращаем название Thymeleaf-шаблона, где у нас будет лежать форма для создания нового юзера
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {       // этим методом будем передавать POST-запрос
        try {
            userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/users";                                      // указываем адрес, на который мы хотим перенаправить пользоватея
    }
}
