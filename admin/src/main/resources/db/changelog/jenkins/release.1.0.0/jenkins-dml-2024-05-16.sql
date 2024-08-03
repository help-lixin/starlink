--liquibase formatted sql

--changeset lixin:jenkins-20240516-1 labels:starlink context:pro
--comment: jenkins_build_dependency
CREATE TABLE `jenkins_build_dependency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_id` varchar(20) DEFAULT NULL,
  `job_name` varchar(20) DEFAULT NULL COMMENT '构建依赖任务名（构建顺序用逗号隔开）',
  `instance_code` varchar(20) DEFAULT NULL,
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='构建依赖表';
--rollback DROP TABLE IF EXISTS jenkins_build_dependency;


--changeset lixin:jenkins-20240516-2 labels:starlink context:pro
--comment: jenkins_build_info
CREATE TABLE `jenkins_build_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `instance_code` varchar(50) NOT NULL COMMENT '实例编码',
  `job_id` bigint(20) NOT NULL COMMENT '任务表id',
  `jenkins_log_id` bigint(20) NOT NULL COMMENT '日志id',
  `job_name` varchar(255) NOT NULL COMMENT '任务名',
  `build_status` tinyint(2) NOT NULL COMMENT '构建状态(-1:失败 0:构建中 1:成功)',
  `start_time` datetime DEFAULT NULL COMMENT '构建开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '构建结束时间',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='构建信息表';
--rollback DROP TABLE IF EXISTS jenkins_build_info;


--changeset lixin:jenkins-20240516-3 labels:starlink context:pro
--comment: jenkins_install_plugins
CREATE TABLE `jenkins_install_plugins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `plugin_name` varchar(90) DEFAULT NULL COMMENT '插件名称',
  `version` varchar(90) DEFAULT NULL COMMENT '版本号',
  `remark` varchar(900) DEFAULT NULL COMMENT '描述',
  `instance_code` varchar(90) DEFAULT NULL,
  `retry_amount` tinyint(2) DEFAULT NULL COMMENT '重试次数',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `install_status` tinyint(2) DEFAULT NULL COMMENT '安装状态',
  `err_msg` text COMMENT '安装错误日志',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='插件已安装列表';
--rollback DROP TABLE IF EXISTS jenkins_install_plugins;


--changeset lixin:jenkins-20240516-4 labels:starlink context:pro
--comment: jenkins_job
CREATE TABLE `jenkins_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `instance_code` varchar(20) DEFAULT NULL,
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名',
  `xml_content` text COMMENT 'xml内容',
  `remark` varchar(900) DEFAULT NULL COMMENT '备注',
  `last_success` varchar(20) DEFAULT NULL COMMENT '最后成功时间',
  `last_failure` varchar(20) DEFAULT NULL COMMENT '最后失败时间',
  `last_duration` varchar(20) DEFAULT NULL COMMENT '最后构建所需时间',
  `aggregated_status` varchar(20) DEFAULT NULL COMMENT '聚合状态（聚合百分比）',
  `scm` varchar(20) DEFAULT NULL COMMENT '仓库类型',
  `tools` varchar(20) DEFAULT NULL COMMENT '工具类型',
  `jdk` varchar(20) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT '0' COMMENT '是否删除（删除为1）',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='jenkins任务表';
--rollback DROP TABLE IF EXISTS jenkins_job;



--changeset lixin:jenkins-20240516-5 labels:starlink context:pro
--comment: jenkins_logs
CREATE TABLE `jenkins_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `jenkins_build_id` bigint(20) DEFAULT NULL COMMENT '构建表id',
  `content` longtext COMMENT '日志信息',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jenkins日志表';
--rollback DROP TABLE IF EXISTS jenkins_logs;


--changeset lixin:jenkins-20240516-6 labels:starlink context:pro
--comment: jenkins_params
CREATE TABLE `jenkins_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_id` bigint(20) DEFAULT NULL COMMENT '任务表id',
  `param_name` varchar(50) DEFAULT NULL COMMENT '参数名',
  `param_value` varchar(50) DEFAULT NULL COMMENT '参数值',
  `default_value` varchar(50) DEFAULT NULL COMMENT '默认值',
  `param_type` varchar(50) DEFAULT NULL COMMENT '参数类型（string，integer..）',
  `description` varchar(900) DEFAULT NULL COMMENT '参数描述',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数表';
--rollback DROP TABLE IF EXISTS jenkins_params;


--changeset lixin:jenkins-20240516-7 labels:starlink context:pro
--comment: jenkins_pipeline_scm
CREATE TABLE `jenkins_pipeline_scm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `url` varchar(200) DEFAULT NULL COMMENT '仓库地址',
  `credentials_id` varchar(20) DEFAULT NULL COMMENT '凭证id（凭证命名的名称）',
  `branch` varchar(200) DEFAULT NULL COMMENT '分支',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='仓库设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_scm;


--changeset lixin:jenkins-20240516-8 labels:starlink context:pro
--comment: jenkins_pipeline_setup_ant
CREATE TABLE `jenkins_pipeline_setup_ant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ant_id` bigint(20) DEFAULT NULL COMMENT 'ant版本id',
  `targets` varchar(50) DEFAULT NULL COMMENT 'ant命令',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='ant构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_ant;


--changeset lixin:jenkins-20240516-9 labels:starlink context:pro
--comment: jenkins_pipeline_setup_common
CREATE TABLE `jenkins_pipeline_setup_common` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_id` bigint(20) DEFAULT NULL,
  `setup_type` varchar(20) DEFAULT NULL COMMENT '执行步骤类型',
  `sequence` int(11) DEFAULT NULL COMMENT '执行顺序',
  `instance_code` varchar(20) DEFAULT NULL,
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_common;


--changeset lixin:jenkins-20240516-10 labels:starlink context:pro
--comment: jenkins_pipeline_setup_go
CREATE TABLE `jenkins_pipeline_setup_go` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `go_id` bigint(50) DEFAULT NULL COMMENT 'go版本id',
  `script` varchar(500) DEFAULT NULL COMMENT 'go脚本',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='go构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_go;


--changeset lixin:jenkins-20240516-11 labels:starlink context:pro
--comment: jenkins_pipeline_setup_gradle
CREATE TABLE `jenkins_pipeline_setup_gradle` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `gradle_id` bigint(20) DEFAULT NULL COMMENT 'gradle版本id',
  `task` varchar(20) DEFAULT NULL COMMENT '任务命令',
  `invoke_gradle` tinyint(2) DEFAULT NULL COMMENT '是否直接调用gradle',
  `make_gradlew_executable` tinyint(2) DEFAULT NULL COMMENT '如果你的gradlew脚本没有执行权限，你应该启用这个选项。这将使Jenkins尝试修改gradlew脚本的权限，使其可以执行',
  `switches` varchar(500) DEFAULT NULL COMMENT '额外命令',
  `wrapper_location` varchar(20) DEFAULT NULL COMMENT '指定工作执行路径',
  `system_properties` varchar(500) DEFAULT NULL COMMENT '传递给Gradle构建的系统属性',
  `project_properties` varchar(500) DEFAULT NULL COMMENT '传递给Gradle构建的项目属性',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='gradle构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_gradle;



--changeset lixin:jenkins-20240516-12 labels:starlink context:pro
--comment: jenkins_pipeline_setup_maven
CREATE TABLE `jenkins_pipeline_setup_maven` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `maven_id` bigint(20) DEFAULT NULL COMMENT 'maven版本id',
  `goals` varchar(500) DEFAULT NULL COMMENT 'maven命令',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='maven构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_maven;



--changeset lixin:jenkins-20240516-13 labels:starlink context:pro
--comment: jenkins_pipeline_setup_nodejs
CREATE TABLE `jenkins_pipeline_setup_nodejs` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `nodejs_id` bigint(50) DEFAULT NULL COMMENT 'nodejs版本id',
  `script` varchar(500) DEFAULT NULL COMMENT '脚本命令',
  `cache_location` varchar(200) DEFAULT NULL COMMENT '缓存路径',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='nodejs构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_nodejs;


--changeset lixin:jenkins-20240516-14 labels:starlink context:pro
--comment: jenkins_pipeline_setup_python
CREATE TABLE `jenkins_pipeline_setup_python` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `python_id` bigint(20) DEFAULT NULL COMMENT 'python版本id',
  `script` varchar(500) DEFAULT NULL COMMENT 'python脚本',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='python构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_python;



--changeset lixin:jenkins-20240516-15 labels:starlink context:pro
--comment: jenkins_pipeline_setup_shell
CREATE TABLE `jenkins_pipeline_setup_shell` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `shell_script` varchar(900) DEFAULT NULL COMMENT 'shell命令脚本',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='shell构建设置';
--rollback DROP TABLE IF EXISTS jenkins_pipeline_setup_shell;



--changeset lixin:jenkins-20240516-16 labels:starlink context:pro
--comment: jenkins_plugin_init
CREATE TABLE `jenkins_plugin_init` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `plugin_name` varchar(50) DEFAULT NULL,
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='初始插件表';
--rollback DROP TABLE IF EXISTS jenkins_plugin_init;


--changeset lixin:jenkins-20240516-17 labels:starlink context:pro
--comment: jenkins_system_config
CREATE TABLE `jenkins_system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(200) DEFAULT NULL COMMENT '插件名称',
  `value` varchar(200) DEFAULT NULL COMMENT '插件路径',
  `plugin_type` varchar(20) DEFAULT NULL COMMENT '插件类型',
  `instance_code` varchar(90) DEFAULT NULL,
  `status` tinyint(2) DEFAULT '1' COMMENT '状态值',
  `is_del` tinyint(2) DEFAULT NULL COMMENT '是否删除（1为删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='插件配置';
--rollback DROP TABLE IF EXISTS jenkins_system_config;


