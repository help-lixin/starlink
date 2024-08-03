package help.lixin.starlink.plugin.nacos.api.model.config;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/14 6:48 下午
 * @Description
 */
public class NacosPublishConfig {

    //配置名
    private String dataId;

    //配置组名
    private String group;

    //租户id
    private String tenant;

    //配置内容
    private String content;

    //标签
    private String tag;

    //配置名
    private String appName;

    //配置标签列表，可多个，逗号分隔
    private String configTags;

    //配置描述
    private String desc;


    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getConfigTags() {
        return configTags;
    }

    public void setConfigTags(String configTags) {
        this.configTags = configTags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
