-- phpMyAdmin SQL Dump
-- version 4.6.4deb1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 17-05-2017 a las 23:15:52
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

--
-- Volcado de datos para la tabla `Competicion`
--

INSERT INTO `Competicion` (`ID`, `COMPETICION`) VALUES
(1, 'Liga Senior +18'),
(2, 'Liga Veteranos +30'),
(3, 'Liga Veteranos +35');

--
-- Volcado de datos para la tabla `Dias`
--

INSERT INTO `Dias` (`ID`, `DIA`) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes'),
(6, 'Sábado'),
(7, 'Domingo');

--
-- Volcado de datos para la tabla `Division`
--

INSERT INTO `Division` (`ID`, `DIVISION`) VALUES
(1, '1ª División'),
(2, '2ª División'),
(3, '3ª División'),
(4, NULL);

--
-- Volcado de datos para la tabla `Equipos`
--

INSERT INTO `equipos` (`ID`, `NOMBRE`, `ID_COMPETICION`, `ID_DIVISION`, `CONGELADO`) VALUES
(1, '(EXPULSADO) Cafetería Picasso F7', 1, 3, 0),
(2, '(EXPULSADO) The Galaxi F7', 1, 3, 0),
(3, '(RETIRADO) Guanchester City', 1, 2, 0),
(4, 'A.C. Plataneiras F7', 1, 1, 0),
(5, 'Af. Cóndor F7', 3, 1, 0),
(6, 'Af. LagunaGest Asesores F7', 2, 4, 0),
(7, 'Aston Birra F7', 3, 1, 0),
(8, 'Atco. Chícharo F7', 2, 4, 0),
(9, 'Atco. Independiente F7 Vet.', 3, 2, 0),
(10, 'Atco. Junior F7', 1, 1, 0),
(11, 'Atco. Laguna F7', 1, 1, 0),
(12, 'Atco. Mencey F7', 1, 3, 0),
(13, 'Atco. Mirca F7', 2, 4, 0),
(14, 'Atl. Platense Fincoman F7', 1, 1, 0),
(15, 'Atlético Charrua F7', 2, 4, 0),
(16, 'Atleticomaetílico F7', 1, 3, 0),
(17, 'Balompédica Piratas', 1, 1, 0),
(18, 'Bar Oasis F7', 2, 4, 0),
(19, 'Bayer Orotava F7', 1, 3, 0),
(20, 'Buendia Zumeria Tamarindo F7', 3, 1, 0),
(21, 'Cacharrito SP', 1, 3, 0),
(22, 'Cafeteria El Cedro F7', 1, 3, 0),
(23, 'Canarias Vertical', 2, 4, 0),
(24, 'Castillo FC', 1, 3, 0),
(25, 'CD La Muralla Veteranos F7', 3, 2, 0),
(26, 'CD Papá Teide F7', 2, 4, 0),
(27, 'Centro Optico Sauzal', 3, 1, 0),
(28, 'CF7 Amigos Futboleros', 2, 4, 0),
(29, 'Chincanayros F7', 3, 1, 0),
(30, 'Chiringuito de Tagoro F7', 1, 2, 0),
(31, 'Chiringuito El Tamboril F7', 2, 4, 0),
(32, 'Chirusa F7', 1, 2, 0),
(33, 'Chupiteria Simpalabras F7', 1, 3, 0),
(34, 'Clinica Veterinaria SC F7', 1, 3, 0),
(35, 'Comarca F7', 2, 4, 0),
(36, 'Deportivo Alisios F7', 1, 1, 0),
(37, 'DIN Acero GDA', 2, 4, 0),
(38, 'Draguillo F7', 1, 1, 0),
(39, 'Explotaciones Jordan F7', 2, 4, 0),
(40, 'Fénix CF7', 1, 1, 0),
(41, 'Ferretería Tenerife F7 Veteranos', 3, 2, 0),
(42, 'Fran El Pollito F7 Vet.', 3, 2, 0),
(43, 'Friendship City  F7', 1, 2, 0),
(44, 'Garimball F7', 2, 4, 0),
(45, 'Gasten Solar', 1, 1, 0),
(46, 'IDM Hypertrophy Nutrition FC', 1, 3, 0),
(47, '(RETIRADO) Inter Tenerife Toldos Atlántic F7', 1, 3, 0),
(48, 'Intersport F7', 1, 2, 0),
(49, 'Juan Casañas Corred. Seguros F7', 2, 4, 0),
(50, 'KM Gym Bahia', 3, 1, 0),
(51, 'La Vieja Escuela', 1, 1, 0),
(52, 'Latinos Vet. F7 (RETIRADOS)', 3, 1, 0),
(53, 'Leones F7', 1, 2, 0),
(54, 'Los Chachis F7 Veteranos', 3, 1, 0),
(55, 'New Team F7', 1, 2, 0),
(56, 'Once Diablos F7 Vet.+35', 3, 2, 0),
(57, 'Portuarios F7 Veteranos', 3, 2, 0),
(58, 'Power Guanche F7', 2, 4, 0),
(59, 'Racayo F7', 1, 2, 0),
(60, 'Racing Esquina F7', 1, 2, 0),
(61, 'Real Unión F7', 1, 2, 0),
(62, 'Red Lions F7', 1, 2, 0),
(63, 'San Cristóbal F7 Veteranos', 3, 2, 0),
(64, 'SD Nottinghan Prisa F7', 1, 2, 0),
(65, 'Servicio Técnico Los Reyes', 3, 2, 0),
(66, 'Servicios Unión F7', 1, 2, 0),
(67, 'Suso NT Grupo Parranda F7', 1, 1, 0),
(68, 'Taganana F7', 1, 1, 0),
(69, 'Tasca La Tertulia F7', 3, 2, 0),
(70, 'Tasca Tagoror Vet. F7', 3, 2, 0),
(71, 'Teide Wap F7', 3, 1, 0),
(72, 'Tilellit F7', 1, 3, 0),
(73, 'Toscal CF', 1, 3, 0),
(74, 'Tratagua''s Team', 1, 1, 0),
(75, 'Tumberos Imas F7', 1, 2, 0),
(76, 'UD La Mina Restaurante Las Rosas', 3, 2, 0),
(77, 'Uruguay Veteranos F7', 3, 1, 0),
(78, 'Valleseco F7 (Retirado)', 1, 1, 0),
(79, 'Valleseco F7 Vet.', 3, 1, 0),
(80, 'Valleseco Vet. B F7', 3, 2, 0),
(81, 'Varufakis-Hemispheria F7', 3, 1, 0),
(82, 'Viñas F7 Veteranos', 3, 2, 0),
(83, 'Racing de Anaga', 1, 3, 0),
(84, 'Once Diablos F7 Vet.+30', 2, 4, 0);

--
-- Volcado de datos para la tabla `Hora`
--

INSERT INTO `Hora` (`ID`, `HORA`) VALUES
(1, '8:30'),
(2, '9:30');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
