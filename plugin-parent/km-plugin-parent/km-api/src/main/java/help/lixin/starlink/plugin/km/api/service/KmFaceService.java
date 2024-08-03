package help.lixin.starlink.plugin.km.api.service;


public class KmFaceService {
    IKmLoginAPIService kmLoginAPIService;

    public KmFaceService(IKmLoginAPIService kmLoginAPIService) {
        this.kmLoginAPIService = kmLoginAPIService;
    }

    public IKmLoginAPIService getKmLoginAPIService() {
        return kmLoginAPIService;
    }

    public void setKmLoginAPIService(IKmLoginAPIService kmLoginAPIService) {
        this.kmLoginAPIService = kmLoginAPIService;
    }
}
