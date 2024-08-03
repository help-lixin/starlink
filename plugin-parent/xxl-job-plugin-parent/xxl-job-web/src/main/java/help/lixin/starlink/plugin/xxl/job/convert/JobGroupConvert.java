package help.lixin.starlink.plugin.xxl.job.convert;

import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;
import help.lixin.starlink.plugin.xxl.job.request.group.JobGroupCreateVO;
import help.lixin.starlink.plugin.xxl.job.request.group.JobGroupUpdateVO;
import org.mapstruct.Mapper;

@Mapper
public interface JobGroupConvert {

    XxlJobGroup convert(JobGroupCreateVO jobGroupCreateVO);

    XxlJobGroup convert(JobGroupUpdateVO jobGroupUpdateVO);
}
