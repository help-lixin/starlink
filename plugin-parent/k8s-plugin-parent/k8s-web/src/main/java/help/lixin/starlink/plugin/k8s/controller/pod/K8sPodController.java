package help.lixin.starlink.plugin.k8s.controller.pod;

import static help.lixin.response.Response.success;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.k8s.convert.K8sPodControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodContainerGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodLogDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.pod.PodContainerGroupVO;
import help.lixin.starlink.plugin.k8s.request.pod.PodLogVO;
import help.lixin.starlink.plugin.k8s.service.IK8sPodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/pod")
@Api(tags = "k8s pod表单")
public class K8sPodController {

    @Autowired
    private IK8sPodService k8sPodService;

    @GetMapping("/list")
    @ApiOperation(value = "查询pod表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sPodControllerConvert mapper = Mappers.getMapper(K8sPodControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sPodService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable String instanceCode) {
        return success(k8sPodService.queryNameSpaceOption(instanceCode));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除pod信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable Long id) {
        return success(k8sPodService.deletePod(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据podId查询YAML相关详情")
    public Response<String> detail(@PathVariable Long id) {
        return success(k8sPodService.queryById(id));
    }

    @GetMapping("/log")
    @ApiOperation(value = "查询pod容器日志")
    public Response<String> queryLog(PodLogVO podLogVO) {
        K8sPodControllerConvert mapper = Mappers.getMapper(K8sPodControllerConvert.class);
        PodLogDTO podLogDTO = mapper.convert(podLogVO);
        return success(k8sPodService.queryLog(podLogDTO));
    }

    @GetMapping("/containerGroup")
    @ApiOperation(value = "查询pod容器组")
    public Response<String> queryContainerGroup(PodContainerGroupVO podContainerGroupVO) {
        K8sPodControllerConvert mapper = Mappers.getMapper(K8sPodControllerConvert.class);
        PodContainerGroupDTO podLogDTO = mapper.convert(podContainerGroupVO);
        return success(k8sPodService.queryContainerGroup(podLogDTO));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{podName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> nameIsExist(@PathVariable String instanceCode, @PathVariable String podName) {
        return success(k8sPodService.nameIsExist(instanceCode, podName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sPodService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

}
