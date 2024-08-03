package help.lixin.starlink.plugin.k8s.controller.node;

import static help.lixin.response.Response.success;

import help.lixin.starlink.plugin.k8s.convert.K8sNodeControllerConvert;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.service.IK8sNodeService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kubernetes/node")
@Api(tags = "k8s node表单")
public class K8sNodeController {

    @Autowired
    private IK8sNodeService k8sNodeService;

    @GetMapping("/list")
    @ApiOperation(value = "查询node表单列表信息")
    public Response<PageResponse<KubernetesAppDTO>> pageList(BasePageListVO basePageListVO) {
        K8sNodeControllerConvert mapper = Mappers.getMapper(K8sNodeControllerConvert.class);
        BasePageListDTO basePageListDTO = mapper.convert(basePageListVO);
        return success(k8sNodeService.queryPageList(basePageListDTO));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Boolean> changeStatus(@PathVariable("status") Integer status, @PathVariable("id") Long id) {
        return success(k8sNodeService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }
}
