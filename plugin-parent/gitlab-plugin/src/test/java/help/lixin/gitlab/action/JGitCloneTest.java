package help.lixin.gitlab.action;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JGitCloneTest {

    public static final String LOCALPATH = "/tmp/spring-web-demo";
    //.git文件路径
    public static final String LOCALGITFILE = LOCALPATH;
    //远程仓库地址
    public static final String SSH_REMOTEREPOURI = "ssh://git@192.168.8.10:2222/erp/spring-web-demo.git";
    // http
    public static final String HTTP_REMOTEREPOURI = "http://192.168.8.10/erp/spring-web-demo.git";

    public static final String GITLAB_USER_NAME = System.getenv("GITLAB_USERNAME");
    public static final String GITLAB_PWD = System.getenv("GITLAB_PWD");

    // USER-NAME
    public static final String PRIVATE_TOKEN = "PRIVATE-TOKEN";

    public static final String TOKEN = System.getenv("GITLAB_TOKEN");

    @Test
    public void testGitCloneForSSH() throws Exception {
        // ssh 的方式有一个要求是:当前宿主机,要求与gitlab已经建立了ssh允许
        JschConfigSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host hc, Session session) {
                super.configure(hc, session);
                session.setConfig("StrictHostKeyChecking", "no");
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                JSch defaultJSch = super.createDefaultJSch(fs);
                // 添加私钥
                // defaultJSch.addIdentity();
                return defaultJSch;
            }
        };

        Git git = Git.cloneRepository()//
                .setTransportConfigCallback(new TransportConfigCallback() {
                    @Override
                    public void configure(Transport transport) {
                        if (transport instanceof SshTransport) {
                            SshTransport sshTransport = (SshTransport) transport;
                            sshTransport.setSshSessionFactory(sshSessionFactory);
                        }
                    }
                }) //
                .setURI(SSH_REMOTEREPOURI)
                //
                .setBranch("main")
                //
                .setDirectory(new File(LOCALGITFILE))
                //
                .call();
        Assert.assertNotNull(git);
    }


    @Test
    public void testGitCloneForUserNameAndPwd() throws Exception {
        // http的方式是支持:用户名或密码 或者 TOKEN来着的
        Git git = Git.cloneRepository()//
                .setURI(HTTP_REMOTEREPOURI)
                // 通过账号和密码验证通过
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(PRIVATE_TOKEN, TOKEN))
//                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(GITLAB_USER_NAME, GITLAB_PWD))
                //
                .setBranch("main")
                //
                .setDirectory(new File(LOCALGITFILE))
                //
                .call();
        Assert.assertNotNull(git);
    }
}
