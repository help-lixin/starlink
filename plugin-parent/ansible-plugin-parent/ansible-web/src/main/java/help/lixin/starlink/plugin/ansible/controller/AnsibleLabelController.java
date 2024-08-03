package help.lixin.starlink.plugin.ansible.controller;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.ansible.convert.AnsibleLabelControllerConvert;
import help.lixin.starlink.plugin.ansible.dto.CreateLabelDTO;
import help.lixin.starlink.plugin.ansible.dto.LabelPageListDTO;
import help.lixin.starlink.plugin.ansible.dto.UpdateInventoryDTO;
import help.lixin.starlink.plugin.ansible.request.CreateLabelVO;
import help.lixin.starlink.plugin.ansible.request.LabelPageListVO;
import help.lixin.starlink.plugin.ansible.request.UpdateInventoryVO;
import help.lixin.starlink.plugin.ansible.service.IAnsibleLabelService;
import io.swagger.annotations.Api;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static help.lixin.response.Response.success;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:36
 * @Description
 */

@RestController
@RequestMapping("/ansible/label")
@Api(tags = "ansible标签控制层")
public class AnsibleLabelController {

    @Autowired
    private IAnsibleLabelService ansibleLabelService;

    @GetMapping(value = "/list")
    public Response<Boolean> pageList(LabelPageListVO labelPageListVO){
        AnsibleLabelControllerConvert mapper = Mappers.getMapper(AnsibleLabelControllerConvert.class);
        LabelPageListDTO labelPageListDTO = mapper.convert(labelPageListVO);
        return success( ansibleLabelService.pageList(labelPageListDTO) );
    }

    @PostMapping(value = "/add")
    public Response<Boolean> add(@RequestBody @Valid CreateLabelVO createLabelVO){
        AnsibleLabelControllerConvert mapper = Mappers.getMapper(AnsibleLabelControllerConvert.class);
        CreateLabelDTO createLabelDTO = mapper.convert(createLabelVO);

        createLabelDTO.setCreateBy(UserContext.getUser().getUserName());
        return success( ansibleLabelService.createLabel(createLabelDTO) );
    }

    @PutMapping(value = "/update")
    public Response<Boolean> update(@RequestBody @Valid UpdateInventoryVO updateInventoryVO){
        AnsibleLabelControllerConvert mapper = Mappers.getMapper(AnsibleLabelControllerConvert.class);
        UpdateInventoryDTO updateInventoryDTO = mapper.convert(updateInventoryVO);

        return success( ansibleLabelService.updateInventory(updateInventoryDTO) );
    }

    @PutMapping(value = "/changeStatus/{id}/{status}")
    public Response<Boolean> changeStatus(@PathVariable Long id ,@PathVariable Integer status){
        return success( ansibleLabelService.changeStatus( id, status, UserContext.getUser().getUserName()) );
    }

    @GetMapping(value = "/checkLabelKey/{labelKey}")
    public Response<Boolean> checkLabelKey(@PathVariable String labelKey){
        return success( ansibleLabelService.checkKey(labelKey) );
    }

    @GetMapping(value = "/queryLabelDetail/{id}")
    public Response<List<String>> queryLabelDetail(@PathVariable Long id){
        return success( ansibleLabelService.queryLabelDetail(id));
    }

    @DeleteMapping(value = "/del/{labelId}")
    public Response<List<String>> removeLabel(@PathVariable Long labelId){
        return success( ansibleLabelService.removeLabel(labelId));
    }

}
