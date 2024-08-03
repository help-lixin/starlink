package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate;

import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.AbstractScm;

public interface IScmDelegate {
    AbstractScm apply(ScmType scmType,//
                      String instanceCode, //
                      JenkinsPipelineScmDTO scmDto);
}
