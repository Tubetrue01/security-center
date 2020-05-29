/*
 Navicat Premium Data Transfer

 Source Server         : docker-company
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 119.3.217.69:3306
 Source Schema         : security

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 29/05/2020 20:41:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `interface_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'url',
  `method` varchar(5) NOT NULL DEFAULT '' COMMENT '请求方法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
BEGIN;
INSERT INTO `interface_info` VALUES (1, '/role/all', 'get');
INSERT INTO `interface_info` VALUES (2, '/user/all', 'get');
COMMIT;

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(8) NOT NULL DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_info
-- ----------------------------
BEGIN;
INSERT INTO `role_info` VALUES (1, '管理员');
INSERT INTO `role_info` VALUES (2, '用户人员');
INSERT INTO `role_info` VALUES (3, '测试人员');
COMMIT;

-- ----------------------------
-- Table structure for role_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `role_interface_info`;
CREATE TABLE `role_interface_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `role_id` int unsigned NOT NULL COMMENT '角色编号',
  `interface_id` int unsigned NOT NULL COMMENT '接口编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_interface_info
-- ----------------------------
BEGIN;
INSERT INTO `role_interface_info` VALUES (1, 1, 1);
INSERT INTO `role_interface_info` VALUES (2, 1, 2);
INSERT INTO `role_interface_info` VALUES (3, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(12) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `role_id` int unsigned DEFAULT NULL COMMENT '角色编号',
  `mobile` char(11) NOT NULL DEFAULT '' COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 'root', '$2a$10$fTNRO7U1iluHs2D3wj5Hv.nf.m0Pv4Y6IHz/QPq9Bl/P0pEY3aKbq', 2, '15910201152');
INSERT INTO `user_info` VALUES (2, 'admin', '$2a$10$GUMJE/76NzlMNNMMuiyfhuPVH.xa2pFUUZbRawnHmaUHhNUBYTrdO', 1, '15910201150');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
