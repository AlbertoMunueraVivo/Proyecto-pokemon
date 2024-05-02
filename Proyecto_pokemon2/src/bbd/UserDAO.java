package bbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto_pokemon";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    /**
     * Retrieves a User object by username.
     *
     * @param username the username to search for in the database
     * @return a User object if found, otherwise null
     */
    public User getUserByUsername(String username) {
        String query = "SELECT id_usuario, nombre_usuario FROM usuarios WHERE nombre_usuario = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String userUsername = resultSet.getString("nombre_usuario");
                return new User(id, userUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Retrieves the ID of the user by username.
     *
     * @param username the username to search for in the database
     * @return the ID of the user if found, otherwise -1
     */
    public int getUserIdByUsername(String username) {
        String query = "SELECT id_usuario FROM usuarios WHERE nombre_usuario = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}