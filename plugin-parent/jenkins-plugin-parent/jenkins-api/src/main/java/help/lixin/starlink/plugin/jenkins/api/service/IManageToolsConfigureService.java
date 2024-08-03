package help.lixin.starlink.plugin.jenkins.api.service;

import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;

/**
 * 对应jenkins:系统管理 --> 全局工具配置功能
 */
public interface IManageToolsConfigureService {

    /**
     * 对应的json内容要如下,每次都是全量的,否则,会进行覆盖来着的.
     * json={
     * 	"jenkins-mvn-GlobalMavenConfig": {
     * 		"": ["0", "0"],
     * 		"settingsProvider": {
     * 			"stapler-class": "jenkins.mvn.DefaultSettingsProvider",
     * 			"$class": "jenkins.mvn.DefaultSettingsProvider"
     *                },
     * 		"globalSettingsProvider": {
     * 			"stapler-class": "jenkins.mvn.DefaultGlobalSettingsProvider",
     * 			"$class": "jenkins.mvn.DefaultGlobalSettingsProvider"
     *        }
     *  },
     * 	"hudson-model-JDK": {
     * 		"tool": [{
     * 			"name": "jdk1.8",
     * 			"home": "/home/app/app/jdk1.8",
     * 			"properties": {
     * 				"stapler-class-bag": "true"
     * 			}
     * 		}        ,
     * 		{
     * 			"name": "jdk1.8.0",
     * 			"home": "/home/app/app/jdk1.8",
     * 			"properties": {
     * 				"stapler-class-bag": "true"
     *            }
     *        }]
     *    },
     * 	"hudson-tasks-Maven$MavenInstallation": {
     * 		"tool": {
     * 			"name": "maven-3.6.3",
     * 			"home": "/home/app/app/maven-3.6.3",
     * 			"properties": {
     * 				"stapler-class-bag": "true"
     *            }
     *        }
     *    },
     * 	"Submit": "",
     * 	"core:apply": ""
     * }
     * @param jsonBody
     * @throws Exception
     */
    boolean configure(String jsonBody);

    /**
     * 检查名称是否可用,检查不通过的情况下会抛出RunteimException
     * 经过N轮测试,好像对名称的检测,没有什么用处,jenkins好像也并没有做唯一检查来着的.
     *
     * @param module : 对哪个模块进行检查(jdk/maven)
     * @param name   : 别名(例如:jdk1.8/jdk1.9/maven3.6.3)
     */
    @Deprecated
    void checkName(JenkinsManageToolsModule module, String name);

    /**
     * 检查home是否可用,检查不通过的情况下会抛出RunteimException
     *
     * @param module : 对哪个模块进行检查(jdk/maven)
     * @param home   : 别名对应的value(例如:/home/app/app/jdk1.8),jenkins会检查这个目录是否存在来着的.
     */
    void checkHome(JenkinsManageToolsModule module, String home);
}
