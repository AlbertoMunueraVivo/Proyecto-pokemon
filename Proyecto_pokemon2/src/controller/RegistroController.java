package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrateButton;

    @FXML
    private void handleRegistro(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Insertar los datos del usuario en la base de datos
        if (insertUser(username, password)) {
            System.out.println("Usuario registrado exitosamente.");
            try {
        		System.out.println("¡Usuario autenticado!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
                Parent root = loader.load();
                Scene scene = registrateButton.getScene(); // Obtener la escena actual del botón
                scene.setRoot(root); // Establecer la nueva raíz de la escena
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error al registrar el usuario.");
        }
    }

    private boolean insertUser(String username, String password) {
        String query = "INSERT INTO usuarios (id_usuario, nombre_usuario, contraseña_usuario) VALUES (?, ?, ?)";
        String query2 = "SELECT MAX(id_usuario) AS id FROM usuarios";
        int id = 0;
     
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_pokemon", "root", "");
        	PreparedStatement tatement = connection.prepareStatement(query2);
        	ResultSet rs = tatement.executeQuery(query2);
        	PreparedStatement statement = connection.prepareStatement(query)) {
        	
        	if (rs.next()) {
                id = rs.getInt("id") + 1;
            }
        	
        	statement.setInt(1, rs.getInt("id") + 1);
            statement.setString(2, username);
            statement.setString(3, password);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
