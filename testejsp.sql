-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 10, 2020 at 09:34 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testejsp`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientinfo`
--

CREATE TABLE `clientinfo` (
  `nameClient` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `addressClient` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stateClient` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cityClient` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cpfClient` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `clientinfo`
--

INSERT INTO `clientinfo` (`nameClient`, `addressClient`, `stateClient`, `cityClient`, `cpfClient`) VALUES
('Maria Conceição', 'Rua General Osório', 'Santa Catarina', 'Blumenau', '28020617043'),
('João da Silva', 'Rua da Lagoa', 'Amazonas', 'Apuí', '81706127057');

-- --------------------------------------------------------

--
-- Table structure for table `telephone`
--

CREATE TABLE `telephone` (
  `number` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cpfClient` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `telephone`
--

INSERT INTO `telephone` (`number`, `cpfClient`) VALUES
('1140028922', '81706127057'),
('4733333333', '28020617043'),
('9733314455', '81706127057');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientinfo`
--
ALTER TABLE `clientinfo`
  ADD PRIMARY KEY (`cpfClient`);

--
-- Indexes for table `telephone`
--
ALTER TABLE `telephone`
  ADD PRIMARY KEY (`number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
