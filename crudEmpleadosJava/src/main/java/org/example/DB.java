package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:mariadb://localhost:3306/db_empleados";
    private static String user = "root";
    private static String password = "";
    private static Connection conn = null;

    public static synchronized Connection getConexion() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conectado a la base de datos");
                System.out.println("Catálogo: " + conn.getCatalog());
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos:");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:");
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
}