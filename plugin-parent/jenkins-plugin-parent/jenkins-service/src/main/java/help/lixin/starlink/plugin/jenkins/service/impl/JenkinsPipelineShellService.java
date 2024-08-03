package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.ShellServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupShell;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupShellDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupShellMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupShellService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineShellService implements IJenkinsPipelineSetupShellService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupShellMapper jenkinsPipelineSetupShellMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveShell(JenkinsPipelineSetupShellDTO jenkinsPipelineSetupShellDTO) {
        Long id = jenkinsPipelineSetupShellDTO.getId();

        ShellServiceConvert mapper = Mappers.getMapper(ShellServiceConvert.class);
        JenkinsPipelineSetupShell jenkinsPipelineSetupShell = mapper.convert(jenkinsPipelineSetupShellDTO);

        if (id == null){
            return jenkinsPipelineSetupShellMapper.insert(jenkinsPipelineSetupShell);
        }else{
            JenkinsPipelineSetupShell jenkinsPipelineSetupShellDB = jenkinsPipelineSetupShellMapper.selectById(id);
            if (jenkinsPipelineSetupShellDB == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupShell.setId(jenkinsPipelineSetupShellDB.getId());
            jenkinsPipelineSetupShell.setStatus(jenkinsPipelineSetupShellDTO.getStatus());
            jenkinsPipelineSetupShell.setCreateBy(jenkinsPipelineSetupShellDB.getCreateBy());
            jenkinsPipelineSetupShell.setUpdateBy(jenkinsPipelineSetupShellDTO.getCreateBy());
            jenkinsPipelineSetupShell.setUpdateTime(new Date());

            return jenkinsPipelineSetupShellMapper.updateById(jenkinsPipelineSetupShell);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupShell jenkinsPipelineSetupShell = jenkinsPipelineSetupShellMapper.selectById(id);
        if (jenkinsPipelineSetupShell == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupShell.setStatus(status);
        jenkinsPipelineSetupShell.setUpdateBy(updateBy);
        jenkinsPipelineSetupShell.setUpdateTime(new Date());

        return jenkinsPipelineSetupShellMapper.updateById(jenkinsPipelineSetupShell);
    }

    @Override
    public JenkinsPipelineSetupShell queryShellInfo(Long id) {
        return jenkinsPipelineSetupShellMapper.selectById(id);
    }

    public JenkinsPipelineShellService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupShellMapper jenkinsPipelineSetupShellMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupShellMapper = jenkinsPipelineSetupShellMapper;
    }
}