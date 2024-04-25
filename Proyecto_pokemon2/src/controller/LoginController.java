package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    @FXML
    private ImageView errorImage;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validar el usuario y contraseña en la base de datos
        if (authenticateUser(username, password)) {
            try {
                System.out.println("¡Usuario autenticado!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
                Parent root = loader.load();
                Scene scene = loginButton.getScene(); // Obtener la escena actual del botón
                scene.setRoot(root); // Establecer la nueva raíz de la escena
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
            errorImage.setVisible(true); // Mostrar la imagen de error
        }
    }
    
    private boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña_usuario = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_pokemon", "root", "");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Devuelve true si se encuentra una coincidencia
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        @FXML
        private Button registrarseButton;

        @FXML
        private void handleRegistrarse(ActionEvent event) {

            	try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registro.fxml"));
                    Parent root = loader.load();
                    Scene scene = registrarseButton.getScene(); // Obtener la escena actual del botón
                    scene.setRoot(root); // Establecer la nueva raíz de la escena
                } catch (IOException e) {
                    e.printStackTrace();
                }
}
}        