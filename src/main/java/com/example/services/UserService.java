package com.example.services;

import java.time.LocalDate;
import java.util.List;

import com.example.dao.UserDAO;
import com.example.entities.User;

public class UserService {

    private final UserDAO UserDAO;

    public UserService(UserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }

    public User getUser(int id) {
        return UserDAO.getUser(id);
    }

    public void createNewUser(String name, LocalDate dateofbirth, String gender) {
        User User = new User(0, name, dateofbirth, gender); // id = 0 (значение неважно, автоинкремент в БД)
        UserDAO.addUser(User);
    }

    public List<User> getAllUsers() {
        return UserDAO.getAllUsers();
    }

    public void updateUser(User user) {
        UserDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        UserDAO.deleteUser(id);
    }
}
