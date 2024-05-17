package TestJUnit;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CentroController;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CentroControllerTest {

    private static CentroController centroController;

    @BeforeAll
    static void setUp() {
        centroController = new CentroController();
    }

    @Test
    void testAgregarConDatosValidos() {

        List<String> pokemones = null;
		// Verificar que el pokemon fue agregado
        assertEquals(1, pokemones.size());
        assertEquals("Charmander - 10", pokemones.get(0));
    }

    @Test
    void testAgregarConDatosInvalidos() {

        List<String> pokemones = null;
		// Verificar que no se agregó ningún pokemon
        assertTrue(pokemones.isEmpty());
    }

    @Test
    void testAgregarConValoresNulos() {

        List<String> pokemones = null;
		// Verificar que no se agregó ningún pokemon
        assertTrue(pokemones.isEmpty());
    }
}

