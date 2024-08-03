package help.lixin.starlink.plugin.nacos.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.nacos.api.dto.ResponseResult;
import help.lixin.starlink.plugin.nacos.api.dto.config.*;
import help.lixin.starlink.plugin.nacos.api.model.config.*;
import help.lixin.starlink.plugin.nacos.api.properties.NacosProperties;
import help.lixin.starlink.plugin.nacos.api.service.INacosConfigService;
import help.lixin.utils.Md5Crypt;
import kong.unirest.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 11:24 上午
 * @Description
 */
public class NacosConfigService implements INacosConfigService {

    private NacosProperties nacosProperties;

    private static final String DEPLOY_V2_CONFIG = "/nacos/v2/cs/config";
    private static final String DEPLOY_V1_CONFIG = "/nacos/v1/cs/configs";
    private static final String HISTORY_V2_CONFIG = "/nacos/v2/cs/history";
    private static final String LISTENER_CONFIG = "/nacos/v1/cs/listener";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            // 序列化日期格式 yyyy-MM-dd'T'HH:mm:ss.SSSZ
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            // 序列化时忽略值为 null 的属性
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            // 忽略值为默认值的属性
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    @Override
    public Boolean publishConfig(NacosPublishConfig nacosPublishConfig) {
        HttpResponse<String> result = Unirest.post(propertiesUrl() + DEPLOY_V2_CONFIG)
            // 命名空间id
            .queryString("tenant", nacosPublishConfig.getTenant()).queryString("dataId", nacosPublishConfig.getDataId())
            .queryString("group", nacosPublishConfig.getGroup()).queryString("content", nacosPublishConfig.getContent())
            .queryString("appName", nacosPublishConfig.getAppName())
            .queryString("configTags", nacosPublishConfig.getConfigTags())
            .queryString("tag", nacosPublishConfig.getTag()).queryString("desc", nacosPublishConfig.getDesc())
            .asString();

        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status != 200 || !jsonNode.get("code").toString().equals("0")) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            return jsonNode.get("data").toString().equals("true");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换异常:" + e.getMessage());
        }
    }

    /**
     * @Param nacosConfigDTO :
     * @Author: 伍岳林
     * @Date: 2023/7/12 10:44 上午
     * @Return: kong.unirest.HttpResponse<java.lang.String>
     * @Description 新建配置
     * @return
     */
    @Override
    public Boolean createConfig(NacosCreateConfig nacosCreateConfig) {

        HttpResponse<String> result = Unirest.post(propertiesUrl() + DEPLOY_V1_CONFIG)
            // 命名空间id
            .queryString("tenant", nacosCreateConfig.getTenant())
            .queryString("namespaceId", nacosCreateConfig.getInstancSpaceId())
            .queryString("dataId", nacosCreateConfig.getDataId()).queryString("group", nacosCreateConfig.getGroup())
            .queryString("content", nacosCreateConfig.getContent())
            .queryString("type", nacosCreateConfig.getType().getDesc())
            .queryString("appName", nacosCreateConfig.getAppName())
            .queryString("configTags", nacosCreateConfig.getTag()).queryString("desc", nacosCreateConfig.getDesc())
            .asString();

        int status = result.getStatus();
        String body = result.getBody();
        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + body);
        }
        return body.equals("true");
    }

    /**
     * @Param nacosUpdateConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/12 4:34 下午
     * @Return: kong.unirest.HttpResponse<java.lang.String>
     * @Description 更新配置
     * @return
     */
    @Override
    public Boolean updateConfig(NacosUpdateConfig nacosUpdateConfig) {

        HttpResponse<String> result = Unirest.post(propertiesUrl() + DEPLOY_V1_CONFIG)
            // 命名空间id
            .queryString("tenant", nacosUpdateConfig.getTenant()).queryString("dataId", nacosUpdateConfig.getDataId())
            .queryString("group", nacosUpdateConfig.getGroup()).queryString("content", nacosUpdateConfig.getContent())
            .queryString("type", nacosUpdateConfig.getType().getDesc())
            .queryString("appName", nacosUpdateConfig.getAppName())
            .queryString("configTags", nacosUpdateConfig.getTag()).queryString("desc", nacosUpdateConfig.getDesc())
            .queryString("id", nacosUpdateConfig.getDesc())
            .queryString("md5", Md5Crypt.md5Crypt(nacosUpdateConfig.getContent())).asString();

        int status = result.getStatus();
        String body = result.getBody();
        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + body);
        }
        return body.equals("true");
    }

    /**
     * @Param nacosConfigDTO :
     * @Author: 伍岳林
     * @Date: 2023/7/12 10:45 上午
     * @Return: kong.unirest.HttpResponse<java.lang.String>
     * @Description 获取配置列表
     * @return
     */
    @Override
    public NacosPageListConfigResponse pageList(NacosPageListConfig nacosPageListConfig) {
        GetRequest request = Unirest.get(propertiesUrl() + DEPLOY_V2_CONFIG + "/searchDetail")
            // 命名空间id
            .queryString("pageNo", nacosPageListConfig.getPageNum())
            .queryString("pageSize", nacosPageListConfig.getPageSize())
            .queryString("dataId", nacosPageListConfig.getDataId())
            .queryString("tenant", nacosPageListConfig.getTenant()).queryString("group", nacosPageListConfig.getGroup())
            .queryString("appName", nacosPageListConfig.getAppName())
            .queryString("config_tags", nacosPageListConfig.getTag())
            .queryString("config_detail", nacosPageListConfig.getConfigDetail())
            .queryString("search", nacosPageListConfig.getSearch().getDesc());

        HttpResponse<String> result = request.asString();
        int status = result.getStatus();
        String body = result.getBody();
        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + body);
        }

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        NacosPageListConfigResponse<NacosPageListDetailConfigResponse> nacosPageListConfigResponse =
            jsonObjectMapper.readValue(body, NacosPageListConfigResponse.class);
        return nacosPageListConfigResponse;
    }

    /**
     * @Param nacosPageListConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/12 4:43 下午
     * @Return: kong.unirest.HttpResponse<java.lang.String>
     * @Description 查看详情
     * @return
     */
    @Override
    public NacosDetailConfigResponse configDetail(NacosDetailConfig nacosDetailConfig) {
        HttpResponse<String> result = Unirest.get(propertiesUrl() + DEPLOY_V1_CONFIG)
            // 命名空间id
            .queryString("namespaceId", nacosDetailConfig.getNameSpaceId())
            .queryString("tenant", nacosDetailConfig.getTenant()).queryString("dataId", nacosDetailConfig.getDataId())
            .queryString("group", nacosDetailConfig.getGroup()).queryString("tag", nacosDetailConfig.getTag())
            .queryString("show", "all").asString();

        int status = result.getStatus();
        String body = result.getBody();
        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + body);
        }

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        NacosDetailConfigResponse nacosDetailConfigResponse =
            jsonObjectMapper.readValue(body, NacosDetailConfigResponse.class);
        return nacosDetailConfigResponse;
    }

    @Override
    public Boolean deleteConfig(NacosDeleteConfig nacosDeleteConfig) {
        HttpResponse<String> result = Unirest.delete(propertiesUrl() + DEPLOY_V2_CONFIG)
            // 命名空间id
            .queryString("namespaceId", nacosDeleteConfig.getNamespaceId())
            .queryString("tenant", nacosDeleteConfig.getTenant()).queryString("ids", nacosDeleteConfig.getIds())
            .queryString("delType", "ids").asString();

        int status = result.getStatus();
        String body = result.getBody();
        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + body);
        }

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        ResponseResult response = jsonObjectMapper.readValue(body, ResponseResult.class);

        return response.getData().equals(true);
    }

    @Override
    public PageResponse<NacosHistoryConfigResponse>
        pageListConfigHistory(NacosPageListHistoryConfig nacosPageListHistoryConfig) {
        HttpResponse<String> result = Unirest.get(propertiesUrl() + HISTORY_V2_CONFIG + "/list")
            // 命名空间id
            .queryString("namespaceId", nacosPageListHistoryConfig.getNamespaceId())
            .queryString("dataId", nacosPageListHistoryConfig.getDataId())
            .queryString("group", nacosPageListHistoryConfig.getGroup())
            .queryString("pageNo", nacosPageListHistoryConfig.getPageNum())
            .queryString("pageSize", nacosPageListHistoryConfig.getPageSize()).asString();

        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            JsonNode jsonData = jsonNode.get("data");
            if (status != 200 || !jsonNode.get("code").toString().equals("0")) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            List<NacosHistoryConfigResponse> list =
                objectMapper.readValue(jsonData.get("pageItems").toString(), List.class);
            PageResponse<NacosHistoryConfigResponse> pageResponse = new PageResponse<>();
            pageResponse.setTotal(jsonData.get("totalCount").intValue());
            pageResponse.setPageCurrent(jsonData.get("pageNumber").intValue());
            pageResponse.setRecords(list);

            return pageResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }

    }

    @Override
    public NacosHistoryConfigResponse queryDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig) {
        String path = "/list";
        return getNacosHistoryConfigResponse(nacosDetailHistoryConfig, path);
    }

    @Override
    public NacosHistoryConfigResponse queryPreviousDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig) {
        String path = "/previous";
        return getNacosHistoryConfigResponse(nacosDetailHistoryConfig, path);
    }

    @Override
    public List<NacosDetailConfigResponse> queryByNameSpaceId(String namespaceId) {
        HttpResponse<String> result = Unirest.get(propertiesUrl() + HISTORY_V2_CONFIG + "/configs")
            // 命名空间id
            .queryString("namespaceId", namespaceId).asString();

        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            if (status != 200 || !jsonNode.get("code").toString().equals("0")) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            List<NacosDetailConfigResponse> results =
                objectMapper.readValue(jsonNode.get("data").toString(), List.class);

            return results;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<byte[]> exportConfig(NacosExportConfig nacosExportConfig) {
        HttpResponse<byte[]> result = Unirest.get(propertiesUrl() + DEPLOY_V1_CONFIG)
            // 命名空间id
            .queryString("exportV2", "true").queryString("tenant", nacosExportConfig.getTenant())
            .queryString("dataId", nacosExportConfig.getDataId()).queryString("group", nacosExportConfig.getGroup())
            .queryString("appName", nacosExportConfig.getAppName()).queryString("ids", nacosExportConfig.getIds())
            .asBytes();

        int status = result.getStatus();
        byte[] resultBody = result.getBody();

        if (status != 200) {
            throw new ServiceException("nacosConfig调用失败:" + resultBody);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        Headers headers = result.getHeaders();
        headers.all().forEach(v -> {
            httpHeaders.add(v.getName(), v.getValue());
        });
        // 将字节数组作为文件下载的响应发送给前端
        return new ResponseEntity<byte[]>(resultBody, httpHeaders, result.getStatus());

    }

    @Override
    public Map<String, Object> importAndPublishConfig(NacosImportConfig nacosImportConfig) {
        try {
            MultipartFile multipartFile = nacosImportConfig.getFile();

            String[] fileArr = multipartFile.getOriginalFilename().split("\\.");
            File file = File.createTempFile(fileArr[0], "." + fileArr[1]);
            multipartFile.transferTo(file);
            HttpResponse<kong.unirest.JsonNode> jsonNodeHttpResponse = Unirest.post(propertiesUrl() + DEPLOY_V1_CONFIG)
                // 命名空间id
                .queryString("namespace", nacosImportConfig.getNameSpaceId())
                .queryString("policy", nacosImportConfig.getPolicy())
                .queryString("src_user", nacosImportConfig.getSrcUser()).field("file", file)
                .queryString("import", "true").asJson();

            file.delete();

            int status = jsonNodeHttpResponse.getStatus();
            String body = jsonNodeHttpResponse.getBody().toString();

            ResponseResult<Map<String, Object>> response = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || response.getCode() != 200) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            return response.getData();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("IO出现异常:" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> cloneConfig(NacosCloneInfoConfig nacosCloneInfoConfig) {
        List<NacosCloneItemConfig> configBeansList = nacosCloneInfoConfig.getConfigBeansList();
        String jsonBody = new Gson().toJson(configBeansList);
        HttpResponse<kong.unirest.JsonNode> result =
            Unirest.post(propertiesUrl() + DEPLOY_V1_CONFIG).header("Content-Type", "application/json")
                // 命名空间id
                .queryString("tenant", nacosCloneInfoConfig.getTenant())
                .queryString("policy", nacosCloneInfoConfig.getPolicy()).queryString("namespaceId", "")
                .queryString("clone", "true").body(jsonBody).asJson();

        int status = result.getStatus();
        String body = result.getBody().toString();

        try {
            ResponseResult<Map<String, Object>> response = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || response.getCode() != 200) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            return response.getData();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }
    }

    @Override
    public NacosListenerResponse queryListenList(NacosListenConfig nacosListenConfig) {
        HttpResponse<kong.unirest.JsonNode> result = Unirest.get(propertiesUrl() + DEPLOY_V1_CONFIG + "/listener")
            // 命名空间id
            .queryString("dataId", nacosListenConfig.getDataId()).queryString("group", nacosListenConfig.getGroup())
            .queryString("tenant", nacosListenConfig.getTenant())
            .queryString("sampleTime", nacosListenConfig.getSampleTime()).asJson();

        int status = result.getStatus();
        String body = result.getBody().toString();

        try {
            ResponseResult<NacosListenerResponse> response = objectMapper.readValue(body, ResponseResult.class);
            if (status != 200 || response.getCode() != 200) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }
            return response.getData();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }
    }

    private NacosHistoryConfigResponse getNacosHistoryConfigResponse(NacosDetailHistoryConfig nacosDetailHistoryConfig,
        String s) {
        HttpResponse<String> result = Unirest.get(propertiesUrl() + HISTORY_V2_CONFIG + s)
            // 命名空间id
            .queryString("namespaceId", nacosDetailHistoryConfig.getNameSpaceId())
            .queryString("tenant", nacosDetailHistoryConfig.getTenant())
            .queryString("dataId", nacosDetailHistoryConfig.getDataId())
            .queryString("group", nacosDetailHistoryConfig.getGroup())
            .queryString("nid", nacosDetailHistoryConfig.getId()).asString();

        int status = result.getStatus();
        String body = result.getBody();

        try {
            JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
            String jsonData = jsonNode.get("data").toString();
            if (status != 200 || !jsonNode.get("code").toString().equals("0")) {
                throw new ServiceException("nacosConfig调用失败:" + body);
            }

            return objectMapper.readValue(jsonData, NacosHistoryConfigResponse.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("json转换出现异常:" + e.getMessage());
        }
    }

    private String propertiesUrl() {
        return nacosProperties.getUrl();
    }

    public NacosConfigService(NacosProperties nacosProperties) {
        this.nacosProperties = nacosProperties;
    }
}
