package help.lixin.starlink.plugin.jenkins.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.JobInfo;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.jenkins.api.model.CreateJobContext;
import help.lixin.starlink.plugin.jenkins.api.model.CredentialEnum;
import help.lixin.starlink.plugin.jenkins.api.model.TriggerBuildContext;
import help.lixin.starlink.plugin.jenkins.api.service.IJobService;
import help.lixin.starlink.plugin.jenkins.api.service.IKeyManagerService;
import help.lixin.starlink.plugin.jenkins.api.service.impl.JobService;
import help.lixin.starlink.plugin.jenkins.convert.JobServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.*;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildDependencyDTO;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.BuildDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDetailDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobPageListDTO;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsJobMapper;
import help.lixin.starlink.plugin.jenkins.service.*;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.FreeStyleProjectGenerateXmlFace;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 8:40 下午
 * @Description
 */
public class JenkinsJobService implements IJenkinsJobService {
    private Logger logger = LoggerFactory.getLogger(JenkinsJobService.class);
    private final AbstractServiceFactory jenkinsServiceFactory;

    private QueryTemplate queryTemplate;

    private IJenkinsParamsService jenkinsParamsService;
    private IJenkinsPipelineScmService jenkinsPipelineScmService;
    private IJenkinsBuildDependencyService jenkinsBuildDependencyService;
    private IJenkinsPipelineSetupCommonService jenkinsPipelineSetupCommonService;
    private IJenkinsBuildInfoService jenkinsBuildInfoService;

    private JenkinsJobMapper jenkinsJobMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Long saveJob(JenkinsJobFormDTO jenkinsJobFormDTO) {
        // 组装xml，返回xml信息
        String xmlContext = xmlPackage(jenkinsJobFormDTO);
        if (logger.isDebugEnabled()) {
            logger.debug("jenkins XML:{}", xmlContext);
        }
        jenkinsJobFormDTO.setXmlContent(xmlContext);

        JenkinsJob jenkinsJob = saveFormData(jenkinsJobFormDTO);
        Long jobId = jenkinsJob.getId();

        // 保存相关组件信息
        saveParams(jenkinsJobFormDTO, jobId);
        saveScm(jenkinsJobFormDTO, jobId);
        saveDependency(jenkinsJobFormDTO, jobId);
        jenkinsPipelineSetupCommonService.saveList(jenkinsJobFormDTO.getSetups(), jobId,
            jenkinsJobFormDTO.getCreateBy());

        String instanceCode = jenkinsJobFormDTO.getInstanceCode();
        JobService jobService = jenkinsServiceFactory.getInstance(instanceCode, JobService.class);
        JobInfo jenkinsJobInfoObject = jobService.getJobInfo(null, jenkinsJobFormDTO.getJobName());
        if (null != jenkinsJobInfoObject) {
            boolean updateResult =
                updateJob(jenkinsJobFormDTO.getJobName(), xmlContext, jenkinsJobFormDTO.getInstanceCode());
            // 更新失败抛异常
            if (!updateResult) {
                throw new ServiceException("执行更新任务失败");
            }
        } else {
            // 创建job，返回创建结果
            RequestStatus jobResult =
                createJob(jenkinsJobFormDTO.getJobName(), xmlContext, jenkinsJobFormDTO.getInstanceCode());
            if (jobResult != null && !jobResult.value()) {
                throw new ServiceException("执行创建任务失败:" + jobResult.errors());
            }
        }
        return jobId;
    }

    private void saveDependency(JenkinsJobFormDTO jenkinsJobFormDTO, Long jobId) {
        List<String> buildDependencys = jenkinsJobFormDTO.getBuildDependencys();
        if (buildDependencys.size() > 0) {
            JenkinsBuildDependencyDTO jenkinsBuildDependencyDTO = new JenkinsBuildDependencyDTO();
            jenkinsBuildDependencyDTO.setCreateBy(jenkinsJobFormDTO.getCreateBy());
            jenkinsBuildDependencyDTO.setJobId(jobId);
            jenkinsBuildDependencyDTO.setJobName(String.join(",", buildDependencys));
            jenkinsBuildDependencyDTO.setStatus(1);
            jenkinsBuildDependencyDTO.setInstanceCode(jenkinsJobFormDTO.getInstanceCode());
            jenkinsBuildDependencyService.saveDependency(jenkinsBuildDependencyDTO);
        }
    }

    private void saveScm(JenkinsJobFormDTO jenkinsJobFormDTO, Long jobId) {
        JenkinsPipelineScmDTO scm = jenkinsJobFormDTO.getScm();
        scm.setId(jobId);
        scm.setCreateBy(jenkinsJobFormDTO.getCreateBy());
        jenkinsPipelineScmService.saveScm(scm);
    }

    private void saveParams(JenkinsJobFormDTO jenkinsJobFormDTO, Long jobId) {
        List<JenkinsParamDTO> params = jenkinsJobFormDTO.getParams();
        jenkinsParamsService.cleanParamsByJobId(jobId);
        params.forEach(v -> {
            v.setJobId(jobId);
            v.setCreateBy(jenkinsJobFormDTO.getCreateBy());
            jenkinsParamsService.saveParams(v);
        });
    }

    /**
     * @Param jenkinsJobFormDTO :
     * @Author: 伍岳林
     * @Date: 2023/12/15 6:18 下午
     * @Return: java.lang.Integer
     * @Description 保存任务表单信息
     * @return
     */
    private JenkinsJob saveFormData(JenkinsJobFormDTO jenkinsJobFormDTO) {
        JobServiceConvert mapper = Mappers.getMapper(JobServiceConvert.class);
        JenkinsJob jenkinsJob = mapper.convert(jenkinsJobFormDTO);

        JenkinsJob jobInfo =
            jenkinsJobMapper.queryJob(jenkinsJobFormDTO.getJobName(), jenkinsJobFormDTO.getInstanceCode());
        if (null == jobInfo) { // 新增
            jenkinsJob.setUpdateBy(jenkinsJobFormDTO.getCreateBy());
            jenkinsJob.setCreateTime(new Date());
            jenkinsJob.setUpdateTime(new Date());
            jenkinsJobMapper.insertSelective(jenkinsJob);
        } else { // 更新
            jenkinsJob.setId(jobInfo.getId());
            jenkinsJob.setCreateBy(jobInfo.getCreateBy());
            jenkinsJob.setCreateTime(jobInfo.getCreateTime());
            jenkinsJob.setUpdateBy(jenkinsJobFormDTO.getCreateBy());
            jenkinsJob.setUpdateTime(new Date());

            jenkinsJobMapper.updateById(jenkinsJob);
        }
        return jenkinsJob;
    }

    private String xmlPackage(JenkinsJobFormDTO jenkinsJobFormDTO) {
        FreeStyleProjectGenerateXmlFace xmlFace = jenkinsServiceFactory.getInstance(jenkinsJobFormDTO.getInstanceCode(),
            FreeStyleProjectGenerateXmlFace.class);
        return xmlFace.generateXml(jenkinsJobFormDTO);
    }

    private RequestStatus createJob(String jobName, String xmlContext, String instanceCode) {
        JobService jobService = jenkinsServiceFactory.getInstance(instanceCode, JobService.class);
        return jobService.createJob(createJobContext(jobName, xmlContext));
    }

    private boolean updateJob(String jobName, String xmlContext, String instanceCode) {
        JobService jobService = jenkinsServiceFactory.getInstance(instanceCode, JobService.class);
        return jobService.updateJob(createJobContext(jobName, xmlContext));
    }

    private CreateJobContext createJobContext(String jobName, String xmlContext) {
        CreateJobContext createJobContext = new CreateJobContext();

        createJobContext.setJobName(jobName);
        createJobContext.setConfigXML(xmlContext);
        return createJobContext;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsJob jenkinsJob = jenkinsJobMapper.selectById(id);
        if (jenkinsJob == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsJob.setStatus(status);
        jenkinsJob.setUpdateBy(updateBy);
        jenkinsJob.setUpdateTime(new Date());

        return jenkinsJobMapper.updateById(jenkinsJob);
    }

    @Override
    public JenkinsJob queryJobInfo(Long id) {
        return jenkinsJobMapper.selectById(id);
    }

    @Override
    public JenkinsJobFormDetailDTO queryJobDetail(Long jobId) {
        JenkinsJob jenkinsJob = jenkinsJobMapper.selectById(jobId);

        if (jenkinsJob == null) {
            throw new ServiceException("该数据不存在");
        }
        JobServiceConvert mapper = Mappers.getMapper(JobServiceConvert.class);
        JenkinsJobFormDetailDTO jenkinsJobFormDetailDTO = mapper.convert(jenkinsJob);

        // setup数据
        List<JenkinsPipelineSetupCommon> jenkinsPipelineSetupCommons =
            jenkinsPipelineSetupCommonService.querySetupList(jobId);
        jenkinsJobFormDetailDTO.setSetups(
            CollectionUtils.isEmpty(jenkinsPipelineSetupCommons) ? new ArrayList<>() : jenkinsPipelineSetupCommons);

        // 参数数据
        List<JenkinsParams> jenkinsParams = jenkinsParamsService.queryParamList(jobId);
        jenkinsJobFormDetailDTO.setParams(CollectionUtils.isEmpty(jenkinsParams) ? new ArrayList<>() : jenkinsParams);

        // 远程仓库数据
        JenkinsPipelineScm jenkinsPipelineScm = jenkinsPipelineScmService.queryScmInfo(jobId);
        jenkinsJobFormDetailDTO.setScm(jenkinsPipelineScm == null ? new JenkinsPipelineScm() : jenkinsPipelineScm);

        // 构建依赖顺序
        JenkinsBuildDependency jenkinsBuildDependency = jenkinsBuildDependencyService.queryByJobId(jobId);
        if (jenkinsBuildDependency == null) {
            jenkinsJobFormDetailDTO.setBuildDependencys(new ArrayList<>());
        } else {
            List<String> dependencys = Arrays.stream(jenkinsBuildDependency.getJobName().split(","))
                .filter((v) -> StringUtils.isNotBlank(v)).collect(Collectors.toList());
            jenkinsJobFormDetailDTO.setBuildDependencys(dependencys);
        }

        return jenkinsJobFormDetailDTO;
    }

    @Override
    public PageResponse<JenkinsJob> pageList(JenkinsJobPageListDTO jenkinsJobPageListDTO) {
        return queryTemplate.execute(jenkinsJobPageListDTO, () -> {
            jenkinsJobMapper.pageList(jenkinsJobPageListDTO);
        });
    }

    @Override
    public List<String> queryJobOptionList(String instanceCode) {
        QueryWrapper<JenkinsJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("instance_code", instanceCode);
        return jenkinsJobMapper.selectList(queryWrapper).stream().map(JenkinsJob::getJobName)
            .collect(Collectors.toList());
    }

    @Override
    public void checkCredentialsId(String instanceCode, String credentialsId, String path, String jobName) {
        IKeyManagerService instance = jenkinsServiceFactory.getInstance(instanceCode, IKeyManagerService.class);
        instance.checkGitCredentialsId(credentialsId, path, CredentialEnum.GIT, jobName);
    }

    @Override
    public Integer buildJob(BuildDTO buildDTO) {
        JenkinsJob jenkinsJob = jenkinsJobMapper.selectById(buildDTO.getJobId());
        // 校验任务是否存在
        if (jenkinsJob == null) {
            throw new ServiceException("该任务不存在");
        }
        Long jobId = jenkinsJob.getId();

        String jobName = jenkinsJob.getJobName();
        // 根据任务id查询相关参数
        List<JenkinsParams> jenkinsParams = jenkinsParamsService.queryParamList(jobId);

        IJobService jobService = jenkinsServiceFactory.getInstance(buildDTO.getInstanceCode(), IJobService.class);
        Map<String, List<String>> propertiesMap = new HashMap<>();
        // 配置参数
        for (JenkinsParams jenkinsParam : jenkinsParams) {
            propertiesMap.put(jenkinsParam.getParamName(), Arrays.asList(jenkinsParam.getParamValue()));
        }

        // 填充数据
        TriggerBuildContext build = TriggerBuildContext.newBuilder().jobName(jobName).properties(propertiesMap).build();
        // 根据任务名获取任务信息
        JobInfo jobInfo = jobService.getJobInfo(null, jobName);
        if (jobInfo == null) {
            throw new ServiceException("无法从jenkins获取任务信息");
        }
        // 获取构建序列
        Integer buildNumber = jobInfo.nextBuildNumber();
        // 运行构建
        Integer buildResult = jobService.triggerBuild(build).value();
        if (buildResult == null) {
            throw new ServiceException("无法开启构建，构建失败");
        }

        JenkinsBuildInfoDTO jenkinsBuildInfoDTO = new JenkinsBuildInfoDTO();
        jenkinsBuildInfoDTO.setJobId(jenkinsJob.getId());
        jenkinsBuildInfoDTO.setJenkinsLogId(buildNumber);
        jenkinsBuildInfoDTO.setBuildStatus(0);
        jenkinsBuildInfoDTO.setStatus(1);
        jenkinsBuildInfoDTO.setJobName(jenkinsJob.getJobName());
        jenkinsBuildInfoDTO.setInstanceCode(jenkinsJob.getInstanceCode());
        jenkinsBuildInfoDTO.setCreateBy(buildDTO.getCreateBy());
        // 创建构建任务
        jenkinsBuildInfoService.saveBuildIfo(jenkinsBuildInfoDTO);

        return buildNumber;
    }

    @Override
    public Boolean jobNameIsExist(String jobName, String instanceCode) {
        return jenkinsJobMapper.queryByJobName(jobName, instanceCode) == null;
    }

    @Override
    public Boolean removeJob(Long jobId) {
        JenkinsJob jenkinsJob = jenkinsJobMapper.selectById(jobId);
        if (jenkinsJob == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsJob.setIsDel(1);
        jenkinsJobMapper.updateById(jenkinsJob);
        IJobService jobService = jenkinsServiceFactory.getInstance(jenkinsJob.getInstanceCode(), IJobService.class);
        jobService.deleteJob(null, jenkinsJob.getJobName());
        return true;
    }

    public JenkinsJobService(AbstractServiceFactory jenkinsServiceFactory, IJenkinsParamsService jenkinsParamsService,
        IJenkinsPipelineScmService jenkinsPipelineScmService,
        IJenkinsBuildDependencyService jenkinsBuildDependencyService,
        IJenkinsPipelineSetupCommonService jenkinsPipelineSetupCommonService,
        IJenkinsBuildInfoService jenkinsBuildInfoService, JenkinsJobMapper jenkinsJobMapper,
        QueryTemplate queryTemplate) {
        this.jenkinsParamsService = jenkinsParamsService;
        this.jenkinsPipelineScmService = jenkinsPipelineScmService;
        this.jenkinsBuildDependencyService = jenkinsBuildDependencyService;
        this.jenkinsPipelineSetupCommonService = jenkinsPipelineSetupCommonService;
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsBuildInfoService = jenkinsBuildInfoService;
        this.jenkinsJobMapper = jenkinsJobMapper;
        this.queryTemplate = queryTemplate;
    }
}
