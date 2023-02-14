package help.lixin;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.gitlab4j.api.GitLabApi;
import org.junit.Before;

import java.io.File;
import java.nio.charset.Charset;

public abstract class BasicTest {
    protected GitLabApi gitLabApi = null;

    @Before
    public void init() throws Exception {
        // 切记,不要把token放在代码里.
        String personalAccessToken = getPersonalAccessToken();
        gitLabApi = new GitLabApi("http://103.215.125.86:1080", personalAccessToken);
    }

    protected String getPersonalAccessToken() throws Exception {
        String tokenPath = "/Users/lixin/gitlab-token.txt";
        // 有这么严重的Bug吗?读取文件时,最后附加上一个回车符.
        String personalAccessToken = FileUtils.readFileToString(new File(tokenPath), Charset.forName("ISO-8859-1"));
        personalAccessToken = personalAccessToken.substring(0, personalAccessToken.length() - 1);
        return personalAccessToken;
    }
}
