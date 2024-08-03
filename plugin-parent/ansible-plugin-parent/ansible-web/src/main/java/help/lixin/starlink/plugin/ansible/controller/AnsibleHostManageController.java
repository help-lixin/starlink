package help.lixin.starlink.plugin.ansible.controller;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.ansible.convert.AnsibleHostControllerConvert;
import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.dto.CreateHostDTO;
import help.lixin.starlink.plugin.ansible.dto.HostPageListDTO;
import help.lixin.starlink.plugin.ansible.request.CreateHostVO;
import help.lixin.starlink.plugin.ansible.request.HostPageListVO;
import help.lixin.starlink.plugin.ansible.service.IAnsibleHostService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static help.lixin.response.Response.success;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 下午4:50
 * @Description
 */
@RestController
@RequestMapping("/ansible/host")
@Api(tags = "ansible主机控制层")
public class AnsibleHostManageController {

    @Autowired
    private IAnsibleHostService ansibleHostService;


    @GetMapping(value = "/list")
    public Response<PageResponse<AnsibleHostManage>> pageList(HostPageListVO hostPageListVO){
        AnsibleHostControllerConvert mapper = Mappers.getMapper(AnsibleHostControllerConvert.class);
        HostPageListDTO hostPageListDTO = mapper.convert(hostPageListVO);

        return success(ansibleHostService.pageList(hostPageListDTO));
    }

    @PostMapping(value = "/add")
    public Response<Boolean> saveHost(@Valid @RequestBody CreateHostVO createHostVO){
        AnsibleHostControllerConvert mapper = Mappers.getMapper(AnsibleHostControllerConvert.class);
        CreateHostDTO createHostDTO = mapper.convert(createHostVO);
        createHostDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(ansibleHostService.saveHost(createHostDTO));
    }

    @PutMapping(value = "/changeStatus/{id}/{status}")
    public Response<Boolean> changeStatus(@PathVariable Long id, @PathVariable Integer status){
        return success(ansibleHostService.changeStatus(id,status, UserContext.getUser().getUserName()));
    }

    @GetMapping("/checkServerName/{serverName}/{sshInstanceCode}")
    public Response<Boolean> checkServerName(@PathVariable String serverName,@PathVariable String sshInstanceCode){
        return success(ansibleHostService.checkServerName(serverName,sshInstanceCode));
    }

    @GetMapping("/queryDetail/{id}")
    public Response<AnsibleHostManage> queryDetail(@PathVariable Long id){
        return success(ansibleHostService.queryDetail(id));
    }

    @GetMapping("/checkInstance/{sshInstanceCode}")
    public Response<Boolean> checkInstance(@PathVariable String sshInstanceCode){
        return success(ansibleHostService.checkInstance(sshInstanceCode));
    }

    @DeleteMapping("/del/{id}")
    public Response<Boolean> checkInstance(@PathVariable Long id){
        return success(ansibleHostService.removeHost(id));
    }

}
