package help.lixin.starlink.plugin.xxl.job.api.service;

import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobReportResponse;

import java.util.Date;

public interface IReportService {
    // /chartInfo
    ResponseWrapper<JobReportResponse> report(Date startDate, Date endDate);
}
