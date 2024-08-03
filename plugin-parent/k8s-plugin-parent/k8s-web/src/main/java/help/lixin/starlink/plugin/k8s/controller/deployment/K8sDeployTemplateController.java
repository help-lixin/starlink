package help.lixin.starlink.plugin.k8s.controller.deployment;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.k8s.convert.K8sDeploymentTemplateControllerConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesDeployTemplate;
import help.lixin.starlink.plugin.k8s.dto.DeployTemplateOption;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.CreateDeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.DeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.request.deploytemplate.CreateDeployTemplateVO;
import help.lixin.starlink.plugin.k8s.request.deploytemplate.DeployTemplateVO;
import help.lixin.starlink.plugin.k8s.service.IK8sDeployTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static help.lixin.response.Response.fail;
import static help.lixin.response.Response.success;
import static org.mapstruct.factory.Mappers.getMapper;

@RestController
@RequestMapping("/kubernetes/deployment/yaml/template")
@Api(tags = "k8s deployment 模板")
public class K8sDeployTemplateController {

    @Autowired
    private IK8sDeployTemplateService k8sDeployTemplateService;

    @GetMapping("/list")
    @ApiOperation(value = "查询deployment表单列表信息")
    public Response<PageResponse<KubernetesDeployTemplate>> pageList(DeployTemplateVO vo) {
        K8sDeploymentTemplateControllerConvert mapper = Mappers.getMapper(K8sDeploymentTemplateControllerConvert.class);
        DeployTemplateDTO dto = mapper.convert(vo);
        return success(k8sDeployTemplateService.queryPageList(dto));
    }

    @GetMapping("/query/{id}")
    @ApiOperation(value = "获取模板详细")
    public Response<KubernetesDeployTemplate> get(@PathVariable Long id) {
        return success(k8sDeployTemplateService.get(id));
    }

    @ApiOperation(value = "删除模板")
    @DeleteMapping("/del/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        return success(k8sDeployTemplateService.delete(id));
    }

    @ApiOperation(value = "修改状态")
    @PutMapping("/changeStatus/{id}/{status}")
    public Response<Integer> changeStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(k8sDeployTemplateService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @ApiOperation(value = "创建模板")
    @PostMapping("/add")
    public Response<Integer> createProject(@Valid @RequestBody CreateDeployTemplateVO vo) {
        K8sDeploymentTemplateControllerConvert mapper = getMapper(K8sDeploymentTemplateControllerConvert.class);
        CreateDeployTemplateDTO dto = mapper.convert(vo);
        dto.setCreateBy(UserContext.getUser().getUserName());
        dto.setUpdateBy(UserContext.getUser().getUserName());
        if (null != dto.getDeployName() && //
                !k8sDeployTemplateService.checkDeployNameUnique(dto)) {
            return fail("新增模板名称'" + dto.getDeployName() + "'失败,模板名称已经存在");
        }
        return success(k8sDeployTemplateService.create(dto));
    }

    @GetMapping("/options")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<DeployTemplateOption>> options() {
        return success(k8sDeployTemplateService.querydeployTemplateOptions());
    }
}
