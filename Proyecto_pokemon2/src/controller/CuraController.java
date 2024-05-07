package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import application.EquipoPokemon;

public class CuraController {

    private Stage stage; // Agregar un campo para almacenar el Stage
    
    @FXML
    private ImageView imagen1;

    // Método para establecer el Stage desde fuera
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        EquipoPokemon.Pokemon.actualizarVitalidadActualDelEquipo();
        System.out.println("Vitalidad de los Pokémon actualizada.");

        // Crear un hilo separado para pausar sin bloquear el hilo de la UI
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Pausa de 10 segundos
                try {
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CentroPokemon.fxml"));
        			Parent root = loader.load();
        			Scene scene = imagen1.getScene(); // Obtener la escena actual del botón
        			scene.setRoot(root); // Establecer la nueva raíz de la escena
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
