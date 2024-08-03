package help.lixin.starlink.plugin.gitlab.dto.base;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/29 2:42 下午
 * @Description
 */
public class ChangeStatusDTO {

    private Integer status;

    private Long id;

    private String instanceCode;

    private String createBy;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
