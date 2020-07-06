-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 06, 2020 at 03:16 PM
-- Server version: 10.2.31-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u8128384_retail`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`city_id`, `city_name`) VALUES
(1, 'Jakarta'),
(2, 'Bandung'),
(3, 'Denpasar'),
(4, 'Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `order_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_ticket`
--

CREATE TABLE `order_ticket` (
  `order_ticker_id` int(11) NOT NULL,
  `passager_name` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `seat_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL,
  `provider_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`provider_id`, `provider_name`) VALUES
(1, 'Garuda');

-- --------------------------------------------------------

--
-- Table structure for table `seat_class`
--

CREATE TABLE `seat_class` (
  `class_id` int(11) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seat_class`
--

INSERT INTO `seat_class` (`class_id`, `class_name`) VALUES
(1, 'First Class'),
(2, 'Business'),
(3, 'Economy');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL,
  `flight_date` date DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `destination` int(11) DEFAULT NULL,
  `origin` int(11) DEFAULT NULL,
  `provider_id` int(11) DEFAULT NULL,
  `seat_class` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticket_id`, `flight_date`, `price`, `stock`, `destination`, `origin`, `provider_id`, `seat_class`) VALUES
(1, '2020-07-07', 800000, 15, 3, 1, 1, 1),
(2, '2020-07-07', 500000, 25, 4, 1, 1, 3),
(3, '2020-07-10', 800000, 34, 1, 3, 1, 2),
(4, '2020-07-10', 500000, 34, 1, 4, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_seat`
--

CREATE TABLE `ticket_seat` (
  `seat_id` int(11) NOT NULL,
  `availability` int(11) NOT NULL,
  `seat_cols` int(11) NOT NULL,
  `seat_row` char(1) NOT NULL,
  `ticket_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_seat`
--

INSERT INTO `ticket_seat` (`seat_id`, `availability`, `seat_cols`, `seat_row`, `ticket_id`) VALUES
(2, 1, 1, 'A', 1),
(3, 1, 2, 'A', 1),
(4, 1, 3, 'A', 1),
(5, 1, 4, 'A', 1),
(6, 1, 1, 'B', 1),
(7, 1, 2, 'B', 1),
(8, 1, 3, 'B', 1),
(9, 1, 4, 'B', 1),
(10, 1, 1, 'C', 1),
(11, 1, 2, 'C', 1),
(12, 1, 3, 'C', 1),
(13, 1, 4, 'C', 1),
(14, 1, 1, 'D', 1),
(15, 1, 2, 'D', 1),
(16, 1, 3, 'D', 1),
(17, 1, 4, 'D', 1),
(18, 1, 1, 'E', 1),
(19, 1, 2, 'E', 1),
(20, 1, 3, 'E', 1),
(21, 0, 4, 'E', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_ticket`
--
ALTER TABLE `order_ticket`
  ADD PRIMARY KEY (`order_ticker_id`),
  ADD KEY `FK5wkauw4f43qi6wxx4femc1q8f` (`order_id`),
  ADD KEY `FK26lebbcpqt6mq04ihp52mgqe6` (`seat_id`);

--
-- Indexes for table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`provider_id`);

--
-- Indexes for table `seat_class`
--
ALTER TABLE `seat_class`
  ADD PRIMARY KEY (`class_id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `FKlfdi9vfh3t0sfqj7qnrm6y8w9` (`destination`),
  ADD KEY `FKoumesl3qft6e6t5vq6ddx4kps` (`origin`),
  ADD KEY `FKl2tdxa0d09htulk06vna91shb` (`provider_id`),
  ADD KEY `FK6fg4rm73pqml34me4gpb8hn9r` (`seat_class`);

--
-- Indexes for table `ticket_seat`
--
ALTER TABLE `ticket_seat`
  ADD PRIMARY KEY (`seat_id`),
  ADD KEY `FK6fvy4rkjntfbumyqtf584g2wk` (`ticket_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `provider`
--
ALTER TABLE `provider`
  MODIFY `provider_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `seat_class`
--
ALTER TABLE `seat_class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ticket_seat`
--
ALTER TABLE `ticket_seat`
  MODIFY `seat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_ticket`
--
ALTER TABLE `order_ticket`
  ADD CONSTRAINT `FK26lebbcpqt6mq04ihp52mgqe6` FOREIGN KEY (`seat_id`) REFERENCES `ticket_seat` (`seat_id`),
  ADD CONSTRAINT `FK5wkauw4f43qi6wxx4femc1q8f` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK6fg4rm73pqml34me4gpb8hn9r` FOREIGN KEY (`seat_class`) REFERENCES `seat_class` (`class_id`),
  ADD CONSTRAINT `FKl2tdxa0d09htulk06vna91shb` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`),
  ADD CONSTRAINT `FKlfdi9vfh3t0sfqj7qnrm6y8w9` FOREIGN KEY (`destination`) REFERENCES `city` (`city_id`),
  ADD CONSTRAINT `FKoumesl3qft6e6t5vq6ddx4kps` FOREIGN KEY (`origin`) REFERENCES `city` (`city_id`);

--
-- Constraints for table `ticket_seat`
--
ALTER TABLE `ticket_seat`
  ADD CONSTRAINT `FK6fvy4rkjntfbumyqtf584g2wk` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;