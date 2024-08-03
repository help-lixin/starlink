package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.request.base.ChangeStatusVO;
import org.mapstruct.Mapper;

@Mapper
public interface GitlabControllerConvert {

    ChangeStatusDTO convert(ChangeStatusVO changeStatusVO);
}
