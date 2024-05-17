package TestJUnit;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CapturaController;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CapturaControllerTest {

    private CapturaController capturaController;

    @BeforeEach
    void setUp() {
        capturaController = new CapturaController();
    }

    @Test
    void testCapturarConDatosValidos() {
        List<String> pokemones = null;
		// Verificar que el pokemon fue agregado
        assertEquals(1, pokemones.size());
        assertEquals("Pikachu - 50 - 100", pokemones.get(0));
    }

    @Test
    void testCapturarConDatosInvalidos() {
        List<String> pokemones = null;
		// Verificar que no se agregó ningún pokemon
        assertTrue(pokemones.isEmpty());
    }

    @Test
    void testCapturarConValoresNulos() {
        List<String> pokemones = null;
		// Verificar que no se agregó ningún pokemon
        assertTrue(pokemones.isEmpty());
    }
}
