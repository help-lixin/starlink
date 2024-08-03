package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupShell;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupShellDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ShellServiceConvert {

    JenkinsPipelineSetupShell convert(JenkinsPipelineSetupShellDTO jenkinsPipelineSetupShellDTO);
}
