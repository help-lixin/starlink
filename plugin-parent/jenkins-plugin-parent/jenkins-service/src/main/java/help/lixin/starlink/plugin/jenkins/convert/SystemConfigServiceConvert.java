package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.sys.JenkinsSystemConfigDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SystemConfigServiceConvert {

    JenkinsSystemConfig convert(JenkinsSystemConfigDTO jenkinsSystemConfigDTO);
}
