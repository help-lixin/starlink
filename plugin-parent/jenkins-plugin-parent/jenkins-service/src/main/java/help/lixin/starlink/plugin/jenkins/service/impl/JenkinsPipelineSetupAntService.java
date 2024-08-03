package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.AntServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupAnt;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupAntDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupAntMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupAntService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupAntService implements IJenkinsPipelineSetupAntService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupAntMapper jenkinsPipelineSetupAntMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveAnt(JenkinsPipelineSetupAntDTO jenkinsPipelineSetupAntDTO) {
        Long id = jenkinsPipelineSetupAntDTO.getId();

        if (id == null){
            AntServiceConvert mapper = Mappers.getMapper(AntServiceConvert.class);
            JenkinsPipelineSetupAnt jenkinsPipelineSetupAnt = mapper.convert(jenkinsPipelineSetupAntDTO);
            return jenkinsPipelineSetupAntMapper.insert(jenkinsPipelineSetupAnt);
        }else{
            JenkinsPipelineSetupAnt jenkinsPipelineSetupAnt = jenkinsPipelineSetupAntMapper.selectById(id);
            if (jenkinsPipelineSetupAnt == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupAnt.setTargets(jenkinsPipelineSetupAntDTO.getTargets());
            jenkinsPipelineSetupAnt.setStatus(jenkinsPipelineSetupAntDTO.getStatus());
            jenkinsPipelineSetupAnt.setUpdateBy(jenkinsPipelineSetupAntDTO.getCreateBy());
            jenkinsPipelineSetupAnt.setUpdateTime(new Date());

            return jenkinsPipelineSetupAntMapper.updateById(jenkinsPipelineSetupAnt);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupAnt jenkinsPipelineSetupAnt = jenkinsPipelineSetupAntMapper.selectById(id);
        if (jenkinsPipelineSetupAnt == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupAnt.setStatus(status);
        jenkinsPipelineSetupAnt.setUpdateBy(updateBy);
        jenkinsPipelineSetupAnt.setUpdateTime(new Date());

        return jenkinsPipelineSetupAntMapper.updateById(jenkinsPipelineSetupAnt);
    }

    public JenkinsPipelineSetupAnt queryAntInfo(Long id) {
        return jenkinsPipelineSetupAntMapper.selectById(id);
    }

    public JenkinsPipelineSetupAntService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupAntMapper jenkinsPipelineSetupAntMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupAntMapper = jenkinsPipelineSetupAntMapper;
    }
}