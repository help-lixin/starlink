package help.lixin.starlink.plugin.gitlab.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.util.FS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import help.lixin.core.log.Log;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.plugin.gitlab.action.entity.*;
import help.lixin.starlink.plugin.gitlab.action.service.IGitlabCredentialService;

public class GitlabCloneAction implements Action {
    private Logger logger = LoggerFactory.getLogger(GitlabCloneAction.class);

    public static final String GITLAB_CLONE_ACTION = "gitlab-clone";
    private IExpressionService expressionService;
    private IGitlabCredentialService gitlabCredentialService;

    private String sourceDirectory;

    public GitlabCloneAction(String sourceDirectory, //
        IExpressionService expressionService, //
        IGitlabCredentialService gitlabCredentialService) {
        this.sourceDirectory = sourceDirectory;
        this.expressionService = expressionService;
        this.gitlabCredentialService = gitlabCredentialService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        Map<String, Object> context = ctx.getVars();
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        // 忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GitCloneParams gitCloneParams = objectMapper.readValue(stageParams, GitCloneParams.class);

        String url = gitCloneParams.getUrl();
        // 如果没有配置项目名称的情况下,会自动读取url上的项目名称
        String projectName = gitCloneParams.getProjectName();
        if (null == projectName) {
            projectName = getProjectName(url);
        }

        // 根据:URL+分支来定位
        String workspaceDir = String.format("%s%s-%s", //
            sourceDirectory, //
            projectName, //
            gitCloneParams.getBranch());

        File workspaceFileDir = new File(workspaceDir);
        if (workspaceFileDir.exists()) { // 存在目录,则先删了目录
            FileUtils.forceDelete(workspaceFileDir);
        }
        // 强制创建目录
        FileUtils.forceMkdir(workspaceFileDir);

        Log.trace("准备工作目录(#%s):[%s]", Constant.SourceCodeRepository.REPOSITORY_WORKSPACE, workspaceDir);

        // 构建clone命令
        CloneCommand cloneCommand = Git.cloneRepository();
        String credentialId = gitCloneParams.getCredentialId();
        if (StringUtils.isNotBlank(credentialId)) {
            CredentialsProvider credentialsProvider = gitlabCredentialService.queryById(credentialId);
            if (null == credentialsProvider) {
                Log.trace("您所配置的凭证主键:[%s]不存在", credentialId);
                return false;
            } else {
                if (credentialsProvider instanceof TokenCredentialsProvider) { // TOKEN
                    TokenCredentialsProvider tokenCredentials = (TokenCredentialsProvider)credentialsProvider;
                    cloneCommand
                        .setCredentialsProvider(new org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider( //
                            "PRIVATE-TOKEN", expressionService.prase(tokenCredentials.getToken(), context)));
                } else if (credentialsProvider instanceof UsernamePasswordCredentialsProvider) {
                    UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =
                        (UsernamePasswordCredentialsProvider)credentialsProvider;
                    cloneCommand
                        .setCredentialsProvider(new org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider( //
                            usernamePasswordCredentialsProvider.getUsername(),
                            expressionService.prase(usernamePasswordCredentialsProvider.getPassword(), context)));
                } else if (credentialsProvider instanceof SSHCredentialsProvider) { // SSH
                    SSHCredentialsProvider sshCredentialsProvider = (SSHCredentialsProvider)credentialsProvider;
                    JschConfigSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
                        @Override
                        protected void configure(OpenSshConfig.Host hc, Session session) {
                            super.configure(hc, session);
                            session.setConfig("StrictHostKeyChecking", "no");
                        }

                        @Override
                        protected JSch createDefaultJSch(FS fs) throws JSchException {
                            JSch defaultJSch = super.createDefaultJSch(fs);
                            String privateKey = sshCredentialsProvider.getPrivateKey();
                            String publicKey = sshCredentialsProvider.getPublicKey();
                            String privateKeyPassphrase = sshCredentialsProvider.getPrivateKeyPassphrase();
                            if (StringUtils.isNotBlank(privateKey)) {
                                String name = String.format("gitlab_key");
                                defaultJSch.addIdentity(name, //
                                    privateKey.getBytes(), //
                                    null != publicKey ? publicKey.getBytes() : null, //
                                    null != privateKeyPassphrase ? privateKeyPassphrase.getBytes() : null);
                            }
                            return defaultJSch;
                        }
                    };
                    cloneCommand//
                        .setTransportConfigCallback((transport) -> {
                            if (transport instanceof SshTransport) {
                                SshTransport sshTransport = (SshTransport)transport;
                                sshTransport.setSshSessionFactory(sshSessionFactory);
                            }
                        });
                }
            }
        }

        Git call = null;
        try {
            Log.trace("准备执行如下命令:[ cd %s && git clone -b %s %s ]", workspaceDir, gitCloneParams.getBranch(), url);
            // 配置url/branch/directory
            call = cloneCommand.setURI(url) //
                .setBranch(gitCloneParams.getBranch()) //
                .setDirectory(new File(workspaceDir)) //
                .call();
            Log.trace("执行如下命令:[ cd %s && git clone -b %s %s ]结束", workspaceDir, gitCloneParams.getBranch(), url);
        } finally {
            if (null != call) {
                call.close();
            }
        }

        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_WORKSPACE, workspaceDir);
        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_URL, url);
        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_BRANCH, gitCloneParams.getBranch());
        ctx.addVar(Constant.Project.PROJECT_NAME, projectName);
        ctx.addVar(Constant.BuildInfo.BUILD_NUMBER, System.currentTimeMillis());

        // String trceMsg = String.format("\n#{%s}=%s\n#{%s}=%s\n#{%s}=%s\n#{%s}=%s\n#{%s}=%d",
        // Constant.SourceCodeRepository.REPOSITORY_WORKSPACE,
        // ctx.getVar(Constant.SourceCodeRepository.REPOSITORY_WORKSPACE), //
        // Constant.SourceCodeRepository.REPOSITORY_URL, ctx.getVar(Constant.SourceCodeRepository.REPOSITORY_URL), //
        // Constant.SourceCodeRepository.REPOSITORY_BRANCH, ctx.getVar(Constant.SourceCodeRepository.REPOSITORY_BRANCH),
        // //
        // Constant.Project.PROJECT_NAME, ctx.getVar(Constant.Project.PROJECT_NAME), //
        // Constant.BuildInfo.BUILD_NUMBER, ctx.getVar(Constant.BuildInfo.BUILD_NUMBER) //
        // );
        // Log.trace(trceMsg);
        return true;
    }

    protected String getProjectName(String urlString) throws Exception {
        String result = null;
        try {
            String[] paths = urlString.replaceAll(".git", "").split("/");
            if (paths.length > 0) {
                result = paths[paths.length - 1];
            }
        } catch (Exception e) {
            String msg = String.format("从URL:[%s]中,解析出项目名称失败,失败原因:[\n%s\n]", urlString, e.getMessage());
            Log.trace(msg);
            throw new Exception(msg);
        }
        return result;
    }

    @Override
    public String name() {
        return GITLAB_CLONE_ACTION;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/gitlab-clone-meta.json";
        Resource metaResource = new ClassPathResource(pluginMetaPath);
        if (metaResource.exists()) {
            try {
                meta = IOUtils.toString(metaResource.getInputStream(), "UTF-8");
            } catch (IOException ignore) {
                String msg = String.format("读取插件元配置[%s]出现异常,异常详细如下:[\n%s\n]", pluginMetaPath, ignore);
                logger.warn(msg);
            }
        }
        return meta;
    }
}