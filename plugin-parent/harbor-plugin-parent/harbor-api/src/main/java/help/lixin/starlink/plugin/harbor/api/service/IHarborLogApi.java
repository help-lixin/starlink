package help.lixin.starlink.plugin.harbor.api.service;

import help.lixin.starlink.plugin.harbor.api.dto.PageResponseDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 5:26 下午
 * @Description
 */
public interface IHarborLogApi {
    PageResponseDTO pageList(int page, int pageSize, String key, String value);
}
