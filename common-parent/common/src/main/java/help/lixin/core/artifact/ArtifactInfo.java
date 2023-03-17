package help.lixin.core.artifact;

import java.io.File;
import java.io.Serializable;

public class ArtifactInfo implements Serializable {
    //  成品库目录(/Users/lixin/GitRepository/spring-web-demo/target)
    private String artifactDir;
    // 成品库文件名
    private String artifactFileName;
    // 成品库全名称
    private String artifactFullName;

    public String getArtifactDir() {
        return artifactDir;
    }

    public void setArtifactDir(String artifactDir) {
        this.artifactDir = artifactDir;
    }

    public String getArtifactFileName() {
        return artifactFileName;
    }

    public void setArtifactFileName(String artifactFileName) {
        this.artifactFileName = artifactFileName;
    }

    public String getArtifactFullName() {
        return artifactFullName;
    }

    public void setArtifactFullName(String artifactFullName) {
        this.artifactFullName = artifactFullName;
        if (null != artifactFullName) {
            try {
                File tmp = new File(artifactFullName);
                if (tmp.exists()) {
                    String artifactDir = tmp.getParent();
                    String artifactFileName = tmp.getName();
                    this.artifactDir = artifactDir;
                    this.artifactFileName = artifactFileName;
                }
            } catch (Exception ignore) {
            }
        }
    }
}
