package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.*;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.TopicRollBackInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.IConsumerService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 5:53 下午
 * @Description
 */
public class ConsumerServiceImpl implements IConsumerService {
    private RocketMQProperties rocketMQProperties;

    private final String CONSUMER = "/consumer/";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ConsumerListDTO> groupList() {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + CONSUMER + "groupList.query").asJson();

        int status = result.getStatus();

        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-groupList调用失败:" + result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),
                new TypeReference<List<ConsumerListDTO>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public ConsumerConnectionInfoDTO consumerConnection(String consumerGroup) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + CONSUMER + "consumerConnection.query")
            .queryString("consumerGroup", consumerGroup).asJson();

        int status = result.getStatus();

        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-consumerConnection调用失败:" + result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(), ConsumerConnectionInfoDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public List<ConsumerOffsetInfoDTO> queryTopicByConsumer(String consumerGroup) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + CONSUMER + "queryTopicByConsumer.query")
                .queryString("consumerGroup", consumerGroup).asJson();

        int status = result.getStatus();

        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-queryTopicByConsumer调用失败:" + result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),
                new TypeReference<List<ConsumerOffsetInfoDTO>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public List<ConsumerSubscriptionGroupDataDTO> examineSubscriptionGroupConfig(String consumerGroup) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + CONSUMER + "examineSubscriptionGroupConfig.query")
                .queryString("consumerGroup", consumerGroup).asJson();

        int status = result.getStatus();

        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-examineSubscriptionGroupConfig调用失败:" + result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),
                new TypeReference<List<ConsumerSubscriptionGroupDataDTO>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Boolean createOrUpdate(ConsumerSubscriptionGroupInfoDTO consumerSubscriptionGroupInfoDTO) {
        try {
            HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + CONSUMER + "createOrUpdate.do")
                .body(objectMapper.writeValueAsString(consumerSubscriptionGroupInfoDTO))
                .contentType("application/json;charset=UTF-8").asJson();

            int status = result.getStatus();
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-createOrUpdate调用失败:" + result);
            }

            return body.getObject().get("data").toString().equals("true");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }

    }

    @Override
    public List<String> fetchBrokerNameList(String consumerGroup) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + CONSUMER + "fetchBrokerNameList.query")
                .queryString("consumerGroup", consumerGroup).asJson();

        int status = result.getStatus();
        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-fetchBrokerNameList调用失败:" + result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),
                new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Boolean deleteSubGroup(ConsumerDeleteDTO consumerDeleteDTO) {
        try {
            HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + CONSUMER + "deleteSubGroup.do")
                .body(objectMapper.writeValueAsString(consumerDeleteDTO)).contentType("application/json;charset=UTF-8")
                .asJson();

            int status = result.getStatus();
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("RocketMqConsumer-deleteSubGroup调用失败:" + result);
            }

            return body.getObject().get("data").toString().equals("true");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Map<String, List<TopicRollBackInfoDTO>> resetOffset(String topicName) {
        HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + CONSUMER + "resetOffset.query")
            .contentType("application/json;charset=UTF-8").asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            Map<String, List<TopicRollBackInfoDTO>> stringListMap = objectMapper.readValue(body.toString(),
                new TypeReference<Map<String, List<TopicRollBackInfoDTO>>>() {});

            return stringListMap;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    public ConsumerServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }
}
