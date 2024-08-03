package help.lixin.starlink.plugin.xxl.job.api.service.impl;

import help.lixin.starlink.plugin.xxl.job.api.mediator.CookieMediator;
import help.lixin.starlink.plugin.xxl.job.api.properties.XxlJobProperties;
import help.lixin.starlink.plugin.xxl.job.api.service.ILoginService;
import kong.unirest.Cookie;
import kong.unirest.Cookies;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

import java.util.concurrent.atomic.AtomicBoolean;

public class LoginService implements ILoginService, SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String LOGIN_URL = "/login";

    private XxlJobProperties properties;
    private String url;
    private CookieMediator cookieMediator;

    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public LoginService(XxlJobProperties properties, CookieMediator cookieMediator) {
        this.properties = properties;
        this.url = properties.getUrl();
        this.cookieMediator = cookieMediator;
    }

    @Override
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            login(properties.getUsername(), properties.getPassword());
        }
    }

    @Override
    public void stop() {
        isRunning.set(false);
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public void login(String userName, String password) {
        try {
            HttpResponse httpResponse = Unirest
                    //
                    .post(url + LOGIN_URL)
                    //
                    .queryString("userName", userName)
                    //
                    .queryString("password", password)
                    //
                    .asEmpty();
            if (httpResponse == null) {
                throw new RuntimeException(" getCookie httpResponse is null");
            }

            if (httpResponse.getStatus() == 200) {
                StringBuilder stringBuilder = new StringBuilder();
                Cookies cookies = httpResponse.getCookies();
                for (Cookie cookie1 : cookies) {
                    stringBuilder.append(cookie1.toString());
                }
                String cookie = stringBuilder.toString();
                Integer indix = cookie.indexOf(";");
                String newCookie = cookie.substring(1, indix);
                cookieMediator.setCookie(newCookie);
                logger.info("xxl-job LoginService  login result:{}", cookie);
            }
        } catch (Exception e) {
            logger.error("xxl-job LoginService  login fail info:{}", e);
        }
    }
}
