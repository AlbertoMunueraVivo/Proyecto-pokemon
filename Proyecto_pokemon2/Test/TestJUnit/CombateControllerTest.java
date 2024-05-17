package TestJUnit;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CombateController;

import static org.junit.jupiter.api.Assertions.*;

class CombateControllerTest {

    private CombateController combateController;
    private TextField pokemon1Field;
    private TextField pokemon2Field;
    private Label resultadoLabel;

    @BeforeEach
    void setUp() {
        combateController = new CombateController();
        pokemon1Field = new TextField();
        pokemon2Field = new TextField();
        resultadoLabel = new Label();

        // Use reflection to set the private fields
        setPrivateField(combateController, "pokemon1Field", pokemon1Field);
        setPrivateField(combateController, "pokemon2Field", pokemon2Field);
        setPrivateField(combateController, "resultadoLabel", resultadoLabel);
    }

    @Test
    void handleCombatButtonAction_Empate() {
        pokemon1Field.setText("Pikachu");
        pokemon2Field.setText("Pikachu");

        assertEquals("Empate", resultadoLabel.getText());
    }

    @Test
    void handleCombatButtonAction_DifferentPokemon() {
        pokemon1Field.setText("Pikachu");
        pokemon2Field.setText("Charmander");

        String resultado = resultadoLabel.getText();
        assertTrue(resultado.equals("Ganador: Pikachu") || resultado.equals("Ganador: Charmander"));
    }

    // Helper method to set private fields
    private void setPrivateField(Object object, String fieldName, Object value) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
