package help.lixin.starlink.plugin.harbor.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.harbor.api.dto.LogInfoDTO;
import help.lixin.starlink.plugin.harbor.dto.QueryLoginfoDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 6:34 下午
 * @Description
 */
public interface IHarborLoginfoService {

    PageResponse<LogInfoDTO> pageList(QueryLoginfoDTO queryLoginfoDTO);
}
