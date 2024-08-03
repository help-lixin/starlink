package help.lixin.starlink.plugin.xxl.job.api.service.impl;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.xxl.job.api.ResponseWrapper;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobGroupResponse;
import help.lixin.starlink.plugin.xxl.job.api.mediator.CookieMediator;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;
import help.lixin.starlink.plugin.xxl.job.api.service.IJobGroupService;
import kong.unirest.*;

import java.lang.reflect.Type;

public class JobGroupService implements IJobGroupService {

    private static final String QUERY = "/jobgroup/pageList";
    private static final String SAVE = "/jobgroup/save";
    private static final String UPDATE = "/jobgroup/update";
    private static final String REMOVE = "/jobgroup/remove";
    private static final String LOAD_BY_ID = "/jobgroup/loadById";

    private String url;
    private CookieMediator cookieMediator;

    public JobGroupService(String url,CookieMediator cookieMediator) {
        this.url = url;
        this.cookieMediator = cookieMediator;
    }

    @Override
    public JobGroupResponse query(int start, int length, String appname, String title) {
        GetRequest request = Unirest.get(url + QUERY);

        String cookie = cookieMediator.getCookie();
        if (null == cookie) {
            throw new ServiceException("请登录后再请求");
        }
        request.cookie(new Cookie(cookie));
        if (start <= 0) {
            request.queryString("start", start);
        }
        if (length <= 0 || length <= 10) {
            request.queryString("length", 10);
        }
        if (null != appname) {
            request.queryString("appname", appname);
        }
        if (null != title) {
            request.queryString("title", title);
        }
        HttpResponse<JobGroupResponse> httpResponse = request.asObject(JobGroupResponse.class);
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> save(XxlJobGroup xxlJobGroup) {
        HttpRequestWithBody post = Unirest.post(url + SAVE);

        String cookie = cookieMediator.getCookie();
        if (null != cookie) {
            post.cookie(new Cookie(cookie));
        }
        post.queryString("addressType", xxlJobGroup.getAddressType());
        if (null != xxlJobGroup.getAppname()) {
            post.queryString("appname", xxlJobGroup.getAppname());
        }
        if (null != xxlJobGroup.getTitle()) {
            post.queryString("title", xxlJobGroup.getTitle());
        }
        HttpResponse<ResponseWrapper<String>> httpResponse = post.asObject(new GenericType<ResponseWrapper<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> update(XxlJobGroup xxlJobGroup) {
        HttpRequestWithBody post = Unirest.post(url + UPDATE);
        String cookie = cookieMediator.getCookie();
        if (null != cookie) {
            post.cookie(new Cookie(cookie));
        }
        post.queryString("addressType", xxlJobGroup.getAddressType());
        post.queryString("id", xxlJobGroup.getId());
        if (null != xxlJobGroup.getAppname()) {
            post.queryString("appname", xxlJobGroup.getAppname());
        }
        if (null != xxlJobGroup.getTitle()) {
            post.queryString("title", xxlJobGroup.getTitle());
        }
        HttpResponse<ResponseWrapper<String>> httpResponse = post.asObject(new GenericType<ResponseWrapper<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return httpResponse.getBody();
    }

    @Override
    public ResponseWrapper<String> remove(int id) {
        GetRequest getRequest = Unirest.get(url + REMOVE)
                //
                .queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            getRequest.cookie(new Cookie(cookieMediator.getCookie()));
        }
        HttpResponse<ResponseWrapper<String>> response = getRequest.asObject(new GenericType<ResponseWrapper<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return response.getBody();
    }

    @Override
    public ResponseWrapper<XxlJobGroup> loadById(int id) {
        GetRequest getRequest = Unirest.get(url + LOAD_BY_ID)
                //
                .queryString("id", id);
        if (null != cookieMediator.getCookie()) {
            getRequest.cookie(new Cookie(cookieMediator.getCookie()));
        }
        HttpResponse<ResponseWrapper<XxlJobGroup>> response = getRequest.asObject(new GenericType<ResponseWrapper<XxlJobGroup>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return response.getBody();
    }
}
