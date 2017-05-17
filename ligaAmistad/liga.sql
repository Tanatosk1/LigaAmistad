-- phpMyAdmin SQL Dump
-- version 4.6.4deb1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 17-05-2017 a las 23:11:31
-- Versión del servidor: 5.7.18-0ubuntu0.16.10.1
-- Versión de PHP: 7.0.15-0ubuntu0.16.10.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `liga`
--
CREATE DATABASE IF NOT EXISTS `liga` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `liga`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Arbitros`
--

DROP TABLE IF EXISTS `Arbitros`;
CREATE TABLE IF NOT EXISTS `Arbitros` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de los arbitros',
  `NOMBRE` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del Arbitro',
  `APELLIDOS` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Apellidos del Arbitro',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Arbitros`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campeonato`
--

DROP TABLE IF EXISTS `Campeonato`;
CREATE TABLE IF NOT EXISTS `Campeonato` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de cada partido',
  `JORNADA` int(10) UNSIGNED NOT NULL COMMENT 'Jornada del campeonato',
  `FECHA` date DEFAULT NULL COMMENT 'Fecha del partido',
  `HORA` time DEFAULT NULL COMMENT 'Hora del partido',
  `ID_LOCAL` int(11) NOT NULL COMMENT 'ID del equipo que juega como local',
  `ID_VISITANTE` int(11) NOT NULL COMMENT 'ID del equipo que juega como visitante',
  `ID_CAMPO` int(11) DEFAULT NULL COMMENT 'ID del campo donde se juega',
  `JUGADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Establece si el partido se realiza o no (0 NO realizado, 1 realizado)',
  `ID_ARBITRO` int(11) DEFAULT NULL COMMENT 'ID del arbitro del partido',
  PRIMARY KEY (`ID`),
  KEY `LOCAL` (`ID_LOCAL`),
  KEY `VISITANTE` (`ID_VISITANTE`),
  KEY `CAMPO` (`ID_CAMPO`),
  KEY `ID_ARBITRO` (`ID_ARBITRO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Campeonato`:
--   `ID_CAMPO`
--       `Campos` -> `ID`
--   `ID_LOCAL`
--       `Equipos` -> `ID`
--   `ID_VISITANTE`
--       `Equipos` -> `ID`
--   `ID_ARBITRO`
--       `Arbitros` -> `ID`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campos`
--

DROP TABLE IF EXISTS `Campos`;
CREATE TABLE IF NOT EXISTS `Campos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del campo',
  `CAMPO` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del campo',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Campos`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cam_Horarios`
--

DROP TABLE IF EXISTS `Cam_Horarios`;
CREATE TABLE IF NOT EXISTS `Cam_Horarios` (
  `ID_CAMPO` int(11) NOT NULL,
  `ID_DIA` int(11) NOT NULL,
  `ID_HORA` int(11) NOT NULL,
  KEY `ID_CAMPO` (`ID_CAMPO`),
  KEY `ID_DIA` (`ID_DIA`),
  KEY `ID_HORA` (`ID_HORA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Cam_Horarios`:
--   `ID_CAMPO`
--       `Campos` -> `ID`
--   `ID_HORA`
--       `Hora` -> `ID`
--   `ID_DIA`
--       `Dias` -> `ID`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Competicion`
--

DROP TABLE IF EXISTS `Competicion`;
CREATE TABLE IF NOT EXISTS `Competicion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la competición',
  `COMPETICION` varchar(20) NOT NULL COMMENT 'Descripción de la competición',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Competicion`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Dias`
--

DROP TABLE IF EXISTS `Dias`;
CREATE TABLE IF NOT EXISTS `Dias` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID para los días',
  `DIA` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Día de la Semana',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Dias`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Division`
--

DROP TABLE IF EXISTS `Division`;
CREATE TABLE IF NOT EXISTS `Division` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la división',
  `DIVISION` varchar(15) DEFAULT NULL COMMENT 'Descripción de la división',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Division`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Equipos`
--

DROP TABLE IF EXISTS `Equipos`;
CREATE TABLE IF NOT EXISTS `Equipos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del Equipo',
  `NOMBRE` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del equipo',
  `ID_COMPETICION` int(11) DEFAULT NULL COMMENT 'ID de la competición en la que participa',
  `ID_DIVISION` int(11) DEFAULT NULL COMMENT 'ID de la división a la que pertenece',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Equipo congelado (0 no congelado, 1 congelado)',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOMBRE` (`NOMBRE`),
  KEY `DIVISION` (`ID_DIVISION`),
  KEY `COMPETICION` (`ID_COMPETICION`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `Equipos`:
--   `ID_DIVISION`
--       `Division` -> `ID`
--   `ID_COMPETICION`
--       `Competicion` -> `ID`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Festivos`
--

DROP TABLE IF EXISTS `Festivos`;
CREATE TABLE IF NOT EXISTS `Festivos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID del día festivo',
  `FECHA` date NOT NULL COMMENT 'Día festivo',
  `MOTIVO` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Festivos`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Hora`
--

DROP TABLE IF EXISTS `Hora`;
CREATE TABLE IF NOT EXISTS `Hora` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID de las horas de campos',
  `HORA` varchar(5) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Horas disponibles',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `Hora`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Restricciones`
--

DROP TABLE IF EXISTS `Restricciones`;
CREATE TABLE IF NOT EXISTS `Restricciones` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la restricción',
  `ID_EQUIPO` int(11) DEFAULT NULL COMMENT 'ID del equipo con el que no puede coincidir otro equipo',
  `ID_DIA` int(11) DEFAULT NULL COMMENT 'ID de la tabla con el día que no pueda jugar',
  `ID_HORA` int(11) DEFAULT NULL COMMENT 'ID de la hora que no pueda jugar',
  `ID_CAMPO` int(11) DEFAULT NULL COMMENT 'ID del campo en el que no pueda jugar',
  `ID_COINCIDE` int(11) DEFAULT NULL COMMENT 'ID del equipo con el que no puede coincidir',
  PRIMARY KEY (`ID`),
  KEY `EQUIPO` (`ID_EQUIPO`),
  KEY `ID_DIA` (`ID_DIA`),
  KEY `ID_HORA` (`ID_HORA`),
  KEY `ID_CAMPO` (`ID_CAMPO`),
  KEY `ID_COINCIDE` (`ID_COINCIDE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `Restricciones`:
--   `ID_EQUIPO`
--       `Equipos` -> `ID`
--   `ID_CAMPO`
--       `Campos` -> `ID`
--   `ID_COINCIDE`
--       `Equipos` -> `ID`
--   `ID_DIA`
--       `Dias` -> `ID`
--   `ID_HORA`
--       `Hora` -> `ID`
--

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Campeonato`
--
ALTER TABLE `Campeonato`
  ADD CONSTRAINT `Campeonato_ibfk_3` FOREIGN KEY (`ID_CAMPO`) REFERENCES `Campos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Campeonato_ibfk_5` FOREIGN KEY (`ID_LOCAL`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Campeonato_ibfk_6` FOREIGN KEY (`ID_VISITANTE`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Campeonato_ibfk_7` FOREIGN KEY (`ID_ARBITRO`) REFERENCES `Arbitros` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `Cam_Horarios`
--
ALTER TABLE `Cam_Horarios`
  ADD CONSTRAINT `Cam_Horarios_ibfk_1` FOREIGN KEY (`ID_CAMPO`) REFERENCES `Campos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cam_Horarios_ibfk_2` FOREIGN KEY (`ID_HORA`) REFERENCES `Hora` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cam_Horarios_ibfk_3` FOREIGN KEY (`ID_DIA`) REFERENCES `Dias` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Equipos`
--
ALTER TABLE `Equipos`
  ADD CONSTRAINT `Equipos_ibfk_1` FOREIGN KEY (`ID_DIVISION`) REFERENCES `Division` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Equipos_ibfk_2` FOREIGN KEY (`ID_COMPETICION`) REFERENCES `Competicion` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `Restricciones`
--
ALTER TABLE `Restricciones`
  ADD CONSTRAINT `Restricciones_ibfk_1` FOREIGN KEY (`ID_EQUIPO`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Restricciones_ibfk_2` FOREIGN KEY (`ID_CAMPO`) REFERENCES `Campos` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Restricciones_ibfk_3` FOREIGN KEY (`ID_COINCIDE`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Restricciones_ibfk_4` FOREIGN KEY (`ID_DIA`) REFERENCES `Dias` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Restricciones_ibfk_5` FOREIGN KEY (`ID_HORA`) REFERENCES `Hora` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;


--
-- Metadatos
--
USE `phpmyadmin`;

--
-- Metadatos para la tabla Arbitros
--

--
-- Metadatos para la tabla Campeonato
--

--
-- Metadatos para la tabla Campos
--

--
-- Metadatos para la tabla Cam_Horarios
--

--
-- Metadatos para la tabla Competicion
--

--
-- Metadatos para la tabla Dias
--

--
-- Metadatos para la tabla Division
--

--
-- Metadatos para la tabla Equipos
--

--
-- Metadatos para la tabla Festivos
--

--
-- Metadatos para la tabla Hora
--

--
-- Metadatos para la tabla Restricciones
--

--
-- Metadatos para la base de datos liga
--

--
-- Volcado de datos para la tabla `pma__pdf_pages`
--

INSERT INTO `pma__pdf_pages` (`db_name`, `page_descr`) VALUES
('liga', 'Relaciones');

SET @LAST_PAGE = LAST_INSERT_ID();

--
-- Volcado de datos para la tabla `pma__table_coords`
--

INSERT INTO `pma__table_coords` (`db_name`, `table_name`, `pdf_page_number`, `x`, `y`) VALUES
('liga', 'Arbitros', @LAST_PAGE, 63, 268),
('liga', 'Cam_Horarios', @LAST_PAGE, 745, 304),
('liga', 'Campeonato', @LAST_PAGE, 339, 179),
('liga', 'Campos', @LAST_PAGE, 561, 299),
('liga', 'Competicion', @LAST_PAGE, 89, 18),
('liga', 'Dias', @LAST_PAGE, 775, 40),
('liga', 'Division', @LAST_PAGE, 127, 108),
('liga', 'Equipos', @LAST_PAGE, 348, 24),
('liga', 'Festivos', @LAST_PAGE, 378, 404),
('liga', 'Hora', @LAST_PAGE, 941, 198),
('liga', 'Restricciones', @LAST_PAGE, 562, 101);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
