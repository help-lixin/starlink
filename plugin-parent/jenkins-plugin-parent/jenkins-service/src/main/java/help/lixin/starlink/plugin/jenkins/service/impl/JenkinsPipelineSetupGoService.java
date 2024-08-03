package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.GoServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGo;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGoDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupGoMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupGoService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupGoService implements IJenkinsPipelineSetupGoService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupGoMapper jenkinsPipelineSetupGoMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveGo(JenkinsPipelineSetupGoDTO jenkinsPipelineSetupGoDTO) {
        Long id = jenkinsPipelineSetupGoDTO.getId();

        if (id == null){
            GoServiceConvert mapper = Mappers.getMapper(GoServiceConvert.class);
            JenkinsPipelineSetupGo jenkinsPipelineScm = mapper.convert(jenkinsPipelineSetupGoDTO);
            return jenkinsPipelineSetupGoMapper.insert(jenkinsPipelineScm);
        }else{
            JenkinsPipelineSetupGo jenkinsPipelineScm = jenkinsPipelineSetupGoMapper.selectById(id);
            if (jenkinsPipelineScm == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineScm.setGoId(jenkinsPipelineSetupGoDTO.getGoId());
            jenkinsPipelineScm.setScript(jenkinsPipelineSetupGoDTO.getScript());
            jenkinsPipelineScm.setStatus(jenkinsPipelineSetupGoDTO.getStatus());
            jenkinsPipelineScm.setUpdateBy(jenkinsPipelineSetupGoDTO.getCreateBy());
            jenkinsPipelineScm.setUpdateTime(new Date());

            return jenkinsPipelineSetupGoMapper.updateById(jenkinsPipelineScm);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupGo jenkinsPipelineSetupGo = jenkinsPipelineSetupGoMapper.selectById(id);
        if (jenkinsPipelineSetupGo == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupGo.setStatus(status);
        jenkinsPipelineSetupGo.setUpdateBy(updateBy);
        jenkinsPipelineSetupGo.setUpdateTime(new Date());

        return jenkinsPipelineSetupGoMapper.updateById(jenkinsPipelineSetupGo);
    }

    @Override
    public JenkinsPipelineSetupGo queryGoInfo(Long id) {
        return jenkinsPipelineSetupGoMapper.selectById(id);
    }

    public JenkinsPipelineSetupGoService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupGoMapper jenkinsPipelineSetupGoMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupGoMapper = jenkinsPipelineSetupGoMapper;
    }
}