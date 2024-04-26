package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import bbd.Conexion;
import controller.InicioController;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// metodo de conexion base de datos
		Conexion.conexionBbd();
		try {

			// Cargar el archivo FXML principal
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
			Parent root = loader.load();
			InicioController controller = loader.getController();
			Scene scene = new Scene(root);

			// Asignar la escena al escenario (ventana principal)
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mi Aplicación"); // Título de la ventana
			primaryStage.show(); // Mostrar la ventana

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}