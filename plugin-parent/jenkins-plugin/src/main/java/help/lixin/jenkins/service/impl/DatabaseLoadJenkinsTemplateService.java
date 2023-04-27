package help.lixin.jenkins.service.impl;

import help.lixin.core.exception.jenkins.JenkinsTemplateNotFoundException;
import help.lixin.jenkins.action.entity.JenkinsActionParams;
import help.lixin.jenkins.service.ILoadJenkinsTemplateService;

public class DatabaseLoadJenkinsTemplateService implements ILoadJenkinsTemplateService {

    private ILoadJenkinsTemplateService parent;

    @Override
    public void setParent(ILoadJenkinsTemplateService loadJenkinsTemplateService) {
        this.parent = loadJenkinsTemplateService;
    }

    @Override
    public String load(JenkinsActionParams params) throws JenkinsTemplateNotFoundException {
        // TODO lixin db load template
        String template = null;
        if (null == template && null != parent) {
            template = parent.load(params);
        }
        return template;
    }
}
