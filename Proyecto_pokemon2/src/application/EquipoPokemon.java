package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbd.SessionManager;
import bbd.UserDAO;

public class EquipoPokemon {

	public static class Pokemon {
		private int idPokemonCreado;
		private String nombre;
		private int vitalidad;
		private int vitalidad_actual;
		private int ataque;
		private int defensa;
		private int ataqueEspecial;
		private int defensaEspecial;
		private int velocidad;
		private String tipo1;
		private String tipo2;
		private String mote;
		private Movimiento movimiento1, movimiento2, movimiento3, movimiento4;
		private int exp;
		private int nivel;
		private String rutaImagen;
		private String equipoPokemon;
		private String tipoDaño;

		// Constructor
		public Pokemon(int idPokemonCreado, String nombre, int vitalidad, int vitalidad_actual, int ataque, int defensa,
				int ataqueEspecial, int defensaEspecial, int velocidad, String tipo1, String tipo2, String mote,
				Movimiento movimiento1, Movimiento movimiento2, Movimiento movimiento3, Movimiento movimiento4, int exp,
				int nivel, String rutaImagen, String equipoPokemon) {
			this.idPokemonCreado = idPokemonCreado;
			this.nombre = nombre;
			this.vitalidad = vitalidad;
			this.vitalidad_actual = vitalidad_actual;
			this.ataque = ataque;
			this.defensa = defensa;
			this.ataqueEspecial = ataqueEspecial;
			this.defensaEspecial = defensaEspecial;
			this.velocidad = velocidad;
			this.tipo1 = tipo1;
			this.tipo2 = tipo2;
			this.mote = mote;
			this.movimiento1 = movimiento1;
			this.movimiento2 = movimiento2;
			this.movimiento3 = movimiento3;
			this.movimiento4 = movimiento4;
			this.exp = exp;
			this.nivel = nivel;
			this.rutaImagen = rutaImagen;
			this.equipoPokemon = equipoPokemon;
		}

		public static List<Pokemon> recuperarCaja() {
			List<Pokemon> pokemons = new ArrayList<>();
			Connection conexion = bbd.Conexion.conexionBbd();
			String username = SessionManager.getInstance().getCurrentUser().getUsername();
			UserDAO userDAO = new UserDAO();
			int idUsuario = userDAO.getUserIdByUsername(username);

			try {
				PreparedStatement pst = conexion.prepareStatement(
						"SELECT p.*, m1.nombre_movimiento AS movimiento1_nombre, m1.potencia AS movimiento1_potencia, m1.precisionA AS movimiento1_precision, m2.nombre_movimiento AS movimiento2_nombre, m2.potencia AS movimiento2_potencia, m2.precisionA AS movimiento2_precision, m3.nombre_movimiento AS movimiento3_nombre, m3.potencia AS movimiento3_potencia, m3.precisionA AS movimiento3_precision, m4.nombre_movimiento AS movimiento4_nombre, m4.potencia AS movimiento4_potencia, m4.precisionA AS movimiento4_precision FROM pokemons p LEFT JOIN movimientos m1 ON p.movimiento1 = m1.id_movimiento LEFT JOIN movimientos m2 ON p.movimiento2 = m2.id_movimiento LEFT JOIN movimientos m3 ON p.movimiento3 = m3.id_movimiento LEFT JOIN movimientos m4 ON p.movimiento4 = m4.id_movimiento WHERE p.dueno = ? AND (p.equipoPokemon IS NULL OR p.equipoPokemon NOT LIKE 'S%');");
				pst.setInt(1, idUsuario);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Movimiento mov1 = new Movimiento(rs.getString("movimiento1_nombre"), 
                            rs.getInt("movimiento1_potencia"), 
                            rs.getInt("movimiento1_precision"), 
                            rs.getString("movimiento1_potencia"),
                            rs.getString("movimiento1_potencia"));
					Movimiento mov2 = new Movimiento(rs.getString("movimiento2_nombre"), 
                            rs.getInt("movimiento2_potencia"), 
                            rs.getInt("movimiento2_precision"), 
                            rs.getString("movimiento1_potencia"),
                            rs.getString("movimiento1_potencia"));
					Movimiento mov3 = new Movimiento(rs.getString("movimiento3_nombre"), 
                            rs.getInt("movimiento3_potencia"), 
                            rs.getInt("movimiento3_precision"), 
                            rs.getString("movimiento1_potencia"),
                            rs.getString("movimiento1_potencia"));
					Movimiento mov4 = new Movimiento(rs.getString("movimiento4_nombre"), 
                            rs.getInt("movimiento4_potencia"), 
                            rs.getInt("movimiento4_precision"), 
                            rs.getString("movimiento1_potencia"),
                            rs.getString("movimiento1_potencia"));
					Pokemon pokemon = new Pokemon(rs.getInt("id_pokemonCreado"), rs.getString("nombre"),
							rs.getInt("vitalidad"), rs.getInt("vitalidad_actual"), rs.getInt("ataque"),
							rs.getInt("defensa"), rs.getInt("ataque_especial"), rs.getInt("defensa_especial"),
							rs.getInt("velocidad"), rs.getString("tipo1"), rs.getString("tipo2"), rs.getString("mote"),
							mov1, mov2, mov3, mov4, rs.getInt("exp"), rs.getInt("nivel"), rs.getString("nombre"),
							rs.getString("equipoPokemon"));
					pokemons.add(pokemon);
				}
			} catch (SQLException e) {
				System.out.println("Error al cargar datos del Pokémon de la caja: " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (conexion != null)
						conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return pokemons;
		}

		public static List<Pokemon> recuperarEquipo() {
			List<Pokemon> pokemons = new ArrayList<>();
			Connection conexion = bbd.Conexion.conexionBbd();
			String username = SessionManager.getInstance().getCurrentUser().getUsername();
			UserDAO userDAO = new UserDAO();
			int idUsuario = userDAO.getUserIdByUsername(username);

			try {
				PreparedStatement pst = conexion.prepareStatement(
						"SELECT \r\n"
						+ "    p.id_pokemonCreado,\r\n"
						+ "    p.mote,\r\n"
						+ "    p.nombre,\r\n"
						+ "    p.vitalidad,\r\n"
						+ "    p.vitalidad_actual,\r\n"
						+ "    p.ataque,\r\n"
						+ "    p.defensa,\r\n"
						+ "    p.ataque_especial,\r\n"
						+ "    p.defensa_especial,\r\n"
						+ "    p.velocidad,\r\n"
						+ "    p.fertilidad,\r\n"
						+ "    p.estamina,\r\n"
						+ "    p.sexo,\r\n"
						+ "    p.tipo1,\r\n"
						+ "    p.tipo2,\r\n"
						+ "    p.EXP,\r\n"
						+ "    p.nivel,\r\n"
						+ "    p.equipoPokemon,\r\n"
						+ "    p.movimiento1 AS movimiento1_nombre,\r\n"
						+ "    m1.tipo_movimiento AS movimiento1_tipo,\r\n"
						+ "    m1.tipo_daño AS movimiento1_tipo_dano,\r\n"
						+ "    m1.potencia AS movimiento1_potencia,\r\n"
						+ "    m1.precisionA AS movimiento1_precision,\r\n"
						+ "    p.movimiento2 AS movimiento2_nombre,\r\n"
						+ "    m2.tipo_movimiento AS movimiento2_tipo,\r\n"
						+ "    m2.tipo_daño AS movimiento2_tipo_dano,\r\n"
						+ "    m2.potencia AS movimiento2_potencia,\r\n"
						+ "    m2.precisionA AS movimiento2_precision,\r\n"
						+ "    p.movimiento3 AS movimiento3_nombre,\r\n"
						+ "    m3.tipo_movimiento AS movimiento3_tipo,\r\n"
						+ "    m3.tipo_daño AS movimiento3_tipo_dano,\r\n"
						+ "    m3.potencia AS movimiento3_potencia,\r\n"
						+ "    m3.precisionA AS movimiento3_precision,\r\n"
						+ "    p.movimiento4 AS movimiento4_nombre,\r\n"
						+ "    m4.tipo_movimiento AS movimiento4_tipo,\r\n"
						+ "    m4.tipo_daño AS movimiento4_tipo_dano,\r\n"
						+ "    m4.potencia AS movimiento4_potencia,\r\n"
						+ "    m4.precisionA AS movimiento4_precision\r\n"
						+ "FROM \r\n"
						+ "    pokemons p\r\n"
						+ "LEFT JOIN \r\n"
						+ "    movimientos m1 ON p.movimiento1 = m1.nombre_movimiento\r\n"
						+ "LEFT JOIN \r\n"
						+ "    movimientos m2 ON p.movimiento2 = m2.nombre_movimiento\r\n"
						+ "LEFT JOIN \r\n"
						+ "    movimientos m3 ON p.movimiento3 = m3.nombre_movimiento\r\n"
						+ "LEFT JOIN \r\n"
						+ "    movimientos m4 ON p.movimiento4 = m4.nombre_movimiento\r\n"
						+ "WHERE p.dueno = ? AND p.equipoPokemon LIKE 'S%'\r\n"
						+ "ORDER BY SUBSTRING(p.equipoPokemon, 2, 1);");
				
				
				pst.setInt(1, idUsuario);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Movimiento mov1 = new Movimiento(rs.getString("movimiento1_nombre"), 
                            rs.getInt("movimiento1_potencia"), 
                            rs.getInt("movimiento1_precision"), 
                            rs.getString("movimiento1_tipo"),
                            rs.getString("movimiento1_tipo_dano"));
					Movimiento mov2 = new Movimiento(rs.getString("movimiento2_nombre"), 
                            rs.getInt("movimiento2_potencia"), 
                            rs.getInt("movimiento2_precision"), 
                            rs.getString("movimiento2_tipo"),
                            rs.getString("movimiento2_tipo_dano"));
					Movimiento mov3 = new Movimiento(rs.getString("movimiento3_nombre"), 
                            rs.getInt("movimiento3_potencia"), 
                            rs.getInt("movimiento3_precision"), 
                            rs.getString("movimiento3_tipo"),
                            rs.getString("movimiento3_tipo_dano"));
					Movimiento mov4 = new Movimiento(rs.getString("movimiento4_nombre"), 
                            rs.getInt("movimiento4_potencia"), 
                            rs.getInt("movimiento4_precision"), 
                            rs.getString("movimiento4_tipo"),
                            rs.getString("movimiento4_tipo_dano"));
					pokemons.add(new Pokemon(rs.getInt("id_pokemonCreado"), rs.getString("nombre"),
							rs.getInt("vitalidad"), rs.getInt("vitalidad_actual"), rs.getInt("ataque"),
							rs.getInt("defensa"), rs.getInt("ataque_especial"), rs.getInt("defensa_especial"),
							rs.getInt("velocidad"), rs.getString("tipo1"), rs.getString("tipo2"), rs.getString("mote"),
							mov1, mov2, mov3, mov4, rs.getInt("exp"), rs.getInt("nivel"), rs.getString("nombre"),
							rs.getString("equipoPokemon")));
				}
			} catch (SQLException e) {
				System.out.println("Error al cargar datos del Pokémon de tu equipo:  " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (conexion != null)
						conexion.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar recursos de base de datos: " + e.getMessage());
				}
			}
			return pokemons;
		}

		public static void actualizarVitalidadActualDelEquipo() {
			List<Pokemon> equipo = recuperarEquipo(); // Recupera los Pokémon del equipo
			Connection conexion = bbd.Conexion.conexionBbd(); // Asume que ya tienes un método para conectar a la base
																// de datos

			try {
				// Prepara la sentencia SQL para actualizar la vitalidad_actual
				PreparedStatement pst = conexion
						.prepareStatement("UPDATE pokemons SET vitalidad_actual = ? WHERE id_pokemonCreado = ?");

				for (Pokemon pokemon : equipo) {
					// Actualiza la vitalidad_actual en la instancia del objeto
					pokemon.setVitalidadActual(pokemon.getVitalidad());

					// Configura los parámetros del PreparedStatement
					pst.setInt(1, pokemon.getVitalidadActual());
					pst.setInt(2, pokemon.getIdPokemonCreado());

					// Ejecuta la actualización en la base de datos
					pst.executeUpdate();
					System.out.println("Vitalidad actualizada en la base de datos para " + pokemon.getNombre() + ": "
							+ pokemon.getVitalidadActual());
				}
			} catch (SQLException e) {
				System.out.println("Error al actualizar la vitalidad en la base de datos: " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (conexion != null)
						conexion.close(); // Cierra la conexión a la base de datos
				} catch (SQLException e) {
					System.out.println("Error al cerrar recursos de base de datos: " + e.getMessage());
				}
			}
		}
		
		public Movimiento getMovimiento1() {
			return movimiento1;
		}

		public Movimiento getMovimiento2() {
			return movimiento2;
		}

		public Movimiento getMovimiento3() {
			return movimiento3;
		}

		public Movimiento getMovimiento4() {
			return movimiento4;
		}

		public void setMovimiento1(Movimiento movimiento) {
			this.movimiento1 = movimiento;
		}

		public void setMovimiento2(Movimiento movimiento2) {
			this.movimiento2 = movimiento2;
		}

		public void setMovimiento3(Movimiento movimiento3) {
			this.movimiento3 = movimiento3;
		}

		public void setMovimiento4(Movimiento movimiento4) {
			this.movimiento4 = movimiento4;
		}

		public int getVitalidad_actual() {
			return vitalidad_actual;
		}

		public void setVitalidad_actual(int vitalidad_actual) {
			this.vitalidad_actual = vitalidad_actual;
		}

		public String getEquipoPokemon() {
			return equipoPokemon;
		}

		public void setEquipoPokemon(String equipoPokemon) {
			this.equipoPokemon = equipoPokemon;
		}

		public int getIdPokemonCreado() {
			return idPokemonCreado;
		}

		public String getequipoPokemon() {
			return equipoPokemon;
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

		public int getVitalidadActual() {
			return vitalidad_actual;
		}

		public void setVitalidadActual(int vitalidad_actual) {
			this.vitalidad_actual = vitalidad_actual;
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

		public int getExp() {
			return exp;
		}

		public void setExp(int exp) {
			this.exp = exp;
		}

		public int getNivel() {
			return nivel;
		}

		public void setNivel(int nivel) {
			this.nivel = nivel;
		}

		public String getRutaImagen() {
			return rutaImagen;
		}

		public void setRutaImagen(String rutaImagen) {
			this.rutaImagen = rutaImagen;
		}
		
		public String getTipoDaño() {
			return tipoDaño;
		}
		
		public void recibirDano(int dano) {
			setVitalidadActual(this.vitalidad_actual - dano);
		}

	}
}
