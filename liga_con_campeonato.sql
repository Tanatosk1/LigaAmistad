-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 26-07-2017 a las 21:15:24
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `liga`
--
CREATE DATABASE IF NOT EXISTS `liga` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `liga`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbitros`
--

CREATE TABLE IF NOT EXISTS `arbitros` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de los arbitros',
  `NOMBRE` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del Arbitro',
  `APELLIDOS` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Apellidos del Arbitro'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `arbitros`
--

TRUNCATE TABLE `arbitros`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

CREATE TABLE IF NOT EXISTS `campeonato` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de cada partido',
  `JORNADA` int(10) unsigned NOT NULL COMMENT 'Jornada del campeonato',
  `FECHA` date DEFAULT NULL COMMENT 'Fecha del partido',
  `HORA` time DEFAULT NULL COMMENT 'Hora del partido',
  `ID_LOCAL` int(11) NOT NULL COMMENT 'ID del equipo que juega como local',
  `ID_VISITANTE` int(11) NOT NULL COMMENT 'ID del equipo que juega como visitante',
  `ID_CAMPO` int(11) DEFAULT NULL COMMENT 'ID del campo donde se juega',
  `JUGADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Establece si el partido se realiza o no (0 NO realizado, 1 realizado)',
  `ID_ARBITRO` int(11) DEFAULT NULL COMMENT 'ID del arbitro del partido'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `campeonato`
--

TRUNCATE TABLE `campeonato`;


--
-- Estructura de tabla para la tabla `campos`
--

CREATE TABLE IF NOT EXISTS `campos` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación del campo',
  `CAMPO` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del campo',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Campo congelado (0 = NO congelado; 1 = Congelado)'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `campos`
--

TRUNCATE TABLE `campos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cam_horarios`
--

CREATE TABLE IF NOT EXISTS `cam_horarios` (
  `ID_CAMPO` int(11) NOT NULL,
  `ID_DIA` int(11) DEFAULT NULL,
  `ID_HORA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `cam_horarios`
--

TRUNCATE TABLE `cam_horarios`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `competicion`
--

CREATE TABLE IF NOT EXISTS `competicion` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la competición',
  `COMPETICION` varchar(20) NOT NULL COMMENT 'Descripción de la competición'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `competicion`
--

TRUNCATE TABLE `competicion`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dias`
--

CREATE TABLE IF NOT EXISTS `dias` (
  `ID` int(11) NOT NULL COMMENT 'ID para los días',
  `DIA` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Día de la Semana'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `dias`
--

TRUNCATE TABLE `dias`;
--
-- Volcado de datos para la tabla `dias`
--

INSERT INTO `dias` (`ID`, `DIA`) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes'),
(6, 'Sábado'),
(7, 'Domingo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `division`
--

CREATE TABLE IF NOT EXISTS `division` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la división',
  `DIVISION` varchar(15) DEFAULT NULL COMMENT 'Descripción de la división'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `division`
--

TRUNCATE TABLE `division`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

CREATE TABLE IF NOT EXISTS `equipos` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación del Equipo',
  `NOMBRE` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del equipo',
  `ID_COMPETICION` int(11) DEFAULT NULL COMMENT 'ID de la competición en la que participa',
  `ID_DIVISION` int(11) DEFAULT NULL COMMENT 'ID de la división a la que pertenece',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Equipo congelado (0 no congelado, 1 congelado)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Truncar tablas antes de insertar `equipos`
--

TRUNCATE TABLE `equipos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `festivos`
--

CREATE TABLE IF NOT EXISTS `festivos` (
  `ID` int(11) NOT NULL COMMENT 'ID del día festivo',
  `FECHA` date NOT NULL COMMENT 'Día festivo',
  `MOTIVO` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `festivos`
--

TRUNCATE TABLE `festivos`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hora`
--

CREATE TABLE IF NOT EXISTS `hora` (
  `ID` int(11) NOT NULL COMMENT 'ID de las horas de campos',
  `HORA` varchar(5) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Horas disponibles',
  `HORARIO` varchar(25) NOT NULL COMMENT 'Primera o Segunda Hora'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `hora`
--

TRUNCATE TABLE `hora`;
--
-- Volcado de datos para la tabla `hora`
--

INSERT INTO `hora` (`ID`, `HORA`, `HORARIO`) VALUES
(1, '8:30', 'Primera'),
(2, '9:30', 'Segunda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restricciones`
--

CREATE TABLE IF NOT EXISTS `restricciones` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la restricción',
  `ID_EQUIPO` int(11) NOT NULL COMMENT 'ID del equipo con el que no puede coincidir otro equipo',
  `ID_DIA` int(11) DEFAULT NULL COMMENT 'ID de la tabla con el día que no pueda jugar',
  `HORA` int(2) DEFAULT NULL COMMENT 'Hora que no puede jugar (Primera, Segunda, Ambas)',
  `ID_CAMPO` int(11) DEFAULT NULL COMMENT 'ID del campo en el que no pueda jugar',
  `ID_COINCIDE` int(11) DEFAULT NULL COMMENT 'ID del equipo con el que no puede coincidir'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Truncar tablas antes de insertar `restricciones`
--

TRUNCATE TABLE `restricciones`;
--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbitros`
--
ALTER TABLE `arbitros`
  ADD PRIMARY KEY (`ID`);

  --
-- Indices de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `LOCAL` (`ID_LOCAL`),
  ADD KEY `VISITANTE` (`ID_VISITANTE`),
  ADD KEY `CAMPO` (`ID_CAMPO`),
  ADD KEY `ID_ARBITRO` (`ID_ARBITRO`);
  
--
-- Indices de la tabla `campos`
--
ALTER TABLE `campos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `cam_horarios`
--
ALTER TABLE `cam_horarios`
  ADD KEY `ID_CAMPO` (`ID_CAMPO`),
  ADD KEY `ID_DIA` (`ID_DIA`),
  ADD KEY `ID_HORA` (`ID_HORA`);

--
-- Indices de la tabla `competicion`
--
ALTER TABLE `competicion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `dias`
--
ALTER TABLE `dias`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `division`
--
ALTER TABLE `division`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD KEY `ID_COMPETICION` (`ID_COMPETICION`),
  ADD KEY `ID_DIVISION` (`ID_DIVISION`);

--
-- Indices de la tabla `festivos`
--
ALTER TABLE `festivos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `hora`
--
ALTER TABLE `hora`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `restricciones`
--
ALTER TABLE `restricciones`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `EQUIPO` (`ID_EQUIPO`),
  ADD KEY `ID_DIA` (`ID_DIA`),
  ADD KEY `ID_HORA` (`HORA`),
  ADD KEY `ID_CAMPO` (`ID_CAMPO`),
  ADD KEY `ID_COINCIDE` (`ID_COINCIDE`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbitros`
--
ALTER TABLE `arbitros`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de los arbitros';
  
--
-- AUTO_INCREMENT de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de partidos';
  
--
-- AUTO_INCREMENT de la tabla `campos`
--
ALTER TABLE `campos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del campo';
--
-- AUTO_INCREMENT de la tabla `competicion`
--
ALTER TABLE `competicion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la competición';
--
-- AUTO_INCREMENT de la tabla `dias`
--
ALTER TABLE `dias`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID para los días',AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `division`
--
ALTER TABLE `division`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la división';
--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del Equipo';
--
-- AUTO_INCREMENT de la tabla `festivos`
--
ALTER TABLE `festivos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID del día festivo';
--
-- AUTO_INCREMENT de la tabla `hora`
--
ALTER TABLE `hora`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID de las horas de campos',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `restricciones`
--
ALTER TABLE `restricciones`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la restricción';
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `campeonato`
--
ALTER TABLE `campeonato`
  ADD CONSTRAINT `campeonato_ibfk_3` FOREIGN KEY (`ID_CAMPO`) REFERENCES `campos` (`ID`), 
  ADD CONSTRAINT `campeonato_ibfk_5` FOREIGN KEY (`ID_LOCAL`) REFERENCES `equipos` (`ID`), 
  ADD CONSTRAINT `campeonato_ibfk_6` FOREIGN KEY (`ID_VISITANTE`) REFERENCES `equipos` (`ID`), 
  ADD CONSTRAINT `campeonato_ibfk_7` FOREIGN KEY (`ID_ARBITRO`) REFERENCES `arbitros` (`ID`); 

--
-- Filtros para la tabla `cam_horarios`
--
ALTER TABLE `cam_horarios`
  ADD CONSTRAINT `cam_horarios_ibfk_3` FOREIGN KEY (`ID_HORA`) REFERENCES `hora` (`ID`),
  ADD CONSTRAINT `cam_horarios_ibfk_1` FOREIGN KEY (`ID_CAMPO`) REFERENCES `campos` (`ID`),
  ADD CONSTRAINT `cam_horarios_ibfk_2` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID`);

--
-- Filtros para la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD CONSTRAINT `equipos_ibfk_2` FOREIGN KEY (`ID_DIVISION`) REFERENCES `division` (`ID`),
  ADD CONSTRAINT `equipos_ibfk_1` FOREIGN KEY (`ID_COMPETICION`) REFERENCES `competicion` (`ID`);

--
-- Filtros para la tabla `restricciones`
--
ALTER TABLE `restricciones`
  ADD CONSTRAINT `restricciones_ibfk_4` FOREIGN KEY (`ID_COINCIDE`) REFERENCES `equipos` (`ID`),
  ADD CONSTRAINT `restricciones_ibfk_1` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID`),
  ADD CONSTRAINT `restricciones_ibfk_2` FOREIGN KEY (`ID_CAMPO`) REFERENCES `campos` (`ID`),
  ADD CONSTRAINT `restricciones_ibfk_3` FOREIGN KEY (`ID_EQUIPO`) REFERENCES `equipos` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
