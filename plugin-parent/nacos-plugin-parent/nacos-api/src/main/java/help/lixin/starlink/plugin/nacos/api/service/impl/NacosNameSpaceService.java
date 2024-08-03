package help.lixin.starlink.plugin.nacos.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.nacos.api.dto.ResponseResult;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NamespaceDetail;
import help.lixin.starlink.plugin.nacos.api.properties.NacosProperties;
import help.lixin.starlink.plugin.nacos.api.service.INacosNameSpaceService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 11:10 上午
 * @Description
 */
public class NacosNameSpaceService implements INacosNameSpaceService {

    private static final String NAME_SPACE = "/nacos/v2/console/namespace";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private NacosProperties nacosProperties;

    public List<NamespaceDetail> nameSpaseList(String nameSpaceId){
        List<NamespaceDetail> resultList = new ArrayList<>();

        HttpResponse<JsonNode> response;

        try {
            if (StringUtils.isBlank( nameSpaceId ) ){
                response = Unirest.get(getUrl() + NAME_SPACE + "/list")
                        //
                        .asJson();
                resultList = objectMapper.readValue(response.getBody().getObject().get("data").toString(), List.class);
            }else{
                response = Unirest.get(getUrl() + NAME_SPACE)
                        // 命名空间ID
                        .queryString("namespaceId", nameSpaceId)
                        //
                        .asJson();
                NamespaceDetail data = objectMapper.readValue(response.getBody().getObject().get("data").toString(), NamespaceDetail.class);
                resultList.add(data);
            }
            int status = response.getStatus();
            String body = response.getBody().toString();

            ResponseResult responseResult = objectMapper.readValue(body, ResponseResult.class);

            if (status != 200 || responseResult.getCode() != 0){
                throw new ServiceException("nacosNameSpace查询失败:"+body);
            }

            return resultList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("");
        }
    }

    public Boolean createNamespace(NacosNameSpaceSave nameSpaceCreate) {
        HttpResponse<String> result = Unirest.post(getUrl() + NAME_SPACE)
                // 命名空间ID
                .queryString("namespaceId", nameSpaceCreate.getNameSpaceId())
                // 命名空间名
                .queryString("namespaceName", nameSpaceCreate.getNameSpaceName())
                // 命名空间描述
                .queryString("namespaceDesc", nameSpaceCreate.getNameSpaceDesc())
                //
                .asString();
        if (result.getStatus() != 200){
            throw new ServiceException("nacosNameSpace创建失败:"+result.getBody());
        }
        return true;
    }


    public Boolean updateNamespace(NacosNameSpaceSave nacosNameSpaceSave) {
        HttpResponse<String> result = Unirest.put(getUrl() + NAME_SPACE)
                // 命名空间ID
                .queryString("namespace", nacosNameSpaceSave.getNameSpaceId())
                // 命名空间名
                .queryString("namespaceShowName", nacosNameSpaceSave.getNameSpaceName())
                // 命名空间描述
                .queryString("namespaceDesc", nacosNameSpaceSave.getNameSpaceDesc())
                //
                .asString();
        if (result.getStatus() != 200){
            throw new ServiceException("nacosNameSpace更新失败:"+result.getBody());
        }
        return true;
    }


    public Boolean deleteNamespace(String nameSpaceId) {
        HttpResponse<String> result = Unirest.delete(getUrl() + NAME_SPACE)
                // 命名空间ID
                .queryString("namespaceId", nameSpaceId)
                //
                .asString();
        if (result.getStatus() != 200){
            throw new ServiceException("nacosNameSpace调用失败:"+result.getBody());
        }
        return true;
    }

    public NacosNameSpaceService(NacosProperties nacosProperties) {
        this.nacosProperties = nacosProperties;
    }
    
    private String getUrl(){
        return nacosProperties.getUrl();
    }
    
}
