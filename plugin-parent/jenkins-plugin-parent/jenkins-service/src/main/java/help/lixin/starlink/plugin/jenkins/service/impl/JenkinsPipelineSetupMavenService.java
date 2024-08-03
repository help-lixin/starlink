package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.MavenServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupMaven;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupMavenDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupMavenMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupMavenService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupMavenService implements IJenkinsPipelineSetupMavenService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupMavenMapper jenkinsPipelineSetupMavenMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveMaven(JenkinsPipelineSetupMavenDTO jenkinsPipelineSetupMavenDTO) {
        Long id = jenkinsPipelineSetupMavenDTO.getId();

        if (id == null){
            MavenServiceConvert mapper = Mappers.getMapper(MavenServiceConvert.class);
            JenkinsPipelineSetupMaven jenkinsPipelineSetupMaven = mapper.convert(jenkinsPipelineSetupMavenDTO);
            return jenkinsPipelineSetupMavenMapper.insert(jenkinsPipelineSetupMaven);
        }else{
            JenkinsPipelineSetupMaven jenkinsPipelineSetupMaven = jenkinsPipelineSetupMavenMapper.selectById(id);
            if (jenkinsPipelineSetupMaven == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupMaven.setMavenId(jenkinsPipelineSetupMavenDTO.getMavenId());
            jenkinsPipelineSetupMaven.setGoals(jenkinsPipelineSetupMaven.getGoals());
            jenkinsPipelineSetupMaven.setStatus(jenkinsPipelineSetupMavenDTO.getStatus());
            jenkinsPipelineSetupMaven.setUpdateBy(jenkinsPipelineSetupMavenDTO.getCreateBy());
            jenkinsPipelineSetupMaven.setUpdateTime(new Date());

            return jenkinsPipelineSetupMavenMapper.updateById(jenkinsPipelineSetupMaven);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupMaven jenkinsPipelineSetupMaven = jenkinsPipelineSetupMavenMapper.selectById(id);
        if (jenkinsPipelineSetupMaven == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupMaven.setStatus(status);
        jenkinsPipelineSetupMaven.setUpdateBy(updateBy);
        jenkinsPipelineSetupMaven.setUpdateTime(new Date());

        return jenkinsPipelineSetupMavenMapper.updateById(jenkinsPipelineSetupMaven);
    }

    @Override
    public JenkinsPipelineSetupMaven queryMavenInfo(Long id) {
        return jenkinsPipelineSetupMavenMapper.selectById(id);
    }

    public JenkinsPipelineSetupMavenService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupMavenMapper pipelineSetupMavenMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupMavenMapper = pipelineSetupMavenMapper;
    }
}