package help.lixin.starlink.plugin.gitlab.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.gitlab.convert.GitlabGroupControllerConvert;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.group.*;
import help.lixin.starlink.plugin.gitlab.request.group.*;
import help.lixin.starlink.plugin.gitlab.service.IGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/11 2:27 下午
 * @Description
 */
@Api(tags = "组相关接口")
@RestController
@RequestMapping("/gitlab/group")
public class GitlabGroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation(value = "查询组列表")
    @PostMapping("/list")
    public Response<PageResponse<GitlabGroup>> pageList(@RequestBody GroupPageListVO groupPageListVO) {
        GitlabGroupControllerConvert gitlabGroupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupPageListDTO groupPageListDTO = gitlabGroupConvert.pageConvert(groupPageListVO);
        return success(groupService.queryGroupList(groupPageListDTO));
    }

    @ApiOperation(value = "修改组状态")
    @PutMapping("/changeStatus/{id}/{status}")
    public Response<Integer> changeStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(groupService.changeStatus(id, status));
    }

    @ApiOperation(value = "根据id查询组信息")
    @GetMapping("/info/{id}")
    public Response<GitlabGroup> queryByGroupId(@PathVariable Long id) {
        return success(groupService.queryGroupInfoById(id));
    }

    @ApiOperation(value = "根据组名查询")
    @GetMapping("/info")
    public Response<GitlabGroup> queryByGroupName(@Valid GroupQueryVO groupQueryVO) {
        GitlabGroupControllerConvert groupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupQueryDTO groupQueryDTO = groupConvert.queryConvert(groupQueryVO);
        return success(groupService.queryByGroupName(groupQueryDTO));
    }

    @ApiOperation(value = "新增、更新组")
    @PostMapping("/add")
    public Response<Integer> saveGroup(@Valid @RequestBody GroupSaveVO groupSaveVO) {
        GitlabGroupControllerConvert groupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupSaveDTO groupSaveDTO = groupConvert.saveConvert(groupSaveVO);
        groupSaveDTO.setCreateBy(UserContext.getUser().getUserName());

        return Response.success(groupService.addGroup(groupSaveDTO));
    }

    @ApiOperation(value = "删除组")
    @DeleteMapping("/del/{groupId}")
    public Response<Integer> removeGroup(@PathVariable Long groupId) {
        return success(groupService.removeGroup(groupId));
    }

    @ApiOperation(value = "新增组成员")
    @PostMapping("/member/add")
    public Response<Boolean> addMember(@Valid @RequestBody GroupAddMemberVO groupAddMemberVO) {
        GitlabGroupControllerConvert groupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupMemberAddDTO groupMemberAddDTO = groupConvert.addConvert(groupAddMemberVO);
        groupMemberAddDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(groupService.addMember(groupMemberAddDTO));
    }

    @ApiOperation(value = "查询成员列表")
    @GetMapping("/member/list")
    public Response<PageResponse<GroupMemberUserGroupDTO>> queryMemberList(@Valid MemberPageListVO memberPageListVO) {
        GitlabGroupControllerConvert groupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupMemberPageListDTO groupPageListDTO = groupConvert.pageConvert(memberPageListVO);
        return success(groupService.queryMemberList(groupPageListDTO));
    }

    @ApiOperation(value = "查询可选用户下拉列表")
    @GetMapping("/member/option/list/{groupId}/{userName}")
    public Response<List<OptionListDTO>> optionalUsers(@PathVariable Long groupId, @PathVariable String userName) {
        return success(groupService.optionalUsers(groupId, userName));
    }

    @ApiOperation(value = "更新组成员")
    @PutMapping("/member/edit")
    public Response<Integer> updateMember(@Valid @RequestBody GroupUpdateMemberVO groupUpdateMemberVO) {
        GitlabGroupControllerConvert groupConvert = Mappers.getMapper(GitlabGroupControllerConvert.class);
        GroupMemberUpdateDTO groupMemberUpdateDTO = groupConvert.updateConvert(groupUpdateMemberVO);
        groupMemberUpdateDTO.setUpdateBy(UserContext.getUser().getUserId().toString());
        return success(groupService.updateMember(groupMemberUpdateDTO));
    }

    @ApiOperation(value = "删除组成员")
    @DeleteMapping("/member/del/{id}")
    public Response<Integer> removeMember(@PathVariable Long id) {
        return success(groupService.removeMember(id, UserContext.getUser().getUserName()));
    }

    @ApiOperation(value = "修改组成员状态")
    @GetMapping("/member/changeStatus/{id}/{status}")
    public Response<Integer> changeMemberStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(groupService.changeMemberStatus(id, status));
    }

    @ApiOperation(value = "根据instanceCode查询gitlabId相关的下拉列表")
    @GetMapping("/selectGitlabIdOptions/{instanceCode}")
    public Response<List<GroupSelectGitlabIdOptionsDTO>> selectGitlabIdOptions(@PathVariable String instanceCode) {
        return success(groupService.querySelectGitlabIdOptions(instanceCode));
    }

    @ApiOperation(value = "根据instanceCode查询Id相关的下拉列表")
    @GetMapping("/selectIdOptions/{instanceCode}")
    public Response<List<GroupSelectIdOptionsDTO>> selectIdOptions(@PathVariable String instanceCode) {
        return success(groupService.querySelectIdOptions(instanceCode));
    }

    @ApiOperation(value = "查询当前实例ip端口地址")
    @GetMapping("/queryGitlabAddr/{instanceCode}")
    public Response<String> queryGitlabAddr(@PathVariable String instanceCode) {
        return success(groupService.queryGitlabAddr(instanceCode));
    }

}
