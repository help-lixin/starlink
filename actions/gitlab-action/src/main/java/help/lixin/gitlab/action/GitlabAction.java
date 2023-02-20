package help.lixin.gitlab.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.gitlab.service.GitlabFaceService;

/**
 * gitlab实现
 */
public class GitlabAction implements Action {
    public static final String GITLAB_ACTION = "gitlab";

    private GitlabFaceService gitlabFaceService;

    public GitlabAction(GitlabFaceService gitlabFaceService) {
        this.gitlabFaceService = gitlabFaceService;
    }


    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        GitlabActionProperties gitlabProperties = objectMapper.readValue(stageParams, GitlabActionProperties.class);
        ctx.addVar("projectName", gitlabProperties.getProjectName());
        ctx.addVar("branch", gitlabProperties.getBranch());
        ctx.addVar("url", gitlabProperties.getUrl());
        return true;
    }

    @Override
    public String name() {
        return GITLAB_ACTION;
    }
}
