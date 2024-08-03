package help.lixin.starlink.plugin.k8s.controller.namespace;

import static help.lixin.response.Response.success;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.k8s.convert.K8sNameSpaceControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.NameSpacePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.SaveNameSpaceDTO;
import help.lixin.starlink.plugin.k8s.request.namespace.NameSpacePageListVO;
import help.lixin.starlink.plugin.k8s.request.namespace.SaveNameSpaceVO;
import help.lixin.starlink.plugin.k8s.service.IK8SNameSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/nameSpace")
@Api(tags = "k8s nameSpace表单")
public class K8sNameSpaceController {

    @Autowired
    private IK8SNameSpaceService nameSpaceService;

    @PostMapping("/add")
    @ApiOperation(value = "新增NameSpace")
    public Response<Boolean> save(@RequestBody SaveNameSpaceVO saveNameSpaceVO) {
        K8sNameSpaceControllerConvert mapper = Mappers.getMapper(K8sNameSpaceControllerConvert.class);
        SaveNameSpaceDTO saveNameSpaceDTO = mapper.convert(saveNameSpaceVO);
        return success(nameSpaceService.save(saveNameSpaceDTO));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询nameSpace表单列表信息")
    public Response<PageResponse<KubernetesNameSpace>> pageList(NameSpacePageListVO nameSpacePageListVO) {
        K8sNameSpaceControllerConvert mapper = Mappers.getMapper(K8sNameSpaceControllerConvert.class);
        NameSpacePageListDTO nameSpacePageListDTO = mapper.convert(nameSpacePageListVO);
        return success(nameSpaceService.queryPageList(nameSpacePageListDTO));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable Integer status, @PathVariable Long id) {
        return success(nameSpaceService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除nameSpace信息")
    public Response<Boolean> delete(@PathVariable Long id) {
        return success(nameSpaceService.deleteNameSpace(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{name}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> deploymentNameIsExist(@PathVariable("instanceCode") String instanceCode, @PathVariable("name") String name) {
        return success(nameSpaceService.nameIsExist(instanceCode, name));
    }
}
