package help.lixin.gitlab.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.gitlab.action.entity.*;
import help.lixin.gitlab.service.GitlabFaceService;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.util.FS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class GitlabCloneAction implements Action {
    private Logger logger = LoggerFactory.getLogger(GitlabCloneAction.class);
    public static final String GITLAB_CLONE_ACTION = "gitlab-clone";

    private GitlabFaceService gitlabFaceService;

    private java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();

    public GitlabCloneAction(GitlabFaceService gitlabFaceService) {
        this.gitlabFaceService = gitlabFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        GitCloneParams gitCloneParams = objectMapper.readValue(stageParams, GitCloneParams.class);

        // 根据:URL+分支来定位
        String resource = String.format("%s/%s", gitCloneParams.getUrl(), gitCloneParams.getBranch());
        crc32.update(resource.getBytes());
        long dirName = crc32.getValue();
        String workspaceDir = String.format("%s/%s", gitCloneParams.getWorkspaceDir(), dirName);

        File workspaceFileDir = new File(workspaceDir);
        if (workspaceFileDir.exists()) { // 存在目录,则先删了目录
            FileUtils.forceDelete(workspaceFileDir);
        }
        // 强制创建目录
        FileUtils.forceMkdir(workspaceFileDir);

        CredentialsProvider credential = gitCloneParams.getCredential();
        // 构建clone命令
        CloneCommand cloneCommand = Git.cloneRepository();
        if (credential instanceof SSHCredentialsProvider) { // SSH
            JschConfigSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
                @Override
                protected void configure(OpenSshConfig.Host hc, Session session) {
                    super.configure(hc, session);
                    session.setConfig("StrictHostKeyChecking", "no");
                }

                @Override
                protected JSch createDefaultJSch(FS fs) throws JSchException {
                    JSch defaultJSch = super.createDefaultJSch(fs);
                    // 可以自定义:添加私钥
                    // defaultJSch.addIdentity();
                    return defaultJSch;
                }
            };
            cloneCommand//
                    .setTransportConfigCallback((transport) -> {
                        if (transport instanceof SshTransport) {
                            SshTransport sshTransport = (SshTransport) transport;
                            sshTransport.setSshSessionFactory(sshSessionFactory);
                        }
                    });
        } else if (credential instanceof UsernamePasswordCredentialsProvider) { // 用户名和密码
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = (UsernamePasswordCredentialsProvider) credential;
            cloneCommand.setCredentialsProvider(new org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider(//
                    usernamePasswordCredentialsProvider.getUsername(),//
                    usernamePasswordCredentialsProvider.getPassword()));
        } else if (credential instanceof TokenCredentialsProvider) { // token
            TokenCredentialsProvider tokenCredentialsProvider = (TokenCredentialsProvider) credential;
            cloneCommand.setCredentialsProvider(new org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider( //
                    "PRIVATE-TOKEN",
                    //
                    tokenCredentialsProvider.getToken()));
        }

        // 配置url/branch/directory
        Git call = cloneCommand.setURI(gitCloneParams.getUrl()) //
                .setBranch(gitCloneParams.getBranch()) //
                .setDirectory(new File(workspaceDir)) //
                .call();

        // 如果没有配置项目名称的情况下,会自动读取url上的项目名称
        String projectName = gitCloneParams.getProjectName();
        if (null == projectName) {
            projectName = getProjectName(gitCloneParams.getUrl());
        }

        ctx.addVar(Constant.CodeRepository.WORKSPACE_DIR, workspaceDir);
        ctx.addVar("projectName", projectName);
        ctx.addVar("branch", gitCloneParams.getBranch());
        ctx.addVar("url", gitCloneParams.getUrl());
        logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        return true;
    }

    protected String getProjectName(String urlString) throws Exception {
        String result = null;
        try {
            URL url = new URL(urlString);
            String[] paths = url.getPath().replaceAll(".git", "").split("/");
            if (paths.length > 0) {
                result = paths[paths.length - 1];
            }
        } catch (Exception e) {
            String msg = String.format("从URL:[%s]中,解析出项目名称失败,失败原因:[\n%s]", urlString, e.getMessage());
            logger.error(msg);
            throw new Exception(msg);
        }
        return result;
    }

    @Override
    public String name() {
        return GITLAB_CLONE_ACTION;
    }
}