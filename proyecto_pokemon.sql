-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-04-2024 a las 12:47:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_pokemon`
--
CREATE DATABASE IF NOT EXISTS `proyecto_pokemon` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyecto_pokemon`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combate`
--

CREATE TABLE `combate` (
  `id_combate` int(11) NOT NULL,
  `fecha_combate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estado` int(11) NOT NULL,
  `nombre_estado` varchar(50) NOT NULL,
  `duracion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id_estado`, `nombre_estado`, `duracion`) VALUES
(1, 'Paralizado', 3),
(2, 'Quemado', 3),
(3, 'Envenenado', 0),
(4, 'Gravemente envenenado', NULL),
(5, 'Dormido', NULL),
(6, 'Congelador', NULL),
(7, 'Pekerus', NULL),
(8, 'Debilitado', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `id_movimiento` int(2) NOT NULL,
  `nombre_movimiento` varchar(20) NOT NULL,
  `tipo_movimiento` varchar(50) DEFAULT NULL,
  `tipo_daño` text DEFAULT NULL,
  `potencia` int(3) DEFAULT NULL,
  `precision` int(11) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `mejora` int(11) DEFAULT NULL,
  `estadistica_mejorada` text DEFAULT NULL,
  `empeora` int(11) DEFAULT NULL,
  `estadistica_empeorada` text DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `movimiento1` varchar(50) DEFAULT NULL,
  `movimiento2` varchar(50) DEFAULT NULL,
  `movimiento3` varchar(50) DEFAULT NULL,
  `movimiento4` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id_movimiento`, `nombre_movimiento`, `tipo_movimiento`, `tipo_daño`, `potencia`, `precision`, `estado`, `mejora`, `estadistica_mejorada`, `empeora`, `estadistica_empeorada`, `duracion`, `movimiento1`, `movimiento2`, `movimiento3`, `movimiento4`) VALUES
(1, 'Burbuja', 'agua', 'Especial', 40, 100, NULL, NULL, NULL, 10, 'Velocidad', NULL, NULL, NULL, NULL, NULL),
(2, 'Cascada', 'agua', 'Fisico', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Hidrobomba', 'agua', 'Especial', 110, 80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Refujio', 'agua', NULL, NULL, 0, NULL, 25, 'Defensa', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Tenaza', 'agua', 'Fisico', 35, 85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Chupavidas', 'bicho', 'Fisico', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'Chupavidas', 'bicho', 'Fisico', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'Impactrueno', 'electrico', 'Especial', 40, 100, 'Paralizado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'Puño trueno', 'electrico', 'Fisico', 75, 100, 'Paralizado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'Rayo', 'electrico', 'Especial', 90, 100, 'Paralizado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'Lengüetazo', 'fantasma', 'Fisico', 30, 100, 'Paralizado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'Puño fuego', 'fuego', 'Fisico', 75, 100, 'Quemado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 'Lanzallamas', 'fuego', 'Especial', 90, 100, 'Quemado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, 'Ascuas', 'fuego', 'Especial', 40, 100, 'Quemado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, 'LLamarada', 'fuego', 'Especial', 110, 85, 'Quemado', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, 'Puño hielo', 'hielo', 'Fisico', 75, 100, 'Congelador', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(17, 'Rayo hielo', 'hielo', 'Especial', 90, 100, 'Congelador', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, 'Ventisca', 'hielo', 'Especial', 110, 70, 'Congelador', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(19, 'Doble patada', 'lucha', 'Fisico', 30, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, 'Patada giro', 'lucha', 'Fisico', 60, 85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, 'Sismico', 'lucha', 'Fisico', 110, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, 'Patada salto alta', 'lucha', 'Fisico', 130, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, 'Latigo cepa', 'planta', 'Fisico', 45, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, 'Rayo solar', 'planta', 'Especial', 120, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, 'Psicorrayo', 'psiquico', 'Especial', 65, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(26, 'Psiquico', NULL, 'Especial', 90, 100, NULL, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL),
(27, 'Avalancha', 'roca', 'Fisico', 75, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(28, 'Lanzarrocas', 'roca', 'Fisico', 120, 70, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(29, 'Mordisco', 'normal', 'Fisico', 60, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(30, 'Excavar', 'tierra', 'Fisico', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, 'hueso palo', 'tierra', 'Fisico', 65, 80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, 'Huesomerang', 'tierra', 'Fisico', 75, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(33, 'Terremoto', 'tierra', 'Fisico', 100, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, 'Ácido', 'veneno', 'Especial', 40, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(35, 'Picotazo Veneno', 'veneno', 'Fisico', 15, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, 'Residuos', 'veneno', 'Especial', 65, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, 'Ataque ala', 'volador', 'Fisico', 60, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(38, 'Pico Taladro', 'volador', 'Fisico', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(39, 'Picotazo', 'volador', 'Fisico', 35, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(40, 'Tornado', 'volador', 'Especial', 40, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(41, 'Vuelo', 'volador', 'Fisico', 90, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(42, 'Agarre', 'normal', 'Fisico', 55, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(43, 'Arañazo', 'normal', 'Fisico', 40, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(44, 'Ataque Furia', 'normal', 'Fisico', 15, 85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(45, 'Atizar', 'normal', 'Fisico', 80, 75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(46, 'Autodestruccion', 'normal', 'Fisico', 999, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(47, 'Bomboclat', 'normal', 'Fisico', 100, 75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(48, 'Bomba Sónica', 'normal', 'Especial', 20, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(49, 'Bombardeo Israelí', 'normal', 'Fisico', 15, 85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(50, 'Cabezazo', 'normal', 'Fisico', 130, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(51, 'Cornada', 'normal', 'Fisico', 65, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(52, 'Soplamocos', 'normal', 'Fisico', 75, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(53, 'Navajazo', 'normal', 'Fisico', 70, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(54, 'Inmolación', 'normal', 'Fisico', 999, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(55, 'Toña', 'normal', 'Fisico', 70, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(56, 'Patadón', 'normal', 'Fisico', 85, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(57, 'Hiperrayo', 'normal', 'Especial', 150, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(58, 'Guillotina', 'normal', 'Fisico', 999, 30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(59, 'Megatoña', 'normal', 'Fisico', 120, 75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(60, 'Meteoros', 'normal', 'Especial', 60, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(61, 'Pisotón', 'normal', 'Fisico', 65, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(62, 'Viento cortante', 'normal', 'Especial', 80, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objetos`
--

CREATE TABLE `objetos` (
  `id_objeto` int(2) NOT NULL,
  `nombre` text NOT NULL,
  `estadistica_aumentada` enum('hp','ataque','defensa','ataque_especial','defensa_especial','velocidad','estamina') DEFAULT NULL,
  `porcentaje-aumentado` int(2) DEFAULT NULL,
  `estadistica_aumentada2` enum('hp','ataque','defensa','ataque_especial','defensa_especial','velocidad''hp','ataque','defensa','ataque_especial','defensa_especial','velocidad','estamina') DEFAULT NULL,
  `porcentaje_aumentado2` int(2) DEFAULT NULL,
  `estadistica_disminuida` enum('hp','ataque','defensa','ataque_especial','defensa_especial','velocidad','estamina') DEFAULT NULL,
  `pororcentaje-disminuido` int(2) DEFAULT NULL,
  `estadistica_disminuida2` enum('hp','ataque','defensa','ataque_especial','defensa_especial','velocidad','estamina') DEFAULT NULL,
  `porcentaje_disminuido2` int(2) DEFAULT NULL,
  `cantidad` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `objetos`
--

INSERT INTO `objetos` (`id_objeto`, `nombre`, `estadistica_aumentada`, `porcentaje-aumentado`, `estadistica_aumentada2`, `porcentaje_aumentado2`, `estadistica_disminuida`, `pororcentaje-disminuido`, `estadistica_disminuida2`, `porcentaje_disminuido2`, `cantidad`) VALUES
(1, 'pesa', 'ataque', 20, 'defensa', 20, 'velocidad', 20, NULL, NULL, 0),
(2, 'pluma', 'velocidad', 30, NULL, NULL, 'defensa', 20, 'defensa_especial', 20, 0),
(3, 'chaleco', 'defensa', 20, 'defensa_especial', 20, 'velocidad', 15, 'ataque', 15, 0),
(4, 'baston', 'estamina', 20, NULL, NULL, 'velocidad', 15, NULL, NULL, 0),
(5, 'pilas', 'estamina', 50, NULL, NULL, 'defensa_especial', 30, NULL, NULL, 0),
(6, 'anillo unico', 'ataque', 10, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(7, 'pokeball', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokedex`
--

CREATE TABLE `pokedex` (
  `id_pokemon` int(2) NOT NULL,
  `nombre` text NOT NULL COMMENT 'El nombre de el pokemon',
  `vitalidad` int(2) NOT NULL COMMENT 'estadistica base de vida',
  `ataque` int(3) NOT NULL COMMENT 'estadistica base de ataque',
  `defensa` int(3) NOT NULL COMMENT 'estadistica base de defensa',
  `ataque_especial` int(3) NOT NULL COMMENT 'estadistica base de ataque especial',
  `defensa_especial` int(3) NOT NULL COMMENT 'estadistica base de defensa especial',
  `velocidad` int(3) NOT NULL COMMENT 'estadistica base de velocidad',
  `tipo1` varchar(50) DEFAULT NULL,
  `tipo2` varchar(50) DEFAULT NULL,
  `imagen` text DEFAULT NULL COMMENT 'la imagen del pokemon'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokedex`
--

INSERT INTO `pokedex` (`id_pokemon`, `nombre`, `vitalidad`, `ataque`, `defensa`, `ataque_especial`, `defensa_especial`, `velocidad`, `tipo1`, `tipo2`, `imagen`) VALUES
(1, 'Venusaur', 80, 82, 83, 100, 100, 80, 'planta', 'veneno', 'venusaurFront.png'),
(2, 'Charizard', 78, 84, 78, 109, 85, 100, 'fuego', 'volador', 'charizardFront.png'),
(3, 'Blastoise', 79, 83, 100, 85, 105, 78, 'agua', NULL, 'blastoiseFront.png'),
(4, 'ButterFree', 60, 45, 50, 90, 80, 70, 'bicho', 'volador', 'butterfreeFront.png'),
(5, 'Beedrill', 65, 90, 40, 45, 80, 75, 'bicho', 'veneno', 'beedrillFront.png'),
(6, 'Pidgeot', 83, 80, 75, 70, 70, 101, 'normal', 'volador', 'pidgeotFront.png'),
(7, 'Raticate', 55, 81, 60, 50, 70, 97, 'normal', NULL, 'raticateFront.png'),
(8, 'Fearow', 65, 90, 65, 61, 61, 100, 'normal', 'volador', 'fearowFront.png'),
(9, 'Arbok', 60, 95, 69, 65, 79, 80, 'veneno', NULL, 'arbokFront.png'),
(10, 'Pikachu', 35, 55, 40, 50, 50, 90, 'electrico', NULL, 'pikachuFront.png'),
(11, 'Sandslash', 75, 100, 110, 45, 55, 65, 'tierra', NULL, 'sandslashFront.png'),
(12, 'Nidoqueen', 90, 92, 87, 75, 85, 76, 'veneno', 'tierra', 'nidoqueenFront.png'),
(13, 'Nidoking', 81, 102, 77, 85, 75, 85, 'veneno', 'tierra', 'nidokingFront.png'),
(14, 'Ninetales', 73, 76, 75, 81, 100, 100, 'fuego', NULL, 'ninetalesFront.png'),
(15, 'Golbat', 75, 80, 70, 65, 75, 90, 'veneno', 'volador', 'golbatFront.png'),
(16, 'Vileplume', 75, 80, 85, 110, 90, 50, 'planta', 'veneno', 'vileplumeFront.png'),
(17, 'Parasect', 60, 95, 80, 60, 80, 30, 'bicho', 'tierra', 'parasectFront.png'),
(18, 'Dugtrio', 35, 100, 50, 50, 70, 120, 'tierra', NULL, 'dugtrioFront.png'),
(19, 'Meowth', 40, 45, 35, 40, 40, 90, 'normal', NULL, 'meowthFront.png'),
(20, 'Golduck', 80, 82, 78, 95, 80, 85, 'agua', NULL, 'golduckFront.png'),
(21, 'Primeape', 65, 105, 60, 60, 70, 95, 'lucha', NULL, 'primeapeFront.png'),
(22, 'Arcanine', 90, 110, 80, 100, 80, 95, 'fuego', NULL, 'arcanineFront.png'),
(23, 'Poliwrath', 90, 95, 95, 70, 90, 70, 'agua', 'lucha', 'poliwrathFront.png'),
(24, 'Alakazam', 55, 50, 45, 135, 95, 120, 'psiquico', NULL, 'alakazamFront.png'),
(25, 'Machamp', 90, 130, 80, 65, 85, 55, 'lucha', NULL, 'machampFront.png'),
(26, 'Victreebel', 80, 105, 65, 100, 70, 70, 'planta', 'veneno', 'victreebelFront.png'),
(27, 'Tentacruel', 80, 70, 65, 80, 120, 100, 'agua', 'veneno', 'tentacruelFront.png'),
(28, 'Golem', 80, 120, 130, 55, 65, 45, 'roca', 'tierra', 'golemFront.png'),
(29, 'Rapidash', 65, 100, 70, 80, 80, 105, 'fuego', NULL, 'rapidashFront.png'),
(30, 'Slowbro', 95, 75, 110, 100, 80, 30, 'agua', 'psiquico', 'slowbroFront.png'),
(31, 'Magneton', 50, 60, 95, 120, 70, 70, 'electrico', 'volador', 'magnetonFront.png'),
(32, 'Farfetch\'d', 52, 90, 55, 58, 62, 60, 'normal', 'volador', 'farfetch\'dFront.png'),
(33, 'Dodrio', 60, 110, 70, 60, 60, 110, 'normal', 'volador', 'dodrioFront.png'),
(34, 'Dewgong', 90, 70, 80, 70, 95, 70, 'agua', 'hielo', 'dewgongFront.png'),
(35, 'Muk', 105, 105, 75, 65, 100, 50, 'veneno', NULL, 'mukFront.png'),
(36, 'Cloyster', 50, 95, 180, 85, 45, 70, 'agua', 'hielo', 'cloysterFront.png'),
(37, 'Gengar', 60, 65, 60, 130, 75, 110, 'fantasma', 'veneno', 'gengarFront.png'),
(38, 'Hypno', 85, 73, 70, 73, 115, 67, 'psiquico', NULL, 'hypnoFront.png'),
(39, 'Kingler', 55, 130, 115, 50, 50, 75, 'agua', NULL, 'kinglerFront.png'),
(40, 'Electrode', 60, 50, 70, 80, 80, 150, 'electrico', NULL, 'electrodeFront.png'),
(41, 'Exeggutor', 95, 95, 85, 125, 75, 55, 'planta', 'psiquico', 'exeggutorFront.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokemons`
--

CREATE TABLE `pokemons` (
  `id_pokemonCreado` int(11) NOT NULL,
  `mote` text DEFAULT NULL,
  `nombre` text DEFAULT NULL,
  `vitalidad` int(2) DEFAULT NULL,
  `vitalidad_actual` int(11) DEFAULT NULL,
  `ataque` int(3) DEFAULT NULL,
  `defensa` int(3) DEFAULT NULL,
  `ataque_especial` int(3) DEFAULT NULL,
  `defensa_especial` int(3) DEFAULT NULL,
  `velocidad` int(3) DEFAULT NULL,
  `fertilidad` int(11) NOT NULL DEFAULT 5,
  `estamina` int(11) NOT NULL DEFAULT 10,
  `sexo` int(11) NOT NULL,
  `tipo1` varchar(50) DEFAULT NULL,
  `tipo2` varchar(50) DEFAULT NULL,
  `movimiento1` varchar(50) DEFAULT NULL,
  `movimiento2` varchar(50) DEFAULT NULL,
  `movimiento3` varchar(50) DEFAULT NULL,
  `movimiento4` varchar(50) DEFAULT NULL,
  `EXP` int(11) NOT NULL DEFAULT 0,
  `nivel` int(11) NOT NULL DEFAULT 1,
  `imagen` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokemons`
--

INSERT INTO `pokemons` (`id_pokemonCreado`, `mote`, `nombre`, `vitalidad`, `vitalidad_actual`, `ataque`, `defensa`, `ataque_especial`, `defensa_especial`, `velocidad`, `fertilidad`, `estamina`, `sexo`, `tipo1`, `tipo2`, `movimiento1`, `movimiento2`, `movimiento3`, `movimiento4`, `EXP`, `nivel`, `imagen`) VALUES
(1, 'Josema', 'Blastoise', 79, NULL, 83, 100, 85, 105, 78, 5, 10, 1, 'agua', NULL, NULL, NULL, NULL, NULL, 0, 1, NULL),
(2, NULL, 'Farfetch\'d', 52, NULL, 90, 55, 58, 62, 60, 5, 10, 1, 'normal', 'volador', NULL, NULL, NULL, NULL, 0, 1, NULL),
(3, NULL, 'Cloyster', 50, NULL, 95, 180, 85, 45, 70, 5, 10, 1, 'agua', 'hielo', NULL, NULL, NULL, NULL, 0, 1, NULL),
(4, NULL, 'Electrode', 60, NULL, 50, 70, 80, 80, 150, 5, 10, 1, 'electrico', NULL, NULL, NULL, NULL, NULL, 0, 1, NULL),
(5, NULL, 'Tentacruel', 80, NULL, 70, 65, 80, 120, 100, 5, 10, 1, 'agua', 'veneno', NULL, NULL, NULL, NULL, 0, 1, NULL),
(6, NULL, 'Charizard', 78, NULL, 84, 78, 109, 85, 100, 5, 10, 1, 'fuego', 'volador', NULL, NULL, NULL, NULL, 0, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id_tipo` int(11) NOT NULL,
  `nombre_tipo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id_tipo`, `nombre_tipo`) VALUES
(1, 'agua'),
(2, 'bicho'),
(3, 'dragon'),
(4, 'electrico'),
(5, 'fantasma'),
(6, 'fuego'),
(7, 'hielo'),
(8, 'lucha'),
(9, 'normal'),
(16, 'normal'),
(10, 'planta'),
(11, 'psiquico'),
(12, 'roca'),
(13, 'tierra'),
(14, 'veneno'),
(15, 'volador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id_turno` int(11) NOT NULL,
  `accion_entrenador` enum('movimiento','usar_objeto','cambiar_pokemon') DEFAULT NULL,
  `accion_rival` enum('movimiento','usar_objeto','cambiar_pokemon') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` text NOT NULL,
  `contraseña_usuario` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `contraseña_usuario`) VALUES
(1, 'Alberto', '1234'),
(2, '1', '1'),
(3, 'Luisre', '1234'),
(4, 'Jorge', '1234'),
(5, 'pachueco', '1234'),
(6, 'Diego', '1234'),
(7, 'Antonio', '1234'),
(8, 'Warsan', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `combate`
--
ALTER TABLE `combate`
  ADD PRIMARY KEY (`id_combate`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estado`),
  ADD UNIQUE KEY `nombre_estado` (`nombre_estado`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `estado` (`estado`),
  ADD KEY `fk_tipo_movimiento` (`tipo_movimiento`),
  ADD KEY `idx_nombre_movimiento` (`nombre_movimiento`),
  ADD KEY `fk_movimiento2` (`movimiento2`),
  ADD KEY `fk_movimiento3` (`movimiento3`),
  ADD KEY `fk_movimiento4` (`movimiento4`),
  ADD KEY `fk_movimiento1_new` (`movimiento1`);

--
-- Indices de la tabla `objetos`
--
ALTER TABLE `objetos`
  ADD PRIMARY KEY (`id_objeto`);

--
-- Indices de la tabla `pokedex`
--
ALTER TABLE `pokedex`
  ADD PRIMARY KEY (`id_pokemon`),
  ADD KEY `fk_tipo1` (`tipo1`),
  ADD KEY `fk_tipo2` (`tipo2`);

--
-- Indices de la tabla `pokemons`
--
ALTER TABLE `pokemons`
  ADD PRIMARY KEY (`id_pokemonCreado`),
  ADD KEY `fk_movimiento13` (`movimiento1`),
  ADD KEY `fk_movimiento24` (`movimiento2`),
  ADD KEY `fk_movimiento35` (`movimiento3`),
  ADD KEY `fk_movimiento46` (`movimiento4`),
  ADD KEY `fk_tipo12` (`tipo1`),
  ADD KEY `fk_tipo22` (`tipo2`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id_tipo`),
  ADD KEY `nombre_tipo_index` (`nombre_tipo`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id_turno`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `combate`
--
ALTER TABLE `combate`
  MODIFY `id_combate` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `id_movimiento` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `objetos`
--
ALTER TABLE `objetos`
  MODIFY `id_objeto` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `pokedex`
--
ALTER TABLE `pokedex`
  MODIFY `id_pokemon` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `pokemons`
--
ALTER TABLE `pokemons`
  MODIFY `id_pokemonCreado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `fk_movimiento1` FOREIGN KEY (`movimiento1`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento1_new` FOREIGN KEY (`movimiento1`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento2` FOREIGN KEY (`movimiento2`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento3` FOREIGN KEY (`movimiento3`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento4` FOREIGN KEY (`movimiento4`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_tipo_movimiento` FOREIGN KEY (`tipo_movimiento`) REFERENCES `tipos` (`nombre_tipo`),
  ADD CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `estados` (`nombre_estado`);

--
-- Filtros para la tabla `pokedex`
--
ALTER TABLE `pokedex`
  ADD CONSTRAINT `fk_tipo1` FOREIGN KEY (`tipo1`) REFERENCES `tipos` (`nombre_tipo`),
  ADD CONSTRAINT `fk_tipo2` FOREIGN KEY (`tipo2`) REFERENCES `tipos` (`nombre_tipo`);

--
-- Filtros para la tabla `pokemons`
--
ALTER TABLE `pokemons`
  ADD CONSTRAINT `fk_movimiento13` FOREIGN KEY (`movimiento1`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento24` FOREIGN KEY (`movimiento2`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento35` FOREIGN KEY (`movimiento3`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_movimiento46` FOREIGN KEY (`movimiento4`) REFERENCES `movimientos` (`nombre_movimiento`),
  ADD CONSTRAINT `fk_tipo12` FOREIGN KEY (`tipo1`) REFERENCES `tipos` (`nombre_tipo`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tipo22` FOREIGN KEY (`tipo2`) REFERENCES `tipos` (`nombre_tipo`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
