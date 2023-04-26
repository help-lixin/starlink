package help.lixin.gitlab.action;

import org.junit.Test;

import java.net.URL;

public class UrlTest {

    @Test
    public void testUrl() throws Exception {
        String urlString = "http://192.168.8.10/erp/spring-web-demo.git";
        URL url = new URL(urlString);
        String[] paths = url.getPath().replaceAll(".git", "").split("/");
        if (paths.length > 0) {
            System.out.println(paths[paths.length - 1]);
        }
    }
}
