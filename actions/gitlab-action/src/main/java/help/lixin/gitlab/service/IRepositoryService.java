package help.lixin.gitlab.service;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;

import java.util.List;

public interface IRepositoryService {
    List<Branch> getBranches(String projectIdOrPath) throws GitLabApiException;
}
