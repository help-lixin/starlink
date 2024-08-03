package help.lixin.starlink.plugin.credential.controller;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.plugin.credential.convert.CredentialConvert;
import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.dto.*;
import help.lixin.starlink.plugin.credential.enums.CredentialEnum;
import help.lixin.starlink.plugin.credential.request.*;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static help.lixin.response.Response.fail;
import static help.lixin.response.Response.success;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 7:11 下午
 * @Description
 */
@RestController
@RequestMapping("/credential")
@Api(tags = "全局凭证管理")
public class CredentialController {

    @Autowired
    private ICredentialService credentialService;

    @PostMapping("/add")
    @ApiOperation(value = "添加凭证")
    public Response<Boolean> add(@RequestBody @Valid CredentialVO credentialVO){
        if (StringUtils.isBlank(credentialVO.getCredentialType().name())) {
            throw new ServiceException("凭证类型不能为空");
        }
        credentialVO.setCreateBy(UserContext.getUser().getUserName());
        if (credentialVO instanceof SSHCredentialVO){

            SSHCredentialVO ssh = (SSHCredentialVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialSshDTO sysCredentialSshDTO = mapper.convert(ssh);
            sysCredentialSshDTO.setCredentialType(CredentialEnum.SSH.name());

            return success(credentialService.save(sysCredentialSshDTO));

        }else if (credentialVO instanceof UserNamePasswordCredentialVO){

            UserNamePasswordCredentialVO userNamePasswordCredentialVO =
                    (UserNamePasswordCredentialVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO =
                    mapper.convert(userNamePasswordCredentialVO);
            sysCredentialUsernamePasswordDTO.setCredentialType(CredentialEnum.USERNAME_PASSWORD.name());

            return success(credentialService.save(sysCredentialUsernamePasswordDTO));

        }else if (credentialVO instanceof SecretCredentialVO){

            SecretCredentialVO secretCredentialVO = (SecretCredentialVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialTextDTO sysCredentialTextDTO = mapper.convert(secretCredentialVO);
            sysCredentialTextDTO.setCredentialType(CredentialEnum.SECRET.name());

            return success(credentialService.save(sysCredentialTextDTO));

        }else if (credentialVO instanceof TokenCredentialVO){

            TokenCredentialVO tokenCredentialVO = (TokenCredentialVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialTokenDTO tokenDTO = mapper.convert(tokenCredentialVO);
            tokenDTO.setCredentialType(CredentialEnum.TOKEN.name());

            return success(credentialService.save(tokenDTO));

        }else if (credentialVO instanceof SysCredentialOpaqueVO){

            SysCredentialOpaqueVO sysCredentialOpaqueVO = (SysCredentialOpaqueVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialOpaqueDTO sysCredentialOpaqueDTO = mapper.convert(sysCredentialOpaqueVO);
            sysCredentialOpaqueDTO.setCredentialType(CredentialEnum.OPAQUE.name());

            return success(credentialService.save(sysCredentialOpaqueDTO));

        }else if (credentialVO instanceof SysCredentialTlsVO){

            SysCredentialTlsVO sysCredentialTlsVO = (SysCredentialTlsVO) credentialVO;

            CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
            SysCredentialTlsDTO sysCredentialTlsDTO = mapper.convert(sysCredentialTlsVO);
            sysCredentialTlsDTO.setCredentialType(CredentialEnum.TLS.name());

            return success(credentialService.save(sysCredentialTlsDTO));

        }

        return fail("不存在该凭证");
    }

    @GetMapping("/syncAllCredential")
    @ApiOperation(value = "一键同步凭证数据")
    public Response<Boolean> syncAllCredential(){
        return success(credentialService.syncAllCredential());
    }

    @GetMapping("/option/{instanceCode}")
    @ApiOperation(value = "查询实例下所有凭证下拉列表")
    public Response<List<SysCredentialOptionDTO>> credentialOptionSelect(@PathVariable String instanceCode){
        return success(credentialService.credentialOptionSelect(instanceCode));
    }

    @GetMapping("/option/{instanceCode}/{nameSpace}/{filterType}/{isFilterURL}")
    @ApiOperation(value = "查询k8s凭证下拉列表")
    public Response<List<SysCredentialNameSpaceDTO>> credentialOptionSelect(@PathVariable String instanceCode,
        @PathVariable String nameSpace, @PathVariable String filterType, @PathVariable Boolean isFilterURL) {
        return success(credentialService.credentialOptionSelect(instanceCode, nameSpace, filterType, isFilterURL));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询凭证列表")
    public Response<PageResponse<SysCredentialCommon>> list(SysCredentialPageListVO sysCredentialPageListVO) {
        CredentialConvert mapper = Mappers.getMapper(CredentialConvert.class);
        SysCredentialPageListDTO convert = mapper.convert(sysCredentialPageListVO);
        return success(credentialService.list(convert));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据凭证id查询相关详情")
    public Response<SysCredentialCommon> queryListById(@PathVariable Long id){
        SysCredentialCommon sysCredentialCommon = credentialService.queryDecodeById(id);
        return success(sysCredentialCommon);
    }

    @GetMapping("/checkKey/{credentialKey}/{instanceCode}")
    @ApiOperation(value = "校验id是否存在")
    public Response<Boolean> checkKey(@PathVariable String credentialKey,@PathVariable String instanceCode){
        return success(credentialService.checkId(credentialKey,instanceCode));
    }

    @PutMapping("/changeStatus/{status}/{id}")
    @ApiOperation(value = "修改凭证状态")
    public Response<Boolean> changeStatus(@PathVariable Integer status,@PathVariable Long id){
        return success(credentialService.changeStatus(status, id, UserContext.getUser().getUserName()));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除凭证")
    public Response<Boolean> removeCredential(@PathVariable Long id){
        return success(credentialService.removeCredential(id));
    }

    @GetMapping("/{instanceCode}/nameSpace/list")
    @ApiOperation(value = "根据实例查询命名空间下拉列表")
    public Response<List<SysCredentialNameSpaceDTO>> queryNameSpaceList(@PathVariable String instanceCode) {
        return success(credentialService.queryNameSpaceList(instanceCode));
    }

}
