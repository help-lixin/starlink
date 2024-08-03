package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.dto.ResponseResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/14 9:57 下午
 * @Description
 */
public class KafkaBSValidateDTO {

    private String zookeeper;

    @ApiModelProperty(value = "jmx端口")
    private Integer jmxPort;

    @ApiModelProperty(value = "集群版本")
    private String kafkaVersion;

    @ApiModelProperty(value = "错误信息")
    private List<ResponseResult> errList;

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public Integer getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }

    public String getKafkaVersion() {
        return kafkaVersion;
    }

    public void setKafkaVersion(String kafkaVersion) {
        this.kafkaVersion = kafkaVersion;
    }

    public List<ResponseResult> getErrList() {
        return errList;
    }

    public void setErrList(List<ResponseResult> errList) {
        this.errList = errList;
    }
}
