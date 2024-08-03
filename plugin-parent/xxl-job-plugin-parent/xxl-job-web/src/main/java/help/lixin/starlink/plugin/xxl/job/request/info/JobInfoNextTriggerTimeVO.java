package help.lixin.starlink.plugin.xxl.job.request.info;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 7:11 下午
 * @Description
 */
@Api(tags = "下次执行时间请求对象")
public class JobInfoNextTriggerTimeVO implements Serializable {

    @Valid
    private EnvRequest envRequest;

    @NotBlank(message = "策略类型不能为空")
    @ApiModelProperty(value = "策略类型")
    private String scheduleType;

    @NotBlank(message = "策略配置不能为空")
    @ApiModelProperty(value = "策略配置")
    private String scheduleConf;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleConf() {
        return scheduleConf;
    }

    public void setScheduleConf(String scheduleConf) {
        this.scheduleConf = scheduleConf;
    }
}
