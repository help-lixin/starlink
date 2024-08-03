package help.lixin.starlink.plugin.jenkins.job.service;

public interface IJenkinsRetryInstallPluginService {

    /**
     * @Author: 伍岳林
     * @Date: 2024/3/7 下午5:56
     * @Return: void
     * @Description 重试插件安装
    */
    void retryInstallPlugin();

    /**
     * @Author: 伍岳林
     * @Date: 2024/3/7 下午5:56
     * @Return: void
     * @Description 查询并更新插件安装结果
    */
    void updatePluginInstallResult();
}
