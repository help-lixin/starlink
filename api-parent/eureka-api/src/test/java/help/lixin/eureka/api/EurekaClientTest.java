package help.lixin.eureka.api;

import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import help.lixin.eureka.api.impl.EurekaClientImpl;
import help.lixin.eureka.api.status.EurekaInstanceStatus;
import org.junit.Before;
import org.junit.Test;

public class EurekaClientTest {

    private EurekaClient eurekaClient;

    @Before
    public void init() {
        eurekaClient = new EurekaClientImpl("http://127.0.0.1:1111/eureka");
    }

    @Test
    public void testRegisterInstance() {
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
                //
                .setInstanceId("127.0.0.1:test-consumer:9999")
                //
                .setAppName("test-consumer")
                //
                .setIPAddr("127.0.0.1")
                //
                .setHostName("127.0.0.1")
                //
                .setPort(9999)
                //
                .setVIPAddress("test-consumer")
                //
                .setDataCenterInfo(() -> DataCenterInfo.Name.MyOwn)
                //
                .build();
        eurekaClient.registerInstance(instanceInfo);
    }


    @Test
    public void deleteTest() {
        eurekaClient.deleteInstance("test-consumer", "127.0.0.1:test-consumer:9999");
    }

    @Test
    public void applicationsTest() {
        // 注意
        // 额,居然要等一段时间才能Query到数据.
        Applications applications = eurekaClient.applications();
        // Application [name=TEST-PROVIDER, isDirty=true,
        // instances=[
        //     InstanceInfo [instanceId = 172.17.5.104:test-provider:8081, appName = TEST-PROVIDER, hostName = 172.17.5.104, status = UP, ipAddr = 172.17.5.104, port = 8081, securePort = 443, dataCenterInfo = com.netflix.appinfo.MyDataCenterInfo@737a135b,
        //     InstanceInfo [instanceId = 172.17.5.104:test-provider:8080, appName = TEST-PROVIDER, hostName = 172.17.5.104, status = UP, ipAddr = 172.17.5.104, port = 8080, securePort = 443, dataCenterInfo = com.netflix.appinfo.MyDataCenterInfo@687ef2e0],
        //     shuffledInstances=null,
        //     instancesMap={
        //        172.17.5.104:test-provider:8081=InstanceInfo [instanceId = 172.17.5.104:test-provider:8081, appName = TEST-PROVIDER, hostName = 172.17.5.104, status = UP, ipAddr = 172.17.5.104, port = 8081, securePort = 443, dataCenterInfo = com.netflix.appinfo.MyDataCenterInfo@737a135b,
        //        172.17.5.104:test-provider:8080=InstanceInfo [instanceId = 172.17.5.104:test-provider:8080, appName = TEST-PROVIDER, hostName = 172.17.5.104, status = UP, ipAddr = 172.17.5.104, port = 8080, securePort = 443, dataCenterInfo = com.netflix.appinfo.MyDataCenterInfo@687ef2e0
        //     }
        // ]
        for (Application application : applications.getRegisteredApplications()) {
            System.out.println(application);
        }
    }

    @Test
    public void applicationTest() {
        Application application = eurekaClient.application("test-consumer");
        for (InstanceInfo instance : application.getInstances()) {
            System.out.println(instance.getId());
        }
    }

    @Test
    public void instanceTest() {
        InstanceInfo instance = eurekaClient.instance("127.0.0.1:test-consumer:9999");
        System.out.println(instance.getId());
        System.out.println(instance.getAppName());
    }

    @Test
    public void updateMetadataTest() {
        InstanceInfo instance = eurekaClient.instance("127.0.0.1:test-consumer:9999");
        eurekaClient.updateMetadata(instance.getAppName(), instance.getInstanceId(), "ff", "fsss");

        InstanceInfo result = eurekaClient.instance("127.0.0.1:test-consumer:9999");
        System.out.println(result.getMetadata());
    }

    @Test
    public void heartBeatTest() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            eurekaClient.heartbeat("test-consumer", "127.0.0.1:test-consumer:9999");
        }
    }

    @Test
    public void outOfServiceTest() {
        eurekaClient.outOfService("test-consumer", "127.0.0.1:test-consumer:9999");
    }

    @Test
    public void backInServiceTest() {
        eurekaClient.backInService("test-consumer", "127.0.0.1:test-consumer:9999", EurekaInstanceStatus.UP);
    }

    @Test
    public void vipsTest() {
        Applications vips = eurekaClient.vips("test-consumer");
        System.out.println(vips);
    }

    @Test
    public void svipsTest() {
        Applications svips = eurekaClient.svips("test-consumer");
        System.out.println(svips);
    }
}
