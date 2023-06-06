package ru.kuzstu.springRESTful.controllers;

import ru.kuzstu.springRESTful.dao.UserDAO;
import ru.kuzstu.springRESTful.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserDAO dao;

    @Autowired
    public UserController(UserDAO dao){
        this.dao = dao;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<String> check(){
        return new ResponseEntity<>("Страница не найдена :(", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<User> getAllUsers(){
        List<User> users = dao.list();
        return users;
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") int userId){
        User user = dao.get(userId);
        return user;
    }

    @PostMapping(path = "/registration")
    public User registration(@RequestBody User user){
        user.setRegistrationDateTime(LocalDateTime.now());
        dao.create(user);
        return user;
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        dao.delete(userId);
    }

    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable("userId") int userId,
                                 @RequestBody User user){
        dao.update(user, userId);
        return user;
    }
}
