package help.lixin.starlink.core.convert;

import help.lixin.starlink.core.dto.PageDTO;
import org.mapstruct.Mapper;

import help.lixin.starlink.request.BasePageRequest;
import help.lixin.starlink.request.PageRequest;

@Mapper
public interface PageConvert {

    PageDTO pageConvert(PageRequest pageRequest);

    PageDTO pageConvert(BasePageRequest pageRequest);
}
