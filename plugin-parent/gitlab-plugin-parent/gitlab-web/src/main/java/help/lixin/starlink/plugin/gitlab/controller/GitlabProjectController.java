package help.lixin.starlink.plugin.gitlab.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.gitlab4j.api.GitLabApiException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.dto.Option;
import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.gitlab.convert.GitlabControllerConvert;
import help.lixin.starlink.plugin.gitlab.convert.GitlabProjectControllerConvert;
import help.lixin.starlink.plugin.gitlab.domain.GitlabGroup;
import help.lixin.starlink.plugin.gitlab.domain.GitlabProject;
import help.lixin.starlink.plugin.gitlab.dto.OptionListDTO;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.*;
import help.lixin.starlink.plugin.gitlab.dto.user.UserProjectDTO;
import help.lixin.starlink.plugin.gitlab.request.base.ChangeStatusVO;
import help.lixin.starlink.plugin.gitlab.request.project.*;
import help.lixin.starlink.plugin.gitlab.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/15 9:23 下午
 * @Description
 */
@Api(tags = "项目相关接口")
@RestController
@RequestMapping("/gitlab/project")
public class GitlabProjectController {

    @Autowired
    private IProjectService projectService;

    @ApiOperation(value = "查询项目列表")
    @GetMapping("/list")
    public Response<PageResponse<GitlabProject>> pageList(@Valid ProjectPageListVO pageRequest) {
        GitlabProjectControllerConvert gitlabProjectConvert = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectQueryDTO projectQueryDTO = gitlabProjectConvert.pageListConvert(pageRequest);

        try {
            return success(projectService.queryProjects(projectQueryDTO));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
    }

    @ApiOperation(value = "查询项目下拉列表")
    @GetMapping("/optionSelects/{instanceCode}")
    public Response<List<Option>> optionSelects(@PathVariable String instanceCode) {
        try {
            return success(projectService.queryProjectOption(instanceCode));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
    }

    @ApiOperation(value = "查询某个项目下所有分支列表")
    @GetMapping("/branch/{projectId}/{instanceCode}")
    public Response<List<Option>> queryBranchByProjectId(@PathVariable Long projectId,
        @PathVariable String instanceCode) {
        return success(projectService.branchList(projectId, instanceCode));
    }

    @ApiOperation(value = "保存项目信息")
    @PostMapping("/add")
    public Response<Integer> saveProject(@Valid @RequestBody ProjectSaveVO projectSaveVO) {
        if (projectSaveVO.getNamespaceByGroup() == null && projectSaveVO.getNamespaceByUser() == null) {
            throw new ServiceException("命名空间不能为空");
        }
        GitlabProjectControllerConvert gitlabProjectConvert = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectSaveDTO projectSaveDTO = gitlabProjectConvert.convert(projectSaveVO);
        try {
            return success(projectService.saveProject(projectSaveDTO));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
    }

    // @ApiOperation(value = "更新项目信息")
    // @PutMapping("/edit")
    // public Response<Integer> updateProject(@Valid @RequestBody ProjectUpdateVO projectUpdateVO) {
    // Project project = new Project();
    // project.setName(projectUpdateVO.getProjectName());
    //
    // EnvConvert envConvert = Mappers.getMapper(EnvConvert.class);
    // EnvDTO envDTO = envConvert.envConvert(projectUpdateVO.getEnvRequest());
    // return success(
    // projectService.updateProject(
    // projectUpdateVO.getProjectName(),
    // projectUpdateVO.getProjectId(),
    // UserContext.getUser().getUserName(),
    // envDTO)
    // );
    // }

    @ApiOperation(value = "删除项目信息")
    @DeleteMapping("/del/{projectId}")
    public Response<Integer> removeProject(@PathVariable Long projectId) {
        try {
            return success(projectService.removeProject(projectId));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
    }

    @ApiOperation(value = "查询项目组成员列表")
    @GetMapping("/member/group/list")
    Response<PageResponse<GitlabGroup>>
        queryGroupMemberList(@Valid ProjectGroupMemeberListVO projectGroupMemeberListVO) {
        GitlabProjectControllerConvert mapper = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectGroupListDTO pageListDTO = mapper.pageListConvert(projectGroupMemeberListVO);
        return success(projectService.queryGroupMemberList(pageListDTO));
    }

    @ApiOperation(value = "添加项目成员")
    @PostMapping("/member/add")
    Response<Boolean> saveUserProject(@RequestBody @Valid ProjectMemberSaveVO projectMemberSaveVO) {
        GitlabProjectControllerConvert mapper = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectMemberSaveDTO projectMemberSaveDTO = mapper.saveConvert(projectMemberSaveVO);
        projectMemberSaveDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(projectService.saveProjectMemeber(projectMemberSaveDTO));
    }

    @ApiOperation(value = "更新项目成员")
    @PutMapping("/member/edit")
    public Response<Integer> updateMember(@Valid @RequestBody ProjectUpdateMemberVO projectUpdateMemberVO) {
        GitlabProjectControllerConvert projectConvert = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectMemberUpdateDTO projectMemberUpdateDTO = projectConvert.updateConvert(projectUpdateMemberVO);
        return success(projectService.updateMember(projectMemberUpdateDTO));
    }

    @ApiOperation(value = "查询项目成员列表")
    @GetMapping("/member/list")
    Response<PageResponse<UserProjectDTO>> queryUserMemberList(@Valid ProjectMemeberListVO projectMemeberListVO) {
        GitlabProjectControllerConvert mapper = Mappers.getMapper(GitlabProjectControllerConvert.class);
        ProjectPageListDTO pageListDTO = mapper.pageListConvert(projectMemeberListVO);
        return success(projectService.queryUserMemberList(pageListDTO));
    }

    @ApiOperation(value = "查询可选用户下拉列表")
    @GetMapping("/member/option/list/{projectId}/{userName}")
    public Response<List<OptionListDTO>> optionalUsers(@PathVariable Long projectId, @PathVariable String userName) {
        return success(projectService.optionalUsers(projectId, userName));
    }

    @ApiOperation(value = "删除项目成员信息")
    @DeleteMapping("/member/del/{id}")
    public Response<Integer> removeProjectMember(@PathVariable Long id) {
        try {
            return success(projectService.removeMember(id));
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }
    }

    @ApiOperation(value = "根据gitlabProjectId查询信息")
    @GetMapping("/info/{projectId}/{instanceCode}")
    Response<GitlabProject> queryMemberInfo(@PathVariable Long projectId, @PathVariable String instanceCode) {
        ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
        projectInfoDTO.setProjectId(projectId);
        projectInfoDTO.setInstanceCode(instanceCode);
        return success(projectService.queryByGitlabProjectId(projectInfoDTO));
    }

    @PutMapping("/changeStatus")
    @ApiOperation(value = "修改项目状态")
    public Response<Integer> changeStatus(@RequestBody ChangeStatusVO changeStatusVO) {
        GitlabControllerConvert gitlabConvert = Mappers.getMapper(GitlabControllerConvert.class);
        ChangeStatusDTO changeStatusDTO = gitlabConvert.convert(changeStatusVO);
        changeStatusDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(projectService.changeStatus(changeStatusDTO));
    }

    @PutMapping("/member/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改项目成员状态")
    public Response<Integer> changeMemberStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(projectService.changeMemberStatus(id, status));
    }
}
