package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.config.DatabaseConfig;
import com.example.entities.User;

public class UserDAO {

    // public User getAllUsers(int id) throws SQLException{
    //     Connection connection = DatabaseConfig.getConnection();
    //     String sql = "SELECT * FROM Users WHERE id = ?";
    //     PreparedStatement statement = connection.prepareStatement(sql);
    //     statement.setInt(1, id);
    //     ResultSet resultSet = statement.executeQuery();

    //     if (resultSet.next()) {
    //         String name = resultSet.getString("name");
    //         int age = resultSet.getInt("age");
    //         return new User(id, name, age);
    //     }
    //     return null;   
    // }
    public User getUser(int id) {
        String query = "SELECT * FROM persons WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
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
}