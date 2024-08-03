package help.lixin.starlink.plugin.eureka.controller;

import static help.lixin.response.Response.fail;
import static help.lixin.response.Response.success;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

import help.lixin.plugin.eureka.api.model.EurekaInstanceStatus;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.eureka.request.BackServiceRequest;
import help.lixin.starlink.plugin.eureka.request.InstanceInfoRequest;
import help.lixin.starlink.plugin.eureka.request.InstanceRequest;
import help.lixin.starlink.plugin.eureka.request.UpdateMetaRequest;
import help.lixin.starlink.plugin.eureka.service.IEurekaClientService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/26 5:51 下午
 * @Description
 */
@RestController
@RequestMapping("/eureka/client")
@Api(tags = "eureka服务控制层")
public class EurekaClientController {

    @Autowired
    private IEurekaClientService eurekaClientService;

    /**
     * 注册一个应用实例
     *
     * @param instanceInfoRequest 应用信息
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册一个应用实例")
    public Response<Boolean> registerInstance(@Valid @RequestBody InstanceInfoRequest instanceInfoRequest) {
        DataCenterInfo.Name name = DataCenterInfo.Name.valueOf(instanceInfoRequest.getDataCenterInfo().name());
        MyDataCenterInfo myDataCenterInfo = new MyDataCenterInfo(name);
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder().setInstanceId(instanceInfoRequest.getInstanceId())
            .setAppName(instanceInfoRequest.getAppName()).setIPAddr(instanceInfoRequest.getIpAddr())
            .setHostName(instanceInfoRequest.getHostName()).setPort(instanceInfoRequest.getPort())
            .setVIPAddress(instanceInfoRequest.getVipAddress()).setDataCenterInfo(myDataCenterInfo)
            .setMetadata(instanceInfoRequest.getMetadata()).setSecurePort(instanceInfoRequest.getSecurePort())
            .setHomePageUrl(instanceInfoRequest.getHomePageUrl(), null)
            .setStatusPageUrl(instanceInfoRequest.getStatusPageUrl(), null)
            .setHealthCheckUrls(instanceInfoRequest.getHealthCheckUrl(), null, null).build();
        return success(
            eurekaClientService.registerInstance(instanceInfo, instanceInfoRequest.getEnvRequest().toInstanceName()));
    }

    /**
     * 删除一个实例
     * 
     * @param appName 应用名
     * @param instanceId 实例id
     * @return
     */
    @DeleteMapping("/{instanceId}/{appName}")
    @ApiOperation(value = "删除一个实例")
    public Response<Boolean> deleteInstance(@PathVariable String appName, @PathVariable String instanceId,
        @Valid EnvRequest envRequest) {
        return success(eurekaClientService.deleteInstance(appName, instanceId, envRequest.toInstanceName()));
    }

    /**
     * 发送一个应用实例心跳
     * 
     * @param instanceRequest 应用名&实例id
     * @return
     */
    @GetMapping("/heartbeat")
    @ApiOperation(value = "发送一个应用实例心跳")
    public Response<Boolean> heartbeat(@Valid InstanceRequest instanceRequest, @Valid EnvRequest envRequest) {
        return success(eurekaClientService.heartbeat(instanceRequest.getAppName(), instanceRequest.getInstanceId(),
            envRequest.toInstanceName()));
    }

    /**
     * 列出所有应用
     *
     * @return json/xml
     */
    @GetMapping("/applications")
    @ApiOperation(value = " 列出所有应用")
    public Response<Applications> applications(@Valid EnvRequest envRequest) {
        return success(eurekaClientService.applications(envRequest.toInstanceName()));
    }

    /**
     * 列出应用下的所有实例
     *
     * @param appName 应用名
     * @return json/xml
     */
    @GetMapping("/application/{appName}")
    @ApiOperation(value = " 列出应用下的所有实例")
    public Response<Application> application(@PathVariable String appName, @Valid EnvRequest envRequest) {
        return success(eurekaClientService.application(appName, envRequest.toInstanceName()));
    }

    /**
     * 查询指定的实例
     *
     * @param instanceRequest 应用名&实例id
     * @return json/xml
     */
    @GetMapping("/instance")
    @ApiOperation(value = " 查询指定的实例")
    public Response<InstanceInfo> instance(InstanceRequest instanceRequest, @Valid EnvRequest envRequest) {
        String appName = instanceRequest.getAppName();
        String instanceId = instanceRequest.getInstanceId();

        if (StringUtils.isBlank(appName)) {
            return success(eurekaClientService.instance(instanceId, envRequest.toInstanceName()));
        } else if (StringUtils.isNotBlank(appName) && StringUtils.isNotBlank(instanceId)) {
            return success(eurekaClientService.instance(appName, instanceId, envRequest.toInstanceName()));
        }

        return fail("参数不能为空", 400);
    }

    /**
     * 中止/失效一个实例
     * 
     * @param instanceRequest 应用名&实例id
     * @return
     */
    @DeleteMapping("/out/service")
    @ApiOperation(value = " 中止/失效一个实例")
    public Response<Boolean> outOfService(@Valid InstanceRequest instanceRequest, @Valid EnvRequest envRequest) {
        return success(eurekaClientService.outOfService(instanceRequest.getAppName(), instanceRequest.getInstanceId(),
            envRequest.toInstanceName()));
    }

    /**
     * 恢复一个实例到指定状态
     * 
     * @param backServiceRequest 应用名&实例id&实例状态
     * @return
     */
    @PutMapping("/back/service")
    @ApiOperation(value = " 恢复一个实例到指定状态")
    public Response<Boolean> backInService(@Valid BackServiceRequest backServiceRequest) {
        return success(eurekaClientService.backInService(backServiceRequest.getAppName(),
            backServiceRequest.getInstanceId(), EurekaInstanceStatus.valueOf(backServiceRequest.getStatus().toString()),
            backServiceRequest.getEnvRequest().toInstanceName()));
    }

    /**
     * 更新实例的元数据
     * 
     * @return
     */
    @PutMapping("/meta")
    @ApiOperation(value = " 更新实例的元数据")
    public Response<Boolean> updateMetadata(@Valid UpdateMetaRequest updateMetaRequest) {
        String appName = updateMetaRequest.getAppName();
        EnvRequest envRequest = updateMetaRequest.getEnvRequest();
        String instanceId = updateMetaRequest.getInstanceId();
        String key = updateMetaRequest.getKey();
        String value = updateMetaRequest.getValue();

        return success(
            eurekaClientService.updateMetadata(appName, instanceId, key, value, envRequest.toInstanceName()));
    }

    /**
     * 在一个特定的vip地址查询所有实例
     *
     * @param vipAddress vip地址
     * @return json/xml
     */
    @GetMapping("/vips/{vipAddress}")
    @ApiOperation(value = "在一个特定的vip地址查询所有实例")
    public Response<Applications> vips(@PathVariable String vipAddress, @Valid EnvRequest envRequest) {
        return success(eurekaClientService.vips(vipAddress, envRequest.toInstanceName()));
    }

    /**
     * 在一个特定的安全vip地址查询所有实例
     *
     * @param svipAddress 安全的vip地址
     * @return json/xml
     */
    @GetMapping("/svips/{svipAddress}")
    @ApiOperation(value = "在一个特定的安全vip地址查询所有实例")
    public Response<Applications> svips(@PathVariable String svipAddress, @Valid EnvRequest envRequest) {
        return success(eurekaClientService.svips(svipAddress, envRequest.toInstanceName()));
    }
}
