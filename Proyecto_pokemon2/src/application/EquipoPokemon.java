package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbd.SessionManager;
import bbd.UserDAO;

public class EquipoPokemon {

	private int idPokemonCreado;
	private String nombre;
	private int vitalidad;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo1;
	private String rutaImagen;
	private String tipo2;
	private String mote;
	private String movimiento1;
	private String movimiento2;
	private String movimiento3;
	private String movimiento4;
	private int exp;
	private int nivel;

	public void recuperarPokemonsUsuario() {
		Connection conexion = bbd.Conexion.conexionBbd();
		String username = SessionManager.getInstance().getCurrentUser().getUsername();
		UserDAO userDAO = new UserDAO();
		int idUsuario = userDAO.getUserIdByUsername(username);
		System.out.println(idUsuario);
		try {
			PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokemons WHERE dueño = ?");
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				this.idPokemonCreado = rs.getInt("id_pokemon");
				this.mote = rs.getString("mote");
				this.nombre = rs.getString("nombre");
				this.vitalidad = rs.getInt("vitalidad");
				this.ataque = rs.getInt("ataque");
				this.defensa = rs.getInt("defensa");
				this.ataqueEspecial = rs.getInt("ataque_especial");
				this.defensaEspecial = rs.getInt("defensa_especial");
				this.velocidad = rs.getInt("velocidad");
				this.tipo1 = rs.getString("tipo1");
				this.tipo2 = rs.getString("tipo2");
				this.movimiento1 = rs.getString("movimiento1");
				this.movimiento2 = rs.getString("movimiento2");
				this.movimiento3 = rs.getString("movimiento3");
				this.movimiento4 = rs.getString("movimiento4");
				this.exp = rs.getInt("exp");
				this.nivel = rs.getInt("nivel");
				this.rutaImagen = rs.getString("imagen");
				System.out.println("Todos los pokemons recuperados");
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar datos del Pokémon: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
