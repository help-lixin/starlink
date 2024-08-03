package help.lixin.starlink.plugin.xxl.job.convert;

import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogClearDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogDetailDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogPageListDTO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogClearVO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogDetailVO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogPageListVO;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/7 3:33 下午
 * @Description
 */
@Mapper
public interface JobLogConvert {

    JobLogPageListDTO convert(JobLogPageListVO jobLogPageListVO);
    JobLogDetailDTO convert(JobLogDetailVO jobLogDetailVO);
    JobLogClearDTO convert(JobLogClearVO jobLogClearVO);
}
