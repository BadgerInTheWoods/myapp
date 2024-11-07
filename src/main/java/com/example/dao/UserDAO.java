package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.config.DatabaseConfig;
import com.example.entities.User;
import com.example.interfaces.DAOinterface;

public class UserDAO implements DAOinterface {

    public UserDAO() {
        DatabaseConfig.checkAndCreateTable();
    }

    @Override
    public User getUser(int id) {
        String query = "SELECT * FROM public.Users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User User) {
        String query = "INSERT INTO public.Users (name, dateofbirth, gender) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, User.getName());
            stmt.setDate(2, java.sql.Date.valueOf(User.getDateOfBirth()));
            stmt.setString(3, User.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<>();
        String query = "SELECT * FROM public.Users ORDER BY name;";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getDate("dateofbirth").toLocalDate(), rs.getString("gender")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }

    @Override
    public void updateUser(User User) {
        String query = "UPDATE public.Users SET name = ?, dateofbirth = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, User.getName());
            stmt.setDate(2, java.sql.Date.valueOf(User.getDateOfBirth()));
            stmt.setInt(3, User.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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
