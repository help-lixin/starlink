package help.lixin.shell;

import help.lixin.core.artifact.ArtifactInfo;
import help.lixin.core.constants.Constant;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShellActionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void initApplication() {
        applicationContext = new AnnotationConfigApplicationContext(ShellConfig.class, ShellActionConfig.class);
    }

    @Test
    public void testExecute() throws Exception {
        List<String> artifacts = new ArrayList<>();
        artifacts.add("/Users/lixin/GitRepository/spring-web-demo/target/spring-web-demo-1.1.0.jar");


        ArtifactInfo info = new ArtifactInfo();
        info.setArtifactFullName(artifacts.get(0));


        DateFormat df = new SimpleDateFormat("HHmm");

        StagePipelineContext context = new StagePipelineContext();
        context.addVar("DATETIME", df.format(new Date()));
        // spring-web-demo
        context.addVar(Constant.Artifact.ARTIFACT_NAME, info.getArtifactFileName());
        // /Users/lixin/GitRepository/spring-web-demo/target
        context.addVar(Constant.Artifact.ARTIFACT_DIR, info.getArtifactDir());

        context.addVar("projectName", "spring-web-demo");
        context.addVar("version", "1.1.0");

        context.addVar(Constant.Docker.DOCKER_FILE, "/Users/lixin/GitRepository/spring-web-demo/target/Dockerfile");
        // 仓库信息
        context.addVar(Constant.Repository.REPOSITORY_URL, "103.215.125.86:3080");
        context.addVar(Constant.Repository.REPOSITORY_USERNAME, "admin");
        context.addVar(Constant.Repository.REPOSITORY_PASSWORD, System.getenv().getOrDefault("HARBOR-PWD", ""));

        context.setStageParams("{ \"cmds\": [  \" cd ${ARTIFACT_DIR}  \" , \" docker build -f ${DOCKER_FILE} --build-arg APP_FILE=${ARTIFACT_NAME}  -t ${projectName}:v${version}.${DATETIME} . \" , \" docker login ${REPOSITORY_URL} -u ${REPOSITORY_USERNAME} -p ${REPOSITORY_PASSWORD} \" ,  \"docker tag ${projectName}:v${version}.${DATETIME}  ${REPOSITORY_URL}/${projectName}/${projectName}:v${version}.${DATETIME} \" , \" docker push ${REPOSITORY_URL}/${projectName}/${projectName}:v${version}.${DATETIME} \"   ] }");

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
