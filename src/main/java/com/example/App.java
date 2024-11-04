package com.example;

import com.example.dao.UserDAO;
import com.example.services.UserService;
import com.example.ui.UserInterface;

public class App {
    public static void main(String[] args) {
        UserDAO UserDAO = new UserDAO();
        UserService UserService = new UserService(UserDAO);
        UserInterface ui = new UserInterface(UserService);

        // Запуск интерфейса пользователя
        ui.start();
    }
}
