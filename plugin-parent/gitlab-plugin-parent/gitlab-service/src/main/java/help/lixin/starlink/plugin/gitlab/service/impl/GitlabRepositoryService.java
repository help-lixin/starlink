package help.lixin.starlink.plugin.gitlab.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.gitlab.convert.RepositoryServiceConvert;
import help.lixin.starlink.plugin.gitlab.dto.repository.BranchDTO;
import help.lixin.starlink.plugin.gitlab.service.IRepositoryService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.models.Branch;
import org.mapstruct.factory.Mappers;

import java.util.List;


public class GitlabRepositoryService implements IRepositoryService {

    private final AbstractServiceFactory serviceFactory;

    public GitlabRepositoryService(AbstractServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }


    @Override
    public List<BranchDTO> getBranches(String projectIdOrPath, String instanceCode) {
        try {
            List<Branch> branches = serviceFactory.getInstance(instanceCode, RepositoryApi.class).getBranches(projectIdOrPath);

            RepositoryServiceConvert mapper = Mappers.getMapper(RepositoryServiceConvert.class);
            return mapper.convert(branches);
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常："+ e.getMessage());
        }
    }
}
