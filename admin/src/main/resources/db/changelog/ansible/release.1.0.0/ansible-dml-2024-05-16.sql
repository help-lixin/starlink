--liquibase formatted sql

--changeset lixin:ansible-20240516-1 labels:starlink context:pro
--comment: ansible_host_manage
CREATE TABLE `ansible_host_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `server_name` varchar(50) NOT NULL COMMENT '主机名称',
  `ssh_instance_code` varchar(50) NOT NULL,
  `know_host` text COMMENT 'ansible的know_host文本',
  `ansible_inventory_dir` varchar(200) DEFAULT NULL COMMENT 'ansible Inventory目录',
  `is_del` tinyint(2) DEFAULT '0' COMMENT '是否删除(删除为1)',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务主机管理';
--rollback DROP TABLE IF EXISTS ansible_host_manage;


--changeset lixin:ansible-20240516-2 labels:starlink context:pro
--comment: ansible_inventory
CREATE TABLE `ansible_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ansible_lable_id` bigint(20) NOT NULL COMMENT 'ansible标签Id',
  `ssh_instance_code` varchar(50) NOT NULL COMMENT 'ssh实例编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='ansible与主机映射';
--rollback DROP TABLE IF EXISTS ansible_inventory;


--changeset lixin:ansible-20240516-3 labels:starlink context:pro
--comment: ansible_label
CREATE TABLE `ansible_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label_key` varchar(50) DEFAULT NULL COMMENT '标签key',
  `label_name` varchar(50) DEFAULT NULL COMMENT '标签名',
  `is_del` tinyint(2) DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='ansible标签表';
--rollback DROP TABLE IF EXISTS ansible_label;