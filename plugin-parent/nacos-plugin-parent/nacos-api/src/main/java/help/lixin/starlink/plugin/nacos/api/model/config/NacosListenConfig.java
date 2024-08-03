package help.lixin.starlink.plugin.nacos.api.model.config;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:47 下午
 * @Description
 */
public class NacosListenConfig {

    private String dataId;
    private String group;
    private String tenant;
    private Integer sampleTime;

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

    public Integer getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Integer sampleTime) {
        this.sampleTime = sampleTime;
    }
}
