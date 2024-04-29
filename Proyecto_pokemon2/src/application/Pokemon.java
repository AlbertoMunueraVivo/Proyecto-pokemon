package application;

import java.sql.*;
import java.util.Scanner;

public class Pokemon {
	private int idPokemonCreado;
	private String nombre;
	private int vitalidad;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo1;
	private String tipo2;
	private String mote; // Este campo se añadirá si el usuario decide poner un mote al Pokémon.

	// Constructor
	public Pokemon(int idPokedex) {
		Connection conexion = bbd.Conexion.conexionBbd();
		try {
			// Carga los datos del Pokémon desde la tabla pokedex
			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokedex WHERE id_pokemon = ?");
			pst.setInt(1, idPokedex);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				this.idPokemonCreado = rs.getInt("id_pokemon");
				this.nombre = rs.getString("nombre");
				this.vitalidad = rs.getInt("vitalidad");
				this.ataque = rs.getInt("ataque");
				this.defensa = rs.getInt("defensa");
				this.ataqueEspecial = rs.getInt("ataque especial");
				this.defensaEspecial = rs.getInt("defensa especial");
				this.velocidad = rs.getInt("velocidad");
				this.tipo1 = rs.getString("tipo1"); // Asumiendo que la columna se llama tipo1
				this.tipo2 = rs.getString("tipo2"); // Asumiendo que la columna se llama tipo2

				// Pregunta por el mote
				Scanner scanner = new Scanner(System.in);
				System.out.print("¿Deseas asignar un mote a tu Pokémon? (s/n): ");
				if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
					System.out.print("Ingresa el mote para " + nombre + ": ");
					this.mote = scanner.nextLine();
				}

				// Prepara la inserción del nuevo Pokémon en la tabla pokemons
				pst = conexion.prepareStatement(
						"INSERT INTO pokemons (nombre, vitalidad, ataque, defensa, ataque_especial, defensa_especial, velocidad, sexo, tipo1, tipo2, mote) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pst.setString(1, this.nombre);
				pst.setInt(2, this.vitalidad);
				pst.setInt(3, this.ataque);
				pst.setInt(4, this.defensa);
				pst.setInt(5, this.ataqueEspecial);
				pst.setInt(6, this.defensaEspecial);
				pst.setInt(7, this.velocidad);
				pst.setInt(8,  1);
				pst.setString(9, this.tipo1);
				pst.setString(10, this.tipo2);
				pst.setString(11, this.mote); // El mote puede ser null si el usuario no asignó uno.
				pst.executeUpdate();
				System.out.println("Nuevo Pokémon añadido con éxito.");
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar o insertar datos del Pokémon: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerrar la conexión
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}