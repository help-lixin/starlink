package help.lixin.mvn.compile.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import help.lixin.mvn.compile.properties.DockerMavenProperties;
import help.lixin.mvn.compile.service.IContainerService;
import help.lixin.mvn.compile.service.impl.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(DockerMavenProperties.class)
public class DockerMavenSourceCompileConfig {

    @Bean
    @ConditionalOnMissingBean(name = "mavenDockerClientConfig")
    public DefaultDockerClientConfig mavenDockerClientConfig(DockerMavenProperties dockerProperties) {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                //
                .withDockerHost(dockerProperties.getHost())
                //
                .withDockerTlsVerify(dockerProperties.isDockerTlsVerify())
                //
                .withRegistryUrl(dockerProperties.getRegistryUrl())
                //
                .withRegistryUsername(dockerProperties.getRegistryUser())
                //
                .withRegistryPassword(dockerProperties.getRegistryPwd())
                //
                .withApiVersion(dockerProperties.getApiVersion())
                //
                .build();

        // 为自己的私有仓库,配置认证信息
//        config.getDockerConfig().getAuths().put(null,null)

        // TODO lixin 留一个Customizer
        return config;
    }


    @Bean
    @ConditionalOnMissingBean(name = "mavenDockerHttpClient")
    public DockerHttpClient mavenDockerHttpClient(DockerMavenProperties dockerProperties, //
                                                  @Autowired @Qualifier("mavenDockerClientConfig") DefaultDockerClientConfig mavenDockerClientConfig) {
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                //
                .dockerHost(mavenDockerClientConfig.getDockerHost())
                //
                .sslConfig(mavenDockerClientConfig.getSSLConfig())
                //
                .maxConnections(dockerProperties.getMaxConnections())
                //
                .connectionTimeout(Duration.ofMinutes(dockerProperties.getConnectionTimeout()))
                //
                .responseTimeout(Duration.ofMinutes(dockerProperties.getResponseTimeout()))
                //
                .build();
        // TODO lixin 留一个Customizer
        return httpClient;
    }

    @Bean
    @ConditionalOnMissingBean(name = "mavenDockerClient")
    public DockerClient mavenDockerClient(DefaultDockerClientConfig dockerClientConfig, //
                                          @Autowired @Qualifier("mavenDockerHttpClient") DockerHttpClient mavenDockerHttpClient) {
        // TODO lixin 留一个Customizer
        return DockerClientImpl.getInstance(dockerClientConfig, mavenDockerHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean(name = "containerService")
    public IContainerService containerService(@Autowired @Qualifier("mavenDockerClient") DockerClient mavenDockerClient) {
        return new ContainerService(mavenDockerClient);
    }
}