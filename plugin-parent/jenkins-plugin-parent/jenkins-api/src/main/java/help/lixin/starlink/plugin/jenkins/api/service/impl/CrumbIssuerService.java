package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.features.CrumbIssuerApi;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrumbIssuerService implements ICrumbIssuerService {
    private Logger logger = LoggerFactory.getLogger(CrumbIssuerService.class);
    private JenkinsClient client;

    public CrumbIssuerService(JenkinsClient client) {
        this.client = client;
    }

    @Override
    public Crumb crumb() {
        CrumbIssuerApi crumbIssuerApi = client.api().crumbIssuerApi();
        Crumb crumb = crumbIssuerApi.crumb();
        return crumb;
    }
}
