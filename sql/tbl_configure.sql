/*
Navicat MySQL Data Transfer

Source Server         : huzhu-aliyun
Source Server Version : 50140
Source Host           : 115.28.142.164:3306
Source Database       : xhyhz

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2016-12-09 11:29:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tbl_configure`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_configure`;
CREATE TABLE `tbl_configure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `conf_type` varchar(64) DEFAULT NULL COMMENT '合约类型',
  `conf_value` varchar(128) DEFAULT NULL COMMENT '合约值',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='配置信息表';

-- ----------------------------
-- Records of tbl_configure
-- ----------------------------
INSERT INTO `tbl_configure` VALUES ('109', 'contract_owner_address', 'ALPzx2aJtw8kLsfy2tMYLbBo2epN47znios', '2016-12-05 12:30:00', null, null);
INSERT INTO `tbl_configure` VALUES ('110', 'contract_owner_phone_num', '18500972365', '2016-12-05 12:30:37', null, null);
INSERT INTO `tbl_configure` VALUES ('111', 'contract_id', 'CONHAEQHoRDcpNsnbCCCqSTGMcqxEi14dsEb', '2016-12-05 18:27:19', null, null);
