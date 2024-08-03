package help.lixin.starlink.plugin.jenkins.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.ScmServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPipelineScmMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPipelineScmService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class JenkinsPipelineScmService implements IJenkinsPipelineScmService {


    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsPipelineScmMapper jenkinsPipelineScmMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveScm(JenkinsPipelineScmDTO jenkinsPipelineScmDTO) {
        Long id = jenkinsPipelineScmDTO.getId();

        ScmServiceConvert mapper = Mappers.getMapper(ScmServiceConvert.class);
        JenkinsPipelineScm jenkinsPipelineScm = mapper.convert(jenkinsPipelineScmDTO);

        JenkinsPipelineScm jenkinsPipelineScmDB = jenkinsPipelineScmMapper.selectById(id);

        if (jenkinsPipelineScmDB == null){
            jenkinsPipelineScm.setCreateTime(new Date());
            jenkinsPipelineScm.setUpdateTime(new Date());
            jenkinsPipelineScm.setUpdateBy(jenkinsPipelineScm.getCreateBy());
            return jenkinsPipelineScmMapper.insert(jenkinsPipelineScm);
        }else{

            jenkinsPipelineScm.setId(jenkinsPipelineScmDB.getId());
            jenkinsPipelineScm.setCreateBy(jenkinsPipelineScmDB.getCreateBy());
            jenkinsPipelineScm.setCreateTime(jenkinsPipelineScmDB.getCreateTime());
            jenkinsPipelineScm.setUpdateBy(jenkinsPipelineScmDTO.getCreateBy());
            jenkinsPipelineScm.setUpdateTime(new Date());

            return jenkinsPipelineScmMapper.updateById(jenkinsPipelineScm);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsPipelineScm jenkinsPipelineScm = jenkinsPipelineScmMapper.selectById(id);
        if (jenkinsPipelineScm == null){
            throw new ServiceException("该id不存在");
        }

        jenkinsPipelineScm.setStatus(status);
        jenkinsPipelineScm.setUpdateBy(updateBy);
        jenkinsPipelineScm.setUpdateTime(new Date());

        return jenkinsPipelineScmMapper.updateById(jenkinsPipelineScm);
    }

    @Override
    public JenkinsPipelineScm queryScmInfo(Long id) {
        return jenkinsPipelineScmMapper.selectById(id);
    }

    public JenkinsPipelineScmService(AbstractServiceFactory jenkinsServiceFactory, JenkinsPipelineScmMapper jenkinsPipelineScmMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsPipelineScmMapper = jenkinsPipelineScmMapper;
    }
}