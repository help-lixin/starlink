package help.lixin.jenkins.api.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.features.CrumbIssuerApi;
import help.lixin.jenkins.api.ICrumbIssuerService;

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
