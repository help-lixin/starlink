package help.lixin.starlink.plugin.xxl.job.api.service.impl;

import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobInfoResponse;
import help.lixin.starlink.plugin.xxl.job.api.mediator.CookieMediator;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.api.service.IJobInfoService;
import kong.unirest.*;

import java.util.List;

public class JobInfoService implements IJobInfoService {


    private static final String QUERY = "/jobinfo/pageList";
    private static final String ADD = "/jobinfo/add";
    private static final String UPDATE = "/jobinfo/update";
    private static final String REMOVE = "/jobinfo/remove";
    private static final String STOP = "/jobinfo/stop";
    private static final String START = "/jobinfo/start";
    private static final String TRIGGER = "/jobinfo/trigger";
    private static final String NEXT_TRIGGER_TIME = "/jobinfo/nextTriggerTime";

    private String url;
    private CookieMediator cookieMediator;

    public JobInfoService(String url,CookieMediator cookieMediator) {
        this.url = url;
        this.cookieMediator = cookieMediator;
    }

    @Override
    public JobInfoResponse query(int start,
                                 //
                                 int length,
                                 // 需要一个执行器
                                 int jobGroup,
                                 // 调度状态：0-停止，1-运行
                                 int triggerStatus,
                                 //
                                 String jobDesc,
                                 //
                                 String executorHandler,
                                 //
                                 String author) {

        GetRequest getRequest = Unirest.get(url + QUERY);
        if (null != cookieMediator.getCookie()) {
            getRequest.cookie(new Cookie(cookieMediator.getCookie()));
        }
        if (start <= 0) {
            start = 0;
        }
        if (length <= 0 || length <= 10) {
            length = 10;
        }
        if (triggerStatus < 0) {
            triggerStatus = -1;
        }
        getRequest.queryString("start", start);
        getRequest.queryString("length", length);
        getRequest.queryString("jobGroup", jobGroup);
        getRequest.queryString("triggerStatus", triggerStatus);
        if (null != jobDesc) {
            getRequest.queryString("jobDesc", jobDesc);
        }
        if (null != executorHandler) {
            getRequest.queryString("executorHandler", executorHandler);
        }
        if (null != author) {
            getRequest.queryString("author", author);
        }
        HttpResponse<JobInfoResponse> responseHttpResponse = getRequest.asObject(JobInfoResponse.class);
        return responseHttpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> add(XxlJobInfo jobInfo) {
        HttpRequestWithBody post = Unirest.post(url + ADD);

        if (null != cookieMediator.getCookie()) {
            post.cookie(new Cookie(cookieMediator.getCookie()));
        }

        // jobGroup是不允许为空的.
        post.queryString("jobGroup", jobInfo.getJobGroup());

        if (null != jobInfo.getJobDesc()) {
            post.queryString("jobDesc", jobInfo.getJobDesc());
        }

        if (null != jobInfo.getAddTime()) {
            post.queryString("addTime", jobInfo.getAddTime());
        }
        if (null != jobInfo.getUpdateTime()) {
            post.queryString("updateTime", jobInfo.getUpdateTime());
        }
        if (null != jobInfo.getAuthor()) {
            post.queryString("author", jobInfo.getAuthor());
        }
        if (null != jobInfo.getAlarmEmail()) {
            post.queryString("alarmEmail", jobInfo.getAlarmEmail());
        }
        post.queryString("scheduleType", jobInfo.getScheduleType());
        post.queryString("scheduleConf", jobInfo.getScheduleConf());
        post.queryString("misfireStrategy", jobInfo.getMisfireStrategy());
        post.queryString("executorRouteStrategy", jobInfo.getExecutorRouteStrategy());
        if (null != jobInfo.getExecutorHandler()) {
            post.queryString("executorHandler", jobInfo.getExecutorHandler());
        }
        if (null != jobInfo.getExecutorParam()) {
            post.queryString("executorParam", jobInfo.getExecutorParam());
        }
        post.queryString("executorBlockStrategy", jobInfo.getExecutorBlockStrategy());
        post.queryString("executorTimeout", jobInfo.getExecutorTimeout());
        post.queryString("executorFailRetryCount", jobInfo.getExecutorFailRetryCount());
        post.queryString("glueType", jobInfo.getGlueType());
        if (null != jobInfo.getGlueSource()) {
            post.queryString("glueSource", jobInfo.getGlueSource());
        }
        if (null != jobInfo.getGlueRemark()) {
            post.queryString("glueRemark", jobInfo.getGlueRemark());
        }
        if (null != jobInfo.getGlueUpdatetime()) {
            post.queryString("glueUpdatetime", jobInfo.getGlueUpdatetime());
        }
        if (null != jobInfo.getChildJobId()) {
            post.queryString("childJobId", jobInfo.getChildJobId());
        }
        post.queryString("triggerStatus", jobInfo.getTriggerStatus());

        HttpResponse<ResponseWrapper<String>> httpResponse = post.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> update(XxlJobInfo jobInfo) {
        HttpRequestWithBody post = Unirest.post(url + UPDATE);

        if (null != cookieMediator.getCookie()) {
            post.cookie(new Cookie(cookieMediator.getCookie()));
        }
        post.queryString("id", jobInfo.getId());

        // jobGroup是不允许为空的.
        post.queryString("jobGroup", jobInfo.getJobGroup());

        if (null != jobInfo.getJobDesc()) {
            post.queryString("jobDesc", jobInfo.getJobDesc());
        }

        if (null != jobInfo.getAddTime()) {
            post.queryString("addTime", jobInfo.getAddTime());
        }
        if (null != jobInfo.getUpdateTime()) {
            post.queryString("updateTime", jobInfo.getUpdateTime());
        }
        if (null != jobInfo.getAuthor()) {
            post.queryString("author", jobInfo.getAuthor());
        }
        if (null != jobInfo.getAlarmEmail()) {
            post.queryString("alarmEmail", jobInfo.getAlarmEmail());
        }
        post.queryString("scheduleType", jobInfo.getScheduleType());
        post.queryString("scheduleConf", jobInfo.getScheduleConf());
        post.queryString("misfireStrategy", jobInfo.getMisfireStrategy());
        post.queryString("executorRouteStrategy", jobInfo.getExecutorRouteStrategy());
        if (null != jobInfo.getExecutorHandler()) {
            post.queryString("executorHandler", jobInfo.getExecutorHandler());
        }
        if (null != jobInfo.getExecutorParam()) {
            post.queryString("executorParam", jobInfo.getExecutorParam());
        }
        post.queryString("executorBlockStrategy", jobInfo.getExecutorBlockStrategy());
        post.queryString("executorTimeout", jobInfo.getExecutorTimeout());
        post.queryString("executorFailRetryCount", jobInfo.getExecutorFailRetryCount());
        post.queryString("glueType", jobInfo.getGlueType());
        if (null != jobInfo.getGlueSource()) {
            post.queryString("glueSource", jobInfo.getGlueSource());
        }
        if (null != jobInfo.getGlueRemark()) {
            post.queryString("glueRemark", jobInfo.getGlueRemark());
        }
        if (null != jobInfo.getGlueUpdatetime()) {
            post.queryString("glueUpdatetime", jobInfo.getGlueUpdatetime());
        }
        if (null != jobInfo.getChildJobId()) {
            post.queryString("childJobId", jobInfo.getChildJobId());
        }
        post.queryString("triggerStatus", jobInfo.getTriggerStatus());
        HttpResponse<ResponseWrapper<String>> httpResponse = post.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> remove(int id) {
        GetRequest request = Unirest.get(url + REMOVE).queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            request.cookie(new Cookie(cookieMediator.getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> response = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return response.getBody();
    }

    @Override
    public ResponseWrapper<String> pause(int id) {
        GetRequest request = Unirest.get(url + STOP).queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            request.cookie(new Cookie(cookieMediator.getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> response = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return response.getBody();
    }

    @Override
    public ResponseWrapper<String> start(int id) {
        GetRequest request = Unirest.get(url + START).queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            request.cookie(new Cookie(cookieMediator.getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> httpResponse = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> triggerJob(int id, String executorParam, String addressList) {
        HttpRequestWithBody request = Unirest.post(url + TRIGGER).queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            request.cookie(new Cookie(cookieMediator.getCookie()));
        }
        if (null != executorParam) {
            request.queryString("executorParam", executorParam);
        }
        if (null != addressList) {
            request.queryString("addressList", addressList);
        }
        HttpResponse<ResponseWrapper<String>> httpResponse = request.asObject(new GenericType<ResponseWrapper<String>>() {
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<List<String>> nextTriggerTime(String scheduleType, String scheduleConf) {
        // TODO
        return null;
    }
}
