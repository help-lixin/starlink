package help.lixin.starlink.core.convert;

import help.lixin.starlink.core.dto.EnvDTO;
import org.mapstruct.Mapper;

import help.lixin.starlink.request.EnvRequest;

@Mapper
public interface EnvConvert {

    EnvDTO envConvert(EnvRequest envRequest);

}
