package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CentroController {
	
	@FXML
    private Button curaButton;
	
	@FXML
    private Button tiendaButton;
	
	@FXML
	private ImageView flechaAtrasImageView;
	
	@FXML
	private void handleBoton(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PasilloCura.fxml"));
			Parent root = loader.load();
			Scene scene = curaButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void handleFlechaAtrasClicked(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
			Parent root = loader.load();
			Scene scene = flechaAtrasImageView.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("La imagen de la flecha hacia atrás fue clicada.");
	}
	
	

}
