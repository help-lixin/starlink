package help.lixin.starlink.plugin.nacos.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.convert.servicemanage.ServiceManageConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceDetailInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServiceFormRequest;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServiceInfoRequest;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServicePageListRequest;
import help.lixin.starlink.plugin.nacos.service.INacosServiceManageService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/24 11:04 上午
 * @Description
 */
@Api(tags = "nacos服务管理控制层")
@RequestMapping("/nacos/service")
@RestController
public class NacosServiceManageController {

    @Autowired
    private INacosServiceManageService nacosServiceManageService;

    @GetMapping("/list")
    @ApiOperation(value = "查询服务管理列表")
    public Response<PageResponse<NacosServiceDetailInfo>>
        pageList(NacosServicePageListRequest nacosServicePageListRequest, @Valid EnvRequest envRequest) {
        ServiceManageConvert convert = Mappers.getMapper(ServiceManageConvert.class);
        NacosServicePageList nacosServicePageList = convert.convert(nacosServicePageListRequest);
        return success(nacosServiceManageService.pageList(nacosServicePageList, envRequest.toInstanceName()));
    }

    @GetMapping("/info")
    @ApiOperation(value = "查询服务详情")
    public Response<String> detail(NacosServiceInfoRequest nacosServiceInfoRequest, @Valid EnvRequest envRequest) {
        ServiceManageConvert convert = Mappers.getMapper(ServiceManageConvert.class);
        NacosServiceInfo serviceInfo = convert.convert(nacosServiceInfoRequest);
        return success(nacosServiceManageService.detail(serviceInfo, envRequest.toInstanceName()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建命名空间")
    public Response<Boolean> create(@Valid @RequestBody NacosServiceFormRequest nacosServiceFormRequest) {
        ServiceManageConvert convert = Mappers.getMapper(ServiceManageConvert.class);
        NacosServiceForm nacosServiceForm = convert.convert(nacosServiceFormRequest);
        return success(nacosServiceManageService.create(nacosServiceForm,
            nacosServiceFormRequest.getEnvRequest().toInstanceName()));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新命名空间")
    public Response<Boolean> update(@Valid @RequestBody NacosServiceFormRequest nacosServiceFormRequest) {
        ServiceManageConvert convert = Mappers.getMapper(ServiceManageConvert.class);
        NacosServiceForm nacosServiceForm = convert.convert(nacosServiceFormRequest);
        return success(nacosServiceManageService.update(nacosServiceForm,
            nacosServiceFormRequest.getEnvRequest().toInstanceName()));
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除命名空间")
    public Response<Boolean> deleteNamespace(@Valid NacosServiceInfoRequest nacosServiceInfoRequest,
        @Valid EnvRequest envRequest) {
        ServiceManageConvert convert = Mappers.getMapper(ServiceManageConvert.class);
        NacosServiceInfo nacosServiceInfo = convert.convert(nacosServiceInfoRequest);
        return success(nacosServiceManageService.remove(nacosServiceInfo, envRequest.toInstanceName()));
    }
}
