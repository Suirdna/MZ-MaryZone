-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Darbinė stotis: localhost
-- Atlikimo laikas: 2015 m. Lap 28 d. 20:57
-- Serverio versija: 5.5.34-cll-lve
-- PHP versija: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Duomenų bazė: `maryzone`
--

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `application_statistics`
--

CREATE TABLE IF NOT EXISTS `application_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_connected` text COLLATE utf8_lithuanian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci AUTO_INCREMENT=26 ;

--
-- Sukurta duomenų kopija lentelei `application_statistics`
--

INSERT INTO `application_statistics` (`id`, `application_connected`) VALUES
(1, '20151123'),
(2, '20151123'),
(3, '20151123'),
(4, '20151123'),
(5, '20151123'),
(6, '20151123'),
(7, '20151123'),
(8, '20151123'),
(9, '20151123'),
(10, '20151123'),
(11, '20151123'),
(12, '20151123'),
(13, '20151123'),
(14, '20151123'),
(15, '20151123'),
(16, '20151123'),
(17, '20151123'),
(18, '20151123'),
(19, '20151123'),
(20, '20151123'),
(21, '20151123'),
(22, '20151123'),
(23, '20151123'),
(24, '20151128'),
(25, '20151128');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `global_advertisiment`
--

CREATE TABLE IF NOT EXISTS `global_advertisiment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adv_add_idd` int(11) NOT NULL,
  `adv_get_idd` int(11) NOT NULL,
  `adv_add_username` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_get_username` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_year` int(11) NOT NULL,
  `adv_month` int(11) NOT NULL,
  `adv_day` int(11) NOT NULL,
  `adv_reputation` int(11) NOT NULL,
  `adv_weight` int(11) NOT NULL,
  `adv_price` int(11) NOT NULL,
  `adv_country` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_city` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_add_signal` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_get_signal` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_stop_signal` text COLLATE utf8_lithuanian_ci NOT NULL,
  `adv_global_signal` int(11) NOT NULL,
  `adv_add_like_signal` text COLLATE utf8_lithuanian_ci,
  `adv_get_like_signal` text COLLATE utf8_lithuanian_ci,
  `adv_status` text COLLATE utf8_lithuanian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci AUTO_INCREMENT=7 ;

--
-- Sukurta duomenų kopija lentelei `global_advertisiment`
--

INSERT INTO `global_advertisiment` (`id`, `adv_add_idd`, `adv_get_idd`, `adv_add_username`, `adv_get_username`, `adv_year`, `adv_month`, `adv_day`, `adv_reputation`, `adv_weight`, `adv_price`, `adv_country`, `adv_city`, `adv_add_signal`, `adv_get_signal`, `adv_stop_signal`, `adv_global_signal`, `adv_add_like_signal`, `adv_get_like_signal`, `adv_status`) VALUES
(1, 1, 0, 'MaryJane', 'null', 2015, 10, 23, 0, 1, 9, 'Lietuva', 'Zarasai', 'null', 'null', 'null', 0, 'null', 'null', 'sold'),
(2, 1, 0, 'MaryJane', 'null', 2015, 10, 23, 1000, 1, 8, 'Lietuva', 'Zarasai', 'null', 'null', 'null', 0, 'null', 'falseSignal', 'on_sale'),
(3, 1, 0, 'MaryJane', 'null', 2015, 10, 23, 1000, 1, 9, 'Lietuva', 'Utena', 'null', 'null', 'null', 0, 'normal', 'normal', 'on_sale'),
(4, 2, 0, 'Torreto', 'null', 2015, 10, 23, 0, 2, 10, 'Lietuva', 'Akmenė', 'null', 'null', 'null', 0, 'normal', 'normal', 'on_sale'),
(5, 2, 0, 'Torreto', 'null', 2015, 10, 23, 0, 2, 10, 'Lietuva', 'Kretinga', 'null', 'null', 'null', 0, 'normal', 'normal', 'on_sale'),
(6, 1, 0, 'MaryJane', 'null', 2015, 10, 23, 1000, 1, 1, 'Lietuva', 'Akmen?', 'null', 'null', 'null', 0, 'normal', 'normal', 'on_sale');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `global_messages`
--

CREATE TABLE IF NOT EXISTS `global_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `user` text COLLATE utf8_lithuanian_ci NOT NULL,
  `messages` text COLLATE utf8_lithuanian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci AUTO_INCREMENT=16 ;

--
-- Sukurta duomenų kopija lentelei `global_messages`
--

INSERT INTO `global_messages` (`id`, `item_id`, `user`, `messages`) VALUES
(15, 2, 'sysAdmin', 'ATSIJUNG? NUO POKALBIO!');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `user_information`
--

CREATE TABLE IF NOT EXISTS `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text COLLATE utf8_lithuanian_ci NOT NULL,
  `password` text COLLATE utf8_lithuanian_ci NOT NULL,
  `question` text COLLATE utf8_lithuanian_ci NOT NULL,
  `answer` text COLLATE utf8_lithuanian_ci NOT NULL,
  `reputation` int(11) NOT NULL,
  `warnings` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci AUTO_INCREMENT=5 ;

--
-- Sukurta duomenų kopija lentelei `user_information`
--

INSERT INTO `user_information` (`id`, `username`, `password`, `question`, `answer`, `reputation`, `warnings`) VALUES
(1, 'MaryJane', 'lol1', 'Kas tu toks?', 'Andrius', 1000, 0),
(3, 'AppTester1', 'system32', 'sys?', 'admin', 0, 0),
(4, 'sysAdmin', '0', 'Kas aš?', 'as', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
