package help.lixin.starlink.plugin.k8s.controller.secret;

import static help.lixin.response.Response.success;

import help.lixin.starlink.plugin.k8s.dto.secret.SecretOptionDTO;
import help.lixin.starlink.plugin.k8s.service.IK8sSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Deprecated
@RestController
@RequestMapping("/kubernetes/secret")
@Api(tags = "k8s deployment表单")
public class K8sSecretController {

    @Autowired
    private IK8sSecretService secretService;

    @GetMapping("/option/{instanceCode}/{nameSpace}")
    @ApiOperation(value = "查询密文下拉列表信息")
    public Response<SecretOptionDTO> querySecrets(@PathVariable String instanceCode, @PathVariable String nameSpace) {
        return success(secretService.querySecrets(instanceCode, nameSpace));
    }

}
