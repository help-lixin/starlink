--liquibase formatted sql

--changeset wuyuelin:credential-20240517-1 labels:starlink context:pro
--comment: k8s凭证中的命名空间
DROP TABLE IF EXISTS sys_credential_namespaces;
CREATE TABLE `sys_credential_namespaces` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `instance_code` varchar(20) NOT NULL COMMENT '插件实例',
  `name_space` varchar(255) NOT NULL COMMENT '命名空间',
  `is_del` tinyint(2) DEFAULT '0' COMMENT '是否删除(删除为1)',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='k8s凭证中的命名空间;';
--rollback DROP TABLE IF EXISTS sys_credential_namespaces;

--changeset wuyuelin:credential-20240517-3 labels:starlink context:pro
--comment: 添加命名空间到common表中
ALTER TABLE `sys_credential_common`
ADD COLUMN `name_space` varchar(255) NULL COMMENT '命名空间' AFTER `remark`;

--changeset wuyuelin:credential-20240517-4 labels:starlink context:pro
--comment: 添加镜像域名到用户名密码表中
ALTER TABLE `starlink`.`sys_credential_username_password`
ADD COLUMN `img_domain` varchar(1024) NULL COMMENT '镜像域名' AFTER `password`;

--changeset wuyuelin:credential-20240517-5 labels:starlink context:pro
--comment: opaque凭证表
DROP TABLE IF EXISTS sys_credential_opaque;
CREATE TABLE sys_credential_opaque(
    `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `key` VARCHAR(255)    COMMENT '键' ,
    `value` VARCHAR(255)    COMMENT '值' ,
    PRIMARY KEY (id)
)  COMMENT = 'Opaque凭证表;';
--rollback DROP TABLE IF EXISTS sys_credential_opaque;

--changeset wuyuelin:credential-20240517-6 labels:starlink context:pro
--comment: TLS凭证表
DROP TABLE IF EXISTS sys_credential_tls;
CREATE TABLE sys_credential_tls(
    `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `private_key` VARCHAR(2000)    COMMENT '私钥' ,
    `certificate` VARCHAR(2000)    COMMENT '证书' ,
    PRIMARY KEY (id)
)  COMMENT = 'TLS凭证表;';
--rollback DROP TABLE IF EXISTS sys_credential_tls;

--changeset wuyuelin:credential-20240517-7 labels:starlink context:pro
--comment: 添加opaque凭证表
ALTER TABLE `sys_credential_opaque`
ADD COLUMN `common_id` bigint(0) NULL AFTER `id`,
CHANGE COLUMN `key` `opq_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键' AFTER `common_id`,
CHANGE COLUMN `value` `opq_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值' AFTER `opq_key`;
