package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.core.dto.EnvDTO;
import help.lixin.starlink.core.dto.PageDTO;
import help.lixin.starlink.plugin.nacos.api.model.enums.NacosSearchEnum;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
public class NacosPageListConfig extends PageDTO {

    private EnvDTO envDTO;
    private String tenant;
    private String dataId;
    private String group;
    //配置信息
    private String configDetail;
    //标签
    private String tag;
    //归属应用
    private String appName;
    //模糊查询切换
    private NacosSearchEnum search;

    public EnvDTO getEnvDTO() {
        return envDTO;
    }

    public void setEnvDTO(EnvDTO envDTO) {
        this.envDTO = envDTO;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public NacosSearchEnum getSearch() {
        return search;
    }

    public void setSearch(NacosSearchEnum search) {
        this.search = search;
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

    public String getConfigDetail() {
        return configDetail;
    }

    public void setConfigDetail(String configDetail) {
        this.configDetail = configDetail;
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

}
