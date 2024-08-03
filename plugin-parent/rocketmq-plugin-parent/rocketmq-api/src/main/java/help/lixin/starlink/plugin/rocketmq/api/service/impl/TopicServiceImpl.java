package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.*;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.ITopicService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/22 4:36 下午
 * @Description
 */
public class TopicServiceImpl implements ITopicService {

    private RocketMQProperties rocketMQProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String TOPIC = "/topic/";

    public TopicServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }

    @Override
    public List<String> queryList() {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + TOPIC + "list.query").asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            TopicListDTO topicListDTO = objectMapper.readValue(body.toString(), TopicListDTO.class);

            return topicListDTO.getTopicList();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Map<String, TopicStatusMessageQueueDTO> queryStatus(String topicName) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + TOPIC + "stats.query").queryString("topic", topicName).asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            TopicStatusDTO topicStatusDTO = objectMapper.readValue(body.toString(), TopicStatusDTO.class);

            return topicStatusDTO.getOffsetTable();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public TopicRouteDTO queryRoute(String topicName) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + TOPIC + "route.query").queryString("topic", topicName).asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            TopicRouteDTO topicRouteDTO = objectMapper.readValue(body.toString(), TopicRouteDTO.class);

            return topicRouteDTO;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Map<String, TopicConsumerDTO> queryConsumerByTopic(String topicName) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + TOPIC + "queryConsumerByTopic.query")
            .queryString("topic", topicName).asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            Map<String, TopicConsumerDTO> topicConsumerDTOMap =
                objectMapper.readValue(body.toString(), new TypeReference<Map<String, TopicConsumerDTO>>() {});

            return topicConsumerDTOMap;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public List<String> queryTopicConsumerInfo(String topicName) {
        HttpResponse<JsonNode> result =
            Unirest.get(rocketMQProperties.getUrl() + TOPIC + "queryTopicConsumerInfo.query")
                .queryString("topic", topicName).asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            Map<String, List<String>> stringListMap =
                objectMapper.readValue(body.toString(), new TypeReference<Map<String, List<String>>>() {});

            return stringListMap.get("groupList");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public List<TopicInfoDTO> queryTopicInfo(String topicName) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + TOPIC + "examineTopicConfig.query")
            .queryString("topic", topicName).asJson();

        int status = result.getStatus();

        try {
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            List<TopicInfoDTO> topicInfoDTOS =
                objectMapper.readValue(body.toString(), new TypeReference<List<TopicInfoDTO>>() {});

            return topicInfoDTOS;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Boolean saveOrUpdate(TopicInfoDTO topicInfoDTO) {
        try {
            HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + TOPIC + "createOrUpdate.do")
                .body(objectMapper.writeValueAsString(topicInfoDTO)).contentType("application/json;charset=UTF-8")
                .asJson();

            int status = result.getStatus();

            Object data = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || data == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            return data.toString().equals("true");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public TopicMessageStatusDTO sendMessage(TopicSendMessageDTO topicSendMessageDTO) {
        try {
            HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + TOPIC + "sendTopicMessage.do")
                .body(objectMapper.writeValueAsString(topicSendMessageDTO))
                .contentType("application/json;charset=UTF-8").asJson();

            int status = result.getStatus();
            Object body = result.getBody().getObject().get("data");

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null) {
                throw new ServiceException("rocketmq调用失败:" + result);
            }

            TopicMessageStatusDTO topicMessageStatusDTO =
                objectMapper.readValue(body.toString(), TopicMessageStatusDTO.class);

            return topicMessageStatusDTO;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    @Override
    public Boolean deleteTopic(String topicName) {
        HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + TOPIC + "deleteTopic.do")
            .queryString("topic", topicName).asJson();

        int status = result.getStatus();
        Object data = result.getBody().getObject().get("data");

        if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || data == null) {
            throw new ServiceException("rocketmq调用失败:" + result);
        }

        return data.toString().equals("true");
    }

}
