package help.lixin.plugin.eureka.api.service.impl;

import java.io.IOException;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

import help.lixin.exception.ServiceException;
import help.lixin.plugin.eureka.api.model.EurekaInstanceStatus;
import help.lixin.plugin.eureka.api.properties.EurekaProperties;
import help.lixin.plugin.eureka.api.service.IEurekaCacheManager;
import help.lixin.plugin.eureka.api.service.IEurekaClient;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class EurekaClientImpl implements IEurekaClient {
    private String eurekaServiceUrl;

    private static final EurekaXmlJacksonCodec DEFAULT_CODEC = new EurekaXmlJacksonCodec();

    private EurekaXmlJacksonCodec codec = DEFAULT_CODEC;
    private IEurekaCacheManager eurekaCacheManager;

    public EurekaClientImpl(EurekaProperties eurekaProperties, IEurekaCacheManager eurekaCacheManager) {
        this(eurekaProperties, DEFAULT_CODEC);
        this.eurekaCacheManager = eurekaCacheManager;
    }

    public EurekaClientImpl(EurekaProperties eurekaProperties, EurekaXmlJacksonCodec codec) {
        String url = eurekaProperties.getUrl();
        this.eurekaServiceUrl = url;
        if (null != codec) {
            this.codec = codec;
        }
    }

    @Override
    public Boolean registerInstance(InstanceInfo instanceInfo) {
        InstanceInfo fromCache = eurekaCacheManager.getFromCache(instanceInfo.getAppName());
        if (fromCache != null) {
            throw new ServiceException("该服务名已注册");
        }
        String url = String.format(eurekaServiceUrl + "/apps/%s", instanceInfo.getAppName());
        post(url, InstanceInfo.class, instanceInfo);
        eurekaCacheManager.putInCache(instanceInfo.getAppName(), instanceInfo);
        return true;
    }

    @Override
    public Boolean deleteInstance(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        delete(url);
        return eurekaCacheManager.removeFromCache(appName);
    }

    @Override
    public Boolean heartbeat(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        Boolean result = put(url);
        if (!result) {
            eurekaCacheManager.removeFromCache(appName);
        }
        return result;
    }

    @Override
    public Applications applications() {
        String url = eurekaServiceUrl + "/apps";
        return get(url, Applications.class);
    }

    @Override
    public Application application(String appName) {
        String url = String.format(eurekaServiceUrl + "/apps/%s", appName);
        return get(url, Application.class);
    }

    @Override
    public InstanceInfo instance(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        return get(url, InstanceInfo.class);
    }

    @Override
    public InstanceInfo instance(String instanceId) {
        String url = String.format(eurekaServiceUrl + "/instances/%s", instanceId);
        return get(url, InstanceInfo.class);
    }

    @Override
    public Boolean outOfService(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=OUT_OF_SERVICE", appName, instanceId);
        Boolean result = put(url);
        if (result) {
            eurekaCacheManager.removeFromCache(appName);
        }
        return result;
    }

    @Override
    public Boolean backInService(String appName, String instanceId, EurekaInstanceStatus status) {
        String url =
            String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=%s", appName, instanceId, status.name());
        delete(url);
        return eurekaCacheManager.removeFromCache(appName);
    }

    @Override
    public Boolean updateMetadata(String appName, String instanceId, String key, String value) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/metadata?%s=%s", appName, instanceId, key, value);
        return put(url);
    }

    @Override
    public Applications vips(String vipAddress) {
        String url = String.format(eurekaServiceUrl + "/vips/%s", vipAddress);
        return get(url, Applications.class);
    }

    @Override
    public Applications svips(String svipAddress) {
        String url = String.format(eurekaServiceUrl + "/svips/%s", svipAddress);
        return get(url, Applications.class);
    }

    protected void post(String url, Class<?> tClass, Object obj) {
        try {
            HttpResponse<String> httpResponse = Unirest.post(url)
                //
                .header("Content-Type", "application/xml")
                //
                .body(codec.getObjectMapper(tClass).writeValueAsBytes(obj))
                //
                .asString();
            if (httpResponse.getStatus() != 204) {
                throw new ServiceException(httpResponse.getStatus(), httpResponse.getBody());
            }
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public Boolean put(String url) {
        try {
            HttpResponse<String> httpResponse = Unirest.put(url).header("Content-Type", "application/xml")
                //
                .asString();
            if (httpResponse.getStatus() != 200) {
                throw new ServiceException(httpResponse.getStatus(), httpResponse.getBody());
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    } // end

    public <T> T get(String url, Class<T> tClass) {
        try {
            HttpResponse<String> httpResponse = Unirest.get(url)
                //
                .header("Content-Type", "application/xml")
                //
                .asString();
            if (httpResponse.getStatus() != 200) {
                throw new ServiceException(httpResponse.getStatus(), httpResponse.getBody());
            }
            String body = httpResponse.getBody();
            if (body != null) {
                return codec.getObjectMapper(tClass).readValue(body, tClass);
            }
            throw new ServiceException(10000, "error body: " + body);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    } // get

    public static void delete(String url) {
        try {
            HttpResponse<String> httpResponse = Unirest.delete(url)
                //
                .header("Content-Type", "application/xml")
                //
                .asString();
            if (httpResponse.getStatus() != 200) {
                throw new ServiceException(httpResponse.getStatus(), httpResponse.getBody());
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    } // end delete
}