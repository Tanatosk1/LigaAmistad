-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 02-02-2018 a las 20:42:32
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `liga`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbitros`
--

DROP TABLE IF EXISTS `arbitros`;
CREATE TABLE IF NOT EXISTS `arbitros` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de los arbitros',
  `NOMBRE` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del Arbitro',
  `APELLIDOS` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Apellidos del Arbitro',
  `NIVEL` tinyint(1) NOT NULL DEFAULT '0',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '1',
  `LUNES` tinyint(1) NOT NULL DEFAULT '0',
  `MARTES` tinyint(1) NOT NULL DEFAULT '0',
  `MIERCOLES` tinyint(1) NOT NULL DEFAULT '0',
  `JUEVES` tinyint(1) NOT NULL DEFAULT '0',
  `VIERNES` tinyint(1) NOT NULL DEFAULT '0',
  `SABADO` tinyint(1) NOT NULL DEFAULT '0',
  `DOMINGO` tinyint(1) NOT NULL DEFAULT '0',
  `IDCAMPO` int(11) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `arbitros`
--

TRUNCATE TABLE `arbitros`;
--
-- Volcado de datos para la tabla `arbitros`
--

INSERT INTO `arbitros` (`ID`, `NOMBRE`, `APELLIDOS`, `NIVEL`, `CONGELADO`, `LUNES`, `MARTES`, `MIERCOLES`, `JUEVES`, `VIERNES`, `SABADO`, `DOMINGO`, `IDCAMPO`) VALUES
(1, 'Cristo', '', 0, 0, 1, 1, 1, 0, 0, 0, 0, 0),
(2, 'Carlos', '', 0, 0, 1, 0, 0, 1, 0, 0, 0, 1),
(3, 'Pistola', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 'Luis', 'Perez', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(5, 'Luis', 'Darias', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(6, 'Tato', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(7, 'Ubay', '', 0, 0, 0, 1, 0, 0, 0, 0, 0, 4),
(8, 'Raico', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(9, 'Castilla', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(10, 'Ferrer', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(11, 'David', '', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbitros`
--
ALTER TABLE `arbitros`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbitros`
--
ALTER TABLE `arbitros`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de los arbitros',AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
