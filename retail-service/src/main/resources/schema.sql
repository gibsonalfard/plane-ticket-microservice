-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2020 at 09:10 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `retail`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `provider_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Indexes for dumped tables
--

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
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `FKl2tdxa0d09htulk06vna91shb` (`provider_id`);

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
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `provider`
--
ALTER TABLE `provider`
  MODIFY `provider_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ticket_seat`
--
ALTER TABLE `ticket_seat`
  MODIFY `seat_id` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `FKl2tdxa0d09htulk06vna91shb` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`);

--
-- Constraints for table `ticket_seat`
--
ALTER TABLE `ticket_seat`
  ADD CONSTRAINT `FK6fvy4rkjntfbumyqtf584g2wk` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
