package help.lixin.mvn.compile.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.artifact.ArtifactInfo;
import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.mvn.compile.action.entity.MavenSourceCompileParams;
import help.lixin.mvn.compile.service.DockerMvnFaceService;
import org.apache.tools.ant.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 参考:https://github.com/AliyunContainerService/maven-image
 */
public class DockerMavenCompileAction implements Action {
    private Logger logger = LoggerFactory.getLogger(DockerMavenCompileAction.class);

    private static final String DOCKER_MAVEN_CONTAINER_WORK_DIR = "/usr/src/mymaven";

    private static final List<String> CMDS = Arrays.asList("mvn", "clean", "install", "-DskipTests", "-X");

    private static final String MAVEN_IMAGE = "registry.cn-hangzhou.aliyuncs.com/acs/maven";

    private static final String ARCHIVE_ARTIFACTS = "target/*.jar";

    public static final String MAVEN_COMPILE_ACTION = "docker-mvn-compile";
    private DockerMvnFaceService dockerMvnFaceService;

    public DockerMavenCompileAction(DockerMvnFaceService dockerMvnFaceService) {
        this.dockerMvnFaceService = dockerMvnFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始执行maven源码编译插件");

        String stageParams = ctx.getStageParams();
        if (null == stageParams) {
            stageParams = "{}";
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> context = ctx.getVars();
        MavenSourceCompileParams mavenSourceCompileParams = mapper.readValue(stageParams, MavenSourceCompileParams.class);
        // 源代码目录(requirce)
        String sourceDir = processSourceDir(mavenSourceCompileParams.getSourceDir(), context);
        // 基础镜像
        String image = expression(mavenSourceCompileParams.getImage(), context);
        // 项目名称(requirce)
        String projectName = processProjectName(mavenSourceCompileParams.getProjectName(), context);
        // binds
        Map<String, String> binds = processBinds(mavenSourceCompileParams.getBinds(), context);
        // cmds
        List<String> cmds = processCmds(mavenSourceCompileParams.getCmds(), context);
        // workingDir
        String workingDir = expression(mavenSourceCompileParams.getWorkingDir(), context);
        // 构建完成后:成品库路径表达式(requirce)
        String archiveArtifacts = expression(mavenSourceCompileParams.getArchiveArtifacts(), context);

        if (null == image) {
            image = MAVEN_IMAGE;
        }

        if (binds.isEmpty()) {
            binds.put(sourceDir, DOCKER_MAVEN_CONTAINER_WORK_DIR);
        }

        if (null == workingDir) {
            workingDir = DOCKER_MAVEN_CONTAINER_WORK_DIR;
        }

        if (cmds.isEmpty()) {
            cmds.addAll(CMDS);
        }

        if (null == archiveArtifacts) {
            archiveArtifacts = ARCHIVE_ARTIFACTS;
        }

        // 容器名称
        String containerUniqueName = genContainerUniqueName(sourceDir, projectName);

        String containerId = dockerMvnFaceService.getContainerService() //
                .mvnCompile(containerUniqueName, image, workingDir, binds, cmds);

        ArtifactInfo artifactInfo = getArtifact(sourceDir, archiveArtifacts);
        ctx.getVars().put(Constant.Artifact.ARTIFACT_DIR, artifactInfo.getArtifactDir());
        ctx.getVars().put(Constant.Artifact.ARTIFACT_NAME, artifactInfo.getArtifactFileName());
        ctx.getVars().put(Constant.Artifact.ARTIFACT_FULL_PATH, artifactInfo.getArtifactFullName());
        logger.info("制品库下载后的目录:[{}]", ctx.getVars().get(Constant.Artifact.ARTIFACT_DIR));
        logger.info("制品库下载后的文件名:[{}]", ctx.getVars().get(Constant.Artifact.ARTIFACT_NAME));
        logger.info("制品库下载后的绝对路径:[{}]", ctx.getVars().get(Constant.Artifact.ARTIFACT_FULL_PATH));

        logger.info("maven源码编译插件执行结束");
        return true;
    }


    protected ArtifactInfo getArtifact(String sourceDir, String... includes) throws Exception {
        ArtifactInfo artifactInfo = null;

        DirectoryScanner ds = new DirectoryScanner();
        ds.setIncludes(includes);
        ds.setBasedir(new File(sourceDir));
        ds.setCaseSensitive(true);
        ds.scan();


        String[] includedFiles = ds.getIncludedFiles();
        if (includedFiles.length > 0) {
            String includedFile = includedFiles[0];
            String dest = String.format("%s%s%s", sourceDir, File.separator, includedFile);

            File testFile = new File(dest);
            if (testFile.exists()) {
                artifactInfo = new ArtifactInfo();
                artifactInfo.setArtifactFullName(dest);
            } else {
                String msg = String.format("获取制品库文件:[%s]出错,文件不存在", dest);
                throw new RuntimeException(msg);
            }
        }
        return artifactInfo;
    }


    protected String genContainerUniqueName(String sourceDir, String projectName) {
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(sourceDir.getBytes());
        long projectUniqueId = crc32.getValue();
        String containerUniqueName = String.format("%s-%d", projectName, projectUniqueId);
        return containerUniqueName;
    }


    protected List<String> processCmds(List<String> cmds, Map<String, Object> ctx) {
        List<String> cmdList = new ArrayList<>(0);
        if (null != cmds && !cmds.isEmpty()) {
            List<String> list = cmds.stream().map(item -> {
                return expression(item, ctx);
            }).collect(Collectors.toList());
            cmdList.addAll(list);
        }
        return cmdList;
    }

    protected String processProjectName(String projectName, Map<String, Object> ctx) {
        if (null != projectName) {
            return expression(projectName, ctx);
        } else {
            return (String) ctx.get("projectName");
        }
    }

    protected String processSourceDir(String sourceDir, Map<String, Object> ctx) {
        if (null != sourceDir) {
            return expression(sourceDir, ctx);
        } else {
            return (String) ctx.get(Constant.CodeRepository.WORKSPACE_DIR);
        }
    }

    protected Map<String, String> processBinds(Map<String, String> binds, Map<String, Object> ctx) {
        Map<String, String> bindMap = new HashMap<>();
        if (null != binds && !binds.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = binds.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = expression(entry.getKey(), ctx);
                String value = expression(entry.getValue(), ctx);
                bindMap.put(key, value);
            }
        }
        return bindMap;
    }


    protected String expression(String template, Map<String, Object> ctx) {
        String value = dockerMvnFaceService.getExpressionService().prase(template, ctx);
        return value;
    }

    @Override
    public String name() {
        return MAVEN_COMPILE_ACTION;
    }
}