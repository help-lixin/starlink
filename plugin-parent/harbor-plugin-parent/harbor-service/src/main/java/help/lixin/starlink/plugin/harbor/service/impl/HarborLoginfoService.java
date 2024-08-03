package help.lixin.starlink.plugin.harbor.service.impl;

import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.api.dto.LogInfoDTO;
import help.lixin.starlink.plugin.harbor.api.dto.PageResponseDTO;
import help.lixin.starlink.plugin.harbor.api.service.impl.HarborLoginfoApi;
import help.lixin.starlink.plugin.harbor.dto.QueryLoginfoDTO;
import help.lixin.starlink.plugin.harbor.service.IHarborLoginfoService;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 6:35 下午
 * @Description
 */
public class HarborLoginfoService implements IHarborLoginfoService {

    private final AbstractServiceFactory harborServiceFactory;

    @Override
    public PageResponse<LogInfoDTO> pageList(QueryLoginfoDTO queryLoginfoDTO) {
        HarborLoginfoApi harborLoginfoApi =
            harborServiceFactory.getInstance(queryLoginfoDTO.getInstanceCode(), HarborLoginfoApi.class);
        PageResponseDTO pageResponseDTO = harborLoginfoApi.pageList(queryLoginfoDTO.getPageNum(),
            queryLoginfoDTO.getPageSize(), queryLoginfoDTO.getKey(), queryLoginfoDTO.getValue());

        PageResponse<LogInfoDTO> response = new PageResponse<>();
        response.setRecords(pageResponseDTO.getRecords());
        response.setPageCurrent(pageResponseDTO.getPageCurrent());
        response.setPageSize(pageResponseDTO.getPageSize());
        response.setTotal(pageResponseDTO.getTotal());
        return response;
    }

    public HarborLoginfoService(AbstractServiceFactory harborServiceFactory) {
        this.harborServiceFactory = harborServiceFactory;
    }
}
