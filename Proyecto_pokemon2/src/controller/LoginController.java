package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbd.SessionManager;
import bbd.User;
import bbd.UserDAO;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private ImageView errorImage;
	
	 private UserDAO userDao = new UserDAO();

	    @FXML
	    public void handleLogin(ActionEvent event) {
	        String username = usernameField.getText();
	        String password = passwordField.getText();

	        if (authenticateUser(username, password)) {
	            try {
	                User user = userDao.getUserByUsername(username);
	                if (user != null) {
	                    SessionManager.getInstance().setCurrentUser(user);
	                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
	                    Scene scene = loginButton.getScene();
	                    scene.setRoot(root);
	                } else {
	                    errorImage.setVisible(true);
	                    System.out.println("Usuario no encontrado.");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Nombre de usuario o contraseña incorrectos.");
	            errorImage.setVisible(true);
	        }
	    }

	private boolean authenticateUser(String username, String password) {
		String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña_usuario = ?";
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_pokemon", "root",
				""); PreparedStatement statement = connection.prepareStatement(query)) {
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