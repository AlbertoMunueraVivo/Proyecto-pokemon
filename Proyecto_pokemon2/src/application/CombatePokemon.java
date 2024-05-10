package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CombatePokemon {
    private ArrayList<Pokemon> pokemons;
    private Iterator<Pokemon> pokemonIterator;
    private Pokemon currentEnemy;

    public CombatePokemon() {
        this.pokemons = new ArrayList<>();
        cargarPokemonsAleatorios();
        asignarMovimientosAPokemons();
        pokemonIterator = pokemons.iterator();
        nextEnemy();
    }

    private void cargarPokemonsAleatorios() {
        Connection conexion = bbd.Conexion.conexionBbd();
        try {
            PreparedStatement pst = conexion.prepareStatement("SELECT * FROM pokedex ORDER BY RAND() LIMIT 6");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setIdPokemonCreado(rs.getInt("id_pokemon"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setVitalidad(rs.getInt("vitalidad"));
                pokemon.setAtaque(rs.getInt("ataque"));
                pokemon.setDefensa(rs.getInt("defensa"));
                pokemon.setAtaqueEspecial(rs.getInt("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getInt("defensa_especial"));
                pokemon.setVelocidad(rs.getInt("velocidad"));
                pokemon.setTipo1(rs.getString("tipo1"));
                pokemon.setTipo2(rs.getString("tipo2"));
                pokemon.setVitalidadMaxima(rs.getInt("vitalidad"));  // Asegúrate de que este método existe y se llama correctamente
                pokemon.setVitalidadActual(rs.getInt("vitalidad")); // Asumiendo que la vitalidad actual comienza como máxima
                pokemon.asignarMovimientosAleatorios();
                pokemons.add(pokemon);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar pokémons aleatorios: " + e.getMessage());
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

    private void asignarMovimientosAPokemons() {
        for (Pokemon pokemon : pokemons) {
            pokemon.asignarMovimientosAleatorios();
        }
    }

    public Pokemon getCurrentEnemy() {
        return currentEnemy;
    }

    public void nextEnemy() {
        if (pokemonIterator.hasNext()) {
            currentEnemy = pokemonIterator.next();
            System.out.println("New enemy loaded: " + currentEnemy.getNombre());
        } else {
            currentEnemy = null; // No more enemies
            System.out.println("No more enemies");
        }
    }
}
