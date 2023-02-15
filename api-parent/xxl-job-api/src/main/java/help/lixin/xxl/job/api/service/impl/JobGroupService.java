package help.lixin.xxl.job.api.service.impl;

import help.lixin.xxl.job.api.ResponseWrapper;
import help.lixin.xxl.job.api.dto.JobGroupResponse;
import help.lixin.xxl.job.api.mediator.CookieMediator;
import help.lixin.xxl.job.api.model.XxlJobGroup;
import help.lixin.xxl.job.api.service.IJobGroupService;
import kong.unirest.*;

import java.lang.reflect.Type;

public class JobGroupService implements IJobGroupService {

    private static final String QUERY = "/jobgroup/pageList";
    private static final String SAVE = "/jobgroup/save";
    private static final String UPDATE = "/jobgroup/update";
    private static final String REMOVE = "/jobgroup/remove";
    private static final String LOAD_BY_ID = "/jobgroup/loadById";

    private String url;

    public JobGroupService(String url) {
        this.url = url;
    }

    @Override
    public JobGroupResponse query(int start, int length, String appname, String title) {
        GetRequest request = Unirest.get(url + QUERY);

        String cookie = CookieMediator.getInstance().getCookie();
        if (null != cookie) {
            request.cookie(new Cookie(cookie));
        }
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

        String cookie = CookieMediator.getInstance().getCookie();
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
        String cookie = CookieMediator.getInstance().getCookie();
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
        if (null != CookieMediator.getInstance().getCookie()) {
            getRequest.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
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
        if (null != CookieMediator.getInstance().getCookie()) {
            getRequest.cookie(new Cookie(CookieMediator.getInstance().getCookie()));
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
