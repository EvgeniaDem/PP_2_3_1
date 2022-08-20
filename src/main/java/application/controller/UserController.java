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

    @Autowired
    private final UserDao userDao;


    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")                                // автоматом при наборе /users будем пападать в этот метод
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

    @GetMapping("/new")
    // по запросу "/new" в браузер вернется форма для создания нового юзера
    public String newUser(Model model) {
        System.out.println("check");
        model.addAttribute("user", new User());
        return "users/new";                                          // возвращаем название Thymeleaf-шаблона, где у нас будет лежать форма для создания нового юзера
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        try {
            userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/users/users";                                      // указываем адрес, на который мы хотим перенаправить пользоватея
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("user", userDao.show(id));   // мы получаем человека по id, кладем его в Модель и к этой Модели мы будем иметь доступ в нашем View
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDao.update(id, user);
        return "redirect:/users/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println("deleteFirst");
        userDao.delete(id);
        System.out.println("deleteSecond");
        return "redirect:/users/users";
    }
}
