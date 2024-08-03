--liquibase formatted sql

--changeset lixin:k8s-20240523-1 labels:starlink context:pro
--comment: k8s deployment template
CREATE TABLE kubernetes_deploy_template(
    `deployment_id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'deployment id' ,
    `deploy_name` VARCHAR(200) NOT NULL  COMMENT 'deploy 别名',
    `yaml_content` text NOT NULL COMMENT 'yaml配置文件',
    `remark` VARCHAR(300)    COMMENT '描述' ,
    `is_del` TINYINT(2)   DEFAULT 0 COMMENT '是否删除（删除为1）' ,
    `status` TINYINT(2)   DEFAULT 1 COMMENT '状态值' ,
    `create_by` VARCHAR(32)    COMMENT '创建人' ,
    `update_by` VARCHAR(32)    COMMENT '更新人' ,
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (deployment_id)
)  COMMENT = 'k8s deployment 模板管理';
--rollback DROP TABLE IF EXISTS kubernetes_deploy_template;
