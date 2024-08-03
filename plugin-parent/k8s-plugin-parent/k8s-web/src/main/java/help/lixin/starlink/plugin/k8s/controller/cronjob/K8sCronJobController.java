package help.lixin.starlink.plugin.k8s.controller.cronjob;

import static help.lixin.response.Response.success;

import java.util.List;

import help.lixin.starlink.plugin.k8s.convert.K8sCronJobControllerConvert;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.starlink.plugin.k8s.request.cronjob.CreateCronJobVO;
import help.lixin.starlink.plugin.k8s.service.IK8sCronJobService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/cronjob")
@Api(tags = "k8s cronjob表单")
public class K8sCronJobController {

    @Autowired
    private IK8sCronJobService k8sCronJobService;

    @GetMapping("/list")
    @ApiOperation(value = "查询cronjob表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sCronJobControllerConvert mapper = Mappers.getMapper(K8sCronJobControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sCronJobService.queryPageList(basePageListDTO));
    }

    @GetMapping("/nameSpace/list/{instanceCode}")
    @ApiOperation(value = "查询nameSpace下拉列表信息")
    public Response<List<NameSpaceOptionDTO>> pageList(@PathVariable("instanceCode") String instanceCode) {
        return success(k8sCronJobService.queryNameSpaceOption(instanceCode));
    }

    @PostMapping("/add")
    @ApiOperation(value = "保存cronjob信息")
    public Response<PageResponse<KubernetesApp>> save(@RequestBody CreateCronJobVO createCronJobVO) {
        K8sCronJobControllerConvert mapper = Mappers.getMapper(K8sCronJobControllerConvert.class);
        K8sAppDTO k8sAppDTO = mapper.convert(createCronJobVO);
        k8sAppDTO.setUserName(UserContext.getUser().getUserName());
        return success(k8sCronJobService.saveCronJob(k8sAppDTO));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除cronjob信息")
    public Response<PageResponse<KubernetesApp>> delete(@PathVariable("id") Long id) {
        return success(k8sCronJobService.deleteCronJob(id, UserContext.getUser().getUserName()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据cronjobId查询YAML相关详情")
    public Response<String> detail(@PathVariable("id") Long id) {
        return success(k8sCronJobService.queryById(id));
    }

    @GetMapping("/nameIsExist/{instanceCode}/{cronjobName}")
    @ApiOperation(value = "校验名称是否存在")
    public Response<Boolean> nameIsExist(@PathVariable("instanceCode") String instanceCode, @PathVariable("cronjobName") String cronjobName) {
        return success(k8sCronJobService.nameIsExist(instanceCode, cronjobName));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sCronJobService.changeRunStatus(status, id, UserContext.getUser().getUserName()));
    }
}
