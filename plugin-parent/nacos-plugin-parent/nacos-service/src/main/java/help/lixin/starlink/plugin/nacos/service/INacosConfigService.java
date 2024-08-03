package help.lixin.starlink.plugin.nacos.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosHistoryConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosListenerResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosPageListDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.model.config.*;
import help.lixin.starlink.plugin.nacos.domain.NacosConfigCenter;

public interface INacosConfigService {

    Boolean publishConfig(NacosPublishConfig nacosPublishConfig, String instanceName);

    Boolean createConfig(NacosCreateConfig nacosCreateConfig, String instanceName);

    Boolean updateConfig(NacosUpdateConfig nacosUpdateConfig, String instanceName);

    PageResponse<NacosPageListDetailConfigResponse> pageList(NacosPageListConfig nacosPageListConfig);

    NacosDetailConfigResponse configDetail(NacosDetailConfig nameSpaceId, String instanceName);

    NacosConfigCenter configDetail(Long id);

    /**
     * @Param nacosDeleteConfig :
     * @Param instanceName :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:45 上午
     * @Return: java.lang.Boolean
     * @Description 删除配置
     */
    Boolean deleteConfig(NacosDeleteConfig nacosDeleteConfig, String instanceName);

    /**
     * @Param nacosPageListHistoryConfig :
     * @Param instanceName :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:52 上午
     * @Return: help.lixin.response.PageResponse<help.lixin.starlink.plugin.nacos.api.model.config.NacosPageListHistoryConfig>
     * @Description 分页查询 历史版本配置
     * @return
     */
    PageResponse<NacosHistoryConfigResponse>
        pageListConfigHistory(NacosPageListHistoryConfig nacosPageListHistoryConfig, String instanceName);

    /**
     * @Param nacosDetailHistoryConfig :
     * @Param instanceName :
     * @Author: 伍岳林
     * @Date: 2023/7/17 5:18 下午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosHistoryConfigResponse
     * @Description 查询 版本历史配置记录详情
     */
    NacosHistoryConfigResponse queryDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig,
        String instanceName);

    /**
     * @Param nacosDetailHistoryConfig :
     * @Param instanceName :
     * @Author: 伍岳林
     * @Date: 2023/7/17 5:18 下午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosHistoryConfigResponse
     * @Description 对比历史版本详情
     */
    NacosHistoryConfigResponse queryPreviousDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig,
        String instanceName);

    /**
     * @Param namespaceId :
     * @Author: 伍岳林
     * @Date: 2023/7/17 7:11 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.nacos.api.dto.response.NacosDetailConfigResponse>
     * @Description 根据命名空间id查询信息（主要用于下拉列表）
     */
    List<NacosDetailConfigResponse> queryByNameSpaceId(String namespaceId, String instanceName);

    // 导出 get exportV2=true
    ResponseEntity<byte[]> exportConfig(NacosExportConfig nacosExportConfig, String instanceName);

    // 导入 post import=true
    Map<String, Object> importAndPublishConfig(NacosImportConfig nacosImportConfig, String instanceName);

    // 克隆 post clone=true
    Map<String, Object> cloneConfig(NacosCloneInfoConfig nacosCloneInfoConfig, String instanceName);

    // 监听查询 get
    NacosListenerResponse queryListenerList(NacosListenConfig nacosListenConfig, String instanceName);
}
