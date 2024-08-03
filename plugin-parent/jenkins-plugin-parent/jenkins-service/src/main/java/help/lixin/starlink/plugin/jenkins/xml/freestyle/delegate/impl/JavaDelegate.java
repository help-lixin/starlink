package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import help.lixin.starlink.plugin.jenkins.mapper.JenkinsSystemConfigMapper;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.IJavaDelegate;


public class JavaDelegate implements IJavaDelegate {
    private JenkinsSystemConfigMapper jenkinsSystemConfigMapper;

    public JavaDelegate(JenkinsSystemConfigMapper jenkinsSystemConfigMapper) {
        this.jenkinsSystemConfigMapper = jenkinsSystemConfigMapper;
    }

    @Override
    public String apply(String instanceCode, String jdkId) {
        JenkinsSystemConfig jenkinsSystemConfig = jenkinsSystemConfigMapper.selectById(jdkId);
        return jenkinsSystemConfig.getName();
    }
}
