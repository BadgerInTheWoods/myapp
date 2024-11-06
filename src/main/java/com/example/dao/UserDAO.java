package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.config.DatabaseConfig;
import com.example.entities.User;

public class UserDAO {

    public User getUser(int id) {
        String query = "SELECT * FROM public.Users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("age"), rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User User) {
        String query = "INSERT INTO public.Users (name, age, gender) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, User.getName());
            stmt.setString(2, User.getAge());
            stmt.setString(3, User.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<>();
        String query = "SELECT * FROM public.Users";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("age"), rs.getString("gender")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }

    public void updateUser(User User) {
        String query = "UPDATE public.Users SET name = ?, age = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, User.getName());
            stmt.setString(2, User.getAge());
            stmt.setInt(3, User.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
