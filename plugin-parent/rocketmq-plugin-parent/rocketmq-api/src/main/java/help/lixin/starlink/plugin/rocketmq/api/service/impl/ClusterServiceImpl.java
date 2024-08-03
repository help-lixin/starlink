package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerServerDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.cluster.ClusterConfigInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.IClusterService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:48 下午
 * @Description
 */
public class ClusterServiceImpl implements IClusterService {

    private RocketMQProperties rocketMQProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String CLUSTER = "/cluster/";

    @Override
    public BrokerServerDTO list() {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + CLUSTER + "list.query")
                .asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                return null;
            }

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

            return objectMapper.readValue(body.toString(), BrokerServerDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public ClusterConfigInfoDTO queryConfig(String brokerAddr) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + CLUSTER + "brokerConfig.query")
                .queryString("brokerAddr",brokerAddr)
                .asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                return null;
            }

            return objectMapper.readValue(body.toString(), ClusterConfigInfoDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }


    public ClusterServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }
}
