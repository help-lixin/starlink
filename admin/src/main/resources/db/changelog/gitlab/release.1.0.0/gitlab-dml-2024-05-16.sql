--liquibase formatted sql

--changeset lixin:gitlab-20240516-1 labels:starlink context:pro
--comment: gitlab_group
CREATE TABLE `gitlab_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gitlab_group_id` bigint(20) NOT NULL COMMENT 'gitlab_group_id',
  `gitlab_group_name` varchar(20) NOT NULL COMMENT '组名',
  `path` varchar(20) NOT NULL COMMENT '路径名（基本与组名一致）',
  `visibility` varchar(20) DEFAULT NULL COMMENT '权限',
  `instance_code` varchar(20) NOT NULL COMMENT '实例code;',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='gitlab组';
--rollback DROP TABLE IF EXISTS gitlab_group;


--changeset lixin:gitlab-20240516-2 labels:starlink context:pro
--comment: gitlab_group_project
CREATE TABLE `gitlab_group_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `access_level` int(11) DEFAULT NULL COMMENT '权限（GUEST(10), REPORTER(20), DEVELOPER(30), MAINTAINER(40), OWNER(50)）',
  `instanceCode` varchar(50) DEFAULT NULL,
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除(删除为1)',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='项目组中间表';
--rollback DROP TABLE IF EXISTS gitlab_group_project;


--changeset lixin:gitlab-20240516-3 labels:starlink context:pro
--comment: gitlab_project
CREATE TABLE `gitlab_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'project_id',
  `gitlab_project_id` bigint(20) NOT NULL COMMENT 'gitlabId',
  `instance_code` varchar(20) NOT NULL COMMENT '实例code;',
  `project_name` varchar(100) NOT NULL COMMENT '项目名',
  `initiallize_with_readme` tinyint(1) DEFAULT NULL COMMENT '是否用readme初始化仓库',
  `visibility` varchar(20) DEFAULT NULL COMMENT '权限',
  `namespace_by_user` bigint(20) DEFAULT NULL COMMENT '按用户划分空间',
  `namespace_by_group` bigint(20) DEFAULT NULL COMMENT '按组划分空间',
  `path` varchar(255) DEFAULT NULL COMMENT '项目路径',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ssh_url` varchar(255) DEFAULT NULL COMMENT 'ssh克隆地址',
  `web_url` varchar(255) DEFAULT NULL COMMENT 'http克隆地址',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='gitlab项目表';
--rollback DROP TABLE IF EXISTS gitlab_project;


--changeset lixin:gitlab-20240516-4 labels:starlink context:pro
--comment: gitlab_role
CREATE TABLE `gitlab_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'role_id',
  `gitlab_role_id` bigint(20) NOT NULL COMMENT 'gitlabId',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `role_code` varchar(20) NOT NULL COMMENT '角色代码',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级id;父级id',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='gitlab角色表';
--rollback DROP TABLE IF EXISTS gitlab_role;


--changeset lixin:gitlab-20240516-5 labels:starlink context:pro
--comment: gitlab_user
CREATE TABLE `gitlab_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'user_id',
  `gitlab_user_id` bigint(20) NOT NULL COMMENT 'gitlabId',
  `sys_user_id` bigint(20) DEFAULT NULL COMMENT '系统用户id',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `nick_name` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `instance_code` varchar(50) DEFAULT NULL,
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='gitlab用户表';
--rollback DROP TABLE IF EXISTS gitlab_user;


--changeset lixin:gitlab-20240516-6 labels:starlink context:pro
--comment: gitlab_user_group
CREATE TABLE `gitlab_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `access_level` int(11) DEFAULT NULL,
  `instance_code` varchar(50) DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL COMMENT '过期时间',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户组中间表';
--rollback DROP TABLE IF EXISTS gitlab_user_group;


--changeset lixin:gitlab-20240516-7 labels:starlink context:pro
--comment: gitlab_user_project
CREATE TABLE `gitlab_user_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'user_project_id',
  `user_id` bigint(20) NOT NULL COMMENT 'user_id',
  `project_id` bigint(20) NOT NULL COMMENT 'project_id',
  `access_level` int(11) DEFAULT NULL COMMENT '权限',
  `instance_code` varchar(50) DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL COMMENT '过期时间',
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='gitlab用户项目中间表';
--rollback DROP TABLE IF EXISTS gitlab_user_project;