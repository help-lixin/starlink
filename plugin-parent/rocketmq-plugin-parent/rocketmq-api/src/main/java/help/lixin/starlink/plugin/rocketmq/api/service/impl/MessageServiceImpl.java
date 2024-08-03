package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.message.MessageInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.IMessageService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/28 10:34 上午
 * @Description
 */
public class MessageServiceImpl implements IMessageService {

    private RocketMQProperties rocketMQProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String MESSAGE = "/message/";

    @Override
    public List<MessageInfoDTO> queryByTopic(Long begin, Long end, String topic) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + MESSAGE + "queryMessageByTopic.query")
                .queryString("begin",begin)
                .queryString("end",end)
                .queryString("topic",topic)
                .asJson();

        int status = result.getStatus();

        try {
            JsonNode jsonBody = result.getBody();

            if (status != 200 || !jsonBody.getObject().get("status").toString().equals("0") || jsonBody == null){
                throw new ServiceException("RocketMqMessage-queryByTopic调用失败:"+result);
            }

            Object body = jsonBody.getObject().get("data");

            return objectMapper.readValue(body.toString(), new TypeReference<List<MessageInfoDTO>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public List<MessageInfoDTO> queryByTopicAndKey(String key, String topic) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + MESSAGE + "queryMessageByTopicAndKey.query")
                .queryString("key",key)
                .queryString("topic",topic)
                .asJson();

        int status = result.getStatus();

        try {
            JsonNode jsonBody = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || jsonBody == null){
                throw new ServiceException("RocketMqMessage-queryByTopicAndKey调用失败:"+result);
            }

            Object body = jsonBody.getObject().get("data");

            return objectMapper.readValue(body.toString(), new TypeReference<List<MessageInfoDTO>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public MessageInfoDTO queryByMessageId(String msgId, String topic) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + MESSAGE + "viewMessage.query")
                .queryString("msgId",msgId)
                .queryString("topic",topic)
                .asJson();

        int status = result.getStatus();

        try {
            JsonNode body = result.getBody();


            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("RocketMqMessage-queryByMessageId调用失败:"+result);
            }


            return objectMapper.readValue(objectMapper.readValue(objectMapper.writeValueAsString(body.getObject().get("data").toString()),JsonNode.class).getObject().get("messageView").toString(),MessageInfoDTO.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    public MessageServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }

}
