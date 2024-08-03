package help.lixin.starlink.plugin.xxl.job.convert;

import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.dto.JobInfoQueryDTO;
import help.lixin.starlink.plugin.xxl.job.request.info.JobInfoCreateVO;
import help.lixin.starlink.plugin.xxl.job.request.info.JobInfoQueryVO;
import help.lixin.starlink.plugin.xxl.job.request.info.JobInfoUpdateVO;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 4:47 下午
 * @Description
 */
@Mapper
public interface JobInfoConvert {

    JobInfoQueryDTO convert(JobInfoQueryVO jobInfoQueryVO);

    XxlJobInfo convert(JobInfoCreateVO jobInfoCreateVO);

    XxlJobInfo convert(JobInfoUpdateVO jobInfoUpdateVO);
}
