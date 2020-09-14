package ru.nsu.database;

import java.sql.Connection;

public interface DataBaseConnection {
    Connection getConnection();
}
