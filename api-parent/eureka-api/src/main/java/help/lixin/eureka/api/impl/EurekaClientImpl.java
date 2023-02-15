package help.lixin.eureka.api.impl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import help.lixin.eureka.api.EurekaClient;
import help.lixin.eureka.api.exception.EurekaClientException;
import help.lixin.eureka.api.status.EurekaInstanceStatus;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.IOException;

public class EurekaClientImpl implements EurekaClient {
    private String eurekaServiceUrl;

    private static final EurekaXmlJacksonCodec DEFAULT_CODEC = new EurekaXmlJacksonCodec();

    private EurekaXmlJacksonCodec codec = DEFAULT_CODEC;

    public EurekaClientImpl(String eurekaServiceUrl) {
        this(eurekaServiceUrl, DEFAULT_CODEC);
    }

    public EurekaClientImpl(String eurekaServiceUrl, EurekaXmlJacksonCodec codec) {
        if (eurekaServiceUrl.endsWith("/")) {
            eurekaServiceUrl = eurekaServiceUrl.substring(0, eurekaServiceUrl.length() - 1);
        }
        this.eurekaServiceUrl = eurekaServiceUrl;
        if (null != codec) {
            this.codec = codec;
        }
    }


    @Override
    public void registerInstance(InstanceInfo instanceInfo) {
        String url = String.format(eurekaServiceUrl + "/apps/%s", instanceInfo.getAppName());
        post(url, InstanceInfo.class, instanceInfo);
    }

    @Override
    public void deleteInstance(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        delete(url);
    }

    @Override
    public void heartbeat(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        put(url);
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
    public void outOfService(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=OUT_OF_SERVICE", appName, instanceId);
        put(url);
    }


    @Override
    public void backInService(String appName, String instanceId, EurekaInstanceStatus status) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=%s", appName, instanceId, status.name());
        delete(url);
    }

    @Override
    public void updateMetadata(String appName, String instanceId, String key, String value) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/metadata?%s=%s", appName, instanceId, key, value);
        put(url);
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
                throw new EurekaClientException(httpResponse.getBody(), httpResponse.getStatus());
            }
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
        }
    }// end post


    public void put(String url) {
        try {
            HttpResponse<String> httpResponse = Unirest.put(url).header("Content-Type", "application/xml")
                    //
                    .asString();
            if (httpResponse.getStatus() != 200) {
                throw new EurekaClientException(httpResponse.getBody(), httpResponse.getStatus());
            }
        } catch (Exception e) {
            throw new EurekaClientException(e, 5000);
        }
    } // end

    public <T> T get(String url, Class<T> tClass) {
        try {
            HttpResponse<String> httpResponse = Unirest.get(url)
                    //
                    .header("Content-Type", "application/xml")
                    //
                    .asString();
            if (httpResponse.getStatus() != 200) {
                throw new EurekaClientException(httpResponse.getBody(), httpResponse.getStatus());
            }
            String body = httpResponse.getBody();
            if (body != null) {
                return codec.getObjectMapper(tClass).readValue(body, tClass);
            }
            throw new EurekaClientException("error body: " + body, 10000);
        } catch (IOException e) {
            throw new EurekaClientException(e, 5000);
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
                throw new EurekaClientException(httpResponse.getBody(), httpResponse.getStatus());
            }
        } catch (Exception e) {
            throw new EurekaClientException(e, 5000);
        }
    } // end delete
}