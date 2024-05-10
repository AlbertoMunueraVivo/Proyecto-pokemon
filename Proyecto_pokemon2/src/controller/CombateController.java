package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.List;

import application.CombatePokemon;
import application.EquipoPokemon;

public class CombateController {
    @FXML
    private ProgressBar healthBarEnemy, healthBarPlayer;
    @FXML
    private ImageView pokemonImageView, myPokemonImageView;
    @FXML
    private ImageView flechaAtrasImageView;
    @FXML
    private Text textEnemigo,vitalidadMax, vitalidadAct, textAliado;

    private CombatePokemon combate;

    @FXML
    public void initialize() {
        combate = new CombatePokemon(); // Inicializa el combate
        cargarPokemonInicial();
        cargarEnemigoInicial();
    }

    private void cargarPokemonInicial() {
        List<EquipoPokemon.Pokemon> equipo = EquipoPokemon.Pokemon.recuperarEquipo();
        if (!equipo.isEmpty()) {
            EquipoPokemon.Pokemon miPrimerPokemon = equipo.get(0);
            System.out.println(miPrimerPokemon);
            actualizarImagenMiPokemon(miPrimerPokemon);
            actualizarNombreAliado(miPrimerPokemon);
            updateHealthBarPlayer(miPrimerPokemon);
            actualizarVitalidadTextos(miPrimerPokemon);
        }
    }

    private void cargarEnemigoInicial() {
        application.Pokemon enemigo = combate.getCurrentEnemy();
        if (enemigo != null) {
            actualizarImagenPokemonEnemigo(enemigo);
            actualizarNombreEnemigo(enemigo);
            updateHealthBarEnemy();
        }
    }

    public void actualizarImagenMiPokemon(EquipoPokemon.Pokemon miPokemon) {
        if (miPokemon != null && miPokemon.getRutaImagen() != null) {
            String ruta = "./sources/sprites/" + miPokemon.getRutaImagen() + "Back.png";
            cargarImagen(myPokemonImageView, ruta);
        }
    }

    public void actualizarImagenPokemonEnemigo(application.Pokemon enemigo) {
        if (enemigo != null && enemigo.getRutaImagen() != null) {
            String ruta = "./sources/sprites/" + enemigo.getRutaImagen() + "Front.png";
            cargarImagen(pokemonImageView, ruta);
        }
    }

    private void cargarImagen(ImageView imageView, String ruta) {
        File archivoImagen = new File(ruta);
        if (archivoImagen.exists()) {
            imageView.setImage(new Image(archivoImagen.toURI().toString()));
        } else {
            System.out.println("El archivo de imagen no existe: " + ruta);
        }
    }

    public void updateHealthBarPlayer(EquipoPokemon.Pokemon miPokemon) {
        if (miPokemon != null && miPokemon.getVitalidad() > 0) {
            double healthProgress = (double) miPokemon.getVitalidadActual() / miPokemon.getVitalidad();
            System.out.println(miPokemon.getVitalidadActual());
            System.out.println(miPokemon.getVitalidad());
            healthBarPlayer.setProgress(healthProgress);
            actualizarVitalidadTextos(miPokemon);
        } else {
            healthBarPlayer.setProgress(0);  // No hay Pokémon o los datos de salud son incorrectos
        }
    }

    public void updateHealthBarEnemy() {
        application.Pokemon enemy = combate.getCurrentEnemy();
        if (enemy != null && enemy.getVitalidadMaxima() > 0) {
            double healthProgress = (double) enemy.getVitalidadActual() / enemy.getVitalidadMaxima();
            healthBarEnemy.setProgress(healthProgress);
        } else {
            healthBarEnemy.setProgress(0);  // No hay enemigo o el enemigo fue derrotado
        }
    }
    
    private void actualizarVitalidadTextos(EquipoPokemon.Pokemon miPokemon) {
        if (miPokemon != null) {
            vitalidadAct.setText(String.valueOf(miPokemon.getVitalidadActual()));
            vitalidadMax.setText(String.valueOf(miPokemon.getVitalidad()));
        } else {
            vitalidadAct.setText("0");
            vitalidadMax.setText("0");
        }
    }
    
    private void actualizarNombreEnemigo(application.Pokemon enemigo) {
        if (enemigo != null) {
            textEnemigo.setText(enemigo.getNombre());
        } else {
            textEnemigo.setText("Sin enemigo");
        }
    }

    private void actualizarNombreAliado(EquipoPokemon.Pokemon aliado) {
        if (aliado != null) {
            textAliado.setText(aliado.getNombre());
        } else {
            textAliado.setText("Sin aliado");
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
