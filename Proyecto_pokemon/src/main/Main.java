package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/proyecto_pokemon";
        String usuario = "root";
        String contraseña = "";
//ab
        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            System.out.println("!Conexión exitosa!");

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
	}

}
