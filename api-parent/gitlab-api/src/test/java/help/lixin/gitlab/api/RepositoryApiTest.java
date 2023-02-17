package help.lixin.gitlab.api;

import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.models.Branch;
import org.junit.Test;

import java.util.List;

public class RepositoryApiTest extends BasicTest {

    /**
     * 获取所有的分支
     *
     * @throws Exception
     */
    @Test
    public void testFetchBranches() throws Exception {
        RepositoryApi repositoryApi = gitLabApi.getRepositoryApi();
        // List<Branch> branches = repositoryApi.getBranches("5");
        // path = group-name/project-name
        List<Branch> branches = repositoryApi.getBranches("order-group/spring-web-demo");
        System.out.println(branches);
    }
}
