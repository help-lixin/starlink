--liquibase formatted sql

--changeset wuyuelin:k8s-20240517-1 labels:starlink context:pro
--comment: k8s应用
CREATE TABLE kubernetes_app(
    `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `name_space_id` BIGINT NOT NULL   COMMENT '命名空间id' ,
    `kind` VARCHAR(50) NOT NULL   COMMENT '部署种类' ,
    `name` VARCHAR(50) NOT NULL   COMMENT '应用名称' ,
    `instance_code` VARCHAR(20) NOT NULL   COMMENT '实例编码' ,
    `is_del` TINYINT(2)   DEFAULT 0 COMMENT '是否删除（删除为1）' ,
    `status` TINYINT(2)   DEFAULT 1 COMMENT '状态值' ,
    `create_by` VARCHAR(32)    COMMENT '创建人' ,
    `update_by` VARCHAR(32)    COMMENT '更新人' ,
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = 'k8s应用;';
--rollback DROP TABLE IF EXISTS kubernetes_app;


--changeset wuyuelin:k8s-20240517-3 labels:starlink context:pro
--comment: 命名空间表
CREATE TABLE kubernetes_name_space(
    `id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `name` VARCHAR(50) NOT NULL   COMMENT '命名空间名字' ,
    `instance_code` VARCHAR(50) NOT NULL   COMMENT '实例编码' ,
    `remark` VARCHAR(300)    COMMENT '描述' ,
    `is_del` TINYINT(2)   DEFAULT 0 COMMENT '是否删除（删除为1）' ,
    `status` TINYINT(2)   DEFAULT 1 COMMENT '状态值' ,
    `create_by` VARCHAR(32)    COMMENT '创建人' ,
    `update_by` VARCHAR(32)    COMMENT '更新人' ,
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '命名空间表;';
--rollback DROP TABLE IF EXISTS kubernetes_name_space;
