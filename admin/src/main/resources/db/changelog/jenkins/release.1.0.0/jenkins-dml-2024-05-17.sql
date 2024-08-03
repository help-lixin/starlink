--liquibase formatted sql

--changeset wuyuelin:jenkins-20240517-1 labels:starlink context:pro
--comment: 修改jenkins_install_plugins重试默认值
ALTER TABLE `jenkins_install_plugins`
MODIFY COLUMN `retry_amount` tinyint(2) NULL DEFAULT 0 COMMENT '重试次数' AFTER `instance_code`;

--changeset wuyuelin:jenkins-20240517-2 labels:starlink context:pro
--comment: 修改jenkins_install_plugins是否删除默认值
ALTER TABLE `jenkins_system_config`
MODIFY COLUMN `is_del` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除（1为删除）' AFTER `status`;