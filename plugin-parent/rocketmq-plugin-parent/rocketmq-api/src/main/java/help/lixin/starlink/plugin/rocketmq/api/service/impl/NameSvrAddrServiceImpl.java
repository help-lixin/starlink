package help.lixin.starlink.plugin.rocketmq.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.rocketmq.api.dto.namesvr.NameSvrInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.properties.RocketMQProperties;
import help.lixin.starlink.plugin.rocketmq.api.service.INameSvrAddrService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 6:42 下午
 * @Description
 */
public class NameSvrAddrServiceImpl implements INameSvrAddrService {
    
    private RocketMQProperties rocketMQProperties;
    
    private final String OPS = "/ops/";

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public NameSvrInfoDTO list() {
        HttpResponse<JsonNode> result = Unirest.get(rocketMQProperties.getUrl() + OPS + "homePage.query")
                .asJson();

        int status = result.getStatus();
        try {
            JsonNode body = result.getBody();

            if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
                throw new ServiceException("RocketMqNameSvrAddr-list调用失败:"+result);
            }

            return objectMapper.readValue(body.getObject().get("data").toString(),  NameSvrInfoDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:"+e.getMessage());
        }
    }

    @Override
    public Boolean updateIsVIPChannel(Boolean useVIPChannel) {
        HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + OPS + "updateIsVIPChannel.do")
                .queryString("useVIPChannel",useVIPChannel)
                .asJson();

        int status = result.getStatus();
        JsonNode body = result.getBody();


        if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
            throw new ServiceException("RocketMqNameSvrAddr-updateIsVIPChannel调用失败:"+result);
        }

        return body.getObject().get("data").toString().equals("true");

    }

    @Override
    public Boolean updateNameSvrAddr(String nameSvrAddrList) {
        HttpResponse<JsonNode> result = Unirest.post(rocketMQProperties.getUrl() + OPS + "updateNameSvrAddr.do")
                .queryString("nameSvrAddrList",nameSvrAddrList)
                .asJson();

        int status = result.getStatus();
        JsonNode body = result.getBody();


        if (status != 200 || !result.getBody().getObject().get("status").toString().equals("0") || body == null){
            throw new ServiceException("RocketMqNameSvrAddr-updateNameSvrAddr调用失败:"+result);
        }

        return body.getObject().get("data").toString().equals("true");

    }

    public NameSvrAddrServiceImpl(RocketMQProperties rocketMQProperties) {
        this.rocketMQProperties = rocketMQProperties;
    }
}
