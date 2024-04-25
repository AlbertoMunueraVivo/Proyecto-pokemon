package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import bbd.Conexion;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Conexion.conexionBbd();
		System.out.println("A");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        
	        primaryStage.setTitle("Proyecto Pokémon");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		  
		  
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
