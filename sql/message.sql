/*
Navicat MySQL Data Transfer

Source Server         : 1906
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : 1906_01

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 16:21:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_contents` varchar(255) DEFAULT NULL COMMENT '留言内容',
  `create_time` datetime DEFAULT NULL COMMENT '留言时间',
  `user_name` varchar(255) DEFAULT NULL COMMENT '留言人',
  `response` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `response_time` datetime DEFAULT NULL COMMENT '回复时间',
  `response_name` varchar(255) DEFAULT NULL COMMENT '回复人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
