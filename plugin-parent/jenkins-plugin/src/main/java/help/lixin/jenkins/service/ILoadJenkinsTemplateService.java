package help.lixin.jenkins.service;

import help.lixin.core.exception.jenkins.JenkinsTemplateNotFoundException;
import help.lixin.jenkins.action.entity.JenkinsActionParams;

/**
 * 加载jenkins模板文件
 */
public interface ILoadJenkinsTemplateService {

    String load(JenkinsActionParams params) throws JenkinsTemplateNotFoundException;

    void setParent(ILoadJenkinsTemplateService loadJenkinsTemplateService);
}
