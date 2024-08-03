package help.lixin.starlink.plugin.nacos.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.convert.namespace.NameSpaceConvert;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.api.model.namespace.NamespaceDetail;
import help.lixin.starlink.plugin.nacos.request.namespace.NacosNameSpaceSaveRequest;
import help.lixin.starlink.plugin.nacos.service.INacosNameSpaceService;
import help.lixin.response.Response;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 6:32 下午
 * @Description
 */
@Api(tags = "nacos命名空间控制层")
@RequestMapping("/nacos/namespace")
@RestController
public class NacosNameSpaceController {

    @Autowired
    private INacosNameSpaceService nacosNameSpaceService;

    @GetMapping("/list")
    @ApiOperation(value = "查询命名空间列表")
    public Response<List<NamespaceDetail>> nameSpaseList(String nameSpaceId, @Valid EnvRequest envRequest) {
        return success(nacosNameSpaceService.nameSpaseList(nameSpaceId, envRequest.toInstanceName()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建命名空间")
    public Response<Boolean> createNamespace(@Valid @RequestBody NacosNameSpaceSaveRequest nacosNameSpaceSaveRequest) {
        NameSpaceConvert convert = Mappers.getMapper(NameSpaceConvert.class);
        NacosNameSpaceSave nameSpaceSave = convert.convert(nacosNameSpaceSaveRequest);
        return success(nacosNameSpaceService.createNamespace(nameSpaceSave,
            nacosNameSpaceSaveRequest.getEnvRequest().toInstanceName()));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新命名空间")
    public Response<Boolean> updateNamespace(@Valid @RequestBody NacosNameSpaceSaveRequest nacosNameSpaceSaveRequest) {
        NameSpaceConvert convert = Mappers.getMapper(NameSpaceConvert.class);
        NacosNameSpaceSave nameSpaceSave = convert.convert(nacosNameSpaceSaveRequest);
        return success(nacosNameSpaceService.updateNamespace(nameSpaceSave,
            nacosNameSpaceSaveRequest.getEnvRequest().toInstanceName()));
    }

    @DeleteMapping("/del/{nameSpaceId}")
    @ApiOperation(value = "删除命名空间")
    public Response<Boolean> deleteNamespace(@PathVariable("nameSpaceId") String nameSpaceId,
        @Valid EnvRequest envRequest) {
        return success(nacosNameSpaceService.deleteNamespace(nameSpaceId, envRequest.toInstanceName()));
    }
}
