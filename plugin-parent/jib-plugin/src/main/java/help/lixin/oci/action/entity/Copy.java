package help.lixin.oci.action.entity;

import java.util.ArrayList;
import java.util.List;

public class Copy {
    // 要传输到容器中的内容
    private List<String> files = new ArrayList(0);

    // 容器中的位置
    private String pathInContainer;

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getPathInContainer() {
        return pathInContainer;
    }

    public void setPathInContainer(String pathInContainer) {
        this.pathInContainer = pathInContainer;
    }
}
