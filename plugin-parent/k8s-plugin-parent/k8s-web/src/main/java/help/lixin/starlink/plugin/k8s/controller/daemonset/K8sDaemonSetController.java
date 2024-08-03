package help.lixin.starlink.plugin.k8s.controller.daemonset;

import static help.lixin.response.Response.success;

import java.util.List;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.k8s.convert.K8sDaemonSetControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.daemonset.CreateDaemonSetVO;
import help.lixin.starlink.plugin.k8s.service.IK8sDaemonSetService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/daemonset")
@Api(tags = "k8s daemonset表单")
public class K8sDaemonSetController {

    @Autowired
    private IK8sDaemonSetService k8sDaemonSetService;

    @GetMapping("/list")
    @ApiOperation(value = "查询daemonset表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sDaemonSetControllerConvert mapper = Mappers.getMapper(K8sDaemonSetControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sDaemonSetService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable("instanceCode") String instanceCode) {
        return success(k8sDaemonSetService.queryNameSpaceOption(instanceCode));
    }

    @PostMapping("/add")
    @ApiOperation(value = "保存daemonset信息")
    public Response<PageResponse<KubernetesApp>> save(@RequestBody CreateDaemonSetVO createDaemonSetVO) {
        K8sDaemonSetControllerConvert mapper = Mappers.getMapper(K8sDaemonSetControllerConvert.class);
        K8sAppDTO k8sAppDTO = mapper.convert(createDaemonSetVO);
        k8sAppDTO.setUserName(UserContext.getUser().getUserName());
        return success(k8sDaemonSetService.saveDaemonSet(k8sAppDTO));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除daemonset信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable("id") Long id) {
        return success(k8sDaemonSetService.deleteDaemonSet(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据daemonsetId查询YAML相关详情")
    public Response<String> detail(@PathVariable("id") Long id) {
        return success(k8sDaemonSetService.queryById(id));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{daemonsetName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> nameIsExist(@PathVariable("instanceCode") String instanceCode, @PathVariable("daemonsetName") String daemonsetName) {
        return success(k8sDaemonSetService.nameIsExist(instanceCode, daemonsetName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sDaemonSetService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

}
