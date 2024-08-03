package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate;

import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.StringParameterDefinition;

import java.util.List;

public interface IParamsDelegate {
    List<StringParameterDefinition> apply(List<JenkinsParamDTO> params);
}
