package ru.nsu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionImpl implements DataBaseConnection {
    public DataBaseConnectionImpl() {
        connectToDataBase();
    }

    private Connection connection;

    private final String host = "127.0.0.1"; // сервер базы данных
    private final int port = 5432; // порт СУБД
    private final String databaseName = "TEST_database"; // база данных
    private final String username = "postgres"; // учетная запись пользователя
    private final String password = "123123"; // пароль пользователя

    @Override
    public Connection getConnection() {
        return connection;
    }

    //throws sql
    private void connectToDataBase() {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
        try {
            connection = DriverManager.getConnection(url, username, password);

            if (connection == null) System.out.println("Нет соединения с БД");
            else System.out.println("Соединение с БД установлено");

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
