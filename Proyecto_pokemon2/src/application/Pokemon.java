package application;

import java.sql.*;
import java.util.Scanner;

import bbd.SessionManager;
import bbd.UserDAO;

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
	private String mote;
	private String rutaImagen;
	private int idUsuario;

	// Constructor
	public Pokemon() {
	}

	public int obtenerMaxId() {
		int maxId = 0;
		Connection conexion = bbd.Conexion.conexionBbd();
		try {
			String query = "SELECT MAX(id_pokemon) AS max_id FROM pokedex";
			PreparedStatement pst = conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maxId = rs.getInt("max_id");
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el máximo ID de Pokémon: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxId;
	}

	// Método para cargar datos de Pokémon
	public void cargarDatos(int idPokedex) {
		Connection conexion = bbd.Conexion.conexionBbd();
		try {
			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokedex WHERE id_pokemon = ?");
			pst.setInt(1, idPokedex);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				this.idPokemonCreado = rs.getInt("id_pokemon");
				this.nombre = rs.getString("nombre");
				this.vitalidad = rs.getInt("vitalidad");
				this.ataque = rs.getInt("ataque");
				this.defensa = rs.getInt("defensa");
				this.ataqueEspecial = rs.getInt("ataque_especial");
				this.defensaEspecial = rs.getInt("defensa_especial");
				this.velocidad = rs.getInt("velocidad");
				this.tipo1 = rs.getString("tipo1");
				this.tipo2 = rs.getString("tipo2");
				this.rutaImagen = rs.getString("imagen");
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar datos del Pokémon: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Método para guardar el Pokémon en la base de datos
	public void guardarEnBaseDatos() {
	    Connection conexion = bbd.Conexion.conexionBbd();
	    try {
	        // Obtener el nombre de usuario de la sesión actual
	        String username = SessionManager.getInstance().getCurrentUser().getUsername();

	        // Obtener el ID de usuario utilizando el nombre de usuario
	        UserDAO userDAO = new UserDAO();
	        int idUsuario = userDAO.getUserIdByUsername(username);
	        System.out.println(idUsuario);
	        if (idUsuario != -1) {
	            PreparedStatement pst = conexion.prepareStatement(
	                    "INSERT INTO pokemons (nombre, vitalidad, ataque, defensa, ataque_especial, defensa_especial, velocidad, sexo, tipo1, tipo2, mote, dueño vitalidad_actual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	            pst.setString(1, this.nombre);
	            pst.setInt(2, this.vitalidad);
	            pst.setInt(3, this.ataque);
	            pst.setInt(4, this.defensa);
	            pst.setInt(5, this.ataqueEspecial);
	            pst.setInt(6, this.defensaEspecial);
	            pst.setInt(7, this.velocidad);
	            pst.setInt(8, 1); // Asumiendo que el sexo está fijado a 1 como ejemplo
	            pst.setString(9, this.tipo1);
	            pst.setString(10, this.tipo2);
	            pst.setString(11, this.mote);
	            pst.setInt(12, idUsuario);
	            pst.setInt(13, this.vitalidad);// Asignar el ID del usuario como dueño del Pokémon

	            pst.executeUpdate();
	            System.out.println("Nuevo Pokémon añadido con éxito.");
	        } else {
	            System.out.println("Error: Usuario no encontrado.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al guardar datos del Pokémon: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conexion != null)
	                conexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public int getIdPokemonCreado() {
		return idPokemonCreado;
	}

	public void setIdPokemonCreado(int idPokemonCreado) {
		this.idPokemonCreado = idPokemonCreado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVitalidad() {
		return vitalidad;
	}

	public void setVitalidad(int vitalidad) {
		this.vitalidad = vitalidad;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getTipo1() {
		return tipo1;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	public String getMote() {
		return mote;
	}

	public void setMote(String mote) {
		this.mote = mote;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	


}