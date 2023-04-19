package jenkins;

import help.lixin.jenkins.service.JenkinsTemplateLoadFaceService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JenkinsTemplateLoadFaceServiceTest {

    @Test
    public void testLoadAndProcess() throws Exception {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("CODE_REPOSITORY", "gitlab");

        JenkinsTemplateLoadFaceService service = new JenkinsTemplateLoadFaceService(null);
        String s = service.loadAndProcess(null, ctx);
        System.out.println(s);
    }
}
