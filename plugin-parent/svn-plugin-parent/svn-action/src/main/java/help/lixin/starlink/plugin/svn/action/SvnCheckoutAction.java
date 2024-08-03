package help.lixin.starlink.plugin.svn.action;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.core.log.Log;
import help.lixin.starlink.plugin.svn.action.entity.CredentialsProvider;
import help.lixin.starlink.plugin.svn.action.entity.SvnCheckoutParams;
import help.lixin.starlink.plugin.svn.action.entity.UsernamePasswordCredentialsProvider;
import help.lixin.starlink.plugin.svn.action.service.ISVNCredentialService;

/**
 * 参考地址: https://wiki.svnkit.com/Managing_A_Working_Copy https://www.cnblogs.com/douJiangYouTiao888/p/6136102.html
 */
public class SvnCheckoutAction implements Action {
    private Logger logger = LoggerFactory.getLogger(SvnCheckoutAction.class);

    public static final String SVN_CHECKOUT_ACTION = "svn-checkout";

    private IExpressionService expressionService;

    private ISVNCredentialService svnCredentialService;

    private String sourceDirectory;

    public SvnCheckoutAction(String sourceDirectory, //
        ISVNCredentialService svnCredentialService, //
        IExpressionService expressionService) {
        this.sourceDirectory = sourceDirectory;
        this.expressionService = expressionService;
        this.svnCredentialService = svnCredentialService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        Map<String, Object> context = ctx.getVars();
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        // 忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SvnCheckoutParams svnCheckoutParams = objectMapper.readValue(stageParams, SvnCheckoutParams.class);

        // 项目名称
        String projectName = svnCheckoutParams.getProjectName();
        if (null == projectName) {
            projectName = getProjectName(svnCheckoutParams.getUrl());
        }

        // 工作目录
        String workspaceDir = String.format("%s%s", //
            sourceDirectory, //
            projectName);
        File workspaceFileDir = new File(workspaceDir);
        if (workspaceFileDir.exists()) { // 存在目录,则先删了目录
            FileUtils.forceDelete(workspaceFileDir);
        }

        // 强制创建目录
        FileUtils.forceMkdir(workspaceFileDir);

        Log.trace("准备工作目录(#%s):[%s]", Constant.SourceCodeRepository.REPOSITORY_WORKSPACE, workspaceDir);

        SVNURL url = SVNURL.parseURIEncoded(svnCheckoutParams.getUrl());
        SVNRepository repository = SVNRepositoryFactory.create(url, null);
        SVNClientManager clientManager = SVNClientManager.newInstance();

        String credentialId = svnCheckoutParams.getCredentialId();
        if (StringUtils.isNotBlank(credentialId)) {
            CredentialsProvider credentialsProvider = svnCredentialService.queryById(credentialId);
            if (null == credentialsProvider) {
                Log.trace("您所配置的凭证主键:[%s]不存在", credentialId);
                return false;
            } else {
                if (credentialsProvider instanceof UsernamePasswordCredentialsProvider) {
                    UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =
                        (UsernamePasswordCredentialsProvider)credentialsProvider;
                    ISVNAuthenticationManager authenticationManager =
                        BasicAuthenticationManager.newInstance(usernamePasswordCredentialsProvider.getUsername(), //
                            usernamePasswordCredentialsProvider.getPassword().toCharArray());
                    repository.setAuthenticationManager(authenticationManager);
                    clientManager.setAuthenticationManager(authenticationManager);
                }
            }
        }

        // 获取最后一个版本
        long latestRevision = repository.getLatestRevision();
        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);

        SVNRevision svnRevision = SVNRevision.create(latestRevision);
        Log.trace("准备执行如下命令:[ cd %s && svn checkout %s ]", workspaceDir, url);
        updateClient.doExport(repository.getLocation(), workspaceFileDir, svnRevision, svnRevision, null, true,
            SVNDepth.INFINITY);
        Log.trace("执行如下命令:[ cd %s && svn checkout %s ]结束", workspaceDir, url);

        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_WORKSPACE, workspaceDir);
        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_URL, svnCheckoutParams.getUrl());
        ctx.addVar(Constant.Project.PROJECT_NAME, projectName);
        ctx.addVar(Constant.BuildInfo.BUILD_NUMBER, System.currentTimeMillis());

        // String trceMsg = String.format("\n#{%s}=%s\n#{%s}=%s\n#{%s}=%s\n#{%s}=%d",
        // Constant.SourceCodeRepository.REPOSITORY_WORKSPACE,
        // ctx.getVar(Constant.SourceCodeRepository.REPOSITORY_WORKSPACE), //
        // Constant.SourceCodeRepository.REPOSITORY_URL, ctx.getVar(Constant.SourceCodeRepository.REPOSITORY_URL), //
        // Constant.Project.PROJECT_NAME, ctx.getVar(Constant.Project.PROJECT_NAME), //
        // Constant.BuildInfo.BUILD_NUMBER, ctx.getVar(Constant.BuildInfo.BUILD_NUMBER) //
        // );
        // Log.trace(trceMsg);
        return true;
    }

    protected String getProjectName(String urlString) throws Exception {
        try {
            URI uri = new URI(urlString);
            return uri.getPath().substring(1);
        } catch (Exception e) {
            String msg = String.format("从URL:[%s]中,解析出项目名称失败,失败原因:[\n%s]", urlString, e.getMessage());
            Log.trace(msg);
            throw new Exception(msg);
        }
    }

    @Override
    public String name() {
        return SVN_CHECKOUT_ACTION;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/svn-clone-meta.json";
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