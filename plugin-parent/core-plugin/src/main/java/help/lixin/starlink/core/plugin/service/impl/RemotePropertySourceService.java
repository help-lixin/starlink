package help.lixin.starlink.core.plugin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.starlink.core.dto.PropertySource;
import help.lixin.starlink.core.plugin.service.IPropertySourceService;
import help.lixin.security.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通过远程请求,拉取配置文件信息
 */
public class RemotePropertySourceService implements IPropertySourceService {

    protected Logger logger = LoggerFactory.getLogger(RemotePropertySourceService.class);

    private RestOperations restTemplate;

    private String propertySourceEndpointUrl;

    private String clientId;

    private String clientSecret;

    private Map<String, String> additionalParameters;


    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPropertySourceEndpointUrl() {
        return propertySourceEndpointUrl;
    }

    public void setPropertySourceEndpointUrl(String propertySourceEndpointUrl) {
        this.propertySourceEndpointUrl = propertySourceEndpointUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    @Override
    public List<PropertySource> pull(String pluginCode, String instanceCode) {
        List<PropertySource> resultList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList(getAuthorizationHeader(clientId, clientSecret)));
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String responsePropertySource = getPropertySourceResult(propertySourceEndpointUrl, pluginCode, instanceCode, headers);
            Response response = mapper.readValue(responsePropertySource, Response.class);
            if (response.getCode() != 200) {
                String msg = String.format("拉取插件:[%s]失败,失败原因:[%s]", pluginCode, response.getMsg());
                throw new RuntimeException(msg);
            }
            List<PropertyConfig> data = response.getData();
            if (Objects.nonNull(data)) {
                List<PropertySource> propertySources = data.stream().map(item -> {
                    PropertySource propertySource = new PropertySource();
                    propertySource.setPluginCode(item.getPluginCode());
                    propertySource.setInstanceCode(item.getInstanceCode());
                    propertySource.setContent(item.getContent());
                    propertySource.setPropertiesHash(item.getPropertiesHash());
                    return propertySource;
                }).collect(Collectors.toList());
                resultList.addAll(propertySources);
            }
        } catch (UnauthorizedException e) {
            String msg = String.format("发送请求:[{}]失败,原因是请求需要[未授权],详细错误信息如下:[\n{}\n]", propertySourceEndpointUrl, e);
            throw new RuntimeException(msg);
        } catch (JsonProcessingException e) {
            String msg = String.format("发送请求:[{}],后解析json出现错误,详细错误信息如下:[\n{}\n]", propertySourceEndpointUrl, e);
            throw new RuntimeException(msg);
        }
        return resultList;
    }


    private String getAuthorizationHeader(String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            logger.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }
        String creds = String.format("%s:%s", clientId, clientSecret);
        try {
            return "Basic " + new String(Base64.getEncoder().encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private String getPropertySourceResult(String path, String pluginCode, String instanceCode, HttpHeaders headers) throws UnauthorizedException {
        RequestEntity<Void> requestEntity = RequestEntity.get(path, pluginCode, instanceCode)//
                .headers(headers) //
                .build();
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(requestEntity, String.class);
        if (responseEntity.getStatusCode().is4xxClientError()) {
            throw new UnauthorizedException(responseEntity.getBody());
        } else if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new UnauthorizedException(responseEntity.getBody());
        }
    }

    public synchronized RestOperations getRestTemplate() {
        if (null == restTemplate) {
            SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
            httpRequestFactory.setConnectTimeout(60 * 1000);
            httpRequestFactory.setReadTimeout(60 * 1000);
            restTemplate = new RestTemplate(httpRequestFactory);
            ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
                @Override
                // Ignore 400
                public void handleError(ClientHttpResponse response) throws IOException {
                    if (response.getRawStatusCode() != 400) {
                        super.handleError(response);
                    }
                }
            });
        }
        return restTemplate;
    }

    static class Response implements Serializable {
        private Integer code;
        private String msg;
        private List<PropertyConfig> data = new ArrayList<>();

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<PropertyConfig> getData() {
            return data;
        }

        public void setData(List<PropertyConfig> data) {
            this.data = data;
        }
    }

    static class PropertyConfig implements Serializable {
        private Long instanceId;
        private String pluginCode;
        private String instanceCode;
        private String content;
        private String propertiesHash;

        public Long getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(Long instanceId) {
            this.instanceId = instanceId;
        }

        public String getPluginCode() {
            return pluginCode;
        }

        public void setPluginCode(String pluginCode) {
            this.pluginCode = pluginCode;
        }

        public String getInstanceCode() {
            return instanceCode;
        }

        public void setInstanceCode(String instanceCode) {
            this.instanceCode = instanceCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPropertiesHash() {
            return propertiesHash;
        }

        public void setPropertiesHash(String propertiesHash) {
            this.propertiesHash = propertiesHash;
        }
    }
}
