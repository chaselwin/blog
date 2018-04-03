/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : girl

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-08 01:32:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_type` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_width` double(11,2) DEFAULT NULL,
  `image_height` double(11,2) DEFAULT NULL,
  `image_object_name` varchar(255) DEFAULT NULL,
  `image_memo` varchar(255) DEFAULT NULL,
  `text_height` double(11,2) DEFAULT NULL,
  `image_create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `image_creator` varchar(255) DEFAULT NULL,
  `image_update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `image_updator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `image_id` (`id`),
  KEY `image_type` (`image_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
