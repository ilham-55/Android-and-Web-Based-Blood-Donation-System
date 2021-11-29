-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 21, 2019 at 10:07 AM
-- Server version: 5.7.27-cll-lve
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arbloodb_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`) VALUES
(1, 'Admin', 'admin@gmail.com', '1234'),
(2, 'Admin', 'joy@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `blood_donor`
--

CREATE TABLE `blood_donor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `cell` varchar(20) NOT NULL,
  `location` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `blood_group` varchar(5) NOT NULL,
  `last_donate_date` varchar(255) DEFAULT NULL,
  `p_timestamp` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood_donor`
--

INSERT INTO `blood_donor` (`id`, `name`, `gender`, `cell`, `location`, `password`, `blood_group`, `last_donate_date`, `p_timestamp`) VALUES
(1, 'Kamal Uddin', 'Male', '01672902636', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'AB-', '2019/07/03', '2019-07-01 14:30:41.899080'),
(16, 'Abu Rayhan', 'Male', '01616311131', 'Dhaka', '827ccb0eea8a706c4c34a16891f84e7b', 'B+', NULL, '2019-07-28 15:41:06.499249'),
(2, 'Rahat Muhin Choudhury', 'Male', '01673535634', 'Chittagong', '12345', 'O+', '2019/04/12', '2019-06-30 18:00:00.000000'),
(29, 'orpit', 'Male', '01838886416', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'A+', '2019/10/18', '2019-10-19 15:44:03.947504'),
(28, 'suvrangshu barua', 'Male', '01733309938', 'Khulna', '81dc9bdb52d04dc20036dbd8313ed055', 'A+', '2019/10/14', '2019-10-16 16:16:28.519057'),
(27, 'rijvi', 'Male', '01867678874', 'Khulna', '81dc9bdb52d04dc20036dbd8313ed055', 'B+', '2019/10/16', '2019-10-16 14:45:49.852082'),
(26, 'shuvo', 'Male', '01670840500', 'Chittagong', 'ad69bc5f0d6a632545657de79e669966', 'B+', NULL, '2019-10-15 03:55:33.559079'),
(11, 'Sajjad Hossain', 'Male', '01815348555', 'Chittagong', '827ccb0eea8a706c4c34a16891f84e7b', 'B+', NULL, '2019-07-18 19:51:27.383618'),
(12, 'Mohammed Jahedul Islam', 'Male', '01521488481', 'Chittagong', '827ccb0eea8a706c4c34a16891f84e7b', 'B+', '2019/09/10', '2019-07-18 19:52:37.617612'),
(13, 'Mohammed Gias Uddin', 'Male', '01676135848', 'Chittagong', '827ccb0eea8a706c4c34a16891f84e7b', 'B+', NULL, '2019-07-18 19:53:42.412084'),
(14, 'Golam Mortuza', 'Male', '01682035868', 'Chittagong', '827ccb0eea8a706c4c34a16891f84e7b', 'O+', '2019/05/20', '2019-07-18 19:56:25.084444'),
(15, 'Masud Khan', 'Male', '01838679747', 'Chittagong', '827ccb0eea8a706c4c34a16891f84e7b', 'AB+', NULL, '2019-07-18 20:05:45.875811'),
(17, 'ramkalyanamnaidu', 'Male', '01713373099', 'Dhaka', 'd8578edf8458ce06fbc5bb76a58c5ca4', 'AB+', NULL, '2019-07-31 13:18:24.906183'),
(18, 'Rahim Uddin', 'Male', '01642751006', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'A+', NULL, '2019-08-05 14:47:35.915204'),
(25, 'Rahim Uddin', 'Male', '01711733850', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'AB+', NULL, '2019-10-08 16:27:09.023605'),
(24, 'Joy Kumar Shill', 'Male', '01836776063', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'AB+', NULL, '2019-10-01 14:36:28.391453'),
(23, 'Kamal Uddin', 'Male', '01672902634', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'AB-', '2019/07/03', '2019-07-01 14:30:41.899080'),
(67, 'prabir', 'Male', '01711733858', 'Chittagong', '81dc9bdb52d04dc20036dbd8313ed055', 'B+', NULL, '2019-10-21 04:05:28.637844');

-- --------------------------------------------------------

--
-- Table structure for table `blood_search`
--

CREATE TABLE `blood_search` (
  `id` int(100) NOT NULL,
  `cell` int(11) NOT NULL,
  `blood_group` varchar(3) NOT NULL,
  `location` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cell` varchar(20) NOT NULL,
  `location` varchar(255) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `longitude` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`id`, `name`, `cell`, `location`, `latitude`, `longitude`) VALUES
(1, 'Maa O Shishu Hospital', '01642751006', 'O.R Nizam Road, GEC, Chittagong', '22.330370', '91.832626'),
(2, 'Bandar Hospital', '01642751006', 'O.R Nizam Road, GEC, Chittagong', '22.330370', '91.832626'),
(3, 'Chittagong Medical College', '01642751006', 'O.R Nizam Road, GEC, Chittagong', '22.330370', '91.832626');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blood_donor`
--
ALTER TABLE `blood_donor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `blood_donor`
--
ALTER TABLE `blood_donor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
