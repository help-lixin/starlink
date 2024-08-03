package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.PythonServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupPython;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupPythonDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupPythonMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupPythonService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupPythonService implements IJenkinsPipelineSetupPythonService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupPythonMapper jenkinsPipelineSetupPythonMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer savePython(JenkinsPipelineSetupPythonDTO jenkinsPipelineSetupPythonDTO) {
        Long id = jenkinsPipelineSetupPythonDTO.getId();

        PythonServiceConvert mapper = Mappers.getMapper(PythonServiceConvert.class);
        JenkinsPipelineSetupPython jenkinsPipelineSetupPython = mapper.convert(jenkinsPipelineSetupPythonDTO);

        if (id == null){
            return jenkinsPipelineSetupPythonMapper.insert(jenkinsPipelineSetupPython);
        }else{
            JenkinsPipelineSetupPython jenkinsPipelineSetupPythonDB = jenkinsPipelineSetupPythonMapper.selectById(id);
            if (jenkinsPipelineSetupPythonDB == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupPython.setId(jenkinsPipelineSetupPythonDTO.getId());
            jenkinsPipelineSetupPython.setStatus(jenkinsPipelineSetupPythonDTO.getStatus());
            jenkinsPipelineSetupPython.setCreateBy(jenkinsPipelineSetupPythonDB.getCreateBy());
            jenkinsPipelineSetupPython.setUpdateBy(jenkinsPipelineSetupPythonDTO.getCreateBy());
            jenkinsPipelineSetupPython.setUpdateTime(new Date());

            return jenkinsPipelineSetupPythonMapper.updateById(jenkinsPipelineSetupPython);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupPython jenkinsPipelineSetupPython = jenkinsPipelineSetupPythonMapper.selectById(id);
        if (jenkinsPipelineSetupPython == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupPython.setStatus(status);
        jenkinsPipelineSetupPython.setUpdateBy(updateBy);
        jenkinsPipelineSetupPython.setUpdateTime(new Date());

        return jenkinsPipelineSetupPythonMapper.updateById(jenkinsPipelineSetupPython);
    }

    @Override
    public JenkinsPipelineSetupPython queryPythonInfo(Long id) {
        return jenkinsPipelineSetupPythonMapper.selectById(id);
    }

    public JenkinsPipelineSetupPythonService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupPythonMapper jenkinsPipelineSetupPythonMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupPythonMapper = jenkinsPipelineSetupPythonMapper;
    }
}