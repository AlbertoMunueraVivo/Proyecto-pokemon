package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class PasilloCController {
	
	@FXML
    private Button curaBoton;
	
	@FXML
	private void handleBoton(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cura.fxml"));
			Parent root = loader.load();
			Scene scene = curaBoton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



