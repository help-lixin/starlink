package help.lixin.starlink.plugin.jenkins.service.impl;

import java.util.Date;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.convert.BuildDependencyServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildDependency;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildDependencyDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsBuildDependencyMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsBuildDependencyService;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 4:11 下午
 * @Description
 */
public class JenkinsBuildDependencyService implements IJenkinsBuildDependencyService {

    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsBuildDependencyMapper jenkinsBuildDependencyMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveDependency(JenkinsBuildDependencyDTO jenkinsBuildDependencyDTO) {
        Long id = jenkinsBuildDependencyDTO.getId();

        BuildDependencyServiceConvert mapper = Mappers.getMapper(BuildDependencyServiceConvert.class);
        JenkinsBuildDependency jenkinsBuildDependency = mapper.convert(jenkinsBuildDependencyDTO);

        JenkinsBuildDependency jenkinsBuildDependencyDB = jenkinsBuildDependencyMapper
            .queryByJob(jenkinsBuildDependency.getJobName(), jenkinsBuildDependency.getInstanceCode());

        if (id == null && jenkinsBuildDependencyDB == null) {
            jenkinsBuildDependency.setCreateTime(new Date());
            jenkinsBuildDependency.setUpdateTime(new Date());
            jenkinsBuildDependency.setUpdateBy(jenkinsBuildDependency.getCreateBy());
            return jenkinsBuildDependencyMapper.insert(jenkinsBuildDependency);
        } else {
            if (jenkinsBuildDependencyDB == null) {
                throw new ServiceException("该id不存在");
            }

            jenkinsBuildDependency.setId(jenkinsBuildDependencyDB.getId());
            jenkinsBuildDependency.setStatus(jenkinsBuildDependencyDTO.getStatus());
            jenkinsBuildDependency.setCreateBy(jenkinsBuildDependencyDB.getCreateBy());
            jenkinsBuildDependency.setCreateTime(jenkinsBuildDependencyDB.getCreateTime());
            jenkinsBuildDependency.setUpdateBy(jenkinsBuildDependencyDTO.getCreateBy());

            return jenkinsBuildDependencyMapper.updateById(jenkinsBuildDependency);
        }
    }

    @Override
    public JenkinsBuildDependency dependencyInfo(Long id) {
        return jenkinsBuildDependencyMapper.selectById(id);
    }

    @Override
    public JenkinsBuildDependency queryByJobId(Long jobId) {
        return jenkinsBuildDependencyMapper.queryByJobId(jobId);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsBuildDependency jenkinsBuildDependency = jenkinsBuildDependencyMapper.selectById(id);
        if (jenkinsBuildDependency == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsBuildDependency.setStatus(status);
        jenkinsBuildDependency.setUpdateBy(updateBy);
        jenkinsBuildDependency.setUpdateTime(new Date());
        return jenkinsBuildDependencyMapper.updateById(jenkinsBuildDependency);
    }

    public JenkinsBuildDependencyService(AbstractServiceFactory jenkinsServiceFactory,
        JenkinsBuildDependencyMapper jenkinsBuildDependencyMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsBuildDependencyMapper = jenkinsBuildDependencyMapper;
    }
}
