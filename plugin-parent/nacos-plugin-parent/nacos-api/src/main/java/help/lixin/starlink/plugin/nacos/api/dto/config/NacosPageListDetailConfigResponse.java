package help.lixin.starlink.plugin.nacos.api.dto.config;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/14 6:01 下午
 * @Description
 */
public class NacosPageListDetailConfigResponse {

    private Long id;
    private String dataId;
    private String group;
    private String content;
    private String tenant;
    private String appName;
    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public String getDataId() {
        return dataId;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getGroup() {
        return group;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    public String getTenant() {
        return tenant;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getAppName() {
        return appName;
    }

}
