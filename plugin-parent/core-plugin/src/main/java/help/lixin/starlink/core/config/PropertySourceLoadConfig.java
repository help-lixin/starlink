package help.lixin.starlink.core.config;

import help.lixin.starlink.core.plugin.service.IPropertySourceService;
import help.lixin.starlink.core.plugin.service.impl.CompositePropertySourceService;
import help.lixin.starlink.core.plugin.service.impl.LocalPropertySourceService;
import help.lixin.starlink.core.plugin.service.impl.RemotePropertySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class PropertySourceLoadConfig {
    /**
     * 组合:本地配置和远程配置的解析.
     *
     * @param propertySourceServices
     * @return
     */
    @Bean
    @Primary
    public IPropertySourceService compositePropertySourceService(List<IPropertySourceService> propertySourceServices) {
        return new CompositePropertySourceService(propertySourceServices);
    }

    /**
     * 读取本地配置文件
     *
     * @param environment
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "localPropertySourceService")
    public IPropertySourceService localPropertySourceService(Environment environment) {
        return new LocalPropertySourceService(environment);
    }

    /**
     * 通过网络请求,读取配置信息,并解析成配置文件.
     *
     * @param propertySourceEndpointUrl
     * @param clientId
     * @param clientSecret
     * @param restTemplate
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "remotePropertySourceService")
    @ConditionalOnProperty(prefix = "plugin.property.source", name = "enable", havingValue = "true", matchIfMissing = true)
    public IPropertySourceService remotePropertySourceService(@Value("${plugin.property.source.endpoint.url:http://192.168.8.15:7001/system/plugin/instance/{pluginCode}/{instanceCode}}") //
                                                              String propertySourceEndpointUrl, //
                                                              @Value("${oauth.clientId}") String clientId, //
                                                              @Value("${oauth.clientSecret}") String clientSecret, //
                                                              @Autowired(required = false) RestTemplate restTemplate) {
        RemotePropertySourceService remotePropertySourceService = new RemotePropertySourceService();
        remotePropertySourceService.setPropertySourceEndpointUrl(propertySourceEndpointUrl);
        remotePropertySourceService.setClientId(clientId);
        remotePropertySourceService.setClientSecret(clientSecret);
        if (null != restTemplate) {
            remotePropertySourceService.setRestTemplate(restTemplate);
        }
        return remotePropertySourceService;
    }
}
