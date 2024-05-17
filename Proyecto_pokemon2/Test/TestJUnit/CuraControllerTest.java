package TestJUnit;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.CuraController;

import static org.junit.jupiter.api.Assertions.*;

class CuraControllerTest {

    private static CuraController curaController;

    @BeforeAll
    static void setUp() {
        curaController = new CuraController();
    }
}


