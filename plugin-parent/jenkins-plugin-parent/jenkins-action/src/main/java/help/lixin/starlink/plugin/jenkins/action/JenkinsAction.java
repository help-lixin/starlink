package help.lixin.starlink.plugin.jenkins.action;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.log.Log;
import help.lixin.core.log.model.LogEntryBuild;
import help.lixin.core.pipeline.PipelineContextHolder;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.action.thread.ActionThread;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.action.domain.JenkinsJobActionParams;
import help.lixin.starlink.plugin.jenkins.action.domain.scm.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.action.domain.setups.*;
import help.lixin.starlink.plugin.jenkins.api.service.impl.JobService;
import help.lixin.starlink.plugin.jenkins.constants.BuildConstants;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.BuildDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.setup.*;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsJobService;

public class JenkinsAction implements Action {
    private Logger logger = LoggerFactory.getLogger(JenkinsAction.class);

    private static final String JOB_NAME = "JOB_NAME";
    private static final String _JENKINS = "_jenkins";

    private static final String FAILURE = "FAILURE";
    private static final String SUCCESS = "SUCCESS";

    public static final String JENKINS_ACTION = "jenkins";

    private String sourceDirectory;

    private final IExpressionService expressionService;

    private AbstractServiceFactory jenkinsServiceFactory;

    private IJenkinsJobService jenkinsJobService;

    public JenkinsAction(String sourceDirectory, //
        AbstractServiceFactory jenkinsServiceFactory, //
        IJenkinsJobService jenkinsJobService, //
        IExpressionService expressionService) {
        this.sourceDirectory = sourceDirectory;
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsJobService = jenkinsJobService;
        this.expressionService = expressionService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始运行插件:[{}]", this.getClass().getName());
        // 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JenkinsJobActionParams actionParams = mapper.readValue(stageParams, JenkinsJobActionParams.class);
        String instanceCode = actionParams.getInstanceCode();
        JenkinsJobFormDTO dto = new JenkinsJobFormDTO();
        dto.setInstanceCode(actionParams.getInstanceCode());

        ScmType scmType = actionParams.getScmType();
        if (null != scmType) {
            dto.setScmType(scmType);
        }

        ToolsType toolsType = actionParams.getToolsType();
        if (null != toolsType) {
            dto.setToolsType(toolsType);
        }

        String jdkId = actionParams.getJdkId();
        if (null != jdkId) {
            dto.setJdkId(jdkId);
        }

        // scm
        JenkinsPipelineScm scm = actionParams.getScm();
        if (null != scm) {
            JenkinsPipelineScmDTO scmDTO = new JenkinsPipelineScmDTO();
            scmDTO.setUrl(scm.getUrl());
            scmDTO.setBranch(scm.getBranch());
            scmDTO.setCredentialsId(scm.getCredentialId());
            dto.setScm(scmDTO);
        }

        // job name进行独特处理
        String jobName = actionParams.getJobName();
        if (null == jobName && null != scm) {
            String projectName = getProjectName(scm.getUrl());
            if (ScmType.GIT.equals(scmType)) {
                String branch = scm.getBranch();
                if (null != branch) {
                    String[] branchSpices = branch.split("/");
                    if (branchSpices.length > 0) {
                        branch = branchSpices[branchSpices.length - 1];
                    }
                }
                dto.setJobName(String.format("%s-%s", projectName, branch));
            } else if (ScmType.SVN.equals(scmType)) {
                dto.setJobName(String.format("%s", projectName));
            }
        }

        // setups
        List<JenkinsPipelineSetupCommon> setups = actionParams.getSetups();
        if (null != setups && !setups.isEmpty()) {
            List<JenkinsPipelineSetupCommonDTO> setupDtos = setups //
                .stream() //
                .map((vo) -> {
                    JenkinsPipelineSetupCommonDTO setupDto = null;
                    if (vo instanceof JenkinsPipelineSetupAnt) {
                        JenkinsPipelineSetupAnt antVo = (JenkinsPipelineSetupAnt)vo;

                        JenkinsPipelineSetupAntDTO antDTO = new JenkinsPipelineSetupAntDTO();
                        antDTO.setInstanceCode(instanceCode);
                        antDTO.setSetupType(antVo.getSetupType());
                        antDTO.setSequence(antDTO.getSequence());
                        antDTO.setAntId(antVo.getAntId());
                        antDTO.setTargets(antVo.getTargets());
                        return antDTO;

                    } else if (vo instanceof JenkinsPipelineSetupGo) {
                        JenkinsPipelineSetupGo goVo = (JenkinsPipelineSetupGo)vo;

                        JenkinsPipelineSetupGoDTO goDto = new JenkinsPipelineSetupGoDTO();
                        goDto.setInstanceCode(instanceCode);
                        goDto.setSetupType(goVo.getSetupType());
                        goDto.setSequence(goVo.getSequence());
                        goDto.setGoId(goVo.getGoId());
                        goDto.setScript(goVo.getScript());
                        goDto.setInstanceCode(instanceCode);

                        return goDto;
                    } else if (vo instanceof JenkinsPipelineSetupGradle) {
                        JenkinsPipelineSetupGradle gradleVo = (JenkinsPipelineSetupGradle)vo;

                        JenkinsPipelineSetupGradleDTO gradleDTO = new JenkinsPipelineSetupGradleDTO();
                        gradleDTO.setInstanceCode(instanceCode);
                        gradleDTO.setSetupType(gradleVo.getSetupType());
                        gradleDTO.setSequence(gradleVo.getSequence());
                        gradleDTO.setGradleId(gradleVo.getGradleId());
                        gradleDTO.setTask(gradleVo.getTask());
                        return gradleDTO;
                    } else if (vo instanceof JenkinsPipelineSetupMaven) {
                        JenkinsPipelineSetupMaven mavenVo = (JenkinsPipelineSetupMaven)vo;

                        JenkinsPipelineSetupMavenDTO mavenDTO = new JenkinsPipelineSetupMavenDTO();
                        mavenDTO.setInstanceCode(instanceCode);
                        mavenDTO.setSetupType(mavenVo.getSetupType());
                        mavenDTO.setMavenId(mavenVo.getMavenId());
                        mavenDTO.setGoals(mavenVo.getGoals());
                        mavenDTO.setSequence(mavenVo.getSequence());

                        return mavenDTO;
                    } else if (vo instanceof JenkinsPipelineSetupNodejs) {
                        JenkinsPipelineSetupNodejs nodejsVo = (JenkinsPipelineSetupNodejs)vo;

                        JenkinsPipelineSetupNodejsDTO nodejsDTO = new JenkinsPipelineSetupNodejsDTO();
                        nodejsDTO.setInstanceCode(instanceCode);
                        nodejsDTO.setSetupType(nodejsVo.getSetupType());
                        nodejsDTO.setSequence(nodejsVo.getSequence());
                        nodejsDTO.setNodejsId(nodejsVo.getNodejsId());
                        nodejsDTO.setScript(nodejsVo.getScript());
                        return nodejsDTO;
                    } else if (vo instanceof JenkinsPipelineSetupPython) {
                        JenkinsPipelineSetupPython pythonVo = (JenkinsPipelineSetupPython)vo;

                        JenkinsPipelineSetupPythonDTO pythonDTO = new JenkinsPipelineSetupPythonDTO();
                        pythonDTO.setInstanceCode(instanceCode);
                        pythonDTO.setSetupType(pythonVo.getSetupType());
                        pythonDTO.setSequence(pythonVo.getSequence());
                        pythonDTO.setPythonId(pythonVo.getPythonId());
                        pythonDTO.setScript(pythonVo.getScript());
                        return pythonDTO;
                    } else if (vo instanceof JenkinsPipelineSetupShell) {
                        JenkinsPipelineSetupShell shellVo = (JenkinsPipelineSetupShell)vo;

                        JenkinsPipelineSetupShellDTO shellDTO = new JenkinsPipelineSetupShellDTO();
                        shellDTO.setInstanceCode(instanceCode);
                        shellDTO.setSetupType(shellVo.getSetupType());
                        shellDTO.setSequence(shellVo.getSequence());
                        shellDTO.setShellScript(shellVo.getShellScript());
                        return shellDTO;
                    }
                    return setupDto;
                }) //
                .collect(Collectors.toList());
            dto.setSetups(setupDtos);
        }

        // 1. 创建job
        Long jobId = jenkinsJobService.saveJob(dto);
        // 2. 触发构建job
        BuildDTO buildDTO = new BuildDTO();
        buildDTO.setInstanceCode(instanceCode);
        buildDTO.setJobId(jobId);
        Integer buildNumber = jenkinsJobService.buildJob(buildDTO);

        // 开启一个线程去实现拉取日志
        FetchJobLogThread fetchJobLogThread = new FetchJobLogThread( //
            PipelineContextHolder.get(), //
            jenkinsServiceFactory, //
            instanceCode, //
            dto.getJobName(), //
            buildNumber //
        );
        fetchJobLogThread.setName(String.format("jenkins-log-%s-%s-%d", dto.getJobName(), jobId, buildNumber));
        fetchJobLogThread.setDaemon(true);
        fetchJobLogThread.start();

        // 开启一个线程去验证是否构建完成.
        CountDownLatch fetchJobBuildStatusLatch = new CountDownLatch(1);
        AtomicReference<String> buildStatus = new AtomicReference<>(BuildConstants.BUILDING);
        // 3. 创建一个线程,去验证是否构建成功.
        Thread fetchJobStatusThread = new FetchJobStatusThread( //
            PipelineContextHolder.get(), //
            jenkinsServiceFactory, //
            dto.getJobName(), //
            buildNumber, //
            instanceCode, //
            fetchJobBuildStatusLatch, //
            buildStatus);
        fetchJobStatusThread.setName(String.format("jenkins-status-%s-%s-%d", dto.getJobName(), jobId, buildNumber));
        fetchJobStatusThread.setDaemon(true);
        fetchJobStatusThread.start();
        // 等待的最大时间为分钟.
        fetchJobBuildStatusLatch.await(actionParams.getExecuteMaxWaitTime(), TimeUnit.MINUTES);

        // 下载回来之后的源码目录(要指定到${jenkins}/workspace)
        String repositoryWorkspace = String.format("%s%s", sourceDirectory, dto.getJobName());
        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_WORKSPACE, repositoryWorkspace);
        ctx.addVar(Constant.Project.PROJECT_NAME, dto.getJobName());
        ctx.addVar(Constant.BuildInfo.BUILD_NUMBER, buildNumber);
        logger.info("jenkins项目工程目录:[{}]", repositoryWorkspace);
        return BuildConstants.SUCCESS.equals(buildStatus.get()) ? true : false;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/jenkins-meta.json";
        Resource resource = new ClassPathResource(pluginMetaPath);
        if (resource.exists()) {
            try {
                meta = IOUtils.toString(resource.getInputStream(), "UTF-8");
            } catch (IOException ignore) {
                logger.warn("读取插件元配置文件[{}]出现异常,异常详细如下:[\n{},\n]", pluginMetaPath, ignore);
            }
        }
        return meta;
    }

    protected String getProjectName(String urlString) throws Exception {
        String result = "";
        try {
            String[] paths = urlString.replaceAll(".git", "").split("/");
            if (paths.length > 0) {
                result = paths[paths.length - 1];
            }
        } catch (Exception e) {
            String msg = String.format("从URL:[%s]中,解析出项目名称失败,失败原因:[\n%s\n]", urlString, e.getMessage());
            logger.error(msg);
            throw new Exception(msg);
        }
        return result;
    }

    @Override
    public String name() {
        return JENKINS_ACTION;
    }
}

class FetchJobLogThread extends ActionThread {
    private Logger logger = LoggerFactory.getLogger(FetchJobLogThread.class);

    private String optionalFolderPath = null;
    private AbstractServiceFactory jenkinsServiceFactory;
    private String instanceCode;
    private String jobName;
    private int buildNumber;
    private int start = 0;

    public FetchJobLogThread(PipelineContext ctx, //
        AbstractServiceFactory jenkinsServiceFactory, //
        String instanceCode, //
        String jobName, //
        int buildNumber) {
        this(ctx, jenkinsServiceFactory, instanceCode, jobName, buildNumber, 0);
    }

    public FetchJobLogThread(PipelineContext ctx, //
        AbstractServiceFactory jenkinsServiceFactory, //
        String instanceCode, //
        String jobName, //
        int buildNumber, //
        int start) {
        super(ctx);
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.instanceCode = instanceCode;
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.start = start;
    }

    public void setOptionalFolderPath(String optionalFolderPath) {
        this.optionalFolderPath = optionalFolderPath;
    }

    @Override
    public void runInternal() {
        int maxCount = 100;
        int count = 0;
        JobService jobService = jenkinsServiceFactory.getInstance(instanceCode, JobService.class);
        while (true) {
            ProgressiveText progressiveText = jobService.lookBuildLog(null, jobName, buildNumber, start);
            if (null == progressiveText) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception ignore) {
                }
                count++;
                if (count < maxCount) {
                    continue;
                } else {
                    break;
                }
            }
            start = progressiveText.size();
            String text = progressiveText.text();
            if (null != text && //
                text.trim().length() > 0) {
                // 记录日志
                Log.trace(new LogEntryBuild() //
                    .withBody(text.trim()) //
                    .build());
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignore) {
            }
            boolean hasMoreData = progressiveText.hasMoreData();
            if (!hasMoreData) {
                break;
            }
        }
    }
}

class FetchJobStatusThread extends ActionThread {
    private Logger logger = LoggerFactory.getLogger(FetchJobStatusThread.class);

    private final AbstractServiceFactory jenkinsServiceFactory;
    private final String jobName;

    private final Integer buildNumber;

    private final String instanceCode;

    private final CountDownLatch latch;
    private final AtomicReference<String> buildStatus;

    public FetchJobStatusThread(PipelineContext ctx, //
        AbstractServiceFactory jenkinsServiceFactory, //
        String jobName, //
        Integer buildNumber, //
        String instanceCode, //
        CountDownLatch latch, //
        AtomicReference<String> buildStatus //
    ) {
        super(ctx);
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.instanceCode = instanceCode;
        this.latch = latch;
        this.buildStatus = buildStatus;
    }

    @Override
    public void runInternal() {
        // 如果状态是构建中,则一直循环,直到job状态为成功/失败为止
        while (BuildConstants.BUILDING.equals(buildStatus.get())) {
            try {
                JobService jobService = jenkinsServiceFactory.getInstance(instanceCode, JobService.class);
                // 调用jenkins,获取job构建的信息
                BuildInfo buildInfo = jobService.getBuildInfo(null, jobName, buildNumber);
                if (null != buildInfo && null != buildInfo.result()) {
                    buildStatus.compareAndSet(BuildConstants.BUILDING, buildInfo.result());
                    if (buildStatus.get().equals(BuildConstants.SUCCESS) //
                        || buildStatus.get().equals(BuildConstants.FAILURE)) {
                        latch.countDown();
                        break;
                    }
                }
            } catch (Exception e) {
                logger.warn("从实例[{}],获取任务名称:[{}] -- [{}],出现异常,异常信息如下:[{}]", instanceCode, jobName, buildNumber, e);
            }
            // 不论成功还是出现了异常失败,都要进行休眠.
            try {
                // 休息30秒
                TimeUnit.SECONDS.sleep(30);
            } catch (Exception igsleep) {
            }
        }
    }
}
