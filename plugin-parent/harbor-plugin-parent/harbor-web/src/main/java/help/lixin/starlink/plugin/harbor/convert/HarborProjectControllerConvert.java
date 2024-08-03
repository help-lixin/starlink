package help.lixin.starlink.plugin.harbor.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.harbor.dto.CreateProjectDTO;
import help.lixin.starlink.plugin.harbor.dto.PageListDTO;
import help.lixin.starlink.plugin.harbor.dto.QueryLoginfoDTO;
import help.lixin.starlink.plugin.harbor.request.CreateProjectVO;
import help.lixin.starlink.plugin.harbor.request.PageListVO;
import help.lixin.starlink.plugin.harbor.request.QueryLoginfoVO;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/5 11:07 上午
 * @Description
 */
@Mapper
public interface HarborProjectControllerConvert {

    CreateProjectDTO createProjectMapper(CreateProjectVO createProjectVO);

    PageListDTO convert(PageListVO pageListVO);

    QueryLoginfoDTO convert(QueryLoginfoVO loginfoVO);

}
