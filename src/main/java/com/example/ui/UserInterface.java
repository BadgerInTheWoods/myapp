package com.example.ui;

import java.util.Scanner;

import com.example.entities.User;
import com.example.services.UserService;

public class UserInterface {

    private final UserService UserService;
    private final Scanner scanner;

    public UserInterface(UserService UserService) {
        this.UserService = UserService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the User Management System");

        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    getUser();
                    break;
                case "3":
                    updateUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "5":
                    listAllUsers();
                    break;
                case "0":
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a new User");
        System.out.println("2. Get User by ID");
        System.out.println("3. Update User information");
        System.out.println("4. Delete User by ID");
        System.out.println("5. List all Users");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void getUser() {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        User User = UserService.getUser(id);
        if (User != null) {
            System.out.println("User found: " + User);
        } else {
            System.out.println("User not found.");
        }
    }

    private void addUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        String age = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        UserService.createNewUser(name, age, gender);
        System.out.println("User added successfully!");
    }

    private void listAllUsers() {
        System.out.println("Listing all Users:");
        for (User User : UserService.getAllUsers()) {
            System.out.println(User);
        }
    }

    private void updateUser() {
        System.out.print("Enter User ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        User User = UserService.getUser(id);

        if (User != null) {
            System.out.print("Enter new name (current: " + User.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("Enter new age (current: " + User.getAge() + "): ");
            String age = scanner.nextLine();
            System.out.print("Enter new gender (current: " + User.getGender() + "): ");
            String gender = scanner.nextLine();

            User.setName(name);
            User.setAge(age);
            User.setGender(gender);
            UserService.updateUser(User);
            System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        UserService.deleteUser(id);
        System.out.println("User deleted successfully!");
    }
}
