package help.lixin.starlink.plugin.jsch.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import help.lixin.starlink.plugin.jsch.api.dto.Download;
import help.lixin.starlink.plugin.jsch.api.dto.LocalDir;
import help.lixin.starlink.plugin.jsch.api.dto.Upload;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.properties.Mode;
import help.lixin.starlink.plugin.jsch.api.service.ISecureCopyService;
import help.lixin.starlink.plugin.jsch.api.util.Scp;

public class SecureCopyService implements ISecureCopyService {
    private JschProperties properties;
    private final Session session;

    public SecureCopyService(JschProperties properties, //
        Session session) {
        this.properties = properties;
        this.session = session;
    }

    /**
     * ant最终配置scp的案例为下: 1. 拷贝远程文件,到本地目录
     * <scp file="root:123456@192.168.122.180:/tmp/cmd.txt" todir="D:/my-app" trust="true"/> <br/>
     * 2. 拷贝远程目录,到本地目录 <scp file="root:123456@192.168.122.180:/tmp/*" todir="d:/my-app" trust="true"/> <br/>
     *
     * @param download
     * @return 此处返回的是下载到本机之后的临时目录, 而非具体的文件,至于文件,需要业务自己去读取.
     */
    public String download(Download download) {
        Scp scp = new Scp() {
            // 重定自己定义的session
            @Override
            protected Session openSession() throws JSchException {
                if (getServerAliveIntervalSeconds() > 0) {
                    session.setServerAliveCountMax(getServerAliveCountMax());
                    session.setServerAliveInterval(getServerAliveIntervalSeconds() * 1000);
                }
                return session;
            }
        };

        // 指定远程目录(密码模式)
        // app:Li@88888888@192.168.1.23:/tmp/
        // 用户名:密码@主机:远程目录
        String remoteFullPath = null;
        if (properties.getMode().equals(Mode.PASSWORD)) {
            remoteFullPath = String.format("%s:%s@%s:%s", //
                properties.getUsername(), //
                properties.getPassword(), //
                properties.getHost(), //
                download.getRemoteDir());
        } else {
            // 密钥模式(指定私钥文件)
            remoteFullPath = String.format("%s@%s:%s", //
                properties.getUsername(), //
                properties.getHost(), //
                download.getRemoteDir());
        }
        scp.setFile(remoteFullPath);

        // 如果本地目录为空的情况下,创建一个临时目录,用来存储远程下载回来的文件到该目录下.
        String localDir = download.getLocalDir();
        if (null == localDir) {
            try {
                localDir = Files.createTempDirectory("jsch").toFile().getPath();
            } catch (IOException e) {
            }
        }
        scp.setTodir(localDir);
        // 指定安全传输
        scp.setTrust(true);
        // 通过sftp来传输
        scp.setSftp(true);
        scp.setProject(new Project());
        // 开始传输
        scp.execute();
        return localDir;
    }

    @Override
    public void upload(Upload upload) throws Exception {
        Scp scp = new Scp() {
            // 重定自己定义的session
            @Override
            protected Session openSession() throws JSchException {
                if (getServerAliveIntervalSeconds() > 0) {
                    session.setServerAliveCountMax(getServerAliveCountMax());
                    session.setServerAliveInterval(getServerAliveIntervalSeconds() * 1000);
                }
                return session;
            }
        };

        // 指定远程目录(密码模式)
        // app:Li@88888888@192.168.1.23:/tmp/
        // 用户名:密码@主机:远程目录
        String remoteFullPath = null;
        if (properties.getMode().equals(Mode.PASSWORD)) {
            remoteFullPath = String.format("%s:%s@%s:%s", //
                properties.getUsername(), //
                properties.getPassword(), //
                properties.getHost(), //
                upload.getRemoteDir());
        } else {
            // 密钥模式(指定私钥文件)
            remoteFullPath = String.format("%s@%s:%s", //
                properties.getUsername(), //
                properties.getHost(), //
                upload.getRemoteDir());
        }

        // 指定远程服务器的目录
        scp.setRemoteTodir(remoteFullPath);

        // 指定本地文件
        FileSet fileSet = new FileSet();
        // 本地文件处理
        if (StringUtils.isNotBlank(upload.getLocalFile())) {
            fileSet.setFile(new File(upload.getLocalFile()));
        } else {
            LocalDir localDir = upload.getLocalDir();
            if (null != localDir) {
                fileSet.setDir(new File(localDir.getLocalDir()));
                if (null != localDir.getExcludes()) {
                    fileSet.setExcludes(localDir.getExcludes());
                }
                if (null != localDir.getIncludes()) {
                    fileSet.setIncludes(localDir.getIncludes());
                }
            }
        }
        scp.addFileset(fileSet);

        // 指定安全传输
        scp.setTrust(true);
        // 通过sftp来传输
        scp.setSftp(true);
        scp.setProject(new Project());
        // 开始传输
        scp.execute();
    }

    @Override
    public JschProperties getJschProperties() {
        return properties;
    }
}
