package help.lixin.jenkins.service;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;

public interface ICrumbIssuerService {

    Crumb crumb();
}
