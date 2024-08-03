package help.lixin.starlink.plugin.nacos.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.nacos.api.conver.InstanceConvert;
import help.lixin.starlink.plugin.nacos.api.dto.ResponseResult;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceResponse;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceList;
import help.lixin.starlink.plugin.nacos.api.properties.NacosProperties;
import help.lixin.starlink.plugin.nacos.api.service.INacosCacheService;
import help.lixin.starlink.plugin.nacos.api.service.INacosInstanceService;
import help.lixin.response.Response;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class NacosInstanceService implements INacosInstanceService {
    
    private NacosProperties nacosProperties;
    private INacosCacheService nacosCacheManager;

    private static final String V2_INSTANCE = "/nacos/v2/ns/instance";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public Boolean registerInstance(NacosInstanceInfo nacosInstanceInfo) {
        HttpResponse<Response> result = Unirest.post(propertiesUrl() + V2_INSTANCE)
                .queryString("namespaceId", nacosInstanceInfo.getNamespaceId())
                .queryString("groupName", nacosInstanceInfo.getGroupName())
                .queryString("serviceName", nacosInstanceInfo.getServiceName())
                .queryString("ip", nacosInstanceInfo.getIp())
                .queryString("clusterName", nacosInstanceInfo.getClusterName())
                .queryString("port", nacosInstanceInfo.getPort())
                .queryString("healthy", nacosInstanceInfo.getHealthy())
                .queryString("weight", nacosInstanceInfo.getWeight())
                .queryString("enabled", nacosInstanceInfo.getEnabled())
                .queryString("metadata", nacosInstanceInfo.getMetadata())
                .queryString("ephemeral", nacosInstanceInfo.getEphemeral())
                .asObject(Response.class);
        int status = result.getStatus();
        Response<String> body = result.getBody();

        if (status != 200){
            throw new ServiceException("服务注册失败:"+body);
        }

        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceDetail detail = instanceConvert.convertDetail(nacosInstanceInfo);
        InstanceDetailResponse instanceDetailResponse = instanceDetail(detail);

        //缓存服务
        nacosCacheManager.putInCache(nacosInstanceInfo.getNamespaceId(), instanceDetailResponse);

        return true;
    }

    public Boolean updateInstance(NacosInstanceInfo nacosInstanceInfo){
        HttpResponse<Response> result = Unirest.put(propertiesUrl() + V2_INSTANCE)
                .queryString("namespaceId", nacosInstanceInfo.getNamespaceId())
                .queryString("groupName", nacosInstanceInfo.getGroupName())
                .queryString("serviceName", nacosInstanceInfo.getServiceName())
                .queryString("ip", nacosInstanceInfo.getIp())
                .queryString("clusterName", nacosInstanceInfo.getClusterName())
                .queryString("port", nacosInstanceInfo.getPort())
                .queryString("healthy", nacosInstanceInfo.getHealthy())
                .queryString("weight", nacosInstanceInfo.getWeight())
                .queryString("enabled", nacosInstanceInfo.getEnabled())
                .queryString("metadata", nacosInstanceInfo.getMetadata())
                .queryString("ephemeral", nacosInstanceInfo.getEphemeral())
                .asObject(Response.class);

        int status = result.getStatus();
        Response<String> body = result.getBody();

        if (status != 200 || body.getCode() != 0){
            throw new ServiceException("调用updateInstance发生异常:"+body);
        }

        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceDetail detail = instanceConvert.convertDetail(nacosInstanceInfo);
        InstanceDetailResponse instanceDetailResponse = instanceDetail(detail);
        nacosCacheManager.putInCache(nacosInstanceInfo.getNamespaceId(), instanceDetailResponse);

        return true;
    }

    public String beat(NacosInstanceBeat nacosInstanceBeat) {

        HttpResponse<Response> result = Unirest.put(propertiesUrl() + V2_INSTANCE + "/beat")
                .queryString("namespaceId", nacosInstanceBeat.getNamespaceId())
                // 	服务名
                .queryString("serviceName", nacosInstanceBeat.getServiceName())
                // 服务实例IP
                .queryString("ip", nacosInstanceBeat.getIp())
                //
                .queryString("port", nacosInstanceBeat.getPort())
                //
                .queryString("clusterName", nacosInstanceBeat.getClusterName())
                //
                .queryString("beat", nacosInstanceBeat.getBeat())
                //
                .asObject(Response.class);
        int status = result.getStatus();
        Response<String> body = result.getBody();
        if (status != 200){
            throw new ServiceException("beat调用失败:"+body);
        }
        return body.getData();
    }


    public String destoryInstance(NacosInstanceInfo nacosInstanceInfo) {

        HttpResponse<Response> result = Unirest.delete(propertiesUrl() + V2_INSTANCE)
                .queryString("namespaceId", nacosInstanceInfo.getNamespaceId())
                .queryString("groupName", nacosInstanceInfo.getGroupName())
                .queryString("serviceName", nacosInstanceInfo.getServiceName())
                .queryString("ip", nacosInstanceInfo.getIp())
                .queryString("clusterName", nacosInstanceInfo.getClusterName())
                .queryString("port", nacosInstanceInfo.getPort())
                .queryString("healthy", nacosInstanceInfo.getHealthy())
                .queryString("weight", nacosInstanceInfo.getWeight())
                .queryString("enabled", nacosInstanceInfo.getEnabled())
                .queryString("metadata", nacosInstanceInfo.getMetadata())
                .queryString("ephemeral", nacosInstanceInfo.getEphemeral())
                .asObject(Response.class);

        int status = result.getStatus();
        Response<String> resultBody = result.getBody();
        if (status != 200){
            throw new ServiceException("nacosNameSpace调用失败:"+resultBody);
        }

        nacosCacheManager.removeFromCache(nacosInstanceInfo.getNamespaceId());
        return resultBody.getData();
    }


    public List<InstanceDetailResponse> cacheList(){
        return nacosCacheManager.cacheList();
    }

    public InstanceDetailResponse cacheDetail(String nameSpaceId){
        return nacosCacheManager.getFromCache(nameSpaceId);
    }

    public InstanceResponse instanceList(NacosInstanceList nacosInstanceList) {
        HttpResponse<JsonNode> result = Unirest.get(propertiesUrl() + V2_INSTANCE + "/list")
                // 服务名
                .queryString("namespaceId", nacosInstanceList.getNamespaceId())
                .queryString("groupName", nacosInstanceList.getGroupName())
                .queryString("serviceName", nacosInstanceList.getServiceName())
                .queryString("clusterName", nacosInstanceList.getClusterName())
                .queryString("ip", nacosInstanceList.getIp())
                .queryString("port", nacosInstanceList.getPort())
                .queryString("healthyOnly", nacosInstanceList.getHealthyOnly())
                .queryString("app", nacosInstanceList.getApp())
                .queryString("userAgent", nacosInstanceList.getUserAgent())
                .queryString("clientVersion", nacosInstanceList.getClientVersion())
                .asJson();

        try {
            int status = result.getStatus();
            String body = result.getBody().toString();
            ResponseResult<InstanceResponse> response = objectMapper.readValue(body, ResponseResult.class);


            if (status != 200 || response.getCode() != 0){
                throw new ServiceException("nacosNameSpace调用失败:"+body);
            }

            return objectMapper.readValue(result.getBody().getObject().get("data").toString(), InstanceResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("nacosNameSpace json转换出现异常");
        }

    }


    public InstanceDetailResponse instanceDetail(NacosInstanceDetail nacosInstanceDetail) {
        HttpResponse<JsonNode> result = Unirest.get(propertiesUrl() + V2_INSTANCE)
                // 服务名
                .queryString("namespaceId", nacosInstanceDetail.getNamespaceId())
                .queryString("groupName", nacosInstanceDetail.getGroupName())
                .queryString("serviceName", nacosInstanceDetail.getServiceName())
                .queryString("clusterName", nacosInstanceDetail.getClusterName())
                .queryString("ip", nacosInstanceDetail.getIp())
                .queryString("port", nacosInstanceDetail.getPort())
                .asJson();
        try {
            int status = result.getStatus();
            String body = result.getBody().toString();

            ResponseResult<InstanceResponse> response = objectMapper.readValue(body, ResponseResult.class);

            if (status != 200 || response.getCode() != 0){
                throw new ServiceException("nacosNameSpace调用失败:"+body);
            }
            return objectMapper.readValue(result.getBody().getObject().get("data").toString(), InstanceDetailResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("nacosNameSpace json转换出现异常");
        }
    }

    public NacosInstanceService(NacosProperties nacosProperties, INacosCacheService nacosCacheManager) {
        this.nacosProperties = nacosProperties;
        this.nacosCacheManager = nacosCacheManager;
    }
    
    private String propertiesUrl(){
        return nacosProperties.getUrl();
    }

}
