package help.lixin.starlink.plugin.harbor.api.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;

import help.lixin.starlink.plugin.harbor.api.dto.LogInfo;
import help.lixin.starlink.plugin.harbor.api.dto.PageResponseDTO;
import help.lixin.starlink.plugin.harbor.api.properties.HarborProperties;
import help.lixin.starlink.plugin.harbor.api.service.IHarborLogApi;
import help.lixin.starlink.plugin.harbor.convert.HarborLoginfoConvert;
import kong.unirest.*;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 5:47 下午
 * @Description
 */
public class HarborLoginfoApi implements IHarborLogApi {

    private HarborProperties properties;
    private static final String API_RESOURCE = "/api/v2.0";
    private static final String LOGS = API_RESOURCE + "/audit-logs";

    {
        Unirest.config().enableCookieManagement(false);
    }

    public HarborLoginfoApi(HarborProperties properties) {
        this.properties = properties;
    }

    @Override
    public PageResponseDTO pageList(int page, int pageSize, String key, String value) {
        GetRequest getRequest = Unirest.get(properties.getUrl() + LOGS);
        GetRequest request = getRequest
            //
            .basicAuth(properties.getUserName(), properties.getPassword())
            //
            .queryString("page", page)
            //
            .queryString("page_size", pageSize);

        if (StringUtils.isNotBlank(key)) {
            request.queryString("q", key + "=~" + value);
        }
        HttpResponse<String> response = request.asString();
        // 报文里居然有java关键字的参数.
        String body = response.getBody();
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        List<LogInfo> logInfos = objectMapper.readValue(body, new GenericType<List<LogInfo>>() {});
        PageResponseDTO pageResponse = new PageResponseDTO();
        HarborLoginfoConvert mapper = Mappers.getMapper(HarborLoginfoConvert.class);
        pageResponse.setRecords(mapper.convert(logInfos));
        List<String> total = response.getHeaders().get("X-Total-Count");
        pageResponse.setTotal(Long.parseLong(total.get(0)));
        return pageResponse;
    }
}
