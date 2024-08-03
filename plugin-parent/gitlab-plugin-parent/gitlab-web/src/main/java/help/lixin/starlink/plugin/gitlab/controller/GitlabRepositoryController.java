package help.lixin.starlink.plugin.gitlab.controller;

import help.lixin.starlink.plugin.gitlab.dto.repository.BranchDTO;
import help.lixin.starlink.plugin.gitlab.service.IRepositoryService;
import help.lixin.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/7 5:13 下午
 * @Description
 */
@RestController
@RequestMapping("/gitlab/repository")
public class GitlabRepositoryController {

    @Autowired
    private IRepositoryService repositoryService;

    @GetMapping("/{projectId}/{instanceCode}")
    public Response<List<BranchDTO>> queryBranchesByProjectId(@PathVariable("projectId") String projectId, @PathVariable("instanceCode") String instanceCode) {
        return Response.success(repositoryService.getBranches(projectId,instanceCode));
    }
}
