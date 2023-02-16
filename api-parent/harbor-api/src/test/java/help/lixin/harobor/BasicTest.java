package help.lixin.harobor;

import help.lixin.harobor.properties.HarborProperties;
import org.apache.commons.io.FileUtils;
import org.junit.Before;

import java.io.File;
import java.nio.charset.Charset;

public abstract class BasicTest {

    protected HarborProperties harborProperties;

    @Before
    public void initHarborProperties() throws Exception {
        harborProperties = new HarborProperties();
        harborProperties.setUrl("http://103.215.125.86:3080/api/v2.0");
        harborProperties.setUserName("admin");
        harborProperties.setPassword(getPassword());
    }

    protected String getPassword() throws Exception {
        String tokenPath = "/Users/lixin/harbor.txt";
        // 有这么严重的Bug吗?读取文件时,最后附加上一个回车符.
        String pwd = FileUtils.readFileToString(new File(tokenPath), Charset.forName("ISO-8859-1"));
        pwd = pwd.substring(0, pwd.length() - 1);
        return pwd;
    }
}
