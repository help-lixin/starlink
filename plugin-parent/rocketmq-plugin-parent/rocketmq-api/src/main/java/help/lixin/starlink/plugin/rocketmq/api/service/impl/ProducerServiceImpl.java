package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.producer.ProducerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.IProducerService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/9/11 3:42 下午
 * @Description
 */
public class ProducerServiceImpl implements IProducerService {

    private RocketMQProperties rocketMQProperties;

    private final String PRODUCER = "/producer/";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ProducerInfoDTO> producerConnection(String producerGroup, String topic) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + PRODUCER + "producerConnection.query")
                .queryString("producerGroup",producerGroup)
                .queryString("topic",topic)
                .asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("rocketmq调用失败:"+result);
            }

            Map<String, List<ProducerInfoDTO>> stringListMap = objectMapper.readValue(body.toString(), new TypeReference<Map<String, List<ProducerInfoDTO>>>() {
            });

            return stringListMap.get("connectionSet");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    public ProducerServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }
}
