-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2021 at 05:15 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `courseCode` varchar(255) NOT NULL,
  `regNumber` varchar(255) NOT NULL,
  `DATE` varchar(255) DEFAULT NULL,
  `presence` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `courseCode`, `regNumber`, `DATE`, `presence`) VALUES
(10, 'CSE2005', '1', '01/04/2020', 1),
(11, 'CSE2005', '1', '02/04/2020', 1),
(12, 'CSE2005', '1', '03/04/2020', 0),
(13, 'EVS2001', '1234', '02/04/2020', 1),
(14, 'EVS2001', '1', '02/04/2020', 0),
(15, 'EVS2001', '12345', '02/04/2020', 0),
(16, 'EVS2001', '1234', '25/05/2020', 1),
(17, 'EVS2001', '12345', '25/05/2020', 1),
(18, 'EVS2001', '1', '25/05/2020', 0);

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `courseCode` varchar(255) NOT NULL,
  `regNumber` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`courseCode`, `regNumber`) VALUES
('CSE2005', '1'),
('EVS2001', '1'),
('EVS2001', '1234'),
('EVS2001', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `credits` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `facUsername` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`code`, `name`, `description`, `credits`, `type`, `facUsername`) VALUES
('CSE2005', 'DD', 'DD', '1', 'Theory Only', ''),
('ECE1001', 'Electronics For Engineers', 'Electronics For Engineers.', '4', 'Lab Only', 'Abhinavgg'),
('EEE2001', 'Basic EEE', 'Very Basic', '3', 'Theory', 'Abhinavgg'),
('EVS2001', 'Environmental Sciences', 'EVS for kids', '2', 'Embedded', 'AbhinavF'),
('gdfgdgfd', 'dgdfgfdg', 'dfgdfg', '1', 'Theory Only', '');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `username` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`username`, `fname`, `lname`, `email`, `password`, `phone`) VALUES
('1', 'Someone', 'New', 'a@gmail.com', '1', '1234567890'),
('a1234', 'Acer', 'Preadator', 'dsa@sss.com', '123', '1234567890'),
('AbhinavF', 'AbhinavF', 'D', 'AbhinavF@gmail.com', '1', '1234567890'),
('Abhinavgg', 'Abhinavgg', 'Abhinavgg', 'Abhinav@gmail.com', '1', '1234567890'),
('cc', 'Siddhant', 'Dwivedi', 's@gmail.com', '123', '111');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `regno` varchar(255) NOT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `Branch` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`regno`, `fname`, `lname`, `email`, `phone`, `address`, `Branch`, `password`) VALUES
('1', 'FF', 'FF', 'f@gmail.com', '1111111111', 'FF1', 'CSE', '1'),
('1234', 'PRANJAL', 'sharma', '1@gmail.com', '1111111111', '11', '111', '111'),
('12345', 'ABHINAV', 'dhamaniya', 'a@gmail.com', '1111111111', '12', '121', '111'),
('17BCE1121', 'ABHINAV', 'Dhamaniya', 'a@gmail.com', '123', '111', 'CSE', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`courseCode`,`regNumber`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`regno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
