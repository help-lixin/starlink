package help.lixin.starlink.plugin.harbor.controller;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.harbor.api.dto.LogInfoDTO;
import help.lixin.starlink.plugin.harbor.convert.HarborProjectControllerConvert;
import help.lixin.starlink.plugin.harbor.dto.QueryLoginfoDTO;
import help.lixin.starlink.plugin.harbor.request.QueryLoginfoVO;
import help.lixin.starlink.plugin.harbor.service.IHarborLoginfoService;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/18 6:44 下午
 * @Description
 */
@RestController
@RequestMapping("/harbor/loginfo")
@Api(tags = "日志信息")
public class HarborLoginfoController {

    @Autowired
    private IHarborLoginfoService harborLoginfoService;

    @GetMapping("/list")
    public Response<PageResponse<LogInfoDTO>> pageList(@Valid QueryLoginfoVO queryLoginfoVO) {
        HarborProjectControllerConvert harborProjectConvert = Mappers.getMapper(HarborProjectControllerConvert.class);
        QueryLoginfoDTO queryLoginfoDTO = harborProjectConvert.convert(queryLoginfoVO);
        return Response.success(harborLoginfoService.pageList(queryLoginfoDTO));
    }
}
