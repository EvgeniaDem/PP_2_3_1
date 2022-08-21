package application.controller;

import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/users")                       // все адреса в контроллере будут начинаться со /users
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")                                // автоматом при наборе /users будем пападать в этот метод
    public String getAll(Model model) {                     // Model передаем, чтобы Spring Framework внедрил эту модель со списком
        model.addAttribute("users", userService.getAll());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.getById(id));       // "user" - это ключ
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/show";
    }

    @GetMapping("/new")                                           // по запросу "/new" в браузер вернется форма для создания нового юзера
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";                                          // возвращаем название Thymeleaf-шаблона, где у нас будет лежать форма для создания нового юзера
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        try {
            userService.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/users/users";                                      // указываем адрес, на который мы хотим перенаправить пользоватея
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("user", userService.getById(id));   // мы получаем человека по id, кладем его в Модель и к этой Модели мы будем иметь доступ в нашем View
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users/users";
    }
}
