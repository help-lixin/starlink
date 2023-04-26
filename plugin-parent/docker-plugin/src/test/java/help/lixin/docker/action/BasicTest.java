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

    protected String registryUrl;
    protected String registryUser;
    protected String registryPwd;

    protected DefaultDockerClientConfig config;

    protected String dockerHost = "tcp://192.168.8.17:2375";


    @Before
    public void init() throws Exception {
        registryUrl = "https://hub.lixin.help";
        registryUser = "admin";
        registryPwd = getPassword();

        config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                // .withDockerHost("unix:///var/run/docker.sock")
                .withDockerHost(dockerHost)
                //
                .withDockerTlsVerify(false)
                // 这里的仓库信息为docker hub信息
//                .withRegistryUrl(registryUrl)
                // 这里的仓库信息为docker hub信息
//                .withRegistryUsername(registryUser)
                // 这里的仓库信息为docker hub信息
//                .withRegistryPassword(registryPwd)
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
                .connectionTimeout(Duration.ofMinutes(1))
                //
                .responseTimeout(Duration.ofMinutes(1))
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
