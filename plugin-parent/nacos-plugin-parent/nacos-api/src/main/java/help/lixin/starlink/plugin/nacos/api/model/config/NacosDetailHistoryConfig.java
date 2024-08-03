package help.lixin.starlink.plugin.nacos.api.model.config;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
public class NacosDetailHistoryConfig {

    private Long id;
    private String nameSpaceId;
    private String tenant;
    private String dataId;
    private String group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
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
