package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildPageListDTO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsBuildInfoVO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsBuildPageListVO;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 5:26 下午
 * @Description
 */
@Mapper
public interface BuildInfoControllerConvert {

    JenkinsBuildInfoDTO convert(JenkinsBuildInfoVO jenkinsBuildInfoVO);

    JenkinsBuildPageListDTO convert(JenkinsBuildPageListVO jenkinsBuildPageListVO);
}
