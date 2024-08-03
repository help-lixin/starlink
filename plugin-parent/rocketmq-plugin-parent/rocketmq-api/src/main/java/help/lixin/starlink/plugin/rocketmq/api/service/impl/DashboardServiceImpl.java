package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.IDashboardService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/9/1 4:55 下午
 * @Description
 */
public class DashboardServiceImpl implements IDashboardService {

    private RocketMQProperties rocketMQProperties;

    private final String DASHBOARD = "/dashboard/";

    private ObjectMapper objectMapper = new ObjectMapper();
    
    
    @Override
    public List<String> topicCurrent() {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + DASHBOARD + "topicCurrent")
                .asJson();

        int status = result.getStatus();
        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("RocketMqDashboard-topicCurrent调用失败:"+result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),  new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public Map<String, List<String>> queryBroker(String queryDate) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + DASHBOARD + "broker.query")
                .queryString("date",queryDate)
                .asJson();

        int status = result.getStatus();
        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("RocketMqDashboard-queryBroker调用失败:"+result);
            }

            Map<String, List<String>> data = objectMapper.readValue(body.getObject().get("data").toString(), new TypeReference<Map<String, List<String>>>() {
            });
            return data;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public List<String> queryTopic(String queryDate, String topicName) {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + DASHBOARD + "topic.query")
                .queryString("topicName",topicName)
                .queryString("date",queryDate)
                .asJson();

        int status = result.getStatus();
        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("RocketMqDashboard-queryTopic调用失败:"+result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),  new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    public DashboardServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }
}
