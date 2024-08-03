package help.lixin.starlink.plugin.ssh.controller;

import static help.lixin.response.Response.success;

import java.util.List;
import java.util.stream.Collectors;

import help.lixin.starlink.plugin.ssh.convert.SshLabelConvert;
import help.lixin.starlink.plugin.ssh.request.PageListSshLabelVO;
import help.lixin.starlink.plugin.ssh.request.SaveSshLabelVO;
import help.lixin.starlink.plugin.ssh.response.SSHSelectOption;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.jsch.service.ISshLabelService;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.jsch.dto.PageListSshLabelDTO;
import help.lixin.starlink.plugin.jsch.dto.SaveSshLabelDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/23 下午2:37
 * @Description
 */
@RestController
@RequestMapping("/ssh/label")
@Api(tags = "ssh标签管理")
public class SshLabelController {

    @Autowired
    private ISshLabelService sshLabelService;

    @PostMapping("/add")
    @ApiOperation(value = "添加SSH")
    public Response<Integer> addSSHLabel(@RequestBody SaveSshLabelVO saveSshLabelVO) {
        SshLabelConvert mapper = Mappers.getMapper(SshLabelConvert.class);
        SaveSshLabelDTO saveSshLabelDTO = mapper.convert(saveSshLabelVO);
        saveSshLabelDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(sshLabelService.save(saveSshLabelDTO));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询SSH标签列表信息")
    public Response<PageResponse> pageList(PageListSshLabelVO pageListSSHLabelVO) {
        SshLabelConvert mapper = Mappers.getMapper(SshLabelConvert.class);
        PageListSshLabelDTO pageListSSHLabelDTO = mapper.convert(pageListSSHLabelVO);
        return success(sshLabelService.pageList(pageListSSHLabelDTO));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询SSH标签信息")
    public Response<List<String>> queryBuildInfoInfo(@PathVariable Long id) {
        return success(sshLabelService.queryInfo(id));
    }

    @GetMapping("/optionSelect")
    @ApiOperation(value = "获取SSH标签框列表")
    @ApiResponses({@ApiResponse(code = 200, message = "获取SSH标签下拉框列表成功")})
    public Response<List<SSHSelectOption>> optionSelect() {
        List<SSHSelectOption> selectOptions = sshLabelService.getAll() //
            .stream().map((item) -> {
                SSHSelectOption option = new SSHSelectOption();
                option.setLabel(item.getLabelName());
                option.setValue(item.getLabelKey());
                return option;
            })//
            .collect(Collectors.toList());
        return success(selectOptions);
    }

    @GetMapping("/checkLabelKey/{labelKey}")
    @ApiOperation(value = "查询标签key是否存在")
    public Response<Boolean> checkLabelKey(@PathVariable String labelKey) {
        return success(sshLabelService.checkLabelKey(labelKey));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Integer> removeInitPlugin(@PathVariable Long id, @PathVariable Integer status) {
        return success(sshLabelService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除ssh")
    public Response<Boolean> removeSsh(@PathVariable Long id) {
        return success(sshLabelService.removeSsh(id));
    }
}
