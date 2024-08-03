package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate;

import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;
import help.lixin.starlink.plugin.jenkins.xml.pojo.setup.AbstractSetup;

import java.util.List;

public interface ISetupDelegate {
    // maven/shell
    List<AbstractSetup> apply(String instanceCode, List<JenkinsPipelineSetupCommonDTO> setups);
}
