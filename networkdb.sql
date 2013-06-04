-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 04 Haz 2013, 11:21:46
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
  `ad` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `fiyat` int(11) NOT NULL,
  `tur_id` int(11) NOT NULL,
  `uretici_id` int(11) NOT NULL,
  `resim_yolu` varchar(200) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ad` (`ad`),
  KEY `tur_id` (`tur_id`),
  KEY `uretici_id` (`uretici_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=89 ;

--
-- Tablo döküm verisi `cihaz`
--

INSERT INTO `cihaz` (`id`, `ad`, `fiyat`, `tur_id`, `uretici_id`, `resim_yolu`) VALUES
(69, 'SW-7854', 500, 1, 1, NULL),
(77, 'SW-7855', 456, 1, 1, NULL),
(79, 'SW-7856', 3425, 1, 1, NULL),
(80, 'Cisco Router 3485', 4500, 2, 1, NULL),
(81, 'Cisco Edge Router 125E', 980, 2, 1, NULL),
(82, 'SW-5546', 4500, 1, 1, NULL),
(84, 'SW-5547', 234, 1, 1, NULL),
(85, 'SW-5850', 344, 1, 1, NULL),
(86, 'Super Switch', 2300, 1, 1, NULL),
(87, 'Juniper Switch S4855', 480, 1, 5, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=107 ;

--
-- Tablo döküm verisi `order`
--

INSERT INTO `order` (`id`, `k_id`, `sipno`, `urun_id`, `adet`, `onay`, `tarih`) VALUES
(101, 35, 2001, 79, 7, 1, '2013-06-03'),
(102, 35, 2001, 85, 5, 1, '2013-06-03');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=53 ;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `kuladi`, `adsoyad`, `adres`, `tel`, `eposta`, `yetki`, `sifre`) VALUES
(35, 'ilker007yasin', 'ilker yasin yildiz', 'Camlitepe', 262, 'ilker007yasin@hotmail.com', 0, '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5'),
(36, 'admin', 'Administrator', 'SAU', 1234, 'admin@nethopping.com', 1, '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `cihaz`
--
ALTER TABLE `cihaz`
  ADD CONSTRAINT `cihaz_ibfk_1` FOREIGN KEY (`tur_id`) REFERENCES `tur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cihaz_ibfk_2` FOREIGN KEY (`uretici_id`) REFERENCES `uretici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
