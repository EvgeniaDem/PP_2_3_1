package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")                       // все адреса в контроллере будут начинаться со /users
public class UserController {

    // получим всем юзеров из DAO и далее передадим на View
    @GetMapping()                               // автоматом при наборе /users будем пападать в этот метод
    public String index(Model model) {             // Model передаем, чтобы Spring Framework внедрил эту модель со списком
        return null;                               // пока null, т.к. пока нет DAO класса
    }

// получение юзера по его id из DAO и далее передадим на View
    @GetMapping("/{id}")                             // в браузере надо будет набирать /people/id, чтобы попать в метод
    public String show(@PathVariable("id") int id, Model model) {    // id - какой дадим на вход, того и вытащим из БЮ
        return null;
    }
}
