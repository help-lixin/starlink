package help.lixin.docker.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import help.lixin.core.pipeline.service.IExpressionService;
import help.lixin.docker.properties.DockerProperties;
import help.lixin.docker.service.DockerFaceService;
import help.lixin.docker.service.IDockerImageApiService;
import help.lixin.docker.service.impl.DockerImageApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(DockerProperties.class)
public class DockerConfig {

    @Bean
    @ConditionalOnMissingBean(name = "dockerClientConfig")
    public DefaultDockerClientConfig dockerClientConfig(DockerProperties dockerProperties) {
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
        // TODO lixin 留一个Customizer
        return config;
    }


    @Bean
    @ConditionalOnMissingBean(name = "dockerHttpClient")
    public DockerHttpClient dockerHttpClient(DockerProperties dockerProperties, //
                                             @Autowired @Qualifier("dockerClientConfig") DefaultDockerClientConfig dockerClientConfig) {
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                //
                .dockerHost(dockerClientConfig.getDockerHost())
                //
                .sslConfig(dockerClientConfig.getSSLConfig())
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
    @ConditionalOnMissingBean(name = "dockerClient")
    public DockerClient dockerClient(DefaultDockerClientConfig dockerClientConfig, //
                                     @Autowired @Qualifier("dockerHttpClient") DockerHttpClient dockerHttpClient) {
        // TODO lixin 留一个Customizer
        return DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDockerImageApiService dockerImageApiService(@Autowired @Qualifier("dockerClientConfig") DefaultDockerClientConfig dockerClientConfig, //
                                                        @Autowired @Qualifier("dockerClient") DockerClient dockerClient) {
        return new DockerImageApiService(dockerClientConfig, dockerClient);
    }

    @Bean
    @ConditionalOnMissingBean(name = "dockerFaceService")
    public DockerFaceService dockerFaceService(DockerProperties dockerProperties, //
                                               IDockerImageApiService dockerImageApiService, //
                                               IExpressionService expressionService) {
        DockerFaceService dockerFaceService = new DockerFaceService(dockerProperties, dockerImageApiService, expressionService);
        return dockerFaceService;
    }
}
