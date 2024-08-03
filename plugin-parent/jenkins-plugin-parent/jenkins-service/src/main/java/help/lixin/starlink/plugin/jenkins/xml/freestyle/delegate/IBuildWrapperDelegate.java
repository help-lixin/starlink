package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate;

import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;
import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.AbstractBuildWrapper;

import java.util.List;

public interface IBuildWrapperDelegate {

    // go/python/nodejs需要在处理
    List<AbstractBuildWrapper> apply(String instanceCode, List<JenkinsPipelineSetupCommonDTO> setups);

}
