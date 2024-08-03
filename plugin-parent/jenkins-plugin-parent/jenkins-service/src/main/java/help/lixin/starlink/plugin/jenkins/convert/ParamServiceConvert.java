package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsParams;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ParamServiceConvert {

    JenkinsParams convert(JenkinsParamDTO jenkinsParamDTO);
}
