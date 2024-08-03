package help.lixin.starlink.plugin.k8s.controller.deployment;

import static help.lixin.response.Response.success;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.k8s.convert.K8sDeploymentControllerConvert;
import help.lixin.starlink.plugin.k8s.convert.K8sPodControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.PodGroupResponseDTO;
import help.lixin.starlink.plugin.k8s.dto.PodGroupResultDTO;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentPodGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentUpdateReplicasDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.deployment.CreateDeploymentVO;
import help.lixin.starlink.plugin.k8s.request.deployment.DeploymentPodGroupVO;
import help.lixin.starlink.plugin.k8s.request.deployment.DeploymentUpdateReplicasVO;
import help.lixin.starlink.plugin.k8s.service.IK8sDeploymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/deployment")
@Api(tags = "k8s deployment表单")
public class K8sDeploymentController {

    @Autowired
    private IK8sDeploymentService k8sDeploymentService;

    @GetMapping("/list")
    @ApiOperation(value = "查询deployment表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sDeploymentControllerConvert mapper = Mappers.getMapper(K8sDeploymentControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sDeploymentService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable("instanceCode") String instanceCode) {
        return success(k8sDeploymentService.queryNameSpaceOption(instanceCode));
    }

    @PostMapping("/add")
    @ApiOperation(value = "保存deployment信息")
    public Response<PageResponse<KubernetesApp>> save(@RequestBody CreateDeploymentVO createDeploymentVO) {
        K8sDeploymentControllerConvert mapper = Mappers.getMapper(K8sDeploymentControllerConvert.class);
        K8sAppDTO k8sAppDTO = mapper.convert(createDeploymentVO);
        k8sAppDTO.setUserName(UserContext.getUser().getUserName());
        return success(k8sDeploymentService.saveDeployment(k8sAppDTO));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除deployment信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable("id") Long id) {
        return success(k8sDeploymentService.deleteDeployment(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据deploymentId查询YAML相关详情")
    public Response<String> detail(@PathVariable("id") Long id) {
        return success(k8sDeploymentService.queryById(id));
    }

    @GetMapping("/deploymentNameIsExist/{instanceCode}/{deploymentName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> deploymentNameIsExist(@PathVariable("instanceCode") String instanceCode,
        @PathVariable("deploymentName") String deploymentName) {
        return success(k8sDeploymentService.nameIsExist(instanceCode, deploymentName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sDeploymentService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/podGroup")
    @ApiOperation(value = "查询deployment pod容器组")
    public Response<PodGroupResultDTO> queryPodGroup(DeploymentPodGroupVO deploymentPodGroupVO) {
        K8sPodControllerConvert mapper = Mappers.getMapper(K8sPodControllerConvert.class);
        DeploymentPodGroupDTO podLogDTO = mapper.convert(deploymentPodGroupVO);
        return success(k8sDeploymentService.queryDeploymentPodGroup(podLogDTO));
    }

    @PutMapping("/updateReplicas")
    @ApiOperation(value = "更新副本数量")
    public Response<List<PodGroupResponseDTO>> updateReplicas(DeploymentUpdateReplicasVO deploymentUpdateReplicasVO) {
        K8sPodControllerConvert mapper = Mappers.getMapper(K8sPodControllerConvert.class);
        DeploymentUpdateReplicasDTO deploymentUpdateReplicasDTO = mapper.convert(deploymentUpdateReplicasVO);
        return success(k8sDeploymentService.updateReplicas(deploymentUpdateReplicasDTO));
    }

}
