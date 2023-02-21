package help.lixin.shell;

import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.shell.action.ShellAction;
import help.lixin.shell.config.ShellActionConfig;
import help.lixin.shell.config.ShellConfig;
import org.apache.commons.io.IOUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellActionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void initApplication() {
        applicationContext = new AnnotationConfigApplicationContext(ShellConfig.class, ShellActionConfig.class);
    }

    @Test
    public void execute() throws Exception {
        List<String> artifacts = new ArrayList<>();
        artifacts.add("/Users/lixin/GitRepository/spring-web-demo/target/spring-web-demo-1.1.0.jar");

        StagePipelineContext context = new StagePipelineContext();
        context.addVar("__artifact", artifacts);
        context.addVar("artifactPath", "/Users/lixin/GitRepository/spring-web-demo/target/");
        context.addVar("dockerfile", "/Users/lixin/GitRepository/spring-web-demo/target/Dockerfile");
        context.addVar("projectName", "spring-web-demo");
        context.addVar("version", "1.1.0");

        context.setStageParams("{ \"cmds\": [  \" cd ${artifactPath}  \" , \" pwd  \" , \" docker build -f ${dockerfile} --build-arg APP_FILE=${__artifact}  -t ${projectName}:v${version} . \"  ] }");

        ShellAction shellAction = applicationContext.getBean(ShellAction.class);
        shellAction.execute(context);
        System.out.println();
    }

    @Test
    public void testEl() throws Exception {
        Map map = new HashMap();
        map.put("__artifactPath", "/Users/lixin/GitRepository/spring-web-demo/target/");

        String cmd = " cd  ${__artifactPath} ";
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        GroupTemplate gt = new GroupTemplate(resourceLoader, Configuration.defaultConfiguration());
        Template t = gt.getTemplate(cmd);
        t.binding(map);
        String targetCmd = t.render();
        System.out.println(targetCmd);
    }

    @Test
    public void testBuildImage() throws Exception {
        // **************************************************************
        // 切记:所有的命令之间是不允许有空格的.
        // **************************************************************
//        String[] command = new String[]{
//                "docker",
//                "build",
//                "-f",
//                "./Dockerfile",
//                "--build-arg",
//                "APP_FILE=spring-web-demo-1.1.0.jar",
//                "-t",
//                "spring-web-demo:v1.0.0",
//                "."
//        };
        String[] command = new String[]{"/bin/bash",
                //
                "-c",
                //
                "cd /Users/lixin/GitRepository/spring-web-demo/target && docker build -f ./Dockerfile --build-arg APP_FILE=spring-web-demo-1.1.0.jar -t spring-web-demo:v1.0.0 . && docker login 103.215.125.86:3080 -u admin -p  && docker tag spring-web-demo:v1.0.0  103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0.0 && docker push 103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0.0"};
        ProcessBuilder cmdBuilder = new ProcessBuilder(command);
        // 指定工作目录
        // cmdBuilder.directory(new File("/Users/lixin/GitRepository/spring-web-demo/target"));
        Process process = cmdBuilder.start();
        int errorCode = process.waitFor();

        InputStream inputStream = process.getInputStream();
        List<String> successLines = IOUtils.readLines(inputStream, "UTF-8");
        Assert.assertEquals(true, errorCode == 0);
    }
}
