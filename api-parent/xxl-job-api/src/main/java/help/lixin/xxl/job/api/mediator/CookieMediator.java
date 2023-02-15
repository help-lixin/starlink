package help.lixin.xxl.job.api.mediator;


public class CookieMediator {

    private static volatile CookieMediator COOKIE_MEDIATOR_INSTANCE;

    public static CookieMediator getInstance() {
        if (null == COOKIE_MEDIATOR_INSTANCE) {
            synchronized (CookieMediator.class) {
                if (null == COOKIE_MEDIATOR_INSTANCE) {
                    COOKIE_MEDIATOR_INSTANCE = new CookieMediator();
                    return COOKIE_MEDIATOR_INSTANCE;
                }
            }
        }
        return COOKIE_MEDIATOR_INSTANCE;
    }

    private CookieMediator() {
    }

    public String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
