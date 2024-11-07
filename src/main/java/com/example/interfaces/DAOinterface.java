package com.example.interfaces;

import java.util.List;

import com.example.entities.User;

public interface DAOinterface {

    void addUser(User User);

    User getUser(int id);

    List<User> getAllUsers();

    void updateUser(User User);

    void deleteUser(int id);
}
