-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-01-2022 a las 23:28:55
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionofertas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciclo`
--

CREATE TABLE `ciclo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `tipo` varchar(60) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ciclo`
--

INSERT INTO `ciclo` (`id`, `nombre`, `tipo`) VALUES
(1, 'DAM', 'CFGS'),
(2, 'DAW', 'CFGS'),
(3, 'ASIR', 'CFGS'),
(4, 'Comercio', 'Empresa'),
(5, 'Electricidad', 'FP Basica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(39);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscrito`
--

CREATE TABLE `inscrito` (
  `id` int(11) NOT NULL,
  `fecha_inscripcion` date NOT NULL,
  `id_alumno` int(11) DEFAULT NULL,
  `id_oferta` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscrito`
--

INSERT INTO `inscrito` (`id`, `fecha_inscripcion`, `id_alumno`, `id_oferta`) VALUES
(35, '2022-01-18', 1, 4),
(34, '2022-01-18', 1, 1),
(38, '2022-01-25', 1, 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE `noticia` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `imagen` varchar(50) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `cicloid` int(11) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `noticia`
--

INSERT INTO `noticia` (`id`, `descripcion`, `imagen`, `titulo`, `cicloid`, `fecha_creacion`) VALUES
(1, 'Animar a Felix, hay que aprobar', '71G1KUtJk+L._AC_SY355_.jpg', 'Futbolin', 1, NULL),
(2, 'Partido entre Raul Gil y Pepe Viyuela', '71VTKeHIxnL._AC_SL1500_.jpg', 'Ping Pong', 2, NULL),
(3, 'Incendio', '1500923201_322998_1500923574_noticia_normal.jpg', 'La FP se incendia', 5, NULL),
(4, 'Raul sigue sin actualizar los apuntes', 'ionic.png', 'Ionic', 4, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE `oferta` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fechamax` date DEFAULT NULL,
  `num_candidatos` int(11) NOT NULL,
  `requisitos` longtext DEFAULT NULL,
  `titular` varchar(60) NOT NULL,
  `cicloid` int(11) DEFAULT NULL,
  `rrhhid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `oferta`
--

INSERT INTO `oferta` (`id`, `descripcion`, `fechamax`, `num_candidatos`, `requisitos`, `titular`, `cicloid`, `rrhhid`) VALUES
(1, 'Hay que animar a Felix', '2022-02-16', 5, 'Tener manos y medir mas de 120cm', 'Animador/a para el partido de futbolin', 1, 4),
(2, 'Hay que desanimar a Raul', '2021-12-22', 5, 'Tener manos y medir mas de 120cm', 'Desanimador/a para el partido de futbolin', 1, 2),
(3, 'Se necesitan  bomberos, la fp esta ardiendo', '2022-04-15', 10, 'Tener mas de 3 dientes', 'La FP se incendia, necesitamos bomberos', 1, 4),
(4, 'Convencer a Raul de que tiene que trabajar', '2023-03-31', 1, 'Padecer de alopecia', 'Raul, trabaja', 1, 4),
(19, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', '2022-01-18', 5, 'Pasar la prueba', 'Prueba 1', 1, 4),
(37, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.', '2022-01-19', 9, 'Pasar la prueba', 'Prueba 1', 2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `empresa` varchar(200) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `cicloid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `apellidos`, `email`, `empresa`, `enabled`, `nombre`, `password`, `role`, `telefono`, `cicloid`) VALUES
(1, 'atienza', 'cristian@gmail.com', NULL, b'1', 'Cristian', '$2a$10$ItGe6TlMw1fFrzy0fvhm/OTbgZQxVS0NGrn2mtnoDtVSmA144BUby', 'ROLE_ALUMNO', '34343433', 1),
(2, 'López', 'pepe@mail.com', 'Education S.A.', b'1', 'Pepe', '$2a$10$BcDlCVmfjAy9mdt8yfAEeudVsKSR0QxQGxsNm8/lccpcAdHG/3Yee', 'ROLE_RRHH', '335354545', NULL),
(4, 'admin', 'admin@admin.com', NULL, b'1', 'admin', '$2a$10$cNApfkgOgj8hYOBoUT1E1.JSrPoFNO4zE3swG0pmxFBI4CCDGjO9O', 'ROLE_ADMIN', '628282828', 1),
(9, 'Ruiz', 'pepe1@mail.com', NULL, b'0', 'Pepe', '$2a$10$2Qx1z7dztpjPhuQufjdm5OjskPFijeYehg6SmXgP6v4AX0YTkXiY6', 'ROLE_ALUMNO', '7373737373', 2),
(11, 'Ruiz', 'manu@mail.com', NULL, b'0', 'Manuel', '$2a$10$4cwazBr33vk7i0q4CV1XpO9JAvXhbbZyWm0dmJm14J./EEdRCVazy', 'ROLE_ALUMNO', '8282828282', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciclo`
--
ALTER TABLE `ciclo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscrito`
--
ALTER TABLE `inscrito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3g8m8g4ei6lbw07pr93w0x4dq` (`id_alumno`),
  ADD KEY `FKe0ptcxg0e1mts17wnttgnarok` (`id_oferta`);

--
-- Indices de la tabla `noticia`
--
ALTER TABLE `noticia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe2r4563fjrrt48y6cbr4tf14u` (`cicloid`);

--
-- Indices de la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf6955dhj3tehx9i3x5b2y7ioi` (`cicloid`),
  ADD KEY `FK5rgussq1v72jb9qfkimvjvl1g` (`rrhhid`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjmwaheya509vojaum0gv1if4k` (`cicloid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
