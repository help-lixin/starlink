package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupCommon;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CommonServiceConvert {

    JenkinsPipelineSetupCommon convert(JenkinsPipelineSetupCommonDTO jenkinsPipelineSetupCommonDTO);
}
