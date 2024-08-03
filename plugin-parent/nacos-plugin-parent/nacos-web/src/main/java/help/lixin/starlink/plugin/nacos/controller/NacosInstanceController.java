package help.lixin.starlink.plugin.nacos.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.convert.instance.InstanceConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceResponse;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceList;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceBeatRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceDetailRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceInfoRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceListRequest;
import help.lixin.starlink.plugin.nacos.service.INacosInstanceService;
import help.lixin.response.Response;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 4:01 下午
 * @Description
 */
@Api(tags = "nacos实例控制层")
@RequestMapping("/nacos/instance")
@RestController
public class NacosInstanceController {

    @Autowired
    private INacosInstanceService nacosInstanceService;

    /**
     * @Param nacosInstanceInfoRequest :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:55 下午
     * @Return: java.lang.Boolean
     * @Description 注册服务
     */
    @PostMapping("/add")
    @ApiOperation(value = "注册服务")
    public Response<Boolean> registerInstance(@Valid @RequestBody NacosInstanceInfoRequest nacosInstanceInfoRequest) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceInfo nacosInstanceInfo = instanceConvert.convert(nacosInstanceInfoRequest);
        return success(nacosInstanceService.registerInstance(nacosInstanceInfo,
            nacosInstanceInfoRequest.getEnvRequest().toInstanceName()));
    }

    /**
     * @Param nacosInstanceInfoRequest :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.Boolean
     * @Description 更新服务
     */
    @PutMapping("/edit")
    @ApiOperation(value = "更新服务")
    public Response<Boolean> updateInstance(@Valid @RequestBody NacosInstanceInfoRequest nacosInstanceInfoRequest) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceInfo nacosInstanceInfo = instanceConvert.convert(nacosInstanceInfoRequest);
        return success(nacosInstanceService.updateInstance(nacosInstanceInfo,
            nacosInstanceInfoRequest.getEnvRequest().toInstanceName()));
    }

    /**
     * @Param nacosInstanceBeat :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 发布beat
     */
    @PostMapping("/beat")
    @ApiOperation(value = "发布beat")
    public Response<String> beat(@Valid @RequestBody NacosInstanceBeatRequest nacosInstanceBeat) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceBeat instanceBeat = instanceConvert.convert(nacosInstanceBeat);
        return success(nacosInstanceService.beat(instanceBeat, nacosInstanceBeat.getEnvRequest().toInstanceName()));
    }

    /**
     * @Param nacosInstanceInfo :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 删除服务
     * @return
     */
    @PostMapping("/del")
    @ApiOperation(value = "删除服务")
    public Response<Boolean> destoryInstance(@Valid @RequestBody NacosInstanceInfoRequest nacosInstanceInfoRequest) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceInfo nacosInstanceInfo = instanceConvert.convert(nacosInstanceInfoRequest);
        return success(nacosInstanceService.destoryInstance(nacosInstanceInfo,
            nacosInstanceInfoRequest.getEnvRequest().toInstanceName()));
    }

    /**
     * @Param nacosInstanceList :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询服务列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询服务列表")
    public Response<InstanceResponse> instanceList(NacosInstanceListRequest nacosInstanceListRequest,
        @Valid EnvRequest envRequest) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceList nacosInstanceList = instanceConvert.convert(nacosInstanceListRequest);
        return success(nacosInstanceService.instanceList(nacosInstanceList, envRequest.toInstanceName()));
    }

    /**
     * @Param envRequest :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询缓存服务列表
     * @return
     */
    @GetMapping("/cache/list")
    @ApiOperation(value = "查询缓存服务列表")
    public Response<InstanceResponse> cacheList(@Valid EnvRequest envRequest) {
        return success(nacosInstanceService.cacheList(envRequest.toInstanceName()));
    }

    /**
     * @Param nameSpaceId :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询缓存服务详情
     * @return
     */
    @GetMapping("/cache/info")
    @ApiOperation(value = "查询缓存服务详情")
    public Response<InstanceResponse> cacheDetail(String nameSpaceId, @Valid EnvRequest envRequest) {
        return success(nacosInstanceService.cacheDetail(nameSpaceId, envRequest.toInstanceName()));
    }

    /**
     * @Param nacosInstanceDetail :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询服务详情
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "查询服务详情")
    public Response<InstanceDetailResponse> instanceDetail(NacosInstanceDetailRequest nacosInstanceDetailRequest,
        @Valid EnvRequest envRequest) {
        InstanceConvert instanceConvert = Mappers.getMapper(InstanceConvert.class);
        NacosInstanceDetail nacosInstanceInfo = instanceConvert.convert(nacosInstanceDetailRequest);
        return success(nacosInstanceService.instanceDetail(nacosInstanceInfo, envRequest.toInstanceName()));
    }

}
