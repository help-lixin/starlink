package help.lixin.starlink.plugin.jsch.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Table: shell_hosts
 */
public class SshHosts implements Serializable {

    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * shell标签Id
     *
     * Column: shell_lable_id Nullable: false
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sshLableId;

    /**
     * ssh实例编码
     *
     * Column: ssh_instance_code Nullable: false
     */
    private String sshInstanceCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSshLableId() {
        return sshLableId;
    }

    public void setSshLableId(Long sshLableId) {
        this.sshLableId = sshLableId;
    }

    public String getSshInstanceCode() {
        return sshInstanceCode;
    }

    public void setSshInstanceCode(String sshInstanceCode) {
        this.sshInstanceCode = sshInstanceCode == null ? null : sshInstanceCode.trim();
    }
}