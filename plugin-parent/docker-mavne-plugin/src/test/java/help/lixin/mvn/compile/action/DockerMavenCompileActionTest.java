package help.lixin.mvn.compile.action;

import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.config.ExpressionConfig;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.mvn.compile.config.DockerMavenActionConfig;
import help.lixin.mvn.compile.config.DockerMavenSourceCompileConfig;
import help.lixin.mvn.compile.service.IContainerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DockerMavenCompileActionTest {

    private AnnotationConfigApplicationContext ctx;

    @Before
    public void init() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("mvn.docker.host", "unix:///var/run/docker.sock");

        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getPropertySources().addFirst(new MapPropertySource("test", properties));
        ctx.register(ExpressionConfig.class, DockerMavenSourceCompileConfig.class, DockerMavenActionConfig.class);
        ctx.refresh();
    }

    @Test
    public void testContainerService() throws Exception {
        IContainerService containerService = ctx.getBean(IContainerService.class);
        String containerName = "test";
        String image = "registry.cn-hangzhou.aliyuncs.com/acs/maven";
        String workingDir = "/usr/src/mymaven";

        Map<String, String> bindMap = new HashMap<>();
        bindMap.put("/tmp/583871138", "/usr/src/mymaven");

        // "mvn", "clean", "install"
        List<String> cmds = new ArrayList<>();
        cmds.add("mvn");
        cmds.add("clean");
        cmds.add("install");
        cmds.add("-X");
        containerService.mvnCompile(containerName, image, workingDir, bindMap, cmds);
        System.out.println(containerService);
    }

    @Test
    public void testActionExecute() throws Exception {
        Action action = ctx.getBean("dockerMavenCompileAction", Action.class);
        StagePipelineContext context = new StagePipelineContext();
        context.setStageParams("{}");
        context.addVar(Constant.CodeRepository.WORKSPACE_DIR, "/tmp/583871138");
        context.addVar("projectName", "spring-web-demo");
        boolean execute = action.execute(context);
        Assert.assertEquals(true, execute);
    }
}