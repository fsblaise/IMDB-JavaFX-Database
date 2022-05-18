-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2021 at 03:48 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `dijak`
--

CREATE TABLE `dijak` (
  `szineszid` int(5) NOT NULL,
  `dij` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `darab` int(3) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `dijak`
--

INSERT INTO `dijak` (`szineszid`, `dij`, `darab`) VALUES
(1, 'Golden Globe', 2),
(1, 'Oscar-díj', 4),
(1, 'Primetime Emmy', 2),
(2, 'Golden Globe', 0),
(2, 'Oscar-díj', 0),
(2, 'Primetime Emmy', 1),
(3, 'Golden Globe', 1),
(3, 'Oscar-díj', 1),
(3, 'Primetime Emmy', 0),
(4, 'Golden Globe', 1),
(4, 'Oscar-díj', 0),
(4, 'Primetime Emmy', 4),
(5, 'Golden Globe', 1),
(5, 'Oscar-díj', 1),
(5, 'Primetime Emmy', 0),
(6, 'Golden Globe', 0),
(6, 'Oscar-díj', 0),
(6, 'Primetime Emmy', 1),
(7, 'Golden Globe', 0),
(7, 'Oscar-díj', 0),
(7, 'Primetime Emmy', 0),
(8, 'Golden Globe', 0),
(8, 'Oscar-díj', 0),
(8, 'Primetime Emmy', 0),
(9, 'Golden Globe', 0),
(9, 'Oscar-díj', 0),
(9, 'Primetime Emmy', 0),
(10, 'Golden Globe', 1),
(10, 'Oscar-díj', 2),
(10, 'Primetime Emmy', 0),
(11, 'Golden Globe', 0),
(11, 'Oscar-díj', 0),
(11, 'Primetime Emmy', 0),
(12, 'Golden Globe', 1),
(12, 'Oscar-díj', 1),
(12, 'Primetime Emmy', 0),
(13, 'Golden Globe', 3),
(13, 'Oscar-díj', 2),
(13, 'Primetime Emmy', 0),
(14, 'Golden Globe', 1),
(14, 'Oscar-díj', 1),
(14, 'Primetime Emmy', 0),
(15, 'Golden Globe', 2),
(15, 'Oscar-díj', 0),
(15, 'Primetime Emmy', 0),
(16, 'Golden Globe', 0),
(16, 'Oscar-díj', 0),
(16, 'Primetime Emmy', 0),
(17, 'Golden Globe', 0),
(17, 'Oscar-díj', 0),
(17, 'Primetime Emmy', 0),
(18, 'Golden Globe', 0),
(18, 'Oscar-díj', 0),
(18, 'Primetime Emmy', 0),
(19, 'Golden Globe', 0),
(19, 'Oscar-díj', 0),
(19, 'Primetime Emmy', 0);

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `filmid` int(5) NOT NULL,
  `cim` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `megjelenes` date DEFAULT NULL,
  `ertekeles` float DEFAULT NULL,
  `hossz` int(3) DEFAULT 0,
  `studioid` int(5) NOT NULL,
  `koltseg` int(10) DEFAULT NULL,
  `oscarszam` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`filmid`, `cim`, `megjelenes`, `ertekeles`, `hossz`, `studioid`, `koltseg`, `oscarszam`) VALUES
(1, 'Három óriásplakát Ebbing határában', '2017-12-01', 8.1, 115, 1, 15000000, 2),
(2, 'A nomádok földje', '2021-02-19', 7.4, 107, 1, 5000000, 3),
(3, 'Bad Boys – Mindörökké rosszfiúk', '2020-01-17', 6.6, 124, 2, 90000000, 0),
(4, 'Baby Driver', '2017-06-28', 7.6, 115, 3, 34000000, 0),
(5, 'Vén rókák', '2017-04-07', 6.6, 96, 4, 25000000, 0),
(6, 'Páncélba zárt szellem', '2017-03-31', 6.3, 107, 5, 110000000, 0),
(7, 'Különösen veszélyes bűnözők', '2021-11-12', 6.4, 118, 6, 160000000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `filmstudio`
--

CREATE TABLE `filmstudio` (
  `studioid` int(5) NOT NULL,
  `nev` varchar(60) COLLATE utf8_hungarian_ci NOT NULL,
  `alapitas` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `oscarszam` int(3) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `filmstudio`
--

INSERT INTO `filmstudio` (`studioid`, `nev`, `alapitas`, `oscarszam`) VALUES
(1, 'Searchlight Pictures', '1994', 43),
(2, 'Columbia Pictures', '1918', 12),
(3, 'TriStar Pictures', '1982', 0),
(4, 'Warner Bros. Pictures', '1923', 25),
(5, 'DreamWorks Pictures', '1994', 3),
(6, 'Netflix', '1997', 15);

-- --------------------------------------------------------

--
-- Table structure for table `mufajok`
--

CREATE TABLE `mufajok` (
  `filmid` int(5) NOT NULL,
  `mufaj` varchar(15) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `mufajok`
--

INSERT INTO `mufajok` (`filmid`, `mufaj`) VALUES
(1, 'dráma'),
(1, 'krimi'),
(1, 'vígjáték'),
(2, 'dráma'),
(3, 'akció'),
(3, 'krimi'),
(3, 'vígjáték'),
(4, 'akció'),
(4, 'dráma'),
(4, 'krimi'),
(5, 'krimi'),
(5, 'vígjáték'),
(6, 'akció'),
(6, 'dráma'),
(6, 'krimi'),
(7, 'akció'),
(7, 'dráma'),
(7, 'vígjáték');

-- --------------------------------------------------------

--
-- Table structure for table `szereplesek`
--

CREATE TABLE `szereplesek` (
  `szineszid` int(5) NOT NULL,
  `filmid` int(5) NOT NULL,
  `szerep` varchar(60) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(20) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `szereplesek`
--

INSERT INTO `szereplesek` (`szineszid`, `filmid`, `szerep`, `tipus`) VALUES
(1, 1, 'Mildred Hayes', 'főszereplő'),
(1, 2, 'Fern', 'főszereplő'),
(2, 1, 'Bill Willoughby', 'mellékszereplő'),
(3, 1, 'Jason Dixon', 'mellékszereplő'),
(4, 1, 'James', 'mellékszereplő'),
(5, 2, 'Peter', 'mellékszereplő'),
(6, 2, 'Dave', 'mellékszereplő'),
(7, 3, 'Marcus Miles Burnett', 'főszereplő'),
(8, 3, 'Michael Eugene (Mike) Lowrey', 'főszereplő'),
(9, 4, 'Miles (Baby)', 'főszereplő'),
(10, 4, 'Doki', 'mellékszereplő'),
(11, 4, 'Debora', 'mellékszereplő'),
(12, 5, 'Willie Davis', 'főszereplő'),
(13, 5, 'Joe Harding', 'főszereplő'),
(14, 5, 'Albert Garner', 'főszereplő'),
(15, 6, 'Motoko Kusanagi', 'főszereplő'),
(16, 6, 'Daisuke Aramaki főnök', 'mellékszereplő'),
(17, 7, 'John Hartley', 'főszereplő'),
(18, 7, 'Nolan Booth', 'főszereplő'),
(19, 7, 'Sarah Black', 'főszereplő');

-- --------------------------------------------------------

--
-- Table structure for table `szinesz`
--

CREATE TABLE `szinesz` (
  `szineszid` int(5) NOT NULL,
  `nev` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `szuldatum` date NOT NULL,
  `eletkor` int(3) NOT NULL,
  `elhunyt` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `szinesz`
--

INSERT INTO `szinesz` (`szineszid`, `nev`, `szuldatum`, `eletkor`, `elhunyt`) VALUES
(1, 'Frances McDormand', '1957-06-23', 64, NULL),
(2, 'Woody Harrelson', '1961-07-23', 60, NULL),
(3, 'Sam Rockwell', '1968-11-05', 53, NULL),
(4, 'Peter Dinklage', '1969-06-11', 52, NULL),
(5, 'Peter Spears', '1965-11-29', 55, NULL),
(6, 'David Strathairn', '1949-01-26', 72, NULL),
(7, 'Martin Lawrence', '1965-04-16', 56, NULL),
(8, 'Will Smith', '1968-09-25', 53, NULL),
(9, 'Ansel Elgort', '1994-03-14', 27, NULL),
(10, 'Kevin Spacey', '1959-07-26', 62, NULL),
(11, 'Lily James', '1989-04-05', 32, NULL),
(12, 'Morgan Freeman', '1937-06-01', 84, NULL),
(13, 'Michael Caine', '1933-03-14', 88, NULL),
(14, 'Alan Arkin', '1934-03-26', 87, NULL),
(15, 'Scarlett Johansson', '1984-11-22', 37, NULL),
(16, 'Takeshi Kitano', '1947-01-18', 74, NULL),
(17, 'Dwayne Johnson', '1972-05-02', 49, NULL),
(18, 'Ryan Reynolds', '1976-10-23', 45, NULL),
(19, 'Gal Gadot', '1985-04-30', 36, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dijak`
--
ALTER TABLE `dijak`
  ADD PRIMARY KEY (`szineszid`,`dij`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`filmid`),
  ADD KEY `film.studioid` (`studioid`);

--
-- Indexes for table `filmstudio`
--
ALTER TABLE `filmstudio`
  ADD PRIMARY KEY (`studioid`);

--
-- Indexes for table `mufajok`
--
ALTER TABLE `mufajok`
  ADD PRIMARY KEY (`filmid`,`mufaj`);

--
-- Indexes for table `szereplesek`
--
ALTER TABLE `szereplesek`
  ADD PRIMARY KEY (`szineszid`,`filmid`);

--
-- Indexes for table `szinesz`
--
ALTER TABLE `szinesz`
  ADD PRIMARY KEY (`szineszid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dijak`
--
ALTER TABLE `dijak`
  ADD CONSTRAINT `dijak_ibfk_1` FOREIGN KEY (`szineszid`) REFERENCES `szinesz` (`szineszid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `film.studioid` FOREIGN KEY (`studioid`) REFERENCES `filmstudio` (`studioid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mufajok`
--
ALTER TABLE `mufajok`
  ADD CONSTRAINT `mufajok.filmid` FOREIGN KEY (`filmid`) REFERENCES `film` (`filmid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `szereplesek`
--
ALTER TABLE `szereplesek`
  ADD CONSTRAINT `szereplesek.szineszid` FOREIGN KEY (`szineszid`) REFERENCES `szinesz` (`szineszid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
