package help.lixin.xxl.job.api.service;

import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobReportResponse;

import java.util.Date;

public interface IReportService {
    // /chartInfo
    ResponseWrapper<JobReportResponse> chartInfo(Date startDate, Date endDate);
}
