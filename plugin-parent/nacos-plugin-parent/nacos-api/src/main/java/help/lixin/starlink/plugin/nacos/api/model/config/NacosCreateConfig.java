package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosTypeEnum;
import help.lixin.starlink.request.EnvRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
public class NacosCreateConfig {
    private EnvRequest envRequest;
    // 租户id
    private String tenant;
    // 实例空间id
    private String instancSpaceId;
    // 配置名
    private String dataId;
    // 配置组名
    private String group;
    // 配置信息
    private String content;
    // 描述
    private String desc;
    // 标签
    private String tag;
    // 格式类型
    private NacosTypeEnum type;
    // 归属应用
    private String appName;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public void setType(NacosTypeEnum type) {
        this.type = type;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getInstancSpaceId() {
        return instancSpaceId;
    }

    public void setInstancSpaceId(String instancSpaceId) {
        this.instancSpaceId = instancSpaceId;
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
