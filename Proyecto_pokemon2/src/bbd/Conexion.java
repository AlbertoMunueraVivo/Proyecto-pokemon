package bbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Método para establecer la conexión a la base de datos
    public static Connection conexionBbd() {
        // Datos de la conexión a BBD
        String url = "jdbc:mysql://localhost:3306/proyecto_pokemon";
        String usuario = "root";
        String contraseña = "";

        Connection conexion = null;
        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("¡Conexión exitosa!");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
        return conexion;
    }
}