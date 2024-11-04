package com.example.services;
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
}
