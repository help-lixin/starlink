package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.IParamsDelegate;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.StringParameterDefinition;

import java.util.ArrayList;
import java.util.List;

public class ParamsDelegate implements IParamsDelegate {
    @Override
    public List<StringParameterDefinition> apply(List<JenkinsParamDTO> params) {
        List<StringParameterDefinition> results = new ArrayList<>(0);
        for (JenkinsParamDTO paramsDTO : params) {
            StringParameterDefinition stringParameterDefinition = new StringParameterDefinition();
            stringParameterDefinition.setName(paramsDTO.getParamName());
            stringParameterDefinition.setDefaultValue(paramsDTO.getDefaultValue());
            stringParameterDefinition.setDescription(paramsDTO.getDescription());
            stringParameterDefinition.setTrim(Boolean.TRUE);
            results.add(stringParameterDefinition);
        }
        return results;
    }
}
