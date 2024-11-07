package com.example.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/myappDB";
    private static final String USER = "sqladmin";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void checkAndCreateTable() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            // Проверяем, существует ли таблица "users"
            ResultSet resultSet = dbMetaData.getTables(null, null, "users", null);

            if (!resultSet.next()) {
                // Таблица не существует, создаем её
                try (Statement statement = connection.createStatement()) {
                    String createTableSQL = "CREATE TABLE users (id SERIAL PRIMARY KEY,name VARCHAR(100),dateofbirth Date,gender VARCHAR(100));";
                    statement.executeUpdate(createTableSQL);
                    System.out.println("Table 'users' created successfully.");
                }
            } else {
                System.out.println("Table 'users' already exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
