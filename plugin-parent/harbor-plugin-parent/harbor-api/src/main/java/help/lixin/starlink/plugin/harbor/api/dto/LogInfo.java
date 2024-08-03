package help.lixin.starlink.plugin.harbor.api.dto;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 5:28 下午
 * @Description
 */
public class LogInfo {

    private Long id;

    /**
     * 操作时间
     */
    private Date op_time;

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
    private String resource_type;

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

    public Date getOp_time() {
        return op_time;
    }

    public void setOp_time(Date op_time) {
        this.op_time = op_time;
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

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
