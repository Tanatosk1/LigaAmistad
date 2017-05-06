-- phpMyAdmin SQL Dump
-- version 4.6.4deb1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 06-05-2017 a las 11:26:46
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

CREATE TABLE `Arbitros` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de los arbitros',
  `NOMBRE` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del Arbitro',
  `APELLIDOS` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Apellidos del Arbitro'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campeonato`
--

CREATE TABLE `Campeonato` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de cada partido',
  `JORNADA` int(10) UNSIGNED NOT NULL COMMENT 'Jornada del campeonato',
  `FECHA` date DEFAULT NULL COMMENT 'Fecha del partido',
  `HORA` time DEFAULT NULL COMMENT 'Hora del partido',
  `ID_LOCAL` int(11) NOT NULL COMMENT 'ID del equipo que juega como local',
  `ID_VISITANTE` int(11) NOT NULL COMMENT 'ID del equipo que juega como visitante',
  `ID_CAMPO` int(11) DEFAULT NULL COMMENT 'ID del campo donde se juega',
  `JUGADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Establece si el partido se realiza o no (0 NO realizado, 1 realizado)',
  `ID_ARBITRO` int(11) DEFAULT NULL COMMENT 'ID del arbitro del partido'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Campos`
--

CREATE TABLE `Campos` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación del campo',
  `CAMPO` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del campo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Campos`
--

INSERT INTO `Campos` (`ID`, `CAMPO`) VALUES
(1, 'Campo F7 El Chorrillo'),
(2, 'Campo F7 Las Delicias'),
(3, 'Campo F7 Sta. M? del Mar'),
(4, 'Campo F7 Tincer'),
(5, 'El Draguillo'),
(6, 'El Tablero1'),
(7, 'El Tablero2'),
(8, 'Las Chumberas'),
(9, 'Llano del Moro'),
(10, '"Monta?a Pacho ""Campo 1"""'),
(11, '"Monta?a Pacho ""Campo 3"""'),
(12, 'Monta?a Pacho 2'),
(13, 'Monta?a Pacho 4'),
(14, 'Ofra-2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Competicion`
--

CREATE TABLE `Competicion` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la competición',
  `COMPETICION` varchar(20) NOT NULL COMMENT 'Descripción de la competición'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Division`
--

CREATE TABLE `Division` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la división',
  `DIVISION` tinyint(4) NOT NULL COMMENT 'Descripción de la división'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Division`
--

INSERT INTO `Division` (`ID`, `DIVISION`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Equipos`
--

CREATE TABLE `Equipos` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación del Equipo',
  `NOMBRE` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del equipo',
  `ID_COMPETICION` int(11) DEFAULT NULL COMMENT 'ID de la competición en la que participa',
  `ID_DIVISION` int(11) DEFAULT NULL COMMENT 'ID de la división a la que pertenece',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Equipo congelado (0 no congelado, 1 congelado)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `Equipos`
--

INSERT INTO `Equipos` (`ID`, `NOMBRE`, `ID_COMPETICION`, `ID_DIVISION`, `CONGELADO`) VALUES
(1, '(EXPULSADO) Cafetería Picasso F7', 1, 3, 0),
(2, '(EXPULSADO) The Galaxi F7', 1, 3, 0),
(3, '(RETIRADO) Guanchester City', 1, 2, 0),
(4, 'A.C. Plataneiras F7', 1, 1, 0),
(5, 'Af. Cóndor F7', 2, 1, 0),
(6, 'Af. LagunaGest Asesores F7', 3, 4, 0),
(7, 'Aston Birra F7', 2, 1, 0),
(8, 'Atco. Chícharo F7', 3, 4, 0),
(9, 'Atco. Independiente F7 Vet.', 2, 2, 0),
(10, 'Atco. Junior F7', 1, 1, 0),
(11, 'Atco. Laguna F7', 1, 1, 0),
(12, 'Atco. Mencey F7', 1, 3, 0),
(13, 'Atco. Mirca F7', 3, 4, 0),
(14, 'Atl. Platense Fincoman F7', 1, 1, 0),
(15, 'Atlético Charrua F7', 3, 4, 0),
(16, 'Atleticomaetílico F7', 1, 3, 0),
(17, 'Balompédica Piratas', 1, 1, 0),
(18, 'Bar Oasis F7', 3, 4, 0),
(19, 'Bayer Orotava F7', 1, 3, 0),
(20, 'Buendia Zumeria Tamarindo F7', 2, 1, 0),
(21, 'Cacharrito SP', 1, 3, 0),
(22, 'Cafeteria El Cedro F7', 1, 3, 0),
(23, 'Canarias Vertical', 3, 4, 0),
(24, 'Castillo FC', 1, 3, 0),
(25, 'CD La Muralla Veteranos F7', 2, 2, 0),
(26, 'CD Papá Teide F7', 3, 4, 0),
(27, 'Centro Optico Sauzal', 2, 1, 0),
(28, 'CF7 Amigos Futboleros', 3, 4, 0),
(29, 'Chincanayros F7', 2, 1, 0),
(30, 'Chiringuito de Tagoro F7', 1, 2, 0),
(31, 'Chiringuito El Tamboril F7', 3, 4, 0),
(32, 'Chirusa F7', 1, 2, 0),
(33, 'Chupiteria Simpalabras F7', 1, 3, 0),
(34, 'Clinica Veterinaria SC F7', 1, 3, 0),
(35, 'Comarca F7', 3, 4, 0),
(36, 'Deportivo Alisios F7', 1, 1, 0),
(37, 'DIN Acero GDA', 3, 4, 0),
(38, 'Draguillo F7', 1, 1, 0),
(39, 'Explotaciones Jordan F7', 3, 4, 0),
(40, 'Fénix CF7', 1, 1, 0),
(41, 'Ferretería Tenerife F7 Veteranos', 2, 2, 0),
(42, 'Fran El Pollito F7 Vet.', 2, 2, 0),
(43, 'Friendship City  F7', 1, 2, 0),
(44, 'Garimball F7', 3, 4, 0),
(45, 'Gasten Solar', 1, 1, 0),
(46, 'IDM Hypertrophy Nutrition FC', 1, 3, 0),
(47, 'Inter Tenerife Toldos Atlántic F7', 1, 3, 0),
(48, 'Intersport F7', 1, 2, 0),
(49, 'Juan Casañas Corred. Seguros F7', 3, 4, 0),
(50, 'KM Gym Bahia', 2, 1, 0),
(51, 'La Vieja Escuela', 1, 1, 0),
(52, 'Latinos Vet. F7 (RETIRADOS)', 2, 1, 0),
(53, 'Leones F7', 1, 2, 0),
(54, 'Los Chachis F7 Veteranos', 2, 1, 0),
(55, 'New Team F7', 1, 2, 0),
(56, 'Once Diablos F7 Vet.+35', 2, 2, 0),
(57, 'Portuarios F7 Veteranos', 2, 2, 0),
(58, 'Power Guanche F7', 3, 4, 0),
(59, 'Racayo F7', 1, 2, 0),
(60, 'Racing Esquina F7', 1, 2, 0),
(61, 'Real Unión F7', 1, 2, 0),
(62, 'Red Lions F7', 1, 2, 0),
(63, 'San Cristóbal F7 Veteranos', 2, 2, 0),
(64, 'SD Nottinghan Prisa F7', 1, 2, 0),
(65, 'Servicio Técnico Los Reyes', 2, 2, 0),
(66, 'Servicios Unión F7', 1, 2, 0),
(67, 'Suso NT Grupo Parranda F7', 1, 1, 0),
(68, 'Taganana F7', 1, 1, 0),
(69, 'Tasca La Tertulia F7', 2, 2, 0),
(70, 'Tasca Tagoror Vet. F7', 2, 2, 0),
(71, 'Teide Wap F7', 2, 1, 0),
(72, 'Tilellit F7', 1, 3, 0),
(73, 'Toscal CF', 1, 3, 0),
(74, 'Tratagua\'s Team', 1, 1, 0),
(75, 'Tumberos Imas F7', 1, 2, 0),
(76, 'UD La Mina Restaurante Las Rosas', 2, 2, 0),
(77, 'Uruguay Veteranos F7', 2, 1, 0),
(78, 'Valleseco F7 (Retirado)', 1, 1, 0),
(79, 'Valleseco F7 Vet.', 2, 1, 0),
(80, 'Valleseco Vet. B F7', 2, 2, 0),
(81, 'Varufakis-Hemispheria F7', 2, 1, 0),
(82, 'Viñas F7 Veteranos', 2, 2, 0),
(83, 'Racing de Anaga', 1, 3, 0),
(84, 'Once Diablos F7 Vet.+30', 3, 4, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Equ_Rest`
--

CREATE TABLE `Equ_Rest` (
  `ID_EQUIPO` int(11) NOT NULL COMMENT 'ID del equipo que tiene restricción',
  `ID_RESTRICCION` int(11) NOT NULL COMMENT 'ID de la restricción que se le aplica a un equipo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Festivos`
--

CREATE TABLE `Festivos` (
  `ID` int(11) NOT NULL COMMENT 'ID del día festivo',
  `FECHA` date NOT NULL COMMENT 'Día festivo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Restricciones`
--

CREATE TABLE `Restricciones` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación de la restricción',
  `RESTRICCION` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Descripción de la restricción',
  `KEYWORD` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Palabra clave para identificar la restricción',
  `ID_EQUIPO` int(11) DEFAULT NULL COMMENT 'ID del equipo con el que no puede coincidir otro equipo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Arbitros`
--
ALTER TABLE `Arbitros`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Campeonato`
--
ALTER TABLE `Campeonato`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `LOCAL` (`ID_LOCAL`),
  ADD KEY `VISITANTE` (`ID_VISITANTE`),
  ADD KEY `CAMPO` (`ID_CAMPO`),
  ADD KEY `ID_ARBITRO` (`ID_ARBITRO`);

--
-- Indices de la tabla `Campos`
--
ALTER TABLE `Campos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Competicion`
--
ALTER TABLE `Competicion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Division`
--
ALTER TABLE `Division`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Equipos`
--
ALTER TABLE `Equipos`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD KEY `DIVISION` (`ID_DIVISION`),
  ADD KEY `COMPETICION` (`ID_COMPETICION`);

--
-- Indices de la tabla `Equ_Rest`
--
ALTER TABLE `Equ_Rest`
  ADD KEY `ID_EQUIPO` (`ID_EQUIPO`),
  ADD KEY `ID_RESTRICCION` (`ID_RESTRICCION`);

--
-- Indices de la tabla `Festivos`
--
ALTER TABLE `Festivos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `Restricciones`
--
ALTER TABLE `Restricciones`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `EQUIPO` (`ID_EQUIPO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Arbitros`
--
ALTER TABLE `Arbitros`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de los arbitros';
--
-- AUTO_INCREMENT de la tabla `Campeonato`
--
ALTER TABLE `Campeonato`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de cada partido';
--
-- AUTO_INCREMENT de la tabla `Campos`
--
ALTER TABLE `Campos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del campo', AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `Competicion`
--
ALTER TABLE `Competicion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la competición';
--
-- AUTO_INCREMENT de la tabla `Division`
--
ALTER TABLE `Division`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la división', AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `Equipos`
--
ALTER TABLE `Equipos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del Equipo', AUTO_INCREMENT=85;
--
-- AUTO_INCREMENT de la tabla `Festivos`
--
ALTER TABLE `Festivos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID del día festivo';
--
-- AUTO_INCREMENT de la tabla `Restricciones`
--
ALTER TABLE `Restricciones`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación de la restricción';
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
-- Filtros para la tabla `Equipos`
--
ALTER TABLE `Equipos`
  ADD CONSTRAINT `Equipos_ibfk_1` FOREIGN KEY (`ID_DIVISION`) REFERENCES `Division` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Equipos_ibfk_2` FOREIGN KEY (`ID_COMPETICION`) REFERENCES `Competicion` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `Equ_Rest`
--
ALTER TABLE `Equ_Rest`
  ADD CONSTRAINT `Equ_Rest_ibfk_1` FOREIGN KEY (`ID_EQUIPO`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Equ_Rest_ibfk_2` FOREIGN KEY (`ID_RESTRICCION`) REFERENCES `Restricciones` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Restricciones`
--
ALTER TABLE `Restricciones`
  ADD CONSTRAINT `Restricciones_ibfk_1` FOREIGN KEY (`ID_EQUIPO`) REFERENCES `Equipos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
