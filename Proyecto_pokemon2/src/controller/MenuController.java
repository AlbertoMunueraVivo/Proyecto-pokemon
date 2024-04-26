package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

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
    private void initialize() {
        // Configuración de los eventos de los botones
        combateButton.setOnAction(event -> abrirVentana("/fxml/combate.fxml"));
        capturaButton.setOnAction(event -> abrirVentana("/fxml/CapturaPokemon.fxml"));
        centroPochiponButton.setOnAction(event -> abrirVentana("/fxml/CentroPokemon.fxml"));
        crianzaButton.setOnAction(event -> abrirVentana("/fxml/Crianza.fxml"));
    }

    private void abrirVentana(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) combateButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
