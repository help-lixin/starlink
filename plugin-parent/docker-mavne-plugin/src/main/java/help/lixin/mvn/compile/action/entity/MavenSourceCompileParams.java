package help.lixin.mvn.compile.action.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MavenSourceCompileParams {
    // 源代码目录
    private String sourceDir;
    // 项目名称
    private String projectName;
    // 基础镜像
    private String image;

    // binds
    private Map<String, String> binds = new HashMap<>(0);

    // 容器镜像目录
    private String workingDir;

    // 容器执行的命令
    private List<String> cmds = new ArrayList<>(0);

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getBinds() {
        return binds;
    }

    public void setBinds(Map<String, String> binds) {
        if (null != binds) {
            this.binds = binds;
        }
    }

    public void setBind(String localPath, String destPath) {
        if (null != binds && null != localPath && null != destPath) {
            binds.put(localPath, destPath);
        }
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public List<String> getCmds() {
        return cmds;
    }

    public void setCmds(List<String> cmds) {
        this.cmds = cmds;
    }
}
