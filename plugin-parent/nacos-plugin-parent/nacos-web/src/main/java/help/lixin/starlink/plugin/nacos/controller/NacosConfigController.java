package help.lixin.starlink.plugin.nacos.controller;

import static help.lixin.response.Response.success;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.api.model.config.*;
import help.lixin.starlink.plugin.nacos.convert.config.ConfigConvert;
import help.lixin.starlink.plugin.nacos.request.config.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import help.lixin.starlink.plugin.nacos.api.dto.config.NacosDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosHistoryConfigResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosListenerResponse;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosPageListDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.domain.NacosConfigCenter;
import help.lixin.starlink.plugin.nacos.service.INacosConfigService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 3:01 下午
 * @Description
 */
@Api(tags = "nacos配置管理控制层")
@RequestMapping("/nacos/config")
@RestController
public class NacosConfigController {

    @Autowired
    private INacosConfigService nacosConfigService;

    @GetMapping("/info")
    @ApiOperation(value = "查询配置详情(查的是nacos的数据)")
    public Response<NacosDetailConfigResponse>
        queryDetailConfig(@Valid NacosDetailConfigRequest nacosDetailConfigRequest, @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosDetailConfig nacosDetailConfig = configConvert.convert(nacosDetailConfigRequest);
        return success(nacosConfigService.configDetail(nacosDetailConfig, envRequest.toInstanceName()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询配置详情(查的是数据库的数据)")
    public Response<NacosConfigCenter> queryDBDetailConfig(@PathVariable("id") Long id) {
        return success(nacosConfigService.configDetail(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询配置分页信息")
    public Response<PageResponse<NacosPageListDetailConfigResponse>>
        pageList(@Valid NacosPageListConfigRequest nacosPageListConfigRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosPageListConfig pageListConfig = configConvert.convert(nacosPageListConfigRequest);
        return success(nacosConfigService.pageList(pageListConfig));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增配置")
    public Response<Boolean> createConfig(@Valid @RequestBody NacosCreateConfigRequest nacosCreateConfigRequest) {

        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosCreateConfig nacosCreateConfig = configConvert.convert(nacosCreateConfigRequest);
        return success(nacosConfigService.createConfig(nacosCreateConfig,
            nacosCreateConfigRequest.getEnvRequest().toInstanceName()));
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布配置（暂时没找到使用的地方）")
    public Response<Boolean> publishConfig(@Valid @RequestBody NacosPublishConfigRequest nacosPublishConfigRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosPublishConfig nacosPublishConfig = configConvert.convert(nacosPublishConfigRequest);
        return success(nacosConfigService.publishConfig(nacosPublishConfig,
            nacosPublishConfigRequest.getEnvRequest().toInstanceName()));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新配置")
    public Response<Boolean> updateConfig(@Valid @RequestBody NacosUpdateConfigRequest nacosUpdateConfigRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosUpdateConfig nacosCreateConfig = configConvert.convert(nacosUpdateConfigRequest);
        return success(nacosConfigService.updateConfig(nacosCreateConfig,
            nacosUpdateConfigRequest.getEnvRequest().toInstanceName()));
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除配置")
    public Response<Boolean> deleteConfig(@Valid NacosDeleteConfigRequest nacosDeleteConfigRequest,
        @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosDeleteConfig nacosDeleteConfig = configConvert.convert(nacosDeleteConfigRequest);
        return success(nacosConfigService.deleteConfig(nacosDeleteConfig, envRequest.toInstanceName()));
    }

    @GetMapping("/history/list")
    @ApiOperation(value = "查询历史版本配置分页信息")
    public Response<PageResponse<NacosHistoryConfigResponse>> pageList(
        @Valid NacosPageListHistoryConfigRequest nacosPageListHistoryConfigRequest, @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosPageListHistoryConfig pageListConfig = configConvert.convert(nacosPageListHistoryConfigRequest);
        return success(nacosConfigService.pageListConfigHistory(pageListConfig, envRequest.toInstanceName()));
    }

    @GetMapping("/history/info")
    @ApiOperation(value = "查询版本历史配置详情")
    public Response<NacosHistoryConfigResponse> queryDetailHistory(
        @Valid NacosDetailHistoryConfigRequest nacosDetailHistoryConfigRequest, @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosDetailHistoryConfig nacosPageListHistoryConfig = configConvert.convert(nacosDetailHistoryConfigRequest);
        return success(nacosConfigService.queryDetailHistory(nacosPageListHistoryConfig, envRequest.toInstanceName()));
    }

    @GetMapping("/history/previous")
    @ApiOperation(value = "对比历史版本详情")
    public Response<NacosHistoryConfigResponse> queryPreviousDetailHistory(
        @Valid NacosDetailHistoryConfigRequest nacosDetailHistoryConfigRequest, @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosDetailHistoryConfig nacosPageListHistoryConfig = configConvert.convert(nacosDetailHistoryConfigRequest);
        return success(
            nacosConfigService.queryPreviousDetailHistory(nacosPageListHistoryConfig, envRequest.toInstanceName()));
    }

    @GetMapping("/history/{nameSpaceId}")
    @ApiOperation(value = "根据命名空间id查询列表（用于查询下拉列表中的数据）")
    public Response<List<NacosDetailConfigResponse>>
        queryByNameSpaceId(@PathVariable(value = "nameSpaceId") String nameSpaceId, @Valid EnvRequest envRequest) {
        return success(nacosConfigService.queryByNameSpaceId(nameSpaceId, envRequest.toInstanceName()));
    }

    // 导出 get exportV2=true
    @GetMapping("/export")
    @ApiOperation(value = "导出配置信息")
    public ResponseEntity<byte[]> exportConfig(@Valid NacosExportConfigRequest nacosExportConfigRequest,
        @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosExportConfig nacosExportConfig = configConvert.convert(nacosExportConfigRequest);
        return nacosConfigService.exportConfig(nacosExportConfig, envRequest.toInstanceName());
    }

    // 导入 post import=true
    @PostMapping("/import")
    @ApiOperation(value = "导入配置")
    public Response<Map<String, Object>> importAndPublishConfig(@RequestBody MultipartFile file,
        @Valid NacosImportConfigRequest nacosImportConfigRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosImportConfig nacosImportConfig = configConvert.convert(nacosImportConfigRequest);
        nacosImportConfig.setFile(file);
        return success(nacosConfigService.importAndPublishConfig(nacosImportConfig,
            nacosImportConfigRequest.getEnvRequest().toInstanceName()));
    }

    @PostMapping("/clone")
    @ApiOperation(value = "克隆")
    public Response<Map<String, Object>>
        cloneConfig(@Valid @RequestBody NacosCloneInfoConfigRequest nacosCloneInfoConfigRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosCloneInfoConfig nacosCloneInfoConfig = configConvert.convert(nacosCloneInfoConfigRequest);
        return success(nacosConfigService.cloneConfig(nacosCloneInfoConfig,
            nacosCloneInfoConfigRequest.getEnvRequest().toInstanceName()));
    }

    @GetMapping("/listener")
    @ApiOperation(value = "服务监听")
    public Response<NacosListenerResponse> queryListenerList(@Valid NacosListenConfigRequest nacosListenConfigRequest,
        @Valid EnvRequest envRequest) {
        ConfigConvert configConvert = Mappers.getMapper(ConfigConvert.class);
        NacosListenConfig nacosListenConfig = configConvert.convert(nacosListenConfigRequest);
        return success(nacosConfigService.queryListenerList(nacosListenConfig, envRequest.toInstanceName()));
    }
}
