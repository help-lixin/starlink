package help.lixin.starlink.plugin.jenkins.api.service;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;

public interface ICrumbIssuerService {

    Crumb crumb();
}
