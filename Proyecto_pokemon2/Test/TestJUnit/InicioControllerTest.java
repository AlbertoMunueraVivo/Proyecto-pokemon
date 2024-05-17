package TestJUnit;

import javafx.scene.control.Label;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.InicioController;

import static org.junit.jupiter.api.Assertions.*;

class InicioControllerTest {

    private static InicioController inicioController;

    @BeforeAll
    static void setUp() {
        inicioController = new InicioController();
    }

}

