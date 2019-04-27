/*
Navicat MySQL Data Transfer

Source Server         : 123.207.137.208
Source Server Version : 50716
Source Host           : 123.207.137.208:3306
Source Database       : bigdata

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-27 09:22:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for loginUser
-- ----------------------------
DROP TABLE IF EXISTS `loginUser`;
CREATE TABLE `loginUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `uuid` varchar(32) NOT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `href` varchar(1024) DEFAULT NULL,
  `imgSrc` varchar(1024) DEFAULT NULL,
  `jiNumber` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for movie_star
-- ----------------------------
DROP TABLE IF EXISTS `movie_star`;
CREATE TABLE `movie_star` (
  `uuid` varchar(32) NOT NULL,
  `movieUuid` varchar(32) NOT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `href` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tv
-- ----------------------------
DROP TABLE IF EXISTS `tv`;
CREATE TABLE `tv` (
  `uuid` varchar(32) NOT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `href` varchar(1024) DEFAULT NULL,
  `imgSrc` varchar(1024) DEFAULT NULL,
  `jiNumber` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tv_star
-- ----------------------------
DROP TABLE IF EXISTS `tv_star`;
CREATE TABLE `tv_star` (
  `uuid` varchar(32) NOT NULL,
  `tvUuid` varchar(32) NOT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `href` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
