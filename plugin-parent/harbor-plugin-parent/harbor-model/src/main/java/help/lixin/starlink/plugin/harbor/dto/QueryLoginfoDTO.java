package help.lixin.starlink.plugin.harbor.dto;

import help.lixin.starlink.core.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 8:21 下午
 * @Description
 */
public class QueryLoginfoDTO extends PageDTO {

    @ApiModelProperty(value = "查询条件(用户名：username,操作:operation,资源:resource,资源类型:resource_type)")
    private String key;

    @ApiModelProperty(value = "查询值")
    private String value;

    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
