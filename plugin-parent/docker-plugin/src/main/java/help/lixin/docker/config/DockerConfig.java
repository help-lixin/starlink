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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(DockerProperties.class)
public class DockerConfig {

    @Bean
    @ConditionalOnMissingBean
    public DefaultDockerClientConfig dockerClientConfig(Environment env, DockerProperties dockerProperties) {
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
    @ConditionalOnMissingBean
    public DockerHttpClient dockerHttpClient(DockerProperties dockerProperties, DefaultDockerClientConfig dockerClientConfig) {
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
    @ConditionalOnMissingBean
    public DockerClient dockerClient(DefaultDockerClientConfig dockerClientConfig, DockerHttpClient dockerHttpClient) {
        // TODO lixin 留一个Customizer
        return DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDockerImageApiService dockerImageApiService(DockerClient dockerClient) {
        return new DockerImageApiService(dockerClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public DockerFaceService dockerFaceService(DockerProperties dockerProperties, //
                                               IDockerImageApiService dockerImageApiService, //
                                               IExpressionService expressionService) {
        DockerFaceService dockerFaceService = new DockerFaceService(dockerProperties, dockerImageApiService, expressionService);
        return dockerFaceService;
    }
}
