package help.lixin.gitlab.action;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.gitlab.service.GitlabFaceService;

public class GitlabCloneAction implements Action {
    public static final String GITLAB_CLONE_ACTION = "gitlab-clone";

    private GitlabFaceService gitlabFaceService;

    public GitlabCloneAction(GitlabFaceService gitlabFaceService) {
        this.gitlabFaceService = gitlabFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {



        return true;
    }

    @Override
    public String name() {
        return GITLAB_CLONE_ACTION;
    }
}
