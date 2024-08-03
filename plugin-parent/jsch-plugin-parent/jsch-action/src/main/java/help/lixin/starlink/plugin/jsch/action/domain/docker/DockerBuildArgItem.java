package help.lixin.starlink.plugin.jsch.action.domain.docker;

import java.io.Serializable;

public class DockerBuildArgItem implements Serializable {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
