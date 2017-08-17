-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2017 a las 14:51:21
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `liga`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cam_horarios`
--

DROP TABLE IF EXISTS `cam_horarios`;
CREATE TABLE IF NOT EXISTS `cam_horarios` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CAMPO` int(11) NOT NULL,
  `ID_DIA` int(11) DEFAULT NULL,
  `ID_HORA` int(11) DEFAULT NULL,
  `ASIGNADO` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID_CAMPO` (`ID_CAMPO`),
  KEY `ID_DIA` (`ID_DIA`),
  KEY `ID_HORA` (`ID_HORA`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cam_horarios`
--
ALTER TABLE `cam_horarios`
  ADD CONSTRAINT `cam_horarios_ibfk_1` FOREIGN KEY (`ID_CAMPO`) REFERENCES `campos` (`ID`),
  ADD CONSTRAINT `cam_horarios_ibfk_2` FOREIGN KEY (`ID_DIA`) REFERENCES `dias` (`ID`),
  ADD CONSTRAINT `cam_horarios_ibfk_3` FOREIGN KEY (`ID_HORA`) REFERENCES `hora` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
