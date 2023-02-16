package help.lixin.xxl.job.api.service.impl;

import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobLogResponse;
import help.lixin.xxl.job.api.dto.LogResult;
import help.lixin.xxl.job.api.mediator.CookieMediator;
import help.lixin.xxl.job.api.model.XxlJobInfo;
import help.lixin.xxl.job.api.service.IJobLogService;
import kong.unirest.*;

import java.util.List;

public class JobLogService implements IJobLogService {

    private static final String GET_JOBS_GROUP = "/joblog/getJobsByGroup";

    private static final String QUERY = "/joblog/pageList";

    private static final String LOG_DETAIL_CAT = "/joblog/logDetailCat";

    private static final String LOG_KILL = "/joblog/logKill";

    private static final String CLEAR_LOG = "/joblog/clearLog";

    private String url;

    public JobLogService(String url) {
        this.url = url;
    }

    @Override
    public ResponseWrapper<List<XxlJobInfo>> getJobsByGroup(int jobGroup) {
        GetRequest request = Unirest.get(url + GET_JOBS_GROUP).queryString("jobGroup", jobGroup);
        if (null != CookieMediator.getInstance().getCookie()) {
            request.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
        }
        HttpResponse<ResponseWrapper<List<XxlJobInfo>>> response = request.asObject(new GenericType<ResponseWrapper<List<XxlJobInfo>>>() {
        });
        return response.getBody();
    }

    @Override
    public JobLogResponse query(int start, int length,
                                // 执行器id
                                int jobGroup,
                                // 任务id
                                int jobId,
                                // 状态(成功/失败/进行中)
                                int logStatus,
                                // 调度时间
                                String filterTime) {
        GetRequest request = Unirest.get(url + QUERY);
        request.queryString("start", start);
        request.queryString("length", length);
        request.queryString("jobGroup", jobGroup);
        request.queryString("jobId", jobId);
        request.queryString("logStatus", logStatus);
        if (null != filterTime) {
            request.queryString("filterTime", filterTime);
        }
        if (null != CookieMediator.getInstance().getCookie()) {
            request.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
        }
        HttpResponse<JobLogResponse> response = request.asObject(JobLogResponse.class);
        return response.getBody();
    }

    @Override
    public ResponseWrapper<LogResult> logDetailCat(String executorAddress,
                                                   //
                                                   long triggerTime,
                                                   //
                                                   long logId,
                                                   //
                                                   int fromLineNum) {
        GetRequest request = Unirest.get(url + LOG_DETAIL_CAT);
        if (null != executorAddress) {
            request.queryString("executorAddress", executorAddress);
        }
        request.queryString("triggerTime", triggerTime);
        request.queryString("logId", logId);
        request.queryString("fromLineNum", fromLineNum);
        if (null != CookieMediator.getInstance().getCookie()) {
            request.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
        }
        HttpResponse<ResponseWrapper<LogResult>> response = request.asObject(new GenericType<ResponseWrapper<LogResult>>() {
        });
        return response.getBody();
    }

    @Override
    public ResponseWrapper<String> logKill(int id) {
        GetRequest request = Unirest.get(url + LOG_KILL);
        request.queryString("id", id);
        if (null != CookieMediator.getInstance().getCookie()) {
            request.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> response = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return response.getBody();
    }

    @Override
    public ResponseWrapper<String> clearLog(int jobGroup,
                                            //
                                            int jobId,
                                            //
                                            int type) {

        GetRequest request = Unirest.get(url + CLEAR_LOG);
        request.queryString("jobGroup", jobGroup);
        request.queryString("jobId", jobId);
        request.queryString("type", type);
        if (null != CookieMediator.getInstance().getCookie()) {
            request.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> response = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return response.getBody();
    }
}
