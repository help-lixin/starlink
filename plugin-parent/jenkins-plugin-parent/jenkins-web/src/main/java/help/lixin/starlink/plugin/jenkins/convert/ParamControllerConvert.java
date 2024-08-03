package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.request.params.JenkinsParamVO;
import org.mapstruct.Mapper;

@Mapper
public interface ParamControllerConvert {

    JenkinsParamDTO convert(JenkinsParamVO jenkinsParamVO);
}
