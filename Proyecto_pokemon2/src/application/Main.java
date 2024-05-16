package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import bbd.Conexion;
import controller.InicioController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private List<Stage> stages = new ArrayList<>(); // Lista para almacenar las instancias de Stage
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static PrintStream logStream;

    @Override
    public void start(Stage primaryStage) {
        // Establecer la conexión a la base de datos
        Connection conexion = Conexion.conexionBbd();

        try {
            // Crear el archivo de logs
            File logFile = new File("src/logs/app.log");
            logFile.getParentFile().mkdirs(); // Crear directorio si no existe
            logStream = new PrintStream(new FileOutputStream(logFile, true), true);

            // Redirigir SLF4J logs a este archivo
            System.setOut(logStream);
            System.setErr(logStream);
            logger.info("Aplicación iniciada correctamente");

            for (Stage stage : stages) {
                stage.close();
            }
            stages.clear(); // Limpiar la lista

            // Cargar el archivo FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
            Parent root = loader.load();

            // Configurar el controlador con el Stage
            InicioController controller = loader.getController();
            controller.setStage(primaryStage); // Asumiendo que existe tal método en InicioController

            // Asignar la escena al escenario (ventana principal)
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Proyecto Pokemon"); // Título de la ventana
            primaryStage.show(); // Mostrar la ventana

            // Agregar el Stage actual a la lista
            stages.add(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error durante la inicialización de la aplicación: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("A");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (logStream != null) {
            logStream.close();
        }
    }
}
