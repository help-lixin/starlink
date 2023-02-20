package help.lixin.gitlab.service.impl;

import help.lixin.gitlab.service.IRepositoryService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.models.Branch;

import java.util.List;


public class RepositoryService implements IRepositoryService {
    private RepositoryApi repositoryApi;

    public RepositoryService(RepositoryApi repositoryApi) {
        this.repositoryApi = repositoryApi;
    }


    @Override
    public List<Branch> getBranches(String projectIdOrPath) throws GitLabApiException {
        return repositoryApi.getBranches(projectIdOrPath);
    }
}
