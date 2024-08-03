package help.lixin.starlink.plugin.jenkins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;
import help.lixin.starlink.plugin.jenkins.api.service.impl.ManageToolsConfigureService;
import help.lixin.starlink.plugin.jenkins.convert.SystemConfigServiceConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginTypeEnum;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsSystemConfigPageDTO;
import help.lixin.starlink.plugin.jenkins.dto.sys.JenkinsSystemConfigDTO;
import help.lixin.starlink.plugin.jenkins.dto.systool.*;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsSystemConfigMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsSystemConfigService;
import help.lixin.response.PageResponse;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 2:54 下午
 * @Description
 */
public class JenkinsSystemConfigService implements IJenkinsSystemConfigService {

    private QueryTemplate queryTemplate;
    private final AbstractServiceFactory jenkinsServiceFactory;
    private JenkinsSystemConfigMapper jenkinsSystemConfigMapper;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveConfig(JenkinsSystemConfigDTO jenkinsSystemConfigDTO) {
        Long id = jenkinsSystemConfigDTO.getId();

        SystemConfigServiceConvert mapper = Mappers.getMapper(SystemConfigServiceConvert.class);
        JenkinsSystemConfig jenkinsSystemConfig = mapper.convert(jenkinsSystemConfigDTO);

        ManageToolsConfigureService configApi =
            jenkinsServiceFactory.getInstance(jenkinsSystemConfig.getInstanceCode(), ManageToolsConfigureService.class);

        // 检验路径是否存在
        if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.JDK)) {
            configApi.checkHome(JenkinsManageToolsModule.JDK, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.MAVEN)) {
            configApi.checkHome(JenkinsManageToolsModule.MAVEN, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GIT)) {
            configApi.checkHome(JenkinsManageToolsModule.GIT, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GRADLE)) {
            configApi.checkHome(JenkinsManageToolsModule.GRADLE, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.ANT)) {
            configApi.checkHome(JenkinsManageToolsModule.ANT, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GO)) {
            configApi.checkHome(JenkinsManageToolsModule.GO, jenkinsSystemConfig.getValue());
        } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.NODE_JS)) {
            configApi.checkHome(JenkinsManageToolsModule.NODE_JS, jenkinsSystemConfig.getValue());
        }

        if (id == null) {
            jenkinsSystemConfig.setUpdateBy(jenkinsSystemConfig.getCreateBy());
            jenkinsSystemConfigMapper.insertSelective(jenkinsSystemConfig);
        } else {
            JenkinsSystemConfig jenkinsSystemConfigDB = jenkinsSystemConfigMapper.selectById(id);
            if (jenkinsSystemConfigDB == null) {
                throw new ServiceException("该id不存在");
            }

            jenkinsSystemConfig.setId(jenkinsSystemConfigDB.getId());
            jenkinsSystemConfig.setCreateBy(jenkinsSystemConfigDB.getCreateBy());
            jenkinsSystemConfig.setCreateTime(jenkinsSystemConfigDB.getCreateTime());
            jenkinsSystemConfig.setUpdateBy(jenkinsSystemConfigDTO.getCreateBy());
            jenkinsSystemConfig.setUpdateTime(new Date());

            jenkinsSystemConfigMapper.updateById(jenkinsSystemConfig);
        }

        return saveTools(jenkinsSystemConfigDTO.getInstanceCode());
    }

    @Override
    public Boolean deleteConfig(Long id) {
        JenkinsSystemConfig jenkinsSystemConfig = jenkinsSystemConfigMapper.selectById(id);
        if (jenkinsSystemConfig == null) {
            throw new ServiceException("该id不存在");
        }
        jenkinsSystemConfig.setStatus(0);
        jenkinsSystemConfig.setIsDel(1);
        jenkinsSystemConfigMapper.updateById(jenkinsSystemConfig);
        saveTools(jenkinsSystemConfig.getInstanceCode());
        return null;
    }

    /**
     * @Param instanceCode :
     * @Author: 伍岳林
     * @Date: 2024/3/17 下午5:13
     * @Return: java.lang.Boolean
     * @Description 保存到jenkins配置中
     */
    private Boolean saveTools(String instanceCode) {
        JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO = new JenkinsSystemConfigPageDTO();
        jenkinsSystemConfigPageDTO.setInstanceCode(instanceCode);
        List<JenkinsSystemConfig> jenkinsSystemConfigs =
            jenkinsSystemConfigMapper.queryList(jenkinsSystemConfigPageDTO);

        JenkinsConfigDTO jenkinsConfigDTO = new JenkinsConfigDTO();

        JDKDTO jdkdto = new JDKDTO();
        MavenDTO mavenDTO = new MavenDTO();
        GitDTO gitDTO = new GitDTO();
        GradleDTO gradleDTO = new GradleDTO();
        AntDTO antDTO = new AntDTO();
        GoDTO goDTO = new GoDTO();
        NodejsDTO nodejsDTO = new NodejsDTO();

        for (JenkinsSystemConfig jenkinsSystemConfig : jenkinsSystemConfigs) {
            if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.JDK)) {

                List<ToolDTO> tool = jdkdto.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                jdkdto.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.MAVEN)) {

                List<ToolDTO> tool = mavenDTO.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                mavenDTO.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GIT)) {

                List<GitToolInfoDTO> tool = gitDTO.getTool();
                GitToolInfoDTO gitToolInfoDTO = new GitToolInfoDTO();
                gitToolInfoDTO.setName(jenkinsSystemConfig.getName());
                gitToolInfoDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(gitToolInfoDTO);
                gitDTO.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GRADLE)) {

                List<ToolDTO> tool = gradleDTO.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                gradleDTO.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.ANT)) {

                List<ToolDTO> tool = antDTO.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                antDTO.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.GO)) {

                List<ToolDTO> tool = goDTO.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                goDTO.setTool(tool);

            } else if (jenkinsSystemConfig.getPluginType().equals(JenkinsPluginTypeEnum.NODE_JS)) {

                List<ToolDTO> tool = nodejsDTO.getTool();
                ToolDTO toolDTO = new ToolDTO();
                toolDTO.setName(jenkinsSystemConfig.getName());
                toolDTO.setHome(jenkinsSystemConfig.getValue());
                tool.add(toolDTO);
                nodejsDTO.setTool(tool);

            }

        }

        jenkinsConfigDTO.setJdkDTO(jdkdto);
        jenkinsConfigDTO.setMavenDTO(mavenDTO);
        jenkinsConfigDTO.setGitDTO(gitDTO);
        jenkinsConfigDTO.setGradleDTO(gradleDTO);
        jenkinsConfigDTO.setAntDTO(antDTO);
        jenkinsConfigDTO.setGoDTO(goDTO);
        jenkinsConfigDTO.setNodejsDTO(nodejsDTO);

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(jenkinsConfigDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("jenkinsSystemConfigService保存过程出现json转换异常" + e.getMessage());
        }
        ManageToolsConfigureService configApi =
            jenkinsServiceFactory.getInstance(instanceCode, ManageToolsConfigureService.class);
        return configApi.configure(json);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer changeStatus(Long id, Integer status, String updateBy) {
        JenkinsSystemConfig jenkinsSystemConfig = jenkinsSystemConfigMapper.selectById(id);
        if (jenkinsSystemConfig == null) {
            throw new ServiceException("该id不存在");
        }

        jenkinsSystemConfig.setStatus(status);
        jenkinsSystemConfig.setUpdateBy(updateBy);
        jenkinsSystemConfig.setUpdateTime(new Date());

        return jenkinsSystemConfigMapper.updateById(jenkinsSystemConfig);
    }

    @Override
    public JenkinsSystemConfig querySystemConfigInfo(Long id) {
        return jenkinsSystemConfigMapper.selectById(id);
    }

    @Override
    public PageResponse<JenkinsSystemConfig> list(JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO) {
        return queryTemplate.execute(jenkinsSystemConfigPageDTO, () -> {
            jenkinsSystemConfigMapper.queryList(jenkinsSystemConfigPageDTO);
        });
    }

    @Override
    public Boolean syncAllSysConfig() {
        List<String> instanceCodes = jenkinsSystemConfigMapper.queryInstanceCodes();
        for (String instanceCode : instanceCodes) {
            saveTools(instanceCode);
        }
        return true;
    }

    @Override
    public List<OptionDTO> queryByPluginType(String pluginType, String instanceCode) {
        List<OptionDTO> resultList = new ArrayList<>();

        JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO = new JenkinsSystemConfigPageDTO();
        jenkinsSystemConfigPageDTO.setInstanceCode(instanceCode);
        jenkinsSystemConfigPageDTO.setPluginType(pluginType);
        jenkinsSystemConfigPageDTO.setStatus(1);
        List<JenkinsSystemConfig> jenkinsSystemConfigs =
            jenkinsSystemConfigMapper.queryList(jenkinsSystemConfigPageDTO);

        jenkinsSystemConfigs.forEach(v -> {
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setLabel(v.getName());
            optionDTO.setValue(v.getId() + "");
            resultList.add(optionDTO);
        });
        return resultList;
    }

    @Override
    public List<String> queryPluginTypeByInstanceCode(String instanceCode) {
        return jenkinsSystemConfigMapper.queryPluginTypeByInstanceCode(instanceCode);
    }

    @Override
    public Boolean checkHome(JenkinsManageToolsModule jenkinsManageToolsModule, String homePath, String instanceCode) {
        ManageToolsConfigureService instance =
            jenkinsServiceFactory.getInstance(instanceCode, ManageToolsConfigureService.class);
        instance.checkHome(jenkinsManageToolsModule, homePath);
        return true;
    }

    @Override
    public Boolean checkName(JenkinsManageToolsModule jenkinsManageToolsModule, String homePath, String instanceCode) {
        ManageToolsConfigureService instance =
            jenkinsServiceFactory.getInstance(instanceCode, ManageToolsConfigureService.class);
        instance.checkName(jenkinsManageToolsModule, homePath);
        return true;
    }

    public JenkinsSystemConfigService(AbstractServiceFactory jenkinsServiceFactory,
        JenkinsSystemConfigMapper jenkinsSystemConfigMapper, QueryTemplate queryTemplate) {
        this.jenkinsServiceFactory = jenkinsServiceFactory;
        this.jenkinsSystemConfigMapper = jenkinsSystemConfigMapper;
        this.queryTemplate = queryTemplate;
    }
}
