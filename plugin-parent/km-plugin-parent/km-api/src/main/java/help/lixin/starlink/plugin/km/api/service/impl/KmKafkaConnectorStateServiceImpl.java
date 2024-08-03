package help.lixin.starlink.plugin.km.api.service.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.km.api.constant.ApiPrefix;
import help.lixin.starlink.plugin.km.api.dto.ResponseResult;
import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.api.properties.KmProperties;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyBase;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboard;
import help.lixin.starlink.plugin.km.api.response.ClusterPhysState;
import help.lixin.starlink.plugin.km.api.service.IKmKafkaConnectorStateService;
import help.lixin.starlink.plugin.km.api.service.IKmLoginAPIService;
import kong.unirest.Cookie;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/14 12:03 下午
 * @Description
 */
public class KmKafkaConnectorStateServiceImpl implements IKmKafkaConnectorStateService {

    private KmProperties kmProperties;
    private IKmLoginAPIService kmLoginAPIService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Boolean getClusterPhyBasicCombineExist(String clusterPhyName) {

        HttpResponse<String> result = Unirest
            .get(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters/" + clusterPhyName
                + "/basic-combine-exist")
            .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status == 200 && jsonNode.get("code").toString().equals("0")) {
                return jsonNode.get("data").get("exist").toString().equals("true");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }

        throw new ServiceException("请求发生异常:" + result.toString());
    }

    @Override
    public KafkaBSValidate validateKafka(String bootstrapServices) {
        HttpResponse<String> result = Unirest
            .post(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "utils/kafka-validator")
            .body("{\"bootstrapServers\":\"" + bootstrapServices + "\"}").contentType("application/json;charset=UTF-8")
            .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status == 200 && jsonNode.get("code").toString().equals("0")) {
                return objectMapper.readValue(jsonNode.get("data").traverse(), KafkaBSValidate.class);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }

        throw new ServiceException("请求发生异常:" + result.toString());
    }

    @Override
    public Boolean addPhysicalClusters(ClusterPhyAdd clusterPhyAddDTO) {

        try {
            HttpResponse<ResponseResult> result = Unirest
                .post(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters")
                .body(objectMapper.writeValueAsString(clusterPhyAddDTO)).contentType("application/json;charset=UTF-8")
                .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asObject(ResponseResult.class);
            int status = result.getStatus();

            if (status == 200 && result.getBody().getCode() == 0) {
                return true;
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("添加集群出现异常:" + e.getMessage());
        }

        return false;
    }

    @Override
    public ClusterPhysState state() {

        try {
            HttpResponse<String> result =
                Unirest.get(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters/state")
                    .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
            int status = result.getStatus();
            String body = result.getBody();

            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status == 200 && jsonNode.get("code").toString().equals("0")) {
                return objectMapper.readValue(jsonNode.get("data").traverse(), ClusterPhysState.class);
            }

            throw new ServiceException("查询结果出现异常:" + body);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("添加集群出现异常:" + e.getMessage());
        }

    }

    @Override
    public PageResponse<ClusterPhyDashboard> list(MultiClusterDashboard multiClusterDashboardDTO) {

        try {
            HttpResponse<String> result =
                Unirest.post(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters/dashboard")
                    .body(objectMapper.writeValueAsString(multiClusterDashboardDTO))
                    .contentType("application/json;charset=UTF-8")
                    .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
            int status = result.getStatus();
            String body = result.getBody();

            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status == 200 && jsonNode.get("code").toString().equals("0")) {
                String data = objectMapper.writeValueAsString(jsonNode.get("data").get("bizData"));

                PageResponse<ClusterPhyDashboard> pageResponse = new PageResponse();

                pageResponse.setPageCurrent(jsonNode.get("data").get("pagination").get("pageNo").intValue());
                pageResponse.setPageSize(jsonNode.get("data").get("pagination").get("pageSize").intValue());
                pageResponse.setTotal(jsonNode.get("data").get("pagination").get("total").intValue());

                TypeReference<List<ClusterPhyDashboard>> typeReference =
                    new TypeReference<List<ClusterPhyDashboard>>() {};
                pageResponse.setRecords(objectMapper.readValue(data, typeReference));
                return pageResponse;
            }

            throw new ServiceException("查询结果出现异常:" + body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("查询列表出现异常:" + e.getMessage());
        }

    }

    @Override
    public PageResponse<TopicOverview> topicList(MultiClusterDashboard multiClusterDashboardDTO, Integer id) {

        try {
            HttpResponse<String> result =
                Unirest.post(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "clusters/" + id + "/topics-overview")
                    .body(objectMapper.writeValueAsString(multiClusterDashboardDTO))
                    .contentType("application/json;charset=UTF-8")
                    .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
            int status = result.getStatus();
            String body = result.getBody();

            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status == 200 && jsonNode.get("code").toString().equals("0")) {
                String data = objectMapper.writeValueAsString(jsonNode.get("data").get("bizData"));
                PageResponse<TopicOverview> pageResponse = new PageResponse();

                pageResponse.setPageCurrent(jsonNode.get("data").get("pagination").get("pageNo").intValue());
                pageResponse.setPageSize(jsonNode.get("data").get("pagination").get("pageSize").intValue());
                pageResponse.setTotal(jsonNode.get("data").get("pagination").get("total").intValue());

                TypeReference<List<TopicOverview>> typeReference = new TypeReference<List<TopicOverview>>() {};
                pageResponse.setRecords(objectMapper.readValue(data, typeReference));
                return pageResponse;
            }

            throw new ServiceException("查询结果出现异常:" + body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("查询列表出现异常:" + e.getMessage());
        }

    }

    @Override
    public ClusterPhyBase detail(Integer id) {
        try {
            HttpResponse<String> result =
                Unirest.get(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters/" + id + "/basic")
                    .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asString();
            int status = result.getStatus();
            String body = result.getBody();
            TypeReference<ResponseResult<ClusterPhyBase>> typeReference =
                new TypeReference<ResponseResult<ClusterPhyBase>>() {};
            ResponseResult<ClusterPhyBase> responseResult = objectMapper.readValue(body, typeReference);
            if (status == 200 && responseResult.getCode() == 0) {
                return responseResult.getData();
            }

            throw new ServiceException("查询结果出现异常:" + body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("查询详情出现异常:" + e.getMessage());
        }

    }

    @Override
    public Boolean del(Integer id) {
        try {
            HttpResponse<ResponseResult> result = Unirest
                .delete(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters")
                .queryString("clusterPhyId", id).cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid()))
                .asObject(ResponseResult.class);
            int status = result.getStatus();

            if (status == 200 && result.getBody().getCode() == 0) {
                return true;
            }

            throw new ServiceException("删除集群出现异常:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("删除集群出现异常:" + e.getMessage());
        }
    }

    @Override
    public Boolean update(ClusterPhyUpdate clusterPhyUpdateDTO) {
        try {
            HttpResponse<ResponseResult> result = Unirest
                .put(kmProperties.getUrl() + ApiPrefix.API_V3_PREFIX + "physical-clusters")
                .body(objectMapper.writeValueAsString(clusterPhyUpdateDTO))
                .contentType("application/json;charset=UTF-8")
                .cookie(new Cookie("JSESSIONID", kmLoginAPIService.fetchCookieValid())).asObject(ResponseResult.class);

            int status = result.getStatus();

            if (status == 200 && result.getBody().getCode() == 0) {
                return true;
            }

            throw new ServiceException("更新出现异常:" + result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("查询列表出现异常:" + e.getMessage());
        }

    }

    public KmKafkaConnectorStateServiceImpl(KmProperties kmProperties, IKmLoginAPIService kmLoginAPIService) {
        this.kmProperties = kmProperties;
        this.kmLoginAPIService = kmLoginAPIService;
    }
}
