package help.lixin.core.credentials;

public abstract class AbstractCredentials implements Credentials {
    protected String url;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
