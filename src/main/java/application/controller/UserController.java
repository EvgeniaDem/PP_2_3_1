package application.controller;

import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/users")                                  // связывает url-адрес со всеми методами класса Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")                                     // это часть url, автоматом при наборе /users будем пападать в этот метод
    public String getAllUsers(Model model) {                     // Model передаем, чтобы Spring Framework внедрил эту модель со списком
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) throws SQLException {    // показывает юзера по id
        model.addAttribute("user", userService.getUserById(id));                      // "user" - это ключ
        return "users/show";
    }

    @GetMapping("/new")
    public String createNewUserForm(Model model) {                  // по запросу "/new" в браузер вернется форма для создания нового юзера
        model.addAttribute("user", new User());
        return "users/new";                                         // возвращаем название Thymeleaf-шаблона, где у нас будет лежать форма для создания нового юзера
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) throws SQLException {
            userService.saveUser(user);
        return "redirect:/users/users";                                // указываем адрес, на который мы хотим перенаправить пользоватея
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") Long id) throws SQLException {
            model.addAttribute("user", userService.getUserById(id));   // мы получаем человека по id, кладем его в Модель и к этой Модели мы будем иметь доступ в нашем View
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String editUserById(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/users/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users/users";
    }
}
