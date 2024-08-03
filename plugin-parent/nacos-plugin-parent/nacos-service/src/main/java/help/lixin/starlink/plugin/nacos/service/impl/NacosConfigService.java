package help.lixin.starlink.plugin.nacos.service.impl;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.nacos.api.model.config.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosHistoryConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosListenerResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosPageListDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.service.INacosConfigService;
import help.lixin.starlink.plugin.nacos.convert.ConfigImplConvert;
import help.lixin.starlink.plugin.nacos.domain.NacosConfigCenter;
import help.lixin.starlink.plugin.nacos.mapper.NacosConfigCenterMapper;
import help.lixin.response.PageResponse;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 11:25 上午
 * @Description
 */
public class NacosConfigService extends InstanceService<INacosConfigService>
    implements help.lixin.starlink.plugin.nacos.service.INacosConfigService {

    private NacosConfigCenterMapper nacosConfigCenterMapper;
    private QueryTemplate queryTemplate;

    @Override
    public Boolean publishConfig(NacosPublishConfig nacosPublishConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.publishConfig(nacosPublishConfig);
    }

    @Override
    @Transactional
    public Boolean createConfig(NacosCreateConfig nacosCreateConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        Boolean result = configService.createConfig(nacosCreateConfig);

        saveConfig(nacosCreateConfig, configService);
        return result;
    }

    @Override
    @Transactional
    public Boolean updateConfig(NacosUpdateConfig nacosUpdateConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        modifyConfig(nacosUpdateConfig);
        return configService.updateConfig(nacosUpdateConfig);
    }

    @Override
    public PageResponse<NacosPageListDetailConfigResponse> pageList(NacosPageListConfig nacosPageListConfig) {
        // IConfigService configService = getApi(instanceName);
        // NacosPageListConfigResponse nacosPageListConfigResponse = configService.pageList(nacosPageListConfig);
        // PageResponse<NacosPageListDetailConfigResponse> pageResult = new PageResponse<>();
        // pageResult.setRecords(nacosPageListConfigResponse.getPageItems());
        // pageResult.setTotal(nacosPageListConfigResponse.getTotalCount());
        // pageResult.setPageCurrent(nacosPageListConfigResponse.getPageNumber());
        // return pageResult;
        return queryTemplate.execute(nacosPageListConfig.curPageDTO(), () -> {
            nacosConfigCenterMapper.queryPageList(nacosPageListConfig);
        });
    }

    @Override
    public NacosDetailConfigResponse configDetail(NacosDetailConfig nacosDetailConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        NacosDetailConfigResponse nacosDetailConfigResponse = configService.configDetail(nacosDetailConfig);
        return nacosDetailConfigResponse;
    }

    @Override
    public NacosConfigCenter configDetail(Long id) {
        return nacosConfigCenterMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Boolean deleteConfig(NacosDeleteConfig nacosDeleteConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        nacosConfigCenterMapper.removeConfig(nacosDeleteConfig.getIds());
        return configService.deleteConfig(nacosDeleteConfig);
    }

    @Override
    public PageResponse<NacosHistoryConfigResponse>
        pageListConfigHistory(NacosPageListHistoryConfig nacosPageListHistoryConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.pageListConfigHistory(nacosPageListHistoryConfig);
    }

    @Override
    public NacosHistoryConfigResponse queryDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig,
        String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.queryDetailHistory(nacosDetailHistoryConfig);
    }

    @Override
    public NacosHistoryConfigResponse queryPreviousDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig,
        String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.queryPreviousDetailHistory(nacosDetailHistoryConfig);
    }

    @Override
    public List<NacosDetailConfigResponse> queryByNameSpaceId(String namespaceId, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.queryByNameSpaceId(namespaceId);
    }

    @Override
    public ResponseEntity<byte[]> exportConfig(NacosExportConfig nacosExportConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.exportConfig(nacosExportConfig);
    }

    @Override
    public Map<String, Object> importAndPublishConfig(NacosImportConfig nacosImportConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.importAndPublishConfig(nacosImportConfig);
    }

    @Override
    public Map<String, Object> cloneConfig(NacosCloneInfoConfig nacosCloneInfoConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.cloneConfig(nacosCloneInfoConfig);
    }

    @Override
    public NacosListenerResponse queryListenerList(NacosListenConfig nacosListenConfig, String instanceName) {
        INacosConfigService configService = getApi(instanceName);
        return configService.queryListenList(nacosListenConfig);
    }

    private void saveConfig(NacosCreateConfig nacosCreateConfig, INacosConfigService configService) {
        ConfigImplConvert configImplConvert = Mappers.getMapper(ConfigImplConvert.class);
        NacosConfigCenter nacosConfigCenter = configImplConvert.convert(nacosCreateConfig);
        NacosDetailConfig detailConfig = configImplConvert.convertDetail(nacosCreateConfig);

        NacosDetailConfigResponse nacosDetailConfigResponse = configService.configDetail(detailConfig);
        nacosConfigCenter.setId(nacosDetailConfigResponse.getId());
        nacosConfigCenter.setEnvCode(nacosCreateConfig.getEnvRequest().getEnvCode());
        nacosConfigCenter.setGroupCode(nacosCreateConfig.getEnvRequest().getGroupCode());
        nacosConfigCenter.setInstanceCode(nacosCreateConfig.getEnvRequest().getInstanceCode());
        nacosConfigCenterMapper.insertSelective(nacosConfigCenter);
    }

    private void modifyConfig(NacosUpdateConfig nacosUpdateConfig) {
        ConfigImplConvert configImplConvert = Mappers.getMapper(ConfigImplConvert.class);
        NacosConfigCenter nacosConfigCenter = configImplConvert.convert(nacosUpdateConfig);
        nacosConfigCenterMapper.updateByGroupAndDataId(nacosConfigCenter);
    }

    public NacosConfigService(PluginNamedContextFactory pluginNamedContextFactory,
        NacosConfigCenterMapper nacosConfigCenterMapper, QueryTemplate queryTemplate) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
        this.nacosConfigCenterMapper = nacosConfigCenterMapper;
        this.queryTemplate = queryTemplate;
    }
}
