package help.lixin.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.jenkins.api.ICrumbIssuerService;
import help.lixin.jenkins.api.impl.CrumbIssuerService;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;

import java.io.File;
import java.nio.charset.Charset;

public abstract class BasicTest {

    protected ICrumbIssuerService crumbIssuerService;
    protected JenkinsClient client;

    private String url = "http://103.215.125.86:2080";
    private String username = "lixin";

    @Before
    public void initClient() throws Exception {
        client = JenkinsClient.builder() //
                .endPoint(url) // Optional. Defaults to
                .credentials(username + ":" + getPwd()) // Optional.
                .build();
        crub();
    }

    public void crub() {
        crumbIssuerService = new CrumbIssuerService(client);
        Crumb crumb = crumbIssuerService.crumb();
        Assert.assertNotNull(null != crumb && crumb.errors().size() == 0);
    }

    protected String getPwd() throws Exception {
        String jenkinsPwd = "/Users/lixin/jenkins-pwd.txt";
        // 有这么严重的Bug吗?读取文件时,最后附加上一个回车符.
        String pwd = FileUtils.readFileToString(new File(jenkinsPwd), Charset.forName("ISO-8859-1"));
        pwd = pwd.substring(0, pwd.length() - 1);
        return pwd;
    }
}
