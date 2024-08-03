package help.lixin.starlink.plugin.jenkins.dto.plugin;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/12 7:05 下午
 * @Description
 */
public class InstallPluginDTO {

    private String pluginName;

    private String version;

    private String instanceCode;

    private String createBy;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
