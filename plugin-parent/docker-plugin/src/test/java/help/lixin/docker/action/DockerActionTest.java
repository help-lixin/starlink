package help.lixin.docker.action;


import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.docker.config.DockerActionConfig;
import help.lixin.docker.config.DockerConfig;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DockerActionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void initApplication() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("docker.registryUrl", "https://hub.lixin.help");
        properties.put("docker.registryUser", "admin");
        properties.put("docker.registryPwd", getPassword());

        properties.put("docker.host", "unix:///var/run/docker.sock");

        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().getPropertySources().addFirst(new MapPropertySource("test", properties));
        applicationContext.register(DockerConfig.class, DockerActionConfig.class);
        applicationContext.refresh();
    }

    @Test
    public void testActionExecute() throws Exception {
        String params = "{ \"dockerFile\":\"/tmp/spring-web-demo/Dockerfile\" , \"tags\":\"hub.lixin.help/spring-web-demo/spring-web-demo:v1.0-1519\", \"args\":[ { \"key\": \"APP_FILE\" , \"value\":\"spring-web-demo-1.1.0.jar\"  } ] }";
        Action action = applicationContext.getBean(Action.class);
        StagePipelineContext ctx = new StagePipelineContext();
        ctx.setStageParams(params);
        action.execute(ctx);
        System.out.println(action);
    }

    protected String getPassword() throws Exception {
        String tokenPath = "/Users/lixin/harbor.txt";
        // 有这么严重的Bug吗?读取文件时,最后附加上一个回车符.
        String pwd = FileUtils.readFileToString(new File(tokenPath), Charset.forName("ISO-8859-1"));
        pwd = pwd.substring(0, pwd.length() - 1);
        return pwd;
    }
}
