/*
Navicat MySQL Data Transfer

Source Server         : 1906
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : 1906_01

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 16:21:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `plate number` varchar(255) DEFAULT NULL COMMENT '车牌号',
  `user_status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `level` varchar(255) DEFAULT NULL COMMENT '0:普通用户 1:普通会员 2:高级会员 3:管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zs', '123', null, null, null, null, null);
INSERT INTO `user` VALUES ('2', 'li', '123', null, null, null, null, null);
INSERT INTO `user` VALUES ('3', 'wu', '123', null, null, null, null, null);
INSERT INTO `user` VALUES ('4', 'ww', '123', null, null, null, null, null);
