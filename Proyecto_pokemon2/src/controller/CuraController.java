package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import application.EquipoPokemon;

public class CuraController {


    @FXML
    private ImageView imagen1;

    public void initialize() {
        EquipoPokemon.Pokemon.actualizarVitalidadActualDelEquipo();
        System.out.println("Vitalidad de los Pokémon actualizada.");

        // Crear un hilo separado para pausar sin bloquear el hilo de la UI
        new Thread(() -> {
            try {
            	playSound(".\\sources\\hormigusica.wav");
                Thread.sleep(10000); // Pausa de 10 segundos
                try {
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CentroPokemon.fxml"));
        			Parent root = loader.load();
        			Scene scene = imagen1.getScene(); // Obtener la escena actual del botón
        			scene.setRoot(root); // Establecer la nueva raíz de la escena
        			System.out.println("Viva Pablo Motos");
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    event.getLine().close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
