package TestJUnit;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.RegistroController;

import static org.junit.jupiter.api.Assertions.*;

class RegistroControllerTest {

    private static RegistroController registroController;

    @BeforeAll
    static void setUp() {
        registroController = new RegistroController();
    }
}

