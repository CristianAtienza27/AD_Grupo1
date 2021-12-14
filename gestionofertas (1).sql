-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2021 a las 23:28:50
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 7.3.31

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
(1, 'DAW', 'Informática');

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
(12);

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE `noticia` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `imagen` varchar(50) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `cicloid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE `oferta` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fechamax` date NOT NULL,
  `num_candidatos` int(11) NOT NULL,
  `requisitos` longtext DEFAULT NULL,
  `titular` varchar(60) NOT NULL,
  `rrhhid` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

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
(1, 'admin', 'admin@admin.com', NULL, b'1', 'admin', '$2a$10$HF/Jvoidey8l/GKgt90jOesfMl9r68A0RKnPqIKVbQqz5KVgOm6Tq', 'ROLE_ADMIN', '628282828', NULL),
(6, 'López', 'pl@mail.com', NULL, b'1', 'Pepe', '$2a$10$R1vuu1QqV7L6pg9gDTkBauEjJkOiozzozpeHcK79FaEePR5RRsqfC', 'ROLE_ALUMNO', '67676767', 1),
(9, 'Lop', 'plo@mail.com', NULL, b'0', 'Pepe', '$2a$10$fj4rJCX3YWIlTKzoNJoZE.v8lCBlsVCYPepdTLggxLWPO7InUhUcK', 'ROLE_ALUMNO', '628282828', 1),
(10, 'Lop', 'mlop@mail.com', NULL, b'0', 'Manuel', '$2a$10$3Ac4ryO.xpeJwMcgvAmww.mQ4M5JrygXHqHTYz/XnSaskKnfw3cTu', 'ROLE_ALUMNO', '628282828', 1),
(11, 'López', 'rl@mail.com', 'Ikea', b'1', 'Rodrigo', '$2a$10$SWdm7yxR3hnGObeYNo2xTOXODhRuQI.15Kjsx9pnCZsYOU6XsjnA6', 'ROLE_RRHH', '628282828', NULL);

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
  ADD KEY `FK5rgussq1v72jb9qfkimvjvl1g` (`rrhhid`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  ADD KEY `FKjmwaheya509vojaum0gv1if4k` (`cicloid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
