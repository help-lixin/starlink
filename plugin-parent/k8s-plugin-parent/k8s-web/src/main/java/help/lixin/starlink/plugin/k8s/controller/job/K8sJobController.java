package help.lixin.starlink.plugin.k8s.controller.job;

import static help.lixin.response.Response.success;

import java.util.List;

import help.lixin.starlink.plugin.k8s.convert.K8sJobControllerConvert;
import help.lixin.starlink.plugin.k8s.request.job.CreateJobVO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.service.IK8sJobService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/job")
@Api(tags = "k8s job表单")
public class K8sJobController {

    @Autowired
    private IK8sJobService k8sJobService;

    @GetMapping("/list")
    @ApiOperation(value = "查询job表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sJobControllerConvert mapper = Mappers.getMapper(K8sJobControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sJobService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable String instanceCode) {
        return success(k8sJobService.queryNameSpaceOption(instanceCode));
    }

    @PostMapping("/add")
    @ApiOperation(value = "保存job信息")
    public Response<PageResponse<KubernetesApp>> save(@RequestBody CreateJobVO createJobVO) {
        K8sJobControllerConvert mapper = Mappers.getMapper(K8sJobControllerConvert.class);
        K8sAppDTO k8sAppDTO = mapper.convert(createJobVO);
        k8sAppDTO.setUserName(UserContext.getUser().getUserName());
        return success(k8sJobService.saveJob(k8sAppDTO));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除job信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable Long id) {
        return success(k8sJobService.deleteJob(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据jobId查询YAML相关详情")
    public Response<String> detail(@PathVariable Long id) {
        return success(k8sJobService.queryById(id));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{jobName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> nameIsExist(@PathVariable String instanceCode, @PathVariable String jobName) {
        return success(k8sJobService.nameIsExist(instanceCode, jobName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sJobService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

    @PutMapping("/restart/{id}")
    @ApiOperation(value = "重新运行job")
    public Response<Boolean> restart(@PathVariable("id") Long id) {
        return success(k8sJobService.restart(id, UserContext.getUser().getUserName()));
    }
}
