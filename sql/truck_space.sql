/*
Navicat MySQL Data Transfer

Source Server         : 1906
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : 1906_01

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 16:21:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for truck_space
-- ----------------------------
DROP TABLE IF EXISTS `truck_space`;
CREATE TABLE `truck_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_number` varchar(255) DEFAULT NULL COMMENT '车位编号',
  `price` decimal(10,2) DEFAULT NULL COMMENT '车位价格',
  `car_status` int(11) DEFAULT NULL COMMENT '0:无车  1有车',
  `car_level` int(11) DEFAULT NULL COMMENT '0:普通车位 1:会员车位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of truck_space
-- ----------------------------
