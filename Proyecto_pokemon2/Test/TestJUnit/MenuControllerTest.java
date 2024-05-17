package TestJUnit;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.MenuController;
import controller.CombateController;


import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {

    private static MenuController menuController;

    @BeforeAll
    static void setUp() {
        menuController = new MenuController();
    }

}

