package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.NodejsServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupNodejs;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupNodejsDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineSetupNodejsMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineSetupNodejsService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineSetupNodejsService implements IJenkinsPipelineSetupNodejsService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineSetupNodejsMapper jenkinsPipelineSetupNodejsMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveNodejs(JenkinsPipelineSetupNodejsDTO jenkinsPipelineSetupNodejsDTO) {
        Long id = jenkinsPipelineSetupNodejsDTO.getId();

        NodejsServiceConvert mapper = Mappers.getMapper(NodejsServiceConvert.class);
        JenkinsPipelineSetupNodejs jenkinsPipelineSetupNodejs = mapper.convert(jenkinsPipelineSetupNodejsDTO);

        if (id == null){
            return jenkinsPipelineSetupNodejsMapper.insert(jenkinsPipelineSetupNodejs);
        }else{
            JenkinsPipelineSetupNodejs jenkinsPipelineSetupNodejsDB = jenkinsPipelineSetupNodejsMapper.selectById(id);
            if (jenkinsPipelineSetupNodejs == null){
                throw new ServiceException("该id不存在");
            }

            jenkinsPipelineSetupNodejs.setId(jenkinsPipelineSetupNodejsDB.getId());
            jenkinsPipelineSetupNodejs.setCreateBy(jenkinsPipelineSetupNodejsDB.getCreateBy());
            jenkinsPipelineSetupNodejs.setUpdateBy(jenkinsPipelineSetupNodejsDTO.getCreateBy());
            jenkinsPipelineSetupNodejs.setUpdateTime(new Date());

            return jenkinsPipelineSetupNodejsMapper.updateById(jenkinsPipelineSetupNodejs);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineSetupNodejs jenkinsPipelineSetupNodejs = jenkinsPipelineSetupNodejsMapper.selectById(id);
        if (jenkinsPipelineSetupNodejs == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineSetupNodejs.setStatus(status);
        jenkinsPipelineSetupNodejs.setUpdateBy(updateBy);
        jenkinsPipelineSetupNodejs.setUpdateTime(new Date());

        return jenkinsPipelineSetupNodejsMapper.updateById(jenkinsPipelineSetupNodejs);
    }

    @Override
    public JenkinsPipelineSetupNodejs queryNodejsInfo(Long id) {
        return jenkinsPipelineSetupNodejsMapper.selectById(id);
    }

    public JenkinsPipelineSetupNodejsService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineSetupNodejsMapper jenkinsPipelineSetupNodejsMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineSetupNodejsMapper = jenkinsPipelineSetupNodejsMapper;
    }
}