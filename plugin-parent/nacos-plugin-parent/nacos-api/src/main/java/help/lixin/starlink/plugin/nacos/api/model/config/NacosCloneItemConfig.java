package help.lixin.starlink.plugin.nacos.api.model.config;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:27 下午
 * @Description
 */
public class NacosCloneItemConfig {

    //cfgId
    private Long cfgId;

    private String dataId;

    private String group;

    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
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
}
