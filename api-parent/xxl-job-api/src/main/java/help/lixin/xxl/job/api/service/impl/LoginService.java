package help.lixin.xxl.job.api.service.impl;

import help.lixin.xxl.job.api.mediator.CookieMediator;
import help.lixin.xxl.job.api.service.ILoginService;
import kong.unirest.Cookie;
import kong.unirest.Cookies;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService implements ILoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String LOGIN_URL = "/login";

    private String url;

    public LoginService(String url) {
        this.url = url;
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
                CookieMediator.getInstance().setCookie(newCookie);
                logger.info("xxl-job LoginService  login result:{}", cookie);
            }
        } catch (Exception e) {
            logger.error("xxl-job LoginService  login fail info:{}", e);
        }
    }
}
