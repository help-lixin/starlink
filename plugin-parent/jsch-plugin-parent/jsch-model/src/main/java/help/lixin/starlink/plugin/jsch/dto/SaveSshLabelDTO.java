package help.lixin.starlink.plugin.jsch.dto;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/23 上午11:33
 * @Description
 */
public class SaveSshLabelDTO {

    private Long id;

    /**
     * 标签key
     */
    private String labelKey;

    /**
     * 标签名
     */
    private String labelName;

    /**
     * 主机配置列表
     */
    private List<String> hosts;

    /**
     * 状态值
     */
    private Integer status;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
