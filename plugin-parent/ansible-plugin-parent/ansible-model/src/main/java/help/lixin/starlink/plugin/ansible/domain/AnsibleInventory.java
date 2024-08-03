package help.lixin.starlink.plugin.ansible.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * Table: ansible_inventory
 */
public class AnsibleInventory implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * ansible标签Id
     *
     * Column:    ansible_lable_id
     * Nullable:  false
     */
    private Long ansibleLableId;

    /**
     * ssh实例编码
     *
     * Column:    ssh_instance_code
     * Nullable:  false
     */
    private String sshInstanceCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnsibleLableId() {
        return ansibleLableId;
    }

    public void setAnsibleLableId(Long ansibleLableId) {
        this.ansibleLableId = ansibleLableId;
    }

    public String getSshInstanceCode() {
        return sshInstanceCode;
    }

    public void setSshInstanceCode(String sshInstanceCode) {
        this.sshInstanceCode = sshInstanceCode == null ? null : sshInstanceCode.trim();
    }
}