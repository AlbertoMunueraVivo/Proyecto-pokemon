package main;

import bbd.Conexion;
import controller.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	  @Override
	    public void start(Stage primaryStage) throws Exception{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto_pokemon/fxml/Inicio.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        
	        primaryStage.setTitle("Proyecto Pokémon");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		  
		  
	    }


	    public static void main(String[] args) {
	        launch(args);
	    }
	
	

}

/*Conexion.conexionBbd();
System.out.println("A");*/