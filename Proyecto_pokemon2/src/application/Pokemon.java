package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import bbd.SessionManager;
import bbd.UserDAO;

public class Pokemon {
	private int idPokemonCreado;
	private String nombre;
	private int vitalidad, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad;
	private String tipo1, tipo2, mote;
	private Movimiento movimiento1, movimiento2, movimiento3, movimiento4;
	private int vitalidadMaxima, vitalidadActual;
	private String tipo;

	
	
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

	public void asignarMovimientosAleatorios() {
	    Connection conexion = bbd.Conexion.conexionBbd();
	    try {
	        PreparedStatement pst = conexion.prepareStatement("SELECT nombre_movimiento, potencia, precisionA, tipo_movimiento, tipo_daño FROM movimientos ORDER BY RAND() LIMIT 4");
	        ResultSet rs = pst.executeQuery();
	        ArrayList<Movimiento> movimientos = new ArrayList<>();
	        while (rs.next()) {
	            String nombreMov = rs.getString("nombre_movimiento");
	            int potencia = rs.getInt("potencia");
	            int precision = rs.getInt("precisionA");
	            String tipo = rs.getString("tipo_movimiento");  // Asegúrate de que esta columna está presente en la tabla
	            String tipoDaño = rs.getString("tipo_daño");
				movimientos.add(new Movimiento(nombreMov, potencia, precision, tipo, tipoDaño));
	        }
	        if (movimientos.size() < 4) {
	            System.out.println("Advertencia: Menos de 4 movimientos disponibles.");
	        }
	        // Asignación de movimientos predeterminados en caso de que no se encuentren suficientes en la base de datos
	        this.movimiento1 = movimientos.size() > 0 ? movimientos.get(0) : new Movimiento("Movimiento default", 0, 0, "Ninguno", "Ninguno");
	        this.movimiento2 = movimientos.size() > 1 ? movimientos.get(1) : new Movimiento("Movimiento default", 0, 0, "Ninguno", "Ninguno");
	        this.movimiento3 = movimientos.size() > 2 ? movimientos.get(2) : new Movimiento("Movimiento default", 0, 0, "Ninguno", "Ninguno");
	        this.movimiento4 = movimientos.size() > 3 ? movimientos.get(3) : new Movimiento("Movimiento default", 0, 0, "Ninguno", "Ninguno");
	    } catch (SQLException e) {
	        System.out.println("Error al asignar movimientos aleatorios: " + e.getMessage());
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
	        asignarMovimientosAleatorios();

	        String username = SessionManager.getInstance().getCurrentUser().getUsername();
	        UserDAO userDAO = new UserDAO();
	        int idUsuario = userDAO.getUserIdByUsername(username);

	        if (idUsuario != -1) {
	            PreparedStatement pst = conexion.prepareStatement(
	                    "INSERT INTO pokemons (nombre, vitalidad, ataque, defensa, ataque_especial, defensa_especial, velocidad, tipo1, tipo2, mote, dueno, vitalidad_actual, movimiento1, movimiento2, movimiento3, movimiento4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

	            pst.setString(1, this.nombre);
	            pst.setInt(2, this.vitalidad);
	            pst.setInt(3, this.ataque);
	            pst.setInt(4, this.defensa);
	            pst.setInt(5, this.ataqueEspecial);
	            pst.setInt(6, this.defensaEspecial);
	            pst.setInt(7, this.velocidad);
	            pst.setString(8, this.tipo1);
	            pst.setString(9, this.tipo2);
	            pst.setString(10, this.mote);
	            pst.setInt(11, idUsuario);
	            pst.setInt(12, this.vitalidad); // Asumiendo que la vitalidad actual es la misma que la inicial

	            // Asignar los nombres de los movimientos
	            pst.setString(13, movimiento1.getNombre());
	            pst.setString(14, movimiento2.getNombre());
	            pst.setString(15, movimiento3.getNombre());
	            pst.setString(16, movimiento4.getNombre());

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
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void setMovimiento1(Movimiento movimiento1) {
		this.movimiento1 = movimiento1;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		return nombre;
	}

	public int getVitalidadActual() {
		return vitalidadActual;
	}

	public void setVitalidadActual(int vitalidadActual) {
		this.vitalidadActual = Math.max(vitalidadActual, 0); // Asegurar que no sea negativa
	}

	public int getVitalidadMaxima() {
		return vitalidadMaxima;
	}

	public void setVitalidadMaxima(int vitalidadMaxima) {
		this.vitalidadMaxima = vitalidadMaxima;
		this.vitalidadActual = vitalidadMaxima; // Resetear la vitalidad actual
	}

	public void recibirDano(int dano) {
		setVitalidadActual(this.vitalidadActual - dano);
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

}