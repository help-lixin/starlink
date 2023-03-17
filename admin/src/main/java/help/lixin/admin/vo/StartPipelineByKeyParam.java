package help.lixin.admin.vo;

public class StartPipelineByKeyParam extends AbstractStartPipelineParam {
    private String key;
    private Integer version;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "StartPipelineByKeyParam{" +
                "key='" + key + '\'' +
                ", version=" + version +
                '}';
    }
}
