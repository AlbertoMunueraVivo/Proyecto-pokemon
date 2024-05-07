package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import application.EquipoPokemon;
import javafx.scene.input.MouseEvent;

import application.Pokemon;

public class EquipoController {

	@FXML
	private ImageView botonAtras;

	@FXML
	private AnchorPane stats, equipoP;

	@FXML
	private TextField quitarText;

	@FXML
	private Text nombre1;

	@FXML
	private TextField textAnadir;

	@FXML
	private Button botonAnadir, botonQuitar;

	@FXML
	private Button X;

	@FXML
	private ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7,
			imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15,
			imageView16, imageView17, imageView18, imageView19, imageView20, imageView21, imageView22, imageView23,
			imageView24, imageView25, imageView26, imageView27, imageView28, imageView29, imageViewEquipo0,
			imageViewEquipo1, imageViewEquipo2, imageViewEquipo3, imageViewEquipo4, imageViewEquipo5;

	private EquipoPokemon.Pokemon pokemonSeleccionado;

	public void initialize() {
		cargarPokemons();
		System.out.println("_____________________________________________");
		cargarEquipoPokemon();
	}

	private void cargarPokemons() {
		List<EquipoPokemon.Pokemon> pokemons = EquipoPokemon.Pokemon.recuperarCaja(); // Llamada al método estático
		System.out.println("Cantidad de Pokémon recuperados: " + pokemons.size()); // Verifica el tamaño de la lista
		for (int i = 0; i < pokemons.size() && i < 30; i++) {
			EquipoPokemon.Pokemon pokemon = pokemons.get(i);
			ImageView imageView = getImageViewByIndex(i);
			if (imageView != null) {
				String ruta = "./sources/sprites/" + pokemon.getRutaImagen() + "BBD.png";
				imageView.setImage(new Image(new File(ruta).toURI().toString()));
				System.out.println(ruta);
			}
		}
	}

	public void cargarEquipoPokemon() {
		List<EquipoPokemon.Pokemon> equipo = EquipoPokemon.Pokemon.recuperarEquipo();
		System.out.println("Equipo recuperado: " + equipo.size());
		for (EquipoPokemon.Pokemon pokemon : equipo) {
			String equipoPokemon = pokemon.getequipoPokemon(); // Asegúrate de que este getter exista y devuelva algo
																// útil
			if (equipoPokemon != null && equipoPokemon.matches("S[1-6]")) {
				int index = Integer.parseInt(equipoPokemon.substring(1)) - 1; // Convierte "S1" a 0, "S2" a 1, etc.

				ImageView imageView = getImageViewByIndex2(index);
				System.out.println("ImageView " + index + ": " + imageView);
				if (imageView != null) {
					String ruta = "./sources/sprites/" + pokemon.getRutaImagen() + "BBD.png";
					imageView.setImage(new Image(new File(ruta).toURI().toString()));
					System.out.println(ruta);
				}

				Text nombreText = (Text) equipoP.lookup("#nombre" + (index + 1));
				if (nombreText != null) {
					nombreText.setText(pokemon.getNombre());
				} else {
					System.out.println("No se encontró el Text nombre" + (index + 1));
				}

				Text nivelText = (Text) equipoP.lookup("#nivel" + (index + 1));
				if (nivelText != null) {
					nivelText.setText("Nivel: " + pokemon.getNivel());
				} else {
					System.out.println("No se encontró el Text nivel" + (index + 1));
				}
			} else {
			}
		}
	}

	@FXML
	private void handleClickImagen(MouseEvent event) {
		ImageView clickedImageView = (ImageView) event.getSource();
		int index = getIndexFromImageView(clickedImageView);
		if (index != -1) {
			EquipoPokemon.Pokemon pokemon = EquipoPokemon.Pokemon.recuperarCaja().get(index);
			this.pokemonSeleccionado = pokemon; // Guardar el Pokémon seleccionado
			updateStatsPanel(pokemon); // Actualizar el panel de estadísticas
		}
	}

	@FXML
	private void handleAnadirClicked(ActionEvent event) {
		String numero = textAnadir.getText();
		if (numero.matches("[1-6]")) {
			String valorEquipo = "S" + numero;
			if (pokemonSeleccionado != null) {
				actualizarEquipoPokemon(pokemonSeleccionado, valorEquipo);
			} else {
				System.out.println("Por favor, selecciona un Pokémon primero.");
			}
		} else {
			System.out.println("Número inválido. Por favor, introduce un número del 1 al 6.");
		}
	}

	private void actualizarEquipoPokemon(EquipoPokemon.Pokemon pokemon, String valorEquipo) {
		System.out.println(
				"Actualizando el Pokémon con ID: " + pokemon.getIdPokemonCreado() + " a equipo: " + valorEquipo);
		Connection conexion = bbd.Conexion.conexionBbd();
		try {
			String sql = "UPDATE pokemons SET equipoPokemon = ? WHERE id_pokemonCreado = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setString(1, valorEquipo);
			pst.setInt(2, pokemon.getIdPokemonCreado());

			int affectedRows = pst.executeUpdate(); // Use executeUpdate for DML (Data Manipulation Language) statements
			System.out.println("Filas afectadas: " + affectedRows);
			if (affectedRows > 0) {
				System.out.println("La actualización fue exitosa.");
			} else {
				System.out.println("No se actualizó ningún registro.");
			}
		} catch (SQLException e) {
			if (e.getSQLState().startsWith("23")) { // Códigos de estado SQL para violaciones de restricción
				System.out.println("No se pudo actualizar porque el valor ya existe en otro registro.");
			} else {
				System.out.println("Error al actualizar datos del Pokémon: " + e.getMessage());
			}
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close(); // Always ensure resources are closed
			} catch (SQLException ex) {
				System.out.println("Error al cerrar recursos: " + ex.getMessage());
			}
		}
	}

	private ImageView getImageViewByIndex2(int index) {
		switch (index) {
		case 0:
			return imageViewEquipo0;
		case 1:
			return imageViewEquipo1;
		case 2:
			return imageViewEquipo2;
		case 3:
			return imageViewEquipo3;
		case 4:
			return imageViewEquipo4;
		case 5:
			return imageViewEquipo5;
		default:
			System.out.println("Índice fuera de rango para los ImageView del equipo: " + index);
			return null;
		}
	}

	private int getIndexFromImageView(ImageView imageView) {
		System.out.println("from image view");
		return switch (imageView.getId()) {
		case "imageView0" -> 0;
		case "imageView1" -> 1;
		case "imageView2" -> 2;
		case "imageView3" -> 3;
		case "imageView4" -> 4;
		case "imageView5" -> 5;
		case "imageView6" -> 6;
		case "imageView7" -> 7;
		case "imageView8" -> 8;
		case "imageView9" -> 9;
		case "imageView10" -> 10;
		case "imageView11" -> 11;
		case "imageView12" -> 12;
		case "imageView13" -> 13;
		case "imageView14" -> 14;
		case "imageView15" -> 15;
		case "imageView16" -> 16;
		case "imageView17" -> 17;
		case "imageView18" -> 18;
		case "imageView19" -> 19;
		case "imageView20" -> 20;
		case "imageView21" -> 21;
		case "imageView22" -> 22;
		case "imageView23" -> 23;
		case "imageView24" -> 24;
		case "imageView25" -> 25;
		case "imageView26" -> 26;
		case "imageView27" -> 27;
		case "imageView28" -> 28;
		case "imageView29" -> 29;
		default -> -1;
		};
	}

	private void updateStatsPanel(EquipoPokemon.Pokemon pokemon) {
		stats.setVisible(true);
		// Asegurarse de que el ImageView dentro del AnchorPane stats se dirige
		// correctamente
		ImageView pokemonImageView = (ImageView) stats.lookup("#pokemonImageView"); // Asegúrate de que este ID coincida
																					// con el ImageView en tu FXML
		System.out.println(pokemonImageView);
		if (pokemonImageView != null) {
			String imagePath = "./sources/sprites/" + pokemon.getRutaImagen() + "BBD.png";
			File file = new File(imagePath);
			Image pokemonImage = new Image(file.toURI().toString());
			System.out.println(pokemonImage);
			pokemonImageView.setImage(pokemonImage);
		}
		// Actualizar los elementos de texto correctamente utilizando el método lookup
		// para encontrarlos por ID
		Text hpText = (Text) stats.lookup("#HP");
		if (hpText != null) {
			hpText.setText("HP: " + pokemon.getVitalidad());
		}

		Text attackText = (Text) stats.lookup("#Attack");
		if (attackText != null) {
			attackText.setText("Ataque: " + pokemon.getAtaque());
		}

		Text DefenseText = (Text) stats.lookup("#Defense");
		if (DefenseText != null) {
			DefenseText.setText("Defensa: " + pokemon.getDefensa());
		}

		Text SpaText = (Text) stats.lookup("#Spa");
		if (SpaText != null) {
			SpaText.setText("Ataque Esp: " + pokemon.getAtaqueEspecial());
		}

		Text SpdText = (Text) stats.lookup("#Spd");
		if (SpdText != null) {
			SpdText.setText("Defensa Esp: " + pokemon.getDefensaEspecial());
		}

		Text SpeedText = (Text) stats.lookup("#Speed");
		if (SpeedText != null) {
			SpeedText.setText("Velocidad: " + pokemon.getVelocidad());
		}
	}

	@FXML
	private void handleClickboton(ActionEvent event) {
		stats.setVisible(false);
	}

	@FXML
	private void handleQuitarClicked(ActionEvent event) {
		String valorEquipo = quitarText.getText();
		// Ensure the value is what you expect, e.g., S2
		if (!valorEquipo.isEmpty() && valorEquipo.matches("[1-6]")) {
			quitarEquipoPokemon(valorEquipo);
		} else {
			System.out.println("Número inválido. Por favor, introduce un valor correcto (e.g., S2).");
		}
	}

	private void quitarEquipoPokemon(String valorEquipo) {
		Connection conexion = bbd.Conexion.conexionBbd();
		try {
			// Query to find the entry and clear the 'equipoPokemon' field
			String sql = "UPDATE pokemons SET equipoPokemon = NULL WHERE equipoPokemon = ?";
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setString(1, "S" + valorEquipo);

			int affectedRows = pst.executeUpdate();
			System.out.println("Filas afectadas: " + affectedRows);

			if (affectedRows > 0) {
				System.out.println("El valor de 'equipoPokemon' ha sido borrado exitosamente.");
			} else {
				System.out.println("No se encontró ningún registro con ese valor.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar datos del Pokémon: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException ex) {
				System.out.println("Error al cerrar recursos: " + ex.getMessage());
			}
		}
	}

	@FXML
	private void handleFlechaAtrasClicked(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
			Parent root = loader.load();
			Scene scene = botonAtras.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("La imagen de la flecha hacia atrás fue clicada.");
	}

	private ImageView getImageViewByIndex(int index) {
		// Implementación de la función que devuelve el ImageView correspondiente
		switch (index) {
		case 0:
			return imageView0;
		case 1:
			return imageView1;
		case 2:
			return imageView2;
		case 3:
			return imageView3;
		case 4:
			return imageView4;
		case 5:
			return imageView5;
		case 6:
			return imageView6;
		case 7:
			return imageView7;
		case 8:
			return imageView8;
		case 9:
			return imageView9;
		case 10:
			return imageView10;
		case 11:
			return imageView11;
		case 12:
			return imageView12;
		case 13:
			return imageView13;
		case 14:
			return imageView14;
		case 15:
			return imageView15;
		case 16:
			return imageView16;
		case 17:
			return imageView17;
		case 18:
			return imageView18;
		case 19:
			return imageView19;
		case 20:
			return imageView20;
		case 21:
			return imageView21;
		case 22:
			return imageView23;
		case 24:
			return imageView24;
		case 25:
			return imageView25;
		case 26:
			return imageView26;
		case 27:
			return imageView27;
		case 28:
			return imageView28;
		case 29:
			return imageView29;
		default:
			return null;
		}
	}
}
