package help.lixin.xxl.job.api;

import help.lixin.xxl.job.api.service.ILoginService;
import help.lixin.xxl.job.api.service.impl.LoginService;
import org.junit.Before;

public abstract class BasicTest {

    protected ILoginService loginService;

    protected String url;

    @Before
    public void login() {
        url = "http://localhost:8080/xxl-job-admin";
        loginService = new LoginService(url);
        loginService.login("admin", "123456");
    }
}
