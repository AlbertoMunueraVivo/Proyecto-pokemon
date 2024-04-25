package controller;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class InicioController {

    @FXML
    private Button boton;

    @FXML
    private void handleBoton(ActionEvent event) {
        // Cargar la pantalla "Login"
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = boton.getScene(); // Obtener la escena actual del botón
            scene.setRoot(root); // Establecer la nueva raíz de la escena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}