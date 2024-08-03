package help.lixin.starlink.plugin.k8s.controller.service;

import static help.lixin.response.Response.success;

import java.util.List;

import help.lixin.starlink.plugin.k8s.convert.K8sJobControllerConvert;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.k8s.convert.K8sServiceControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.service.CreateServiceVO;
import help.lixin.starlink.plugin.k8s.service.IK8sServiceService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/service")
@Api(tags = "k8s service表单")
public class K8sServiceController {

    @Autowired
    private IK8sServiceService k8sServiceService;

    @GetMapping("/list")
    @ApiOperation(value = "查询service表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sJobControllerConvert mapper = Mappers.getMapper(K8sJobControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sServiceService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable String instanceCode) {
        return success(k8sServiceService.queryNameSpaceOption(instanceCode));
    }

    @PostMapping("/add")
    @ApiOperation(value = "保存service信息")
    public Response<PageResponse<KubernetesApp>> save(@RequestBody CreateServiceVO createServiceVO) {
        K8sServiceControllerConvert mapper = Mappers.getMapper(K8sServiceControllerConvert.class);
        K8sAppDTO k8sAppDTO = mapper.convert(createServiceVO);
        k8sAppDTO.setUserName(UserContext.getUser().getUserName());
        return success(k8sServiceService.saveService(k8sAppDTO));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除service信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable Long id) {
        return success(k8sServiceService.deleteService(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据serviceId查询YAML相关详情")
    public Response<String> detail(@PathVariable Long id) {
        return success(k8sServiceService.queryById(id));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{serviceName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> nameIsExist(@PathVariable String instanceCode, @PathVariable String serviceName) {
        return success(k8sServiceService.nameIsExist(instanceCode, serviceName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sServiceService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

}
