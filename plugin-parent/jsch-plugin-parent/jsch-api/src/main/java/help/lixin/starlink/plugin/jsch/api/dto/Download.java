package help.lixin.starlink.plugin.jsch.api.dto;

public class Download {
    // 远程目录或文件
    private String remoteDir;
    // 本地目录(可以为空,为空的情况下,会创建一个临时文件来存放.)
    private String localDir;

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }
}
