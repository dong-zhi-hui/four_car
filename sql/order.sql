/*
Navicat MySQL Data Transfer

Source Server         : 1906
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : 1906_01

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 16:21:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `plate_number` varchar(255) DEFAULT NULL COMMENT '车牌号',
  `car_number` varchar(255) DEFAULT NULL COMMENT '车位编号',
  `price` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `order_status` int(255) DEFAULT NULL COMMENT '0:待支付 1已支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
