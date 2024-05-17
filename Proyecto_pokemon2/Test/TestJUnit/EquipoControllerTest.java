package TestJUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import controller.EquipoController;
 
import static org.junit.jupiter.api.Assertions.*;
 
class EquipoControllerTest {
 
    private EquipoController equipoController;
    private TextField nombrePokemonField;
    private TextField listaPokemon;
 
    @BeforeEach
    void setUp() {
        equipoController = new EquipoController();
        nombrePokemonField = new TextField();
 
        // Use reflection to set the private fields
        setPrivateField(equipoController, "nombrePokemonField", nombrePokemonField);
        setPrivateField(equipoController, "listaPokemon", listaPokemon);
 
        equipoController.initialize();
    }
 
    private void setPrivateField(EquipoController equipoController2, String string, TextField nombrePokemonField2) {
		// TODO Auto-generated method stub
	}
 
	@Test
    void handleAddPokemonButtonAction_ValidInput() {
        nombrePokemonField.setText("Pikachu");
 
        String items = listaPokemon.getId();
    }
 
    @Test
    void handleAddPokemonButtonAction_EmptyInput() {
        nombrePokemonField.setText("");
 
        String items = listaPokemon.getId();
        assertTrue(items.isEmpty());
    }
 
        ObservableList<String> initialList = FXCollections.observableArrayList("Pikachu", "Charmander");
    }