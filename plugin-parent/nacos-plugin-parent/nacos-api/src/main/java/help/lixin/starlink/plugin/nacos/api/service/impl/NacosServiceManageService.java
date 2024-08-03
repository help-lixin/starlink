package help.lixin.starlink.plugin.nacos.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.nacos.api.dto.ResponseResult;
import help.lixin.starlink.plugin.nacos.api.dto.servicemanage.ServiceNameViewResponse;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceDetailInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.properties.NacosProperties;
import help.lixin.starlink.plugin.nacos.api.service.INacosServiceManageService;
import help.lixin.response.PageResponse;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 2:42 下午
 * @Description
 */
public class NacosServiceManageService implements INacosServiceManageService {
    private static final String V2_SERVICE = "/nacos/v2/ns/service";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private NacosProperties properties;

    public PageResponse<NacosServiceDetailInfo> pageList(NacosServicePageList nacosServicePageList) {
        HttpResponse<JsonNode> result = Unirest.get(propertiesUrl() + V2_SERVICE + "/list")
                // 当前页码
                .queryString("pageNo", nacosServicePageList.getPageNum())
                // 分页大小
                .queryString("pageSize", nacosServicePageList.getPageSize())
                // 分组名
                .queryString("groupName", nacosServicePageList.getGroupName())
                // 命名空间id
                .queryString("namespaceId",nacosServicePageList.getNameSpaceId())
                // 估计是查询条件
                .queryString("selector",nacosServicePageList.getSelector())
                .asJson();
        int status = result.getStatus();
        String body = result.getBody().toString();

        try {
            ResponseResult responseResult = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || responseResult.getCode() != 0){
                throw new ServiceException("nacosNameSpace调用失败:"+body);
            }
            ServiceNameViewResponse data = objectMapper.readValue(result.getBody().getObject().get("data").toString(), ServiceNameViewResponse.class);

            PageResponse<NacosServiceDetailInfo> pageResponse = new PageResponse<>();
            pageResponse.setTotal(data.getCount());
            pageResponse.setRecords(data.getServices());
            pageResponse.setPageCurrent(nacosServicePageList.getPageNum());
            pageResponse.setPageSize(nacosServicePageList.getPageSize());
            return pageResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换失败:"+body);
        }
    }

    @Override
    public Boolean create(NacosServiceForm nacosServiceForm) {
        HttpResponse<JsonNode> result = Unirest.post(propertiesUrl() + V2_SERVICE )
                .queryString("namespaceId", nacosServiceForm.getNamespaceId())
                .queryString("serviceName", nacosServiceForm.getServiceName())
                .queryString("groupName", nacosServiceForm.getGroupName())
                .queryString("ephemeral",nacosServiceForm.getEphemeral())
                .queryString("protectThreshold",nacosServiceForm.getProtectThreshold())
                .queryString("metadata",nacosServiceForm.getMetadata())
                .queryString("selector",nacosServiceForm.getSelector())
                .asJson();
        int status = result.getStatus();
        String body = result.getBody().toString();
        if (status != 200){
            throw new ServiceException("nacosNameSpace调用失败:"+body);
        }
        return result.getBody().getObject().get("data").equals("ok");
    }

    @Override
    public Boolean update(NacosServiceForm nacosServiceForm) {
        HttpResponse<JsonNode> result = Unirest.put(propertiesUrl() + V2_SERVICE )
                .queryString("namespaceId", nacosServiceForm.getNamespaceId())
                .queryString("serviceName", nacosServiceForm.getServiceName())
                .queryString("groupName", nacosServiceForm.getGroupName())
                .queryString("ephemeral",nacosServiceForm.getEphemeral())
                .queryString("protectThreshold",nacosServiceForm.getProtectThreshold())
                .queryString("metadata",nacosServiceForm.getMetadata())
                .queryString("selector",nacosServiceForm.getSelector())
                .asJson();

        int status = result.getStatus();
        String body = result.getBody().toString();

        try {
            ResponseResult responseResult = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || responseResult.getCode() != 0){
                throw new ServiceException("nacosNameSpace调用失败:"+body);
            }
            return result.getBody().getObject().get("data").equals("ok");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("service更新失败:"+body);
        }

    }

    @Override
    public Boolean remove(NacosServiceInfo nacosServiceInfo) {
        HttpResponse<JsonNode> result = Unirest.delete(propertiesUrl() + V2_SERVICE )
                .queryString("namespaceId", nacosServiceInfo.getNamespaceId())
                .queryString("serviceName", nacosServiceInfo.getServiceName())
                .queryString("groupName", nacosServiceInfo.getGroupName())
                .asJson();
        int status = result.getStatus();
        String body = result.getBody().toString();
        try {
            ResponseResult responseResult = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || responseResult.getCode() != 0){
                throw new ServiceException("nacosNameSpace调用失败:"+body);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("service更新失败:"+body);
        }
        return body.equals("ok");
    }

    @Override
    public String detail(NacosServiceInfo nacosServiceInfo) {
        HttpResponse<ResponseResult> result = Unirest.get(propertiesUrl() + V2_SERVICE )
                .queryString("namespaceId", nacosServiceInfo.getNamespaceId())
                .queryString("serviceName", nacosServiceInfo.getServiceName())
                .queryString("groupName", nacosServiceInfo.getGroupName())
                .asObject(ResponseResult.class);
        int status = result.getStatus();
        String body = result.getBody().toString();
        if (status != 200){
            throw new ServiceException("nacosNameSpace调用失败:"+body);
        }
        return result.getBody().getData().toString();
    }


    private String propertiesUrl(){
        return properties.getUrl();
    }

    public NacosServiceManageService(NacosProperties properties) {
        this.properties = properties;
    }
}
