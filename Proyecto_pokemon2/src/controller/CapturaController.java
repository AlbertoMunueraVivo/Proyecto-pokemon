package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import application.Pokemon; // Importa la clase Pokemon

public class CapturaController {

    private Pokemon pokemon; // Añade un campo para el objeto Pokemon

    public CapturaController() {
        pokemon = new Pokemon(); // Instancia el objeto Pokemon
    }

    @FXML
    private Button btnCapturar;

    @FXML
    private Button btnTipoPokebola;

    @FXML
    private Button btnGenerarPK;
    
    @FXML
    private ImageView flechaAtrasImageView;
    
    @FXML
    private ImageView imgPokemon;


    // Método para manejar el evento del botón Capturar
    @FXML
    private void handleCapturar(ActionEvent event) {
        System.out.println("Guardando Pokémon en la base de datos...");
        pokemon.guardarEnBaseDatos(); // Guarda el Pokémon en la base de datos
    }

    // Método para manejar el evento del botón Generar PK
    @FXML
    private void handleGenerarPK(ActionEvent event) {
    	System.out.println("Cargando datos del Pokémon...");
        int maxId = pokemon.obtenerMaxId();  // Obtiene el máximo ID de la base de datos
        if (maxId > 0) {
            int idAleatorio = (int) (Math.random() * maxId) + 1;
            pokemon.cargarDatos(idAleatorio); // Carga datos de un Pokémon aleatorio
            
            String ruta = "./sources/sprites/"+pokemon.getRutaImagen();
            imgPokemon.setImage(new Image(new File(ruta).toURI().toString()));
            System.out.println("Datos cargados para el Pokémon con ID: " + idAleatorio);
        } else {
            System.out.println("No hay Pokémon disponibles para cargar.");
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

    // Implementa el método para manejar TipoPokebola si es necesario
    @FXML
    private void handleTipoPokebola(ActionEvent event) {
        System.out.println("Seleccionar Tipo de Pokebola!");
    }
}