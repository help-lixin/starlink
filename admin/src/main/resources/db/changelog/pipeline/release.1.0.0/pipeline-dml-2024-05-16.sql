--liquibase formatted sql

--changeset lixin:pipeline-20240516-1 labels:starlink context:pro
--comment: sys_process_definition
CREATE TABLE `sys_process_definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程定义ID',
  `process_definition_id` varchar(60) NOT NULL COMMENT '流程引擎(activiti/camunda/flowable)的主键id',
  `process_definition_format` varchar(20) NOT NULL COMMENT '提交的流程定义格式(json/yaml)',
  `process_definition_body` text NOT NULL COMMENT '流程定义内容',
  `process_definition_key` varchar(50) NOT NULL COMMENT '流程定义唯一key',
  `process_definition_name` varchar(100) NOT NULL COMMENT '流程定义名称',
  `process_definition_hash` varchar(100) DEFAULT NULL COMMENT '流程定义内容摘要',
  `process_definition_version` int(10) DEFAULT NULL COMMENT '流程版本',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='业务流程定义表';
--rollback DROP TABLE IF EXISTS sys_process_definition;

--changeset lixin:pipeline-20240516-2 labels:starlink context:pro
--comment: sys_process_instance
CREATE TABLE `sys_process_instance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程实例ID',
  `suspended` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:active/2:suspended',
  `ended` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否启用(0:未启用 1:启用)',
  `process_instance_id` varchar(100) NOT NULL COMMENT '流程实例id',
  `process_definition_id` varchar(60) NOT NULL COMMENT '流程引擎(camunda/flowable)定义的id',
  `business_key` varchar(100) NOT NULL DEFAULT '' COMMENT '业务key',
  `root_process_instance_id` varchar(100) DEFAULT NULL COMMENT 'root流程实例id',
  `process_status` tinyint(255) DEFAULT '0' COMMENT '流程状态(1: 正常完成 2: 出现异常)',
  `process_error_log` text COMMENT '流程异常时,存储的异常信息',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程实例表';
--rollback DROP TABLE IF EXISTS sys_process_instance;


--changeset lixin:pipeline-20240516-3 labels:starlink context:pro
--comment: sys_process_instance_logs
CREATE TABLE `sys_process_instance_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `process_instance_id` varchar(100) NOT NULL COMMENT '流程实例id',
  `process_task_id` varchar(100) NOT NULL COMMENT '流程实例下,对应:流程任务id',
  `process_node_id` varchar(100) NOT NULL COMMENT '流程节点id(xml中的id)',
  `process_node_name` varchar(200) DEFAULT NULL COMMENT '流程节点name(xml中的name)',
  `process_operate_id` varchar(100) NOT NULL COMMENT '启动流程实例者id',
  `process_log` text NOT NULL COMMENT '日志详细内容',
  `action_name` varchar(100) NOT NULL COMMENT '插件编码(action_code)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程实例运行日志';
--rollback DROP TABLE IF EXISTS sys_process_instance_logs;