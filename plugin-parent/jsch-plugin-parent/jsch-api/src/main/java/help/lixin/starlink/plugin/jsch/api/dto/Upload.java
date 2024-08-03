package help.lixin.starlink.plugin.jsch.api.dto;

public class Upload {

    // 配置远程文件路径
    private String remoteFile;
    // 只能是远程目录
    private String remoteDir;
    // 本地目录
    private LocalDir localDir;
    // 本地文件
    private String localFile;

    public String getRemoteFile() {
        return remoteFile;
    }

    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public LocalDir getLocalDir() {
        return localDir;
    }

    public void setLocalDir(LocalDir localDir) {
        this.localDir = localDir;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }
}