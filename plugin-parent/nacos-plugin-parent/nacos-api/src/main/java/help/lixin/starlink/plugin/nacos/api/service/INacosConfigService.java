package help.lixin.starlink.plugin.nacos.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosHistoryConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosListenerResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosPageListConfigResponse;
import help.lixin.starlink.plugin.nacos.api.model.config.*;

public interface INacosConfigService {

    /**
     * @Param nacosPublishConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:46 上午
     * @Return: java.lang.Boolean
     * @Description 发布配置信息
     */
    Boolean publishConfig(NacosPublishConfig nacosPublishConfig);

    /**
     * @Param nacosCreateConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:46 上午
     * @Return: java.lang.Boolean
     * @Description 创建配置信息
     */
    Boolean createConfig(NacosCreateConfig nacosCreateConfig);

    /**
     * @Param nacosUpdateConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:46 上午
     * @Return: java.lang.Boolean
     * @Description 更新配置信息
     */
    Boolean updateConfig(NacosUpdateConfig nacosUpdateConfig);

    /**
     * @Param nacosPageListConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:46 上午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosPageListConfigResponse
     * @Description 配置列表 分页查询
     */
    NacosPageListConfigResponse pageList(NacosPageListConfig nacosPageListConfig);

    /**
     * @Param nacosDetailConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:46 上午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosDetailConfigResponse
     * @Description 查询配置详情
     */
    NacosDetailConfigResponse configDetail(NacosDetailConfig nacosDetailConfig);

    /**
     * @Param nacosDeleteConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:45 上午
     * @Return: java.lang.Boolean
     * @Description 删除配置
     */
    Boolean deleteConfig(NacosDeleteConfig nacosDeleteConfig);

    /**
     * @Param nacosPageListHistoryConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 10:45 上午
     * @Return: help.lixin.response.PageResponse<help.lixin.starlink.plugin.nacos.api.model.config.NacosPageListHistoryConfig>
     * @Description 分页查询 版本历史配置记录
     * @return
     */
    PageResponse<NacosHistoryConfigResponse>
        pageListConfigHistory(NacosPageListHistoryConfig nacosPageListHistoryConfig);

    /**
     * @Param nacosDetailHistoryConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 5:18 下午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosHistoryConfigResponse
     * @Description 查询 版本历史配置记录详情
     */
    NacosHistoryConfigResponse queryDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig);

    /**
     * @Param nacosDetailHistoryConfig :
     * @Author: 伍岳林
     * @Date: 2023/7/17 5:40 下午
     * @Return: help.lixin.starlink.plugin.nacos.api.dto.response.NacosHistoryConfigResponse
     * @Description 对比历史版本详情
     */
    NacosHistoryConfigResponse queryPreviousDetailHistory(NacosDetailHistoryConfig nacosDetailHistoryConfig);

    /**
     * @Param namespaceId :
     * @Author: 伍岳林
     * @Date: 2023/7/17 7:11 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.nacos.api.dto.response.NacosDetailConfigResponse>
     * @Description 根据命名空间id查询信息（主要用于下拉列表）
     */
    List<NacosDetailConfigResponse> queryByNameSpaceId(String namespaceId);

    // 导出 get exportV2=true
    ResponseEntity<byte[]> exportConfig(NacosExportConfig nacosExportConfig);

    // 导入 post import=true
    Map<String, Object> importAndPublishConfig(NacosImportConfig nacosImportConfig);

    // 克隆 post clone=true
    Map<String, Object> cloneConfig(NacosCloneInfoConfig nacosCloneInfoConfig);

    // 监听查询 get
    NacosListenerResponse queryListenList(NacosListenConfig nacosListenConfig);
}
