package help.lixin.starlink.plugin.harbor.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 5:28 下午
 * @Description
 */
public class LogInfoDTO implements Serializable {
    private Long id;

    /**
     * 操作时间
     */
    private Date opTime;

    /**
     * 操作
     */
    private String operation;

    /**
     * 源文件镜像
     */
    private String resource;

    /**
     * 源类型
     */
    private String resourceType;

    /**
     * 操作用户
     */
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
