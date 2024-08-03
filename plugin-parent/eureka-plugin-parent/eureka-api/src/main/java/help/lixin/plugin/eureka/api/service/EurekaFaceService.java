package help.lixin.plugin.eureka.api.service;

public class EurekaFaceService {

    private IEurekaClient eurekaClient;

    public EurekaFaceService(IEurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }
}
