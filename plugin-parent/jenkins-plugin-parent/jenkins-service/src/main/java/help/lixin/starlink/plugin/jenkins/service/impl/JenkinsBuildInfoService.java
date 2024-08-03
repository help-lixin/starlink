package help.lixin.starlink.plugin.jenkins.service.impl;

import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.jenkins.convert.BuildInfoServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildInfo;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildPageListDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsBuildInfoMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsBuildInfoService;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 5:01 下午
 * @Description
 */
public class JenkinsBuildInfoService implements IJenkinsBuildInfoService {

    private Logger logger = LoggerFactory.getLogger(JenkinsBuildInfoService.class);
    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsBuildInfoMapper jenkinsBuildInfoMapper;
    private QueryTemplate queryTemplate;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveBuildIfo(JenkinsBuildInfoDTO jenkinsBuildInfoDTO) {
        Long id = jenkinsBuildInfoDTO.getId();

        JenkinsBuildInfo jenkinsBuildDependencyDB =
            jenkinsBuildInfoMapper.queryBuildInfo(jenkinsBuildInfoDTO.getJobName(),
                jenkinsBuildInfoDTO.getJenkinsLogId(), jenkinsBuildInfoDTO.getInstanceCode());
        if (id == null && jenkinsBuildInfoDTO != null) {
            BuildInfoServiceConvert mapper = Mappers.getMapper(BuildInfoServiceConvert.class);
            JenkinsBuildInfo jenkinsBuildInfo = mapper.convert(jenkinsBuildInfoDTO);
            jenkinsBuildInfo.setStartTime(new Date());
            jenkinsBuildInfo.setCreateTime(new Date());
            return jenkinsBuildInfoMapper.insertBuildInfo(jenkinsBuildInfo);
        } else {

            if (jenkinsBuildDependencyDB == null) {
                logger.error("数据库中不存在参数为:jobName[{}],jenkinsLogId[{}],instanceCode[{}] 的数据",
                    jenkinsBuildInfoDTO.getJobName(), jenkinsBuildInfoDTO.getJenkinsLogId(),
                    jenkinsBuildInfoDTO.getInstanceCode());
            }
            if (!jenkinsBuildDependencyDB.getId().equals(id)) {
                logger.error("该id与存在数据不一致");
            }

            jenkinsBuildDependencyDB.setBuildStatus(jenkinsBuildInfoDTO.getBuildStatus());
            jenkinsBuildDependencyDB.setUpdateTime(new Date());
            jenkinsBuildDependencyDB.setEndTime(new Date());

            return jenkinsBuildInfoMapper.updateById(jenkinsBuildDependencyDB);
        }
    }

    @Override
    public JenkinsBuildInfo queryBuildInfo(Long id) {
        return jenkinsBuildInfoMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsBuildInfo jenkinsBuildInfo = jenkinsBuildInfoMapper.selectById(id);
        if (jenkinsBuildInfo == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsBuildInfo.setStatus(status);
        jenkinsBuildInfo.setUpdateBy(updateBy);
        jenkinsBuildInfo.setUpdateTime(new Date());
        return jenkinsBuildInfoMapper.updateById(jenkinsBuildInfo);
    }

    @Override
    public PageResponse<JenkinsBuildInfo> pageList(JenkinsBuildPageListDTO jenkinsBuildPageListDTO) {
        return queryTemplate.execute(jenkinsBuildPageListDTO, () -> {
            jenkinsBuildInfoMapper.pageList(jenkinsBuildPageListDTO);
        });
    }

    @Override
    public List<JenkinsBuildInfo> queryBuildInfoList(Long jobId) {
        return jenkinsBuildInfoMapper.selectList(new QueryWrapper<JenkinsBuildInfo>().eq("job_id", jobId));
    }

    public JenkinsBuildInfoService(AbstractServiceFactory jenkinsServiceFactory,
        JenkinsBuildInfoMapper jenkinsBuildInfoMapper, QueryTemplate queryTemplate) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsBuildInfoMapper = jenkinsBuildInfoMapper;
        this.queryTemplate = queryTemplate;
    }
}
