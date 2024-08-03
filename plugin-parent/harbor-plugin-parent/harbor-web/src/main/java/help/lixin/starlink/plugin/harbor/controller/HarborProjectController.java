package help.lixin.starlink.plugin.harbor.controller;

import static help.lixin.response.Response.success;
import static org.mapstruct.factory.Mappers.getMapper;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.harbor.convert.HarborProjectControllerConvert;
import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.dto.CreateProjectDTO;
import help.lixin.starlink.plugin.harbor.dto.HarborProjectOption;
import help.lixin.starlink.plugin.harbor.dto.PageListDTO;
import help.lixin.starlink.plugin.harbor.dto.RepositoryNodeDTO;
import help.lixin.starlink.plugin.harbor.request.CreateProjectVO;
import help.lixin.starlink.plugin.harbor.request.PageListVO;
import help.lixin.starlink.plugin.harbor.service.IHarborProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/harbor/project")
public class HarborProjectController {

    @Autowired
    private IHarborProjectService harborProjectService;

    @ApiOperation(value = "查询项目列表")
    @GetMapping("/list")
    public Response<PageResponse<HarborProject>> pageList(PageListVO pageRequest) {
        HarborProjectControllerConvert pageConvert = getMapper(HarborProjectControllerConvert.class);
        PageListDTO envDTO = pageConvert.convert(pageRequest);
        return success(harborProjectService.pageList(envDTO));
    }

    @ApiOperation(value = "根据项目名称查询")
    @GetMapping("/info/{projectName}/{instanceCode}")
    public Response<List<HarborProject>> queryByProjectName(@PathVariable String projectName,
        @PathVariable String instanceCode) {
        return success(harborProjectService.queryProject(projectName, instanceCode));
    }

    @ApiOperation(value = "创建项目")
    @PostMapping("/add")
    public Response<Integer> createProject(@Valid @RequestBody CreateProjectVO createProjectVO) {
        HarborProjectControllerConvert harborProjectControllerConvert = getMapper(HarborProjectControllerConvert.class);
        CreateProjectDTO createProjectDTO = harborProjectControllerConvert.createProjectMapper(createProjectVO);

        return success(harborProjectService.createProject(createProjectDTO));
    }

    @ApiOperation(value = "修改状态")
    @PutMapping("/changeStatus/{id}/{status}")
    public Response<Integer> changeStatus(@PathVariable Long id, @PathVariable Integer status) {
        return success(harborProjectService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @GetMapping("/optionSelect/{instanceCode}")
    @ApiOperation(value = "根据实例,获取所有可用的项目名称列表")
    @ApiResponses({@ApiResponse(code = 200, message = "根据实例,获取所有可用的项目名称列表")})
    public Response<List<HarborProjectOption>> optionselect(@PathVariable("instanceCode") String instanceCode) {
        return success(harborProjectService.queryOptions(instanceCode));
    }

    @ApiOperation(value = "校验项目名称是否存在")
    @GetMapping("/projectNameIsExist/{projectName}/{instanceCode}")
    public Response<Boolean> projectNameIsExist(@PathVariable String projectName, @PathVariable String instanceCode) {
        return success(harborProjectService.projectNameIsExist(projectName, instanceCode));
    }

    @ApiOperation(value = "查询仓库目录树状节点")
    @GetMapping("/queryNodeList/{projectName}/{instanceCode}")
    public Response<List<RepositoryNodeDTO>> queryNodeList(@PathVariable String projectName,
        @PathVariable String instanceCode) {
        return success(harborProjectService.queryNodeList(instanceCode, projectName));
    }

    @ApiOperation(value = "修改项目访问级别")
    @GetMapping("/changeAccessLevel/{projectId}")
    public Response<List<RepositoryNodeDTO>> changeAccessLevel(@PathVariable Long projectId) {
        return success(harborProjectService.changeAccessLevel(projectId));
    }

    @ApiOperation(value = "删除项目")
    @DeleteMapping("/del/{projectId}")
    public Response<Boolean> removeProject(@PathVariable Long projectId) {
        return success(harborProjectService.removeProject(projectId));
    }
}
