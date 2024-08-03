--liquibase formatted sql

--changeset lixin:ssh-20240516-1 labels:starlink context:pro
--comment: ssh_hosts
CREATE TABLE `ssh_hosts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ssh_lable_id` bigint(20) NOT NULL COMMENT 'shell标签Id',
  `ssh_instance_code` varchar(50) NOT NULL COMMENT 'ssh实例编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='shell与主机映射';
--rollback DROP TABLE IF EXISTS ssh_hosts;


--changeset lixin:ssh-20240516-2 labels:starlink context:pro
--comment: ssh_label
CREATE TABLE `ssh_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label_key` varchar(50) DEFAULT NULL COMMENT '标签key',
  `label_name` varchar(50) DEFAULT NULL COMMENT '标签名',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='shell标签表';
--rollback DROP TABLE IF EXISTS ssh_label;