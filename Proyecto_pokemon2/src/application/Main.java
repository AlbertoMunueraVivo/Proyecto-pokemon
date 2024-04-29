package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import bbd.Conexion;
import controller.InicioController;
import java.sql.Connection;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		    // Establecer la conexión a la base de datos
		    Connection conexion = Conexion.conexionBbd();	  
		try {

			// Cargar el archivo FXML principal
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
			Parent root = loader.load();
			InicioController controller = loader.getController();
			Scene scene = new Scene(root);

			// Asignar la escena al escenario (ventana principal)
			primaryStage.setScene(scene);
			primaryStage.setTitle("Proyecto Pokemon"); // Título de la ventana
			primaryStage.show(); // Mostrar la ventana

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}