package com.msara.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection getConexion(){

        String url = "jdbc:sqlserver://localhost:1433;" +
                "database=bd1;" +
                "user=sa;" +
                "password=12345678;";
        try {
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar con la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
