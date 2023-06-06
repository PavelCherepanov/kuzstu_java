package ru.kuzstu.springRESTful.dao;

import ru.kuzstu.springRESTful.models.User;

import java.util.List;

public interface UserDAO {
    List<User> list();
    void create(User user);
    User get(int id);
    void update(User user, int id);
    void delete(int id);
}
