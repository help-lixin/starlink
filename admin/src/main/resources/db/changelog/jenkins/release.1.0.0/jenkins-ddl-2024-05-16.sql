--liquibase formatted sql


--changeset lixin:jenkins-20240516-100 labels:starlink context:pro
--comment: jenkins托管时,自动安装的插件
INSERT INTO `jenkins_plugin_init` VALUES (1, 'golang', 1, NULL, NULL, '2023-12-14 20:40:40', '2023-12-14 20:40:40');
INSERT INTO `jenkins_plugin_init` VALUES (2, 'nodejs', 1, NULL, NULL, '2023-12-14 20:40:49', '2023-12-14 20:40:49');
INSERT INTO `jenkins_plugin_init` VALUES (3, 'git', 1, NULL, NULL, '2023-12-14 20:41:07', '2023-12-14 20:41:07');
INSERT INTO `jenkins_plugin_init` VALUES (4, 'subversion', 1, NULL, NULL, '2023-12-14 20:41:10', '2023-12-14 20:41:10');
INSERT INTO `jenkins_plugin_init` VALUES (5, 'gradle', 1, NULL, NULL, '2024-01-09 11:01:43', '2024-01-09 11:01:43');
INSERT INTO `jenkins_plugin_init` VALUES (6, 'ant', 1, NULL, NULL, '2024-01-09 11:03:05', '2024-01-09 11:03:05');
INSERT INTO `jenkins_plugin_init` VALUES (7, 'gitlab-plugin', 1, NULL, NULL, '2024-01-09 12:34:10', '2024-01-09 12:34:10');
