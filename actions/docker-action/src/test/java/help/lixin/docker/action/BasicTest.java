package help.lixin.docker.action;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.io.FileUtils;
import org.junit.Before;

import java.io.File;
import java.nio.charset.Charset;
import java.time.Duration;

public abstract class BasicTest {

    protected DockerClient dockerClient;

    @Before
    public void init() throws Exception {
        String registryUrl = "http://103.215.125.86:3080";
        String registryUser = "admin";
        String registryPass = getPassword();

        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                //
                .withDockerHost("unix:///var/run/docker.sock")
                //
                .withDockerTlsVerify(false)
                //
                .withRegistryUrl(registryUrl)
                //
                .withRegistryUsername(registryUser)
                //
                .withRegistryPassword(registryPass)
                //
//                .withApiVersion("1.41")
                //
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                //
                .dockerHost(config.getDockerHost())
                //
                .sslConfig(config.getSSLConfig())
                //
                .maxConnections(100)
                //
                .connectionTimeout(Duration.ofSeconds(3000))
                //
                .responseTimeout(Duration.ofSeconds(4500))
                //
                .build();

        this.dockerClient = DockerClientImpl.getInstance(config, httpClient);
    }


    protected String getPassword() throws Exception {
        String tokenPath = "/Users/lixin/harbor.txt";
        // 有这么严重的Bug吗?读取文件时,最后附加上一个回车符.
        String pwd = FileUtils.readFileToString(new File(tokenPath), Charset.forName("ISO-8859-1"));
        pwd = pwd.substring(0, pwd.length() - 1);
        return pwd;
    }
}
