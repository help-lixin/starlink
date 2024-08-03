package help.lixin.starlink.plugin.jenkins.service.impl;

import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.convert.ParamServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsParams;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsParamsMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsParamsService;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/12 5:54 下午
 * @Description
 */
public class JenkinsParamsService implements IJenkinsParamsService {

    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsParamsMapper jenkinsParamsMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer saveParams(JenkinsParamDTO jenkinsParamDTO) {
        Long id = jenkinsParamDTO.getId();

        if (id == null) {
            ParamServiceConvert paramServiceConvert = Mappers.getMapper(ParamServiceConvert.class);
            JenkinsParams jenkinsParams = paramServiceConvert.convert(jenkinsParamDTO);
            jenkinsParams.setCreateTime(new Date());
            jenkinsParams.setUpdateTime(new Date());
            jenkinsParams.setUpdateBy(jenkinsParams.getCreateBy());
            return jenkinsParamsMapper.insert(jenkinsParams);
        } else {
            JenkinsParams jenkinsParams = jenkinsParamsMapper.selectById(id);
            if (jenkinsParams == null) {
                throw new ServiceException("该id不存在");
            }

            jenkinsParams.setUpdateTime(new Date());
            jenkinsParams.setParamValue(jenkinsParamDTO.getParamValue());
            jenkinsParams.setParamName(jenkinsParamDTO.getParamName());
            jenkinsParams.setDefaultValue(jenkinsParamDTO.getDefaultValue());
            jenkinsParams.setParamType(jenkinsParamDTO.getParamType());
            jenkinsParams.setUpdateBy(jenkinsParamDTO.getCreateBy());

            return jenkinsParamsMapper.updateById(jenkinsParams);
        }
    }

    @Override
    public List<JenkinsParams> queryParamList(Long jobId) {
        return jenkinsParamsMapper.queryByJobId(jobId);
    }

    @Override
    public JenkinsParams paramInfo(Long id) {
        return jenkinsParamsMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsParams jenkinsParams = jenkinsParamsMapper.selectById(id);
        if (jenkinsParams == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsParams.setStatus(status);
        jenkinsParams.setUpdateBy(updateBy);
        jenkinsParams.setUpdateTime(new Date());
        return jenkinsParamsMapper.updateById(jenkinsParams);
    }

    @Override
    public Boolean cleanParamsByJobId(Long jobId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("job_id", jobId);
        return jenkinsParamsMapper.delete(queryWrapper) > 0;
    }

    public JenkinsParamsService(AbstractServiceFactory jenkinsServiceFactory, JenkinsParamsMapper jenkinsParamsMapper) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsParamsMapper = jenkinsParamsMapper;
    }
}
