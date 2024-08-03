package help.lixin.starlink.plugin.jenkins.convert.rule;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/29 3:57 下午
 * @Description
 */
public class JobRule {

    public ScmType toScmType(String scm){
        return ScmType.valueOf(scm);
    }

    public JenkinsPipelineScm toPipelinScm(String value) {
        return null;
    }
}
