package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import application.CombatePokemon;
import application.EquipoPokemon;
import application.Movimiento;
import application.Pokemon;

public class CombateController {
	@FXML
	private ProgressBar healthBarEnemy, healthBarPlayer;
	@FXML
	private ImageView pokemonImageView, myPokemonImageView, flechaAtrasImageView, combateImage,
			tipoMovimiento1ImageView, tipoMovimiento2ImageView, tipoMovimiento3ImageView, tipoMovimiento4ImageView;
	@FXML
	private AnchorPane combatePanel, ganar, perdio;
	@FXML
	private Text nombreMovimiento1, potenciaMovimiento1, precisionMovimiento1, nombreMovimiento2, potenciaMovimiento2,
			precisionMovimiento2, nombreMovimiento3, potenciaMovimiento3, precisionMovimiento3, nombreMovimiento4,
			potenciaMovimiento4, precisionMovimiento4, textEnemigo, vitalidadMax, vitalidadAct, textAliado;

	private CombatePokemon combate;
	application.EquipoPokemon.Pokemon miPokemon;
	private Pokemon pokemonRival;
	
	private int currentIndex = 0;

	@FXML
	public void initialize() {
		combate = new CombatePokemon(); // Inicializa el combate
		cargarPokemonInicial();
		cargarEnemigoInicial();
	}

	private void cargarPokemonInicial() {
		List<EquipoPokemon.Pokemon> equipo = EquipoPokemon.Pokemon.recuperarEquipo();
		if (!equipo.isEmpty()) {
			miPokemon = equipo.get(0); // Aquí renombramos miPrimerPokemon a miPokemon
			System.out.println(miPokemon);
			actualizarImagenMiPokemon(miPokemon);
			actualizarNombreAliado(miPokemon);
			updateHealthBarPlayer();
			actualizarVitalidadTextos(miPokemon);
			actualizarMovimientos(miPokemon);
			actualizarTiposMovimientos(miPokemon);
		}
	}
	
	private void cargarSiguienteEnemigo() {
	    combate.nextEnemy(); // Cargar el siguiente enemigo utilizando el método de la clase CombatePokemon
	    Pokemon siguienteEnemigo = combate.getCurrentEnemy();
	    if (siguienteEnemigo != null) {
	        pokemonRival = siguienteEnemigo;
	        actualizarImagenPokemonEnemigo(pokemonRival);
	        actualizarNombreEnemigo(pokemonRival);
	        updateHealthBarEnemy();
	    } else {
	        // Si no hay más enemigos, mostrar algún mensaje o realizar alguna acción adecuada
	        System.out.println("No hay más enemigos disponibles.");
	        ganar.setVisible(true);
	    }
	}
	
	private void cargarSiguientePokemonEquipo() {
	    System.out.println("Cargando siguiente Pokémon");
	    List<EquipoPokemon.Pokemon> equipo = EquipoPokemon.Pokemon.recuperarEquipo();
	    System.out.println("Current index: " + currentIndex);
	    
	    // Verificar si hay más Pokémon en el equipo
	    if (!equipo.isEmpty()) {
	        int nextIndex = (currentIndex + 1) % equipo.size(); // Calcular el índice del siguiente Pokémon de forma circular
	        miPokemon = equipo.get(nextIndex); // Obtener el siguiente Pokémon en la lista
	        currentIndex = nextIndex; // Actualizar currentIndex al índice del nuevo Pokémon
	        System.out.println("Nuevo Pokémon cargado: " + miPokemon.getNombre());

	        // Actualizar la interfaz de usuario con la información del nuevo Pokémon
	        actualizarImagenMiPokemon(miPokemon);
	        actualizarNombreAliado(miPokemon);
	        updateHealthBarPlayer();
	        actualizarVitalidadTextos(miPokemon);
	        actualizarMovimientos(miPokemon);
	        actualizarTiposMovimientos(miPokemon);
	    } else {
	        // Si no hay Pokémon en el equipo, mostrar algún mensaje o realizar alguna acción adecuada
	        System.out.println("No hay Pokémon disponibles en tu equipo.");
	        perdio.setVisible(true);
	        
	    }
	}



	public void actualizarImagenTipo(ImageView imageView, String tipo) {
		String rutaTipo = "./sources/tipos/" + tipo + ".png"; // Asumiendo que tienes una imagen para cada tipo
		File archivoTipo = new File(rutaTipo);
		if (archivoTipo.exists()) {
			imageView.setImage(new Image(archivoTipo.toURI().toString()));
			System.out.println(rutaTipo);
		} else {
			System.out.println("No se encontró el archivo de tipo: " + rutaTipo);
		}
	}

	public void actualizarTiposMovimientos(EquipoPokemon.Pokemon pokemon) {
		if (pokemon != null) {
			actualizarImagenTipo(tipoMovimiento1ImageView, pokemon.getMovimiento1().getTipo());
			actualizarImagenTipo(tipoMovimiento2ImageView, pokemon.getMovimiento2().getTipo());
			actualizarImagenTipo(tipoMovimiento3ImageView, pokemon.getMovimiento3().getTipo());
			actualizarImagenTipo(tipoMovimiento4ImageView, pokemon.getMovimiento4().getTipo());
		}
	}

	private void cargarEnemigoInicial() {
		application.Pokemon enemigo = combate.getCurrentEnemy();
		pokemonRival = enemigo;
		if (enemigo != null) {
			actualizarImagenPokemonEnemigo(enemigo);
			actualizarNombreEnemigo(enemigo);
			updateHealthBarEnemy();
		}
	}

	public void actualizarImagenMiPokemon(EquipoPokemon.Pokemon miPokemon) {
		if (miPokemon != null && miPokemon.getRutaImagen() != null) {
			String ruta = "./sources/sprites/" + miPokemon.getRutaImagen() + "Back.png";
			cargarImagen(myPokemonImageView, ruta);
		}
	}

	public void actualizarImagenPokemonEnemigo(application.Pokemon enemigo) {
		if (enemigo != null && enemigo.getRutaImagen() != null) {
			String ruta = "./sources/sprites/" + enemigo.getRutaImagen() + "Front.png";
			cargarImagen(pokemonImageView, ruta);
		}
	}

	private void cargarImagen(ImageView imageView, String ruta) {
		File archivoImagen = new File(ruta);
		if (archivoImagen.exists()) {
			imageView.setImage(new Image(archivoImagen.toURI().toString()));
		} else {
			System.out.println("El archivo de imagen no existe: " + ruta);
		}
	}

	public void updateHealthBarPlayer() {
		if (miPokemon != null && miPokemon.getVitalidad() > 0) {
			double healthProgress = (double) miPokemon.getVitalidadActual() / miPokemon.getVitalidad();
			System.out.println(miPokemon.getVitalidadActual());
			System.out.println(miPokemon.getVitalidad());
			healthBarPlayer.setProgress(healthProgress);
			actualizarVitalidadTextos(miPokemon);
		} else {
			healthBarPlayer.setProgress(0); // No hay Pokémon o los datos de salud son incorrectos
		}
	}

	public void updateHealthBarEnemy() {
		application.Pokemon enemy = combate.getCurrentEnemy();
		if (enemy != null && enemy.getVitalidadMaxima() > 0) {

			double healthProgress = (double) enemy.getVitalidadActual() / enemy.getVitalidadMaxima();
			healthBarEnemy.setProgress(healthProgress);
		} else {
			healthBarEnemy.setProgress(0); // No hay enemigo o el enemigo fue derrotado
		}
	}

	private void actualizarVitalidadTextos(EquipoPokemon.Pokemon miPokemon) {
		if (miPokemon != null) {
			vitalidadAct.setText(String.valueOf(miPokemon.getVitalidadActual()));
			vitalidadMax.setText(String.valueOf(miPokemon.getVitalidad()));
		} else {
			vitalidadAct.setText("0");
			vitalidadMax.setText("0");
		}
	}

	private void actualizarNombreEnemigo(application.Pokemon enemigo) {
		if (enemigo != null) {
			textEnemigo.setText(enemigo.getNombre());
		} else {
			textEnemigo.setText("Sin enemigo");
		}
	}

	private void actualizarNombreAliado(EquipoPokemon.Pokemon aliado) {
		if (aliado != null) {
			textAliado.setText(aliado.getNombre());
		} else {
			textAliado.setText("Sin aliado");
		}
	}

	@FXML
	private void handlecombateClicked(MouseEvent event) {
		System.out.println("Combate clicado");
		combatePanel.setVisible(true);
	}

	@FXML
	private void handleFlechaAtrasClicked(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
			Parent root = loader.load();
			Scene scene = flechaAtrasImageView.getScene(); // Obtener la escena actual del botón
			scene.setRoot(root); // Establecer la nueva raíz de la escena
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("La imagen de la flecha hacia atrás fue clicada.");
	}

	private void actualizarMovimientos(EquipoPokemon.Pokemon pokemon) {
		if (pokemon != null && pokemon.getMovimiento1() != null) {
			nombreMovimiento1.setText(pokemon.getMovimiento1().getNombre());
			potenciaMovimiento1.setText("Potencia: " + String.valueOf(pokemon.getMovimiento1().getPotencia())); // Convertir
																												// a
																												// String
			precisionMovimiento1.setText("Precision: " + String.valueOf(pokemon.getMovimiento1().getPrecision()));
			nombreMovimiento2.setText(pokemon.getMovimiento2().getNombre());
			potenciaMovimiento2.setText("Potencia: " + String.valueOf(pokemon.getMovimiento2().getPotencia())); // Convertir
																												// a
																												// String
			precisionMovimiento2.setText("Precision: " + String.valueOf(pokemon.getMovimiento2().getPrecision()));
			nombreMovimiento3.setText(pokemon.getMovimiento3().getNombre());
			potenciaMovimiento3.setText("Potencia: " + String.valueOf(pokemon.getMovimiento3().getPotencia())); // Convertir
																												// a
																												// String
			precisionMovimiento3.setText("Precision: " + String.valueOf(pokemon.getMovimiento3().getPrecision()));
			nombreMovimiento4.setText(pokemon.getMovimiento4().getNombre());
			potenciaMovimiento4.setText("Potencia: " + String.valueOf(pokemon.getMovimiento4().getPotencia())); // Convertir
																												// a
																												// String
			precisionMovimiento4.setText("Precision: " + String.valueOf(pokemon.getMovimiento4().getPrecision()));
			// Repite para los otros movimientos si es necesario
		} else {
			nombreMovimiento1.setText("N/A");
			potenciaMovimiento1.setText("N/A");
			precisionMovimiento1.setText("N/A");
			// Repite para los otros movimientos si es necesario
		}
	}

	@FXML
	private void handleAtaque1(ActionEvent event) {
		System.out.println("Ataque 1 PULSADO!");
		realizarAtaque(miPokemon.getMovimiento1());
		System.out.println("Va a atacar el enemigo");
		combatePanel.setVisible(false);
		// Crea una transición de pausa de 2 segundos
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(e -> {
			if (pokemonRival.getVitalidadActual() > 0) {
				realizarContraataque();
			} else if (pokemonRival.getVitalidadActual() <= 0) {
				cargarSiguienteEnemigo();
			}
		});
		pause.play();
	}

	@FXML
	private void handleAtaque2(ActionEvent event) {
		System.out.println("Ataque 2 PULSADO!");
		realizarAtaque(miPokemon.getMovimiento2());
		System.out.println("Va a atacar el enemigo");
		combatePanel.setVisible(false);
		// Crea una transición de pausa de 2 segundos
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(e -> {
			if (pokemonRival.getVitalidadActual() > 0) {
				realizarContraataque();
			} else if (pokemonRival.getVitalidadActual() <= 0) {
				cargarSiguienteEnemigo();
			}
		});
		pause.play();
	}

	@FXML
	private void handleAtaque3(ActionEvent event) {
		System.out.println("Ataque 3 PULSADO!");
		realizarAtaque(miPokemon.getMovimiento3());
		System.out.println("Va a atacar el enemigo");
		combatePanel.setVisible(false);
		// Crea una transición de pausa de 2 segundos
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(e -> {
			if (pokemonRival.getVitalidadActual() > 0) {
				realizarContraataque();
			} else if (pokemonRival.getVitalidadActual() <= 0) {
				cargarSiguienteEnemigo();
			}
		});
		pause.play();
	}
	
	@FXML
	private void handleAtaque4(ActionEvent event) {
		System.out.println("Ataque 4 PULSADO!");
		realizarAtaque(miPokemon.getMovimiento4());
		System.out.println("Va a atacar el enemigo");
		combatePanel.setVisible(false);
		// Crea una transición de pausa de 2 segundos
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(e -> {
			if (pokemonRival.getVitalidadActual() > 0) {
				realizarContraataque();
			} else if (pokemonRival.getVitalidadActual() <= 0) {
				cargarSiguienteEnemigo();
			}
		});
		pause.play();
	}
	
	
	
	private void realizarAtaque(Movimiento movimiento) {
		if (pokemonRival.getVitalidadActual() > 0) {
			double dano = calcularDañoAliado(movimiento, miPokemon, pokemonRival);
			System.out.println("Daño ataque: " + dano);

			pokemonRival.recibirDano((int) dano);
			updateHealthBarEnemy();
			System.out.println("Vida del enemigo: " + pokemonRival.getVitalidadActual());
			if (pokemonRival.getVitalidadActual() <= 0) {
				System.out.println("¡El rival ha sido derrotado!");
				
				// Finalizar el combate o preparar la siguiente ronda
			}
		}
	}

	private void realizarAtaqueEnemigo(Movimiento movimiento) {
		if (miPokemon.getVitalidadActual() > 0) {
			double dano = calcularDañoEnemigo(movimiento, miPokemon, pokemonRival);
			System.out.println("Daño ataque: " + dano);

			miPokemon.recibirDano((int) dano);
			updateHealthBarPlayer();
			System.out.println("Vida del aliado: " + miPokemon.getVitalidadActual());
			if (miPokemon.getVitalidadActual() <= 0) {
				System.out.println("¡El aliado ha sido derrotado!");
				cargarSiguientePokemonEquipo();
			}
		}
	}

	private double calcularDañoAliado(Movimiento movimiento, EquipoPokemon.Pokemon miPokemon,
			application.Pokemon pokemonRival) {
		int ataque = miPokemon.getAtaque();
		int ataqueEsp = miPokemon.getAtaqueEspecial();// Considera si es un ataque especial
		int potencia = movimiento.getPotencia();
		int defensa = pokemonRival.getDefensa();
		int defensaEsp = pokemonRival.getDefensaEspecial();
		String tipoDaño = movimiento.getTipoDaño();
		System.out.println("tipo de daño del movimiento: " + tipoDaño);
		double B = 1;
		double E = 1;
		
		if (tipoDaño == "fisico") {
			return 0.01 * B * E * 100 * (((0.2 * 25 + 1) * ataque * potencia) / (25 * defensa) + 2);
		}else {
			return 0.01 * B * E * 100 * (((0.2 * 25 + 1) * ataqueEsp * potencia) / (25 * defensaEsp) + 2);
		}
	}

	private double calcularDañoEnemigo(Movimiento movimiento, EquipoPokemon.Pokemon miPokemon,
			application.Pokemon pokemonRival) {
		int ataque = pokemonRival.getAtaque(); // Considera si es un ataque especial
		int ataqueEsp = pokemonRival.getAtaqueEspecial();
		int potencia = movimiento.getPotencia();
		int defensa = miPokemon.getDefensa(); // Considera si debe usar defensa especial
		int defensaEsp = miPokemon.getDefensaEspecial();
		String tipoDaño = movimiento.getTipoDaño();
		System.out.println("tipo de daño del movimiento: " + tipoDaño);
		double B = 1;
		double E = 1;
		
		if (tipoDaño == "fisico") {
			System.out.println("Tipo de daño usado: Fisico");
			return 0.01 * B * E * 100 * (((0.2 * 25 + 1) * ataque * potencia) / (25 * defensa) + 2);
			
		}else {
			System.out.println("Tipo de daño usado: Especial");
			return 0.01 * B * E * 100 * (((0.2 * 25 + 1) * ataqueEsp * potencia) / (25 * defensaEsp) + 2);
		}
	}

	private void realizarContraataque() {
		Movimiento movimientoEnemigo = pokemonRival.getMovimiento1(); // Simplicidad: elige siempre el primer movimiento
		realizarAtaqueEnemigo(movimientoEnemigo);
		System.out.println("contrataque realizao");
	}
}
