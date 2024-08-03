package help.lixin.plugin.eureka.api.properties;

public class EurekaProperties {
    private String url;
    private Integer expiration;

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
