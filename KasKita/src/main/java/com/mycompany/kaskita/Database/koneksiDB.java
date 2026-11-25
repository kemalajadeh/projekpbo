package com.mycompany.kaskita.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksiDB {
    private static final String URL = "jdbc:mysql://localhost:3306/db_kaskita";
    private static final String USER = "root";
    private static final String PASS = "lerd";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
