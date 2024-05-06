package controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import application.EquipoPokemon;

public class MenuController {

    @FXML
    private Button combateButton;

    @FXML
    private Button equipoButton;

    @FXML
    private Button entrenamientoButton;

    @FXML
    private Button capturaButton;

    @FXML
    private Button centroPochiponButton;

    @FXML
    private Button crianzaButton;
    
    @FXML
    private ImageView flechaAtrasImageView;

    @FXML
	private void handleBoton(ActionEvent event) {
		// Cargar la pantalla "Login"
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Combate.fxml"));
			Parent root = loader.load();
			Scene scene = combateButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
	private void handleBoton2(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EquipoPokemon.fxml"));
			Parent root = loader.load();
			Scene scene = equipoButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
			EquipoPokemon equipoPokemon = new EquipoPokemon();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
	private void handleBoton3(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
			Parent root = loader.load();
			Scene scene = entrenamientoButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
	private void handleBoton4(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CapturaPokemon.fxml"));
			Parent root = loader.load();
			Scene scene = capturaButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
	private void handleBoton5(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CentroPokemon.fxml"));
			Parent root = loader.load();
			Scene scene = centroPochiponButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
	private void handleBoton6(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Crianza.fxml"));
			Parent root = loader.load();
			Scene scene = crianzaButton.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @FXML
    private void handleFlechaAtrasClicked(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
			Parent root = loader.load();
			Scene scene = flechaAtrasImageView.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("La imagen de la flecha hacia atrás fue clicada.");
    }
}
