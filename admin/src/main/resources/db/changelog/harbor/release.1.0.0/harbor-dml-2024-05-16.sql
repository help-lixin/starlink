--liquibase formatted sql

--changeset lixin:harbor-20240516-1 labels:starlink context:pro
--comment: harbor_project
CREATE TABLE `harbor_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'harbor_config_id',
  `harbor_project_id` bigint(20) DEFAULT NULL COMMENT 'harbor项目表id',
  `instance_code` varchar(20) NOT NULL COMMENT '实例code',
  `is_public` tinyint(1) DEFAULT NULL COMMENT '是否公开权限',
  `project_name` varchar(20) NOT NULL COMMENT '项目名',
  `capacity` varchar(20) DEFAULT NULL COMMENT '容量',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='harbor项目表';
--rollback DROP TABLE IF EXISTS harbor_project;