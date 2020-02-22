/*
Navicat MySQL Data Transfer

Source Server         : 192.168.160.130
Source Server Version : 50728
Source Host           : 192.168.160.130:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-22 18:09:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `oid` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for shop_product
-- ----------------------------
DROP TABLE IF EXISTS `shop_product`;
CREATE TABLE `shop_product` (
  `pid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `stock` int(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for shop_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user` (
  `uid` int(11) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for shop-txLog
-- ----------------------------
DROP TABLE IF EXISTS `shop-txLog`;
CREATE TABLE `shop-txLog` (
  `txId` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`txId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
