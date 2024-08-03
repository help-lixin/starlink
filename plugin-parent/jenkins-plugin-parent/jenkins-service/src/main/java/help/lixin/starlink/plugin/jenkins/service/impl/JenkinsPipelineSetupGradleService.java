package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.GradleServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGradle;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGradleDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupGradleMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupGradleService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupGradleService implements IJenkinsPipelineSetupGradleService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupGradleMapper jenkinsPipelineSetupGradleMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveGradle(JenkinsPipelineSetupGradleDTO jenkinsPipelineSetupGradleDTO) {
        Long id = jenkinsPipelineSetupGradleDTO.getId();

        GradleServiceConvert mapper = Mappers.getMapper(GradleServiceConvert.class);
        JenkinsPipelineSetupGradle jenkinsPipelineSetupGradle = mapper.convert(jenkinsPipelineSetupGradleDTO);

        if (id == null){
            return jenkinsPipelineSetupGradleMapper.insert(jenkinsPipelineSetupGradle);
        }else{
            JenkinsPipelineSetupGradle jenkinsPipelineSetupGradleDB = jenkinsPipelineSetupGradleMapper.selectById(id);
            if (jenkinsPipelineSetupGradleDB == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupGradle.setId(jenkinsPipelineSetupGradleDB.getId());
            jenkinsPipelineSetupGradle.setCreateBy(jenkinsPipelineSetupGradleDB.getCreateBy());
            jenkinsPipelineSetupGradle.setCreateTime(jenkinsPipelineSetupGradleDB.getCreateTime());
            jenkinsPipelineSetupGradle.setUpdateBy(jenkinsPipelineSetupGradleDTO.getCreateBy());
            jenkinsPipelineSetupGradle.setUpdateTime(new Date());

            return jenkinsPipelineSetupGradleMapper.updateById(jenkinsPipelineSetupGradle);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupGradle jenkinsPipelineSetupGradle = jenkinsPipelineSetupGradleMapper.selectById(id);
        if (jenkinsPipelineSetupGradle == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupGradle.setStatus(status);
        jenkinsPipelineSetupGradle.setUpdateBy(updateBy);
        jenkinsPipelineSetupGradle.setUpdateTime(new Date());

        return jenkinsPipelineSetupGradleMapper.updateById(jenkinsPipelineSetupGradle);
    }

    @Override
    public JenkinsPipelineSetupGradle queryGradleInfo(Long id) {
        return jenkinsPipelineSetupGradleMapper.selectById(id);
    }

    public JenkinsPipelineSetupGradleService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupGradleMapper jenkinsPipelineSetupGradleMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupGradleMapper = jenkinsPipelineSetupGradleMapper;
    }
}