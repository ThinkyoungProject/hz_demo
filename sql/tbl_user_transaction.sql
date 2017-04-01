/*
Navicat MySQL Data Transfer

Source Server         : huzhu-aliyun
Source Server Version : 50140
Source Host           : 115.28.142.164:3306
Source Database       : xhyhz

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2016-12-09 11:30:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tbl_user_transaction`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_transaction`;
CREATE TABLE `tbl_user_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `trx_from_address` varchar(128) DEFAULT NULL COMMENT '交易发起地址',
  `transfer_from_address` varchar(128) DEFAULT NULL COMMENT '转账源地址',
  `transfer_to_address` varchar(128) DEFAULT NULL COMMENT '转账目的地址',
  `trx_time` datetime DEFAULT NULL COMMENT '创建时间',
  `trx_id` varchar(128) DEFAULT NULL COMMENT '交易id',
  `ext_trx_id` varchar(128) DEFAULT NULL COMMENT '扩展交易id',
  `block_id` varchar(128) DEFAULT NULL COMMENT '块id',
  `block_num` int(11) DEFAULT NULL COMMENT '快号(高度)',
  `block_trx_num` int(11) DEFAULT NULL COMMENT '交易快的位置',
  `trx_type` tinyint(4) DEFAULT NULL COMMENT '0用户参保,1用户赔付',
  `trx_state` tinyint(4) DEFAULT NULL COMMENT '0未确认,1已确认',
  `transfer_amount` double(10,2) DEFAULT NULL COMMENT '转账金额',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COMMENT='用户交易表';

