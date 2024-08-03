package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosTypeEnum;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
public class NacosUpdateConfig {

    private Long id;
    private String tenant;
    private String nameSpaceId;
    private String dataId;
    private String group;
    //配置信息
    private String content;
    //描述
    private String desc;
    //标签
    private String tag;
    //格式类型
    private NacosTypeEnum type;
    //归属应用
    private String appName;
    //Beta发布
    private String betaIps;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBetaIps() {
        return betaIps;
    }

    public void setBetaIps(String betaIps) {
        this.betaIps = betaIps;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NacosTypeEnum getType() {
        return type;
    }

    public void setType(String type) {
        this.type = NacosTypeEnum.match(type);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
