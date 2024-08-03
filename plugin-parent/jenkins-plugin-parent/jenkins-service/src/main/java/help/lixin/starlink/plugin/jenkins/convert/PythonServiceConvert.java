package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupPython;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupPythonDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PythonServiceConvert {

    JenkinsPipelineSetupPython convert(JenkinsPipelineSetupPythonDTO jenkinsPipelineSetupPythonDTO);
}
