package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import bbd.Conexion;
import controller.InicioController;


public class Main extends Application {	
		
	    @Override
	    public void start(Stage primaryStage) {
	    	Conexion.conexionBbd();
	        try {
	        
	            // Cargar el archivo FXML principal
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));
	            Parent root = loader.load();

	            // Obtener el controlador asociado al archivo FXML
	            InicioController controller = loader.getController();

	            // Configurar la escena con el nodo raíz del archivo FXML
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