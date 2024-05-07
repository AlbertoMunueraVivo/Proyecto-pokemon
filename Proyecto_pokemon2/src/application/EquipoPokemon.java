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
	    private int ataque;
	    private int defensa;
	    private int ataqueEspecial;
	    private int defensaEspecial;
	    private int velocidad;
	    private String tipo1;
	    private String tipo2;
	    private String mote;
	    private String movimiento1;
	    private String movimiento2;
	    private String movimiento3;
	    private String movimiento4;
	    private int exp;
	    private int nivel;
	    private String rutaImagen;
		private String equipoPokemon;

	    // Constructor
	    public Pokemon(int idPokemonCreado, String nombre, int vitalidad, int ataque, int defensa, int ataqueEspecial, int defensaEspecial,
	                   int velocidad, String tipo1, String tipo2, String mote, String movimiento1, String movimiento2, 
	                   String movimiento3, String movimiento4, int exp, int nivel, String rutaImagen, String equipoPokemon) {
	        this.idPokemonCreado = idPokemonCreado;
	        this.nombre = nombre;
	        this.vitalidad = vitalidad;
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
	        System.out.println(idUsuario);

	        try {
	            PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokemons WHERE dueno = ? AND (equipoPokemon IS NULL OR equipoPokemon NOT LIKE 'S%')");
	            pst.setInt(1, idUsuario);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Pokemon pokemon = new Pokemon(
	                    rs.getInt("id_pokemonCreado"), rs.getString("nombre"), rs.getInt("vitalidad"), rs.getInt("ataque"),
	                    rs.getInt("defensa"), rs.getInt("ataque_especial"), rs.getInt("defensa_especial"), rs.getInt("velocidad"),
	                    rs.getString("tipo1"), rs.getString("tipo2"), rs.getString("mote"), rs.getString("movimiento1"),
	                    rs.getString("movimiento2"), rs.getString("movimiento3"), rs.getString("movimiento4"),
	                    rs.getInt("exp"), rs.getInt("nivel"), rs.getString("nombre"), rs.getString("equipoPokemon")
	                );
	                pokemons.add(pokemon);
	                System.out.println("Pokemon recuperado: " + pokemon.getNombre());

	            }
	            System.out.println("Caja recuperada");
	        } catch (SQLException e) {
	            System.out.println("Error al cargar datos del Pokémon: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return pokemons;
	    }

	
	    public static List<Pokemon> recuperarEquipo() {
	        List<Pokemon> pokemons = new ArrayList<>();
	        Connection conexion = bbd.Conexion.conexionBbd();
	        String username = SessionManager.getInstance().getCurrentUser().getUsername();
	        UserDAO userDAO = new UserDAO();
	        int idUsuario = userDAO.getUserIdByUsername(username);
	        System.out.println(idUsuario);

	        try {
	            PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokemons WHERE dueno = ? AND (equipoPokemon IS NULL OR equipoPokemon LIKE 'S%')");
	            pst.setInt(1, idUsuario);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Pokemon pokemon = new Pokemon(
	                    rs.getInt("id_pokemonCreado"), rs.getString("nombre"), rs.getInt("vitalidad"), rs.getInt("ataque"),
	                    rs.getInt("defensa"), rs.getInt("ataque_especial"), rs.getInt("defensa_especial"), rs.getInt("velocidad"),
	                    rs.getString("tipo1"), rs.getString("tipo2"), rs.getString("mote"), rs.getString("movimiento1"),
	                    rs.getString("movimiento2"), rs.getString("movimiento3"), rs.getString("movimiento4"),
	                    rs.getInt("exp"), rs.getInt("nivel"), rs.getString("nombre"), rs.getString("equipoPokemon")
	                );
	                pokemons.add(pokemon);
	                System.out.println("Pokemon recuperado: " + pokemon.getNombre());
	            }
	            System.out.println("Equipo recuperado");
	        } catch (SQLException e) {
	            System.out.println("Error al cargar datos del Pokémon: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return pokemons;
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

	public String getMovimiento1() {
		return movimiento1;
	}

	public void setMovimiento1(String movimiento1) {
		this.movimiento1 = movimiento1;
	}

	public String getMovimiento2() {
		return movimiento2;
	}

	public void setMovimiento2(String movimiento2) {
		this.movimiento2 = movimiento2;
	}

	public String getMovimiento3() {
		return movimiento3;
	}

	public void setMovimiento3(String movimiento3) {
		this.movimiento3 = movimiento3;
	}

	public String getMovimiento4() {
		return movimiento4;
	}

	public void setMovimiento4(String movimiento4) {
		this.movimiento4 = movimiento4;
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
	
	
	
	
	
	
	
	
	
	
	
	}
}
