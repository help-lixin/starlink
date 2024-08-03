--liquibase formatted sql

--changeset lixin:credential-20240516-1 labels:starlink context:pro
--comment: sys_credential_common
CREATE TABLE `sys_credential_common` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `credential_key` varchar(50) NOT NULL COMMENT '凭证唯一名称',
  `credential_name` varchar(50) DEFAULT NULL COMMENT '凭证别名',
  `credential_type` varchar(50) DEFAULT NULL COMMENT '凭证类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '描述',
  `instance_code` varchar(50) DEFAULT NULL COMMENT '实例编码',
  `plugin_code` varchar(50) DEFAULT NULL COMMENT '插件编码',
  `is_del` tinyint(2) DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='凭证公共表';
--rollback DROP TABLE IF EXISTS sys_credential_common;


--changeset lixin:credential-20240516-2 labels:starlink context:pro
--comment: sys_credential_ssh
CREATE TABLE `sys_credential_ssh` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `passphrase` varchar(2000) DEFAULT NULL COMMENT '私钥密码',
  `private_key` longtext COMMENT '私钥',
  `public_key` varchar(2000) DEFAULT NULL COMMENT '公钥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ssh凭证';
--rollback DROP TABLE IF EXISTS sys_credential_ssh;


--changeset lixin:credential-20240516-3 labels:starlink context:pro
--comment: sys_credential_text
CREATE TABLE `sys_credential_text` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `secret` text COMMENT '凭证文本',
  `password` varchar(2000) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='凭证文本';
--rollback DROP TABLE IF EXISTS sys_credential_text;


--changeset lixin:credential-20240516-4 labels:starlink context:pro
--comment: sys_credential_token
CREATE TABLE `sys_credential_token` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `user_name` varchar(50) DEFAULT NULL,
  `token` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token表';
--rollback DROP TABLE IF EXISTS sys_credential_token;


--changeset lixin:credential-20240516-5 labels:starlink context:pro
--comment: sys_credential_username_password
CREATE TABLE `sys_credential_username_password` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(2000) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户名密码凭证';
--rollback DROP TABLE IF EXISTS sys_credential_username_password;