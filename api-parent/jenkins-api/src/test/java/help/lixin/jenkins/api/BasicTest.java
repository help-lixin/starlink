package help.lixin.jenkins.api;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.queue.QueueItem;
import com.cdancy.jenkins.rest.features.CrumbIssuerApi;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.cdancy.jenkins.rest.features.QueueApi;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTest {
    public static void main(String[] args) throws Exception {
        JenkinsClient client = JenkinsClient.builder() //
                .endPoint("http://172.30.50.7:8080") // Optional. Defaults to
                .credentials("lixin:lixin") // Optional.
                .build();
        crumb(client);

        // 查询
        JobList jobList = jobList(client);
        System.out.println(jobList);

        // 创建任务
        RequestStatus job = createJob(client);
        System.out.println(job);

        IntegerResponse response = build(client);
        System.out.println(response);

        queue(client, response.value());
        System.out.println();
    }


    public static void queue(JenkinsClient client, Integer queueId) {
        QueueApi queueApi = client.api().queueApi();
        QueueItem item = queueApi.queueItem(queueId);
        System.out.println(item);
    }


    public static IntegerResponse build(JenkinsClient client) {
        JobsApi jobsApi = client.api().jobsApi();
        Map<String, List<String>> properties = new HashMap<>();
        properties.put("env", Arrays.asList("dev"));
        properties.put("BRANCH", Arrays.asList("master"));

        IntegerResponse integerResponse = jobsApi.buildWithParameters(null, "tmp-setting-service", properties);
        System.out.println(integerResponse);
        return integerResponse;
    }


    /**
     * 创建任务
     *
     * @param client
     * @return
     * @throws Exception
     */
    public static RequestStatus createJob(JenkinsClient client) throws Exception {
        String path = "/Users/lixin/Workspace/jenkins-demo/src/main/resources/tmp-setting-service.xml";
        String xmlFile = IOUtils.toString(new FileInputStream(path));
        JobsApi jobsApi = client.api().jobsApi();
        RequestStatus requestStatus = jobsApi.create(null, "tmp-setting-service", xmlFile);
        return requestStatus;
    }

    /**
     * 获取所有的任务列表
     *
     * @param client
     * @return
     */
    public static JobList jobList(JenkinsClient client) {
        JobsApi jobsApi = client.api().jobsApi();
        JobList jobList = jobsApi.jobList("");
        return jobList;
    }


    /**
     * crumb登录
     *
     * @param client
     */
    public static void crumb(JenkinsClient client) {
        CrumbIssuerApi crumbIssuerApi = client.api().crumbIssuerApi();
        Crumb crumb = crumbIssuerApi.crumb();
    }
}
