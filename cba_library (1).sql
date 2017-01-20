-- phpMyAdmin SQL Dump
-- version 4.4.15.7
-- http://www.phpmyadmin.net
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pát 20. led 2017, 08:04
-- Verze serveru: 5.6.31
-- Verze PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `cba_library`
--
CREATE DATABASE IF NOT EXISTS `cba_library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cba_library`;

-- --------------------------------------------------------

--
-- Struktura tabulky `attribute`
--

CREATE TABLE IF NOT EXISTS `attribute` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `attribute`
--

INSERT INTO `attribute` (`id`, `name`) VALUES
(1, 'author');

-- --------------------------------------------------------

--
-- Struktura tabulky `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'book'),
(2, 'movie');

-- --------------------------------------------------------

--
-- Struktura tabulky `category_attribute`
--

CREATE TABLE IF NOT EXISTS `category_attribute` (
  `category_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `category_attribute`
--

INSERT INTO `category_attribute` (`category_id`, `attribute_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `img` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `item`
--

INSERT INTO `item` (`id`, `name`, `img`, `description`, `category_id`, `user_id`) VALUES
(1, 'Y Book', 'starwars.jpg', 'description 1', 1, 1),
(2, 'Movie', 'arrival.jpg', 'description 2', 2, 1),
(3, 'G Book', 'starwars.jpg', 'description 3', 1, 1),
(4, 'C Book', 'starwars.jpg', 'description 4', 1, 1),
(5, 'A Book', 'starwars.jpg', 'description 5', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `item_attribute`
--

CREATE TABLE IF NOT EXISTS `item_attribute` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `data` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `review`
--

CREATE TABLE IF NOT EXISTS `review` (
  `id` int(11) NOT NULL,
  `value` varchar(45) DEFAULT NULL,
  `timestamp` varchar(45) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabulky `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Vypisuji data pro tabulku `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `email`, `password`, `salt`) VALUES
(1, 'test', 'test', 'test', 'test', 'test');

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `attribute`
--
ALTER TABLE `attribute`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `category_attribute`
--
ALTER TABLE `category_attribute`
  ADD PRIMARY KEY (`category_id`,`attribute_id`),
  ADD KEY `fk_category_has_attribute_attribute1_idx` (`attribute_id`),
  ADD KEY `fk_category_has_attribute_category1_idx` (`category_id`);

--
-- Klíče pro tabulku `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`,`category_id`,`user_id`),
  ADD KEY `fk_item_category1_idx` (`category_id`),
  ADD KEY `fk_item_user1_idx` (`user_id`);

--
-- Klíče pro tabulku `item_attribute`
--
ALTER TABLE `item_attribute`
  ADD PRIMARY KEY (`id`,`item_id`,`attribute_id`),
  ADD KEY `fk_item_attribute_item1_idx` (`item_id`),
  ADD KEY `fk_item_attribute_attribute1_idx` (`attribute_id`);

--
-- Klíče pro tabulku `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`,`item_id`,`user_id`),
  ADD KEY `fk_review_item1_idx` (`item_id`),
  ADD KEY `fk_review_user1_idx` (`user_id`);

--
-- Klíče pro tabulku `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `attribute`
--
ALTER TABLE `attribute`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pro tabulku `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pro tabulku `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pro tabulku `item_attribute`
--
ALTER TABLE `item_attribute`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pro tabulku `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pro tabulku `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `category_attribute`
--
ALTER TABLE `category_attribute`
  ADD CONSTRAINT `fk_category_has_attribute_attribute1` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_category_has_attribute_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Omezení pro tabulku `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_item_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Omezení pro tabulku `item_attribute`
--
ALTER TABLE `item_attribute`
  ADD CONSTRAINT `fk_item_attribute_attribute1` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_attribute_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Omezení pro tabulku `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_review_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_review_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
