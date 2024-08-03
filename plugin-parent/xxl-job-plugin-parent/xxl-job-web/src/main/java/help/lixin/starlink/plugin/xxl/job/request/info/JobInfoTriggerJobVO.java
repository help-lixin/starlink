package help.lixin.starlink.plugin.xxl.job.request.info;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 7:00 下午
 * @Description
 */
@Api(tags = "执行任务请求对象")
public class JobInfoTriggerJobVO implements Serializable {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "执行参数")
    private String executorParam;

    @ApiModelProperty(value = "机器地址")
    private String addressList;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
