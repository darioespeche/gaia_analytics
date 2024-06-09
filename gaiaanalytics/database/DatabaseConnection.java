package com.gaiaanalytics.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL, usuario y contraseña para la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/gaia_analytics";
    private static final String USER = "root";
    private static final String PASSWORD = "Mate9324sach.";

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
