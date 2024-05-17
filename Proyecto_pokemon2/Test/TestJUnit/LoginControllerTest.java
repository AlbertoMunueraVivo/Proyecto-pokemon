package TestJUnit;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import controller.LoginController;
 
import static org.junit.jupiter.api.Assertions.*;
 
class LoginControllerTest {
 
    private LoginController loginController;
    private TextField usernameField;
    private PasswordField passwordField;
 
    @BeforeEach
    void setUp() {
        loginController = new LoginController();
        usernameField = new TextField();
        passwordField = new PasswordField();
        // Use reflection to set the private fields
        setPrivateField(loginController, "usernameField", usernameField);
        setPrivateField(loginController, "passwordField", passwordField);
    }
 
    @Test
    void handleLoginButtonAction_Success() {
        usernameField.setText("admin");
        passwordField.setText("admin");
        // Mocking ActionEvent
        ActionEvent event = new ActionEvent();
        // Assert that the alert shows "Login Successful"
        // This is a simplistic approach, in real scenarios you may need a more complex approach to test UI alerts
        assertEquals("Login Successful", getLastAlert().getTitle());
        assertEquals("Welcome admin", getLastAlert().getContentText());
    }
 
    @Test
    void handleLoginButtonAction_Failure() {
        usernameField.setText("user");
        passwordField.setText("password");
        // Mocking ActionEvent
        ActionEvent event = new ActionEvent();
        // Assert that the alert shows "Login Failed"
        assertEquals("Login Failed", getLastAlert().getTitle());
        assertEquals("Invalid username or password.", getLastAlert().getContentText());
    }
 
    // Helper methods to access private fields and get last alert (for testing purposes)
    private void setPrivateField(Object object, String fieldName, Object value) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private Alert getLastAlert() {
        // Implement a method to capture the last Alert shown
        // This requires a custom implementation since JavaFX does not provide a way to capture alerts directly
        return new Alert(Alert.AlertType.NONE);
    }
}
