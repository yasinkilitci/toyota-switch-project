-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 06 Haz 2013, 20:30:40
-- Sunucu sürümü: 5.5.24-log
-- PHP Sürümü: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `networkdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cihaz`
--

CREATE TABLE IF NOT EXISTS `cihaz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `resim_yolu` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `tur_id` int(11) DEFAULT NULL,
  `uretici_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=6 ;

--
-- Tablo döküm verisi `cihaz`
--

INSERT INTO `cihaz` (`id`, `ad`, `ip`, `resim_yolu`, `tur_id`, `uretici_id`) VALUES
(1, 'Cisco SW-7250', '192.168.1.1', NULL, 1, 1),
(2, 'Telco Ultramanyak Switch', '1.0.0.1', NULL, 1, 1),
(3, 'Cisco Mini SW-1250E', '88.55.13.5', NULL, 1, 1),
(4, 'SW-7266E', '65.32.12.9', NULL, 1, 1),
(5, '234', '345', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k_id` int(11) NOT NULL,
  `sipno` int(11) NOT NULL,
  `urun_id` int(11) NOT NULL,
  `adet` int(11) NOT NULL,
  `onay` int(11) NOT NULL,
  `tarih` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=103 ;

--
-- Tablo döküm verisi `order`
--

INSERT INTO `order` (`id`, `k_id`, `sipno`, `urun_id`, `adet`, `onay`, `tarih`) VALUES
(101, 35, 2001, 79, 7, 1, '2013-06-03'),
(102, 35, 2001, 85, 5, 1, '2013-06-03');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `port`
--

CREATE TABLE IF NOT EXISTS `port` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duplex` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `sonerisim` datetime DEFAULT NULL,
  `speedtype` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `vlan` int(11) DEFAULT NULL,
  `cihaz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3u33mb00kld06c4174kx5jxiv` (`cihaz_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=121 ;

--
-- Tablo döküm verisi `port`
--

INSERT INTO `port` (`id`, `duplex`, `name`, `sonerisim`, `speedtype`, `status`, `vlan`, `cihaz_id`) VALUES
(1, 0, 'Fa0/0', '2013-06-05 21:17:56', 0, 0, 0, 1),
(2, 0, 'Fa0/1', '2013-06-05 21:17:56', 0, 0, 0, 1),
(3, 0, 'Fa0/2', '2013-06-05 21:17:56', 0, 0, 0, 1),
(4, 0, 'Fa0/3', '2013-06-05 21:17:56', 0, 0, 0, 1),
(5, 0, 'Fa0/4', '2013-06-05 21:17:56', 0, 0, 0, 1),
(6, 0, 'Fa0/5', '2013-06-05 21:17:56', 0, 0, 0, 1),
(7, 0, 'Fa0/6', '2013-06-05 21:17:56', 0, 0, 0, 1),
(8, 0, 'Fa0/7', '2013-06-05 21:17:56', 0, 0, 0, 1),
(9, 0, 'Fa0/8', '2013-06-05 21:17:56', 0, 0, 0, 1),
(10, 0, 'Fa0/9', '2013-04-05 21:17:56', 0, 0, 0, 1),
(11, 0, 'Fa0/10', '2013-06-05 21:17:56', 0, 0, 0, 1),
(12, 0, 'Fa0/11', '2013-06-05 21:17:56', 0, 0, 0, 1),
(13, 0, 'Fa0/12', '2013-06-05 21:17:56', 0, 0, 0, 1),
(14, 0, 'Fa0/13', '2013-06-05 21:17:56', 0, 0, 0, 1),
(15, 0, 'Fa0/14', '2013-04-05 21:17:56', 0, 0, 0, 1),
(16, 0, 'Fa0/15', '2013-06-05 21:17:56', 0, 0, 0, 1),
(17, 0, 'Fa0/16', '2013-06-05 21:17:56', 0, 0, 0, 1),
(18, 0, 'Fa0/17', '2013-06-05 21:17:56', 0, 0, 0, 1),
(19, 0, 'Fa0/18', '2013-06-05 21:17:56', 0, 0, 0, 1),
(20, 0, 'Fa0/19', '2013-04-05 21:17:56', 0, 0, 0, 1),
(21, 0, 'Fa0/20', '2013-06-05 21:17:56', 0, 0, 0, 1),
(22, 0, 'Fa0/21', '2013-06-05 21:17:56', 0, 0, 0, 1),
(23, 0, 'Fa0/22', '2013-06-05 21:17:56', 0, 0, 0, 1),
(24, 0, 'Fa0/23', '2013-06-05 21:17:56', 0, 0, 0, 1),
(25, 0, 'Fa0/0', '2013-06-05 21:18:35', 0, 0, 0, 2),
(26, 0, 'Fa0/1', '2013-06-05 21:18:35', 0, 0, 0, 2),
(27, 0, 'Fa0/2', '2013-04-05 21:18:35', 0, 0, 0, 2),
(28, 0, 'Fa0/3', '2013-06-05 21:18:35', 0, 0, 0, 2),
(29, 0, 'Fa0/4', '2013-06-05 21:18:35', 0, 0, 0, 2),
(30, 0, 'Fa0/5', '2013-06-05 21:18:35', 0, 0, 0, 2),
(31, 0, 'Fa0/6', '2013-06-05 21:18:35', 0, 0, 0, 2),
(32, 0, 'Fa0/7', '2013-06-05 21:18:35', 0, 0, 0, 2),
(33, 0, 'Fa0/8', '2013-06-05 21:18:35', 0, 0, 0, 2),
(34, 0, 'Fa0/9', '2013-06-05 21:18:35', 0, 0, 0, 2),
(35, 0, 'Fa0/10', '2013-06-05 21:18:35', 0, 0, 0, 2),
(36, 0, 'Fa0/11', '2013-06-05 21:18:35', 0, 0, 0, 2),
(37, 0, 'Fa0/12', '2013-06-05 21:18:35', 0, 0, 0, 2),
(38, 0, 'Fa0/13', '2013-06-05 21:18:35', 0, 0, 0, 2),
(39, 0, 'Fa0/14', '2013-06-05 21:18:35', 0, 0, 0, 2),
(40, 0, 'Fa0/15', '2013-06-05 21:18:35', 0, 0, 0, 2),
(41, 0, 'Fa0/16', '2013-06-05 21:18:35', 0, 0, 0, 2),
(42, 0, 'Fa0/17', '2013-06-05 21:18:35', 0, 0, 0, 2),
(43, 0, 'Fa0/18', '2013-06-05 21:18:35', 0, 0, 0, 2),
(44, 0, 'Fa0/19', '2013-06-05 21:18:35', 0, 0, 0, 2),
(45, 0, 'Fa0/20', '2013-06-05 21:18:35', 0, 0, 0, 2),
(46, 0, 'Fa0/21', '2013-06-05 21:18:35', 0, 0, 0, 2),
(47, 0, 'Fa0/22', '2013-06-05 21:18:35', 0, 0, 0, 2),
(48, 0, 'Fa0/23', '2013-06-05 21:18:35', 0, 0, 0, 2),
(49, 0, 'Fa0/0', '2013-06-05 21:19:39', 0, 0, 0, 3),
(50, 0, 'Fa0/1', '2013-06-05 21:19:39', 0, 0, 0, 3),
(51, 0, 'Fa0/2', '2013-06-05 21:19:39', 0, 0, 0, 3),
(52, 0, 'Fa0/3', '2013-06-05 21:19:39', 0, 0, 0, 3),
(53, 0, 'Fa0/4', '2013-06-05 21:19:39', 0, 0, 0, 3),
(54, 0, 'Fa0/5', '2013-06-05 21:19:39', 0, 0, 0, 3),
(55, 0, 'Fa0/6', '2013-06-05 21:19:39', 0, 0, 0, 3),
(56, 0, 'Fa0/7', '2013-06-05 21:19:39', 0, 0, 0, 3),
(57, 0, 'Fa0/8', '2013-06-05 21:19:39', 0, 0, 0, 3),
(58, 0, 'Fa0/9', '2013-06-05 21:19:39', 0, 0, 0, 3),
(59, 0, 'Fa0/10', '2013-06-05 21:19:39', 0, 0, 0, 3),
(60, 0, 'Fa0/11', '2013-06-05 21:19:39', 0, 0, 0, 3),
(61, 0, 'Fa0/12', '2013-06-05 21:19:39', 0, 0, 0, 3),
(62, 0, 'Fa0/13', '2013-06-05 21:19:39', 0, 0, 0, 3),
(63, 0, 'Fa0/14', '2013-06-05 21:19:39', 0, 0, 0, 3),
(64, 0, 'Fa0/15', '2013-06-05 21:19:39', 0, 0, 0, 3),
(65, 0, 'Fa0/16', '2013-06-05 21:19:39', 0, 0, 0, 3),
(66, 0, 'Fa0/17', '2013-06-05 21:19:39', 0, 0, 0, 3),
(67, 0, 'Fa0/18', '2013-06-05 21:19:39', 0, 0, 0, 3),
(68, 0, 'Fa0/19', '2013-06-05 21:19:39', 0, 0, 0, 3),
(69, 0, 'Fa0/20', '2013-06-05 21:19:39', 0, 0, 0, 3),
(70, 0, 'Fa0/21', '2013-06-05 21:19:39', 0, 0, 0, 3),
(71, 0, 'Fa0/22', '2013-06-05 21:19:39', 0, 0, 0, 3),
(72, 0, 'Fa0/23', '2013-06-05 21:19:39', 0, 0, 0, 3),
(73, 0, 'Fa0/0', '2013-06-06 13:28:19', 0, 0, 0, 4),
(74, 0, 'Fa0/1', '2013-06-06 13:28:19', 0, 0, 0, 4),
(75, 0, 'Fa0/2', '2013-06-06 13:28:19', 0, 0, 0, 4),
(76, 0, 'Fa0/3', '2013-06-06 13:28:19', 0, 0, 0, 4),
(77, 0, 'Fa0/4', '2013-06-06 13:28:19', 0, 0, 0, 4),
(78, 0, 'Fa0/5', '2013-06-06 13:28:19', 0, 0, 0, 4),
(79, 0, 'Fa0/6', '2013-06-06 13:28:19', 0, 0, 0, 4),
(80, 0, 'Fa0/7', '2013-06-06 13:28:19', 0, 0, 0, 4),
(81, 0, 'Fa0/8', '2013-06-06 13:28:19', 0, 0, 0, 4),
(82, 0, 'Fa0/9', '2013-06-06 13:28:19', 0, 0, 0, 4),
(83, 0, 'Fa0/10', '2013-06-06 13:28:19', 0, 0, 0, 4),
(84, 0, 'Fa0/11', '2013-06-06 13:28:19', 0, 0, 0, 4),
(85, 0, 'Fa0/12', '2013-06-06 13:28:19', 0, 0, 0, 4),
(86, 0, 'Fa0/13', '2013-06-06 13:28:19', 0, 0, 0, 4),
(87, 0, 'Fa0/14', '2013-06-06 13:28:19', 0, 0, 0, 4),
(88, 0, 'Fa0/15', '2013-06-06 13:28:19', 0, 0, 0, 4),
(89, 0, 'Fa0/16', '2013-06-06 13:28:19', 0, 0, 0, 4),
(90, 0, 'Fa0/17', '2013-06-06 13:28:19', 0, 0, 0, 4),
(91, 0, 'Fa0/18', '2013-06-06 13:28:19', 0, 0, 0, 4),
(92, 0, 'Fa0/19', '2013-06-06 13:28:19', 0, 0, 0, 4),
(93, 0, 'Fa0/20', '2013-06-06 13:28:19', 0, 0, 0, 4),
(94, 0, 'Fa0/21', '2013-06-06 13:28:19', 0, 0, 0, 4),
(95, 0, 'Fa0/22', '2013-06-06 13:28:19', 0, 0, 0, 4),
(96, 0, 'Fa0/23', '2013-06-06 13:28:19', 0, 0, 0, 4),
(97, 0, 'Fa0/0', '2013-06-06 13:37:02', 0, 0, 0, 5),
(98, 0, 'Fa0/1', '2013-06-06 13:37:02', 0, 0, 0, 5),
(99, 0, 'Fa0/2', '2013-06-06 13:37:02', 0, 0, 0, 5),
(100, 0, 'Fa0/3', '2013-06-06 13:37:02', 0, 0, 0, 5),
(101, 0, 'Fa0/4', '2013-06-06 13:37:02', 0, 0, 0, 5),
(102, 0, 'Fa0/5', '2013-06-06 13:37:02', 0, 0, 0, 5),
(103, 0, 'Fa0/6', '2013-06-06 13:37:02', 0, 0, 0, 5),
(104, 0, 'Fa0/7', '2013-06-06 13:37:02', 0, 0, 0, 5),
(105, 0, 'Fa0/8', '2013-06-06 13:37:02', 0, 0, 0, 5),
(106, 0, 'Fa0/9', '2013-06-06 13:37:02', 0, 0, 0, 5),
(107, 0, 'Fa0/10', '2013-06-06 13:37:02', 0, 0, 0, 5),
(108, 0, 'Fa0/11', '2013-06-06 13:37:02', 0, 0, 0, 5),
(109, 0, 'Fa0/12', '2013-06-06 13:37:02', 0, 0, 0, 5),
(110, 0, 'Fa0/13', '2013-06-06 13:37:02', 0, 0, 0, 5),
(111, 0, 'Fa0/14', '2013-06-06 13:37:02', 0, 0, 0, 5),
(112, 0, 'Fa0/15', '2013-06-06 13:37:02', 0, 0, 0, 5),
(113, 0, 'Fa0/16', '2013-06-06 13:37:02', 0, 0, 0, 5),
(114, 0, 'Fa0/17', '2013-06-06 13:37:02', 0, 0, 0, 5),
(115, 0, 'Fa0/18', '2013-06-06 13:37:02', 0, 0, 0, 5),
(116, 0, 'Fa0/19', '2013-06-06 13:37:02', 0, 0, 0, 5),
(117, 0, 'Fa0/20', '2013-06-06 13:37:02', 0, 0, 0, 5),
(118, 0, 'Fa0/21', '2013-06-06 13:37:02', 0, 0, 0, 5),
(119, 0, 'Fa0/22', '2013-06-06 13:37:02', 0, 0, 0, 5),
(120, 0, 'Fa0/23', '2013-06-06 13:37:02', 0, 0, 0, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sorumluluk`
--

CREATE TABLE IF NOT EXISTS `sorumluluk` (
  `kul_id` int(11) NOT NULL,
  `cihaz_id` int(11) NOT NULL,
  PRIMARY KEY (`kul_id`,`cihaz_id`),
  KEY `FK_3qxxegkx8hds8i50aripglxra` (`cihaz_id`),
  KEY `FK_as4ovignrnre4avx7h8xgid51` (`kul_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `sorumluluk`
--

INSERT INTO `sorumluluk` (`kul_id`, `cihaz_id`) VALUES
(35, 2),
(35, 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `tur`
--

CREATE TABLE IF NOT EXISTS `tur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=5 ;

--
-- Tablo döküm verisi `tur`
--

INSERT INTO `tur` (`id`, `ad`) VALUES
(1, 'Switch'),
(2, 'Router'),
(4, 'Gateway');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `uretici`
--

CREATE TABLE IF NOT EXISTS `uretici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=16 ;

--
-- Tablo döküm verisi `uretici`
--

INSERT INTO `uretici` (`id`, `ad`) VALUES
(1, 'Cisco Systems'),
(2, 'Telco Systems'),
(3, 'Dell'),
(4, 'OneAccess'),
(5, 'Juniper Networks'),
(6, 'Huawei'),
(7, 'Rad Data Communications'),
(8, 'Avaya'),
(9, 'Alcatel-Lucent'),
(10, 'ECI Telecom'),
(11, 'Netgear'),
(12, 'Mellanox Technologies'),
(13, 'Level One'),
(14, 'Enterasys Networks'),
(15, 'Brocade Communications Systems');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kuladi` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `adsoyad` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `adres` varchar(150) COLLATE utf8_turkish_ci NOT NULL,
  `tel` int(11) NOT NULL,
  `eposta` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `yetki` int(11) NOT NULL,
  `sifre` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eposta` (`eposta`),
  UNIQUE KEY `kuladi` (`kuladi`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=37 ;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `kuladi`, `adsoyad`, `adres`, `tel`, `eposta`, `yetki`, `sifre`) VALUES
(35, 'ilker007yasin', 'ilker yasin yildiz', 'Camlitepe', 262, 'ilkeryasinyildiz@gmail.com', 0, '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5'),
(36, 'admin', 'Administrator', 'SAU', 1234, 'yasinkilitci@gmail.com', 1, '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `port`
--
ALTER TABLE `port`
  ADD CONSTRAINT `FK_3u33mb00kld06c4174kx5jxiv` FOREIGN KEY (`cihaz_id`) REFERENCES `cihaz` (`id`);

--
-- Tablo kısıtlamaları `sorumluluk`
--
ALTER TABLE `sorumluluk`
  ADD CONSTRAINT `FK_3qxxegkx8hds8i50aripglxra` FOREIGN KEY (`cihaz_id`) REFERENCES `cihaz` (`id`),
  ADD CONSTRAINT `FK_as4ovignrnre4avx7h8xgid51` FOREIGN KEY (`kul_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
