package help.lixin.jenkins.api;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;

public interface ICrumbIssuerService {

    Crumb crumb();
}
