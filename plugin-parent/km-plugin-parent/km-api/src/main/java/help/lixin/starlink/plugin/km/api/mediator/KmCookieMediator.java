package help.lixin.starlink.plugin.km.api.mediator;


import java.util.Date;

public class KmCookieMediator {

    public String cookie;

    public Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
