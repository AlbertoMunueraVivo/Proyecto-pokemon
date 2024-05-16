package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import application.Pokemon; // Importa la clase Pokemon
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapturaController {

    private static final Logger logger = LoggerFactory.getLogger(CapturaController.class);

    private Pokemon pokemon; // Añade un campo para el objeto Pokemon

    public CapturaController() {
        pokemon = new Pokemon(); // Instancia el objeto Pokemon
    }

    @FXML
    private Button btnCapturar;

    @FXML
    private ImageView escape;

    @FXML
    private Button btnTipoPokebola;

    @FXML
    private Button btnGenerarPK;

    @FXML
    private ImageView flechaAtrasImageView;

    @FXML
    private ImageView imgPokemon;

    @FXML
    private ImageView imagenMote;

    @FXML
    private Button btnHecho;

    @FXML
    private TextField texto;

    // Método para manejar el evento del botón Capturar
    @FXML
    private void handleCapturar(ActionEvent event) {
        // Probabilidad de 2/3 de capturar el Pokémon
        Random random = new Random();
        int randomValue = random.nextInt(3); // Genera un número aleatorio entre 0 y 2
        if (randomValue != 0) { // Si el número no es 0, se captura el Pokémon
            logger.info("Pokémon capturado exitosamente.");
            imagenMote.setVisible(true);
            texto.setVisible(true);
            btnHecho.setVisible(true);
        } else {
            logger.info("El Pokémon se escapó.");
            escape.setVisible(true);
            logger.debug("Cargando datos del Pokémon...");
            btnCapturar.setVisible(true);
            int maxId = pokemon.obtenerMaxId(); // Obtiene el máximo ID de la base de datos
            if (maxId > 0) {
                int idAleatorio = (int) (Math.random() * maxId) + 1;
                pokemon.cargarDatos(idAleatorio); // Carga datos de un Pokémon aleatorio

                String ruta = "./sources/sprites/" + pokemon.getRutaImagen();
                imgPokemon.setImage(new Image(new File(ruta).toURI().toString()));
                logger.debug("Datos cargados para el Pokémon con ID: {}", idAleatorio);
            } else {
                logger.warn("No hay Pokémon disponibles para cargar.");
            }
        }
    }

    // Método para manejar el evento del botón Generar PK
    @FXML
    private void handleGenerarPK(ActionEvent event) {
        logger.debug("Cargando datos del Pokémon...");
        btnCapturar.setVisible(true);
        escape.setVisible(false);

        logger.debug("Todo invisible");
        int maxId = pokemon.obtenerMaxId(); // Obtiene el máximo ID de la base de datos
        if (maxId > 0) {
            int idAleatorio = (int) (Math.random() * maxId) + 1;
            pokemon.cargarDatos(idAleatorio); // Carga datos de un Pokémon aleatorio

            String ruta = "./sources/sprites/" + pokemon.getRutaImagen() + "Front.png";
            imgPokemon.setImage(new Image(new File(ruta).toURI().toString()));
            logger.debug("Datos cargados para el Pokémon con ID: {}", idAleatorio);
        } else {
            logger.warn("No hay Pokémon disponibles para cargar.");
        }
    }

    @FXML
    private void handleFlechaAtrasClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            Parent root = loader.load();
            Scene scene = flechaAtrasImageView.getScene(); // Obtener la escena actual del botón
            scene.setRoot(root); // Establecer la nueva raíz de la escena
            logger.info("La imagen de la flecha hacia atrás fue clicada.");
        } catch (IOException e) {
            logger.error("Error al cargar el menú.", e);
        }
    }

    @FXML
    private void handleHecho(ActionEvent event) {
        logger.info("Botón 'Hecho' presionado.");
        String mote = texto.getText();
        pokemon.setMote(mote);
        pokemon.guardarEnBaseDatos();
        logger.info("Pokémon guardado con mote: {}", mote);
        imagenMote.setVisible(false);
        texto.setVisible(false);
        btnHecho.setVisible(false);
    }

    // Implementa el método para manejar TipoPokebola si es necesario
    @FXML
    private void handleTipoPokebola(ActionEvent event) {
        logger.info("Seleccionar Tipo de Pokebola!");
    }
}
