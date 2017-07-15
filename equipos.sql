-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 15-07-2017 a las 08:49:16
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

DROP TABLE IF EXISTS `equipos`;
CREATE TABLE IF NOT EXISTS `equipos` (
  `ID` int(11) NOT NULL COMMENT 'Numero de identificación del Equipo',
  `NOMBRE` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del equipo',
  `ID_COMPETICION` int(11) DEFAULT NULL COMMENT 'ID de la competición en la que participa',
  `ID_DIVISION` int(11) DEFAULT NULL COMMENT 'ID de la división a la que pertenece',
  `CONGELADO` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Equipo congelado (0 no congelado, 1 congelado)'
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`ID`, `NOMBRE`, `ID_COMPETICION`, `ID_DIVISION`, `CONGELADO`) VALUES
(1, '(EXPULSADO) Cafetería Picasso F7', 1, 4, 1),
(2, '(EXPULSADO) The Galaxi F7', 1, 4, 0),
(3, '(RETIRADO) Guanchester City', 1, 3, 0),
(4, 'A.C. Plataneiras F7', 1, 2, 0),
(5, 'Af. Cóndor F7', 2, 2, 0),
(6, 'Af. LagunaGest Asesores F7', 3, 1, 0),
(7, 'Aston Birra F7', 2, 2, 0),
(8, 'Atco. Chícharo F7', 3, 1, 0),
(9, 'Atco. Independiente F7 Vet.', 2, 3, 0),
(10, 'Atco. Junior F7', 1, 2, 0),
(11, 'Atco. Laguna F7', 1, 2, 0),
(12, 'Atco. Mencey F7', 1, 4, 0),
(13, 'Atco. Mirca F7', 3, 1, 0),
(14, 'Atl. Platense Fincoman F7', 1, 2, 0),
(15, 'Atlético Charrua F7', 3, 1, 0),
(16, 'Atleticomaetílico F7', 1, 4, 0),
(17, 'Balompédica Piratas', 1, 2, 0),
(18, 'Bar Oasis F7', 3, 1, 0),
(19, 'Bayer Orotava F7', 1, 4, 0),
(20, 'Buendia Zumeria Tamarindo F7', 2, 2, 0),
(21, 'Cacharrito SP', 1, 4, 0),
(22, 'Cafeteria El Cedro F7', 1, 4, 0),
(23, 'Canarias Vertical', 3, 1, 0),
(24, 'Castillo FC', 1, 4, 0),
(25, 'CD La Muralla Veteranos F7', 2, 3, 0),
(26, 'CD Papá Teide F7', 3, 1, 0),
(27, 'Centro Optico Sauzal', 2, 2, 0),
(28, 'CF7 Amigos Futboleros', 3, 1, 0),
(29, 'Chincanayros F7', 2, 2, 0),
(30, 'Chiringuito de Tagoro F7', 1, 3, 0),
(31, 'Chiringuito El Tamboril F7', 3, 1, 0),
(32, 'Chirusa F7', 1, 3, 0),
(33, 'Chupiteria Simpalabras F7', 1, 4, 0),
(34, 'Clinica Veterinaria SC F7', 1, 4, 0),
(35, 'Comarca F7', 3, 1, 0),
(36, 'Deportivo Alisios F7', 1, 2, 0),
(37, 'DIN Acero GDA', 3, 1, 0),
(38, 'Draguillo F7', 1, 2, 0),
(39, 'Explotaciones Jordan F7', 3, 1, 0),
(40, 'Fénix CF7', 1, 2, 0),
(41, 'Ferretería Tenerife F7 Veteranos', 2, 3, 0),
(42, 'Fran El Pollito F7 Vet.', 2, 3, 0),
(43, 'Friendship City  F7', 1, 3, 0),
(44, 'Garimball F7', 3, 1, 0),
(45, 'Gasten Solar', 1, 2, 0),
(46, 'IDM Hypertrophy Nutrition FC', 1, 4, 0),
(47, '(RETIRADO) Inter Tenerife Toldos Atlántic F7', 1, 4, 0),
(48, 'Intersport F7', 1, 3, 0),
(49, 'Juan Casañas Corred. Seguros F7', 3, 1, 0),
(50, 'KM Gym Bahia', 2, 2, 0),
(51, 'La Vieja Escuela', 1, 2, 0),
(52, 'Latinos Vet. F7 (RETIRADOS)', 2, 2, 0),
(53, 'Leones F7', 1, 3, 0),
(54, 'Los Chachis F7 Veteranos', 2, 2, 0),
(55, 'New Team F7', 1, 3, 0),
(56, 'Once Diablos F7 Vet.+35', 2, 3, 0),
(57, 'Portuarios F7 Veteranos', 2, 3, 0),
(58, 'Power Guanche F7', 3, 1, 0),
(59, 'Racayo F7', 1, 3, 0),
(60, 'Racing Esquina F7', 1, 3, 0),
(61, 'Real Unión F7', 1, 3, 0),
(62, 'Red Lions F7', 1, 3, 0),
(63, 'San Cristóbal F7 Veteranos', 2, 3, 0),
(64, 'SD Nottinghan Prisa F7', 1, 3, 0),
(65, 'Servicio Técnico Los Reyes', 2, 3, 0),
(66, 'Servicios Unión F7', 1, 3, 0),
(67, 'Suso NT Grupo Parranda F7', 1, 2, 0),
(68, 'Taganana F7', 1, 2, 0),
(69, 'Tasca La Tertulia F7', 2, 3, 0),
(70, 'Tasca Tagoror Vet. F7', 2, 3, 0),
(71, 'Teide Wap F7', 2, 2, 0),
(72, 'Tilellit F7', 1, 4, 0),
(73, 'Toscal CF', 1, 4, 0),
(74, 'Tratagua''s Team', 1, 2, 0),
(75, 'Tumberos Imas F7', 1, 3, 0),
(76, 'UD La Mina Restaurante Las Rosas', 2, 3, 0),
(77, 'Uruguay Veteranos F7', 2, 2, 0),
(78, 'Valleseco F7 (Retirado)', 1, 2, 0),
(79, 'Valleseco F7 Vet.', 2, 2, 0),
(80, 'Valleseco Vet. B F7', 2, 3, 0),
(81, 'Varufakis-Hemispheria F7', 2, 2, 0),
(82, 'Viñas F7 Veteranos', 2, 3, 0),
(83, 'Racing de Anaga', 1, 4, 0),
(84, 'Once Diablos F7 Vet.+30', 3, 1, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOMBRE` (`NOMBRE`),
  ADD KEY `DIVISION` (`ID_DIVISION`),
  ADD KEY `COMPETICION` (`ID_COMPETICION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Numero de identificación del Equipo',AUTO_INCREMENT=85;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD CONSTRAINT `Equipos_ibfk_1` FOREIGN KEY (`ID_DIVISION`) REFERENCES `division` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Equipos_ibfk_2` FOREIGN KEY (`ID_COMPETICION`) REFERENCES `competicion` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
