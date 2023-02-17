package help.lixin.jenkins.api;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.jenkins.BasicTest;
import help.lixin.jenkins.api.impl.CrumbIssuerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrumbIssuerServiceTest extends BasicTest {

    private ICrumbIssuerService crumbIssuerService;

    @Before
    public void init() {
        crumbIssuerService = new CrumbIssuerService(client);
    }

    @Test
    public void testCrumb() {
        Crumb crumb = crumbIssuerService.crumb();
        Assert.assertNotNull(null != crumb && crumb.errors().size() == 0);
    }
}
