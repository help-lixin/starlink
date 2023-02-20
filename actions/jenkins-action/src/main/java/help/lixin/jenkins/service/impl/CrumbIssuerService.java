package help.lixin.jenkins.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.features.CrumbIssuerApi;
import help.lixin.jenkins.service.ICrumbIssuerService;

public class CrumbIssuerService implements ICrumbIssuerService {

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
