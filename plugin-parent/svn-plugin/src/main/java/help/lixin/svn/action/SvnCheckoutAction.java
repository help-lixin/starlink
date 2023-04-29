package help.lixin.svn.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.svn.action.entity.SvnCheckoutParams;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * 参考地址: https://wiki.svnkit.com/Managing_A_Working_Copy
 * https://www.cnblogs.com/douJiangYouTiao888/p/6136102.html
 */
public class SvnCheckoutAction implements Action {
    private Logger logger = LoggerFactory.getLogger(SvnCheckoutAction.class);

    public static final String SVN_CHECKOUT_ACTION = "svn-checkout";

    private java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.debug("开始执行插件:[{}]", this.getClass().getName());
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        SvnCheckoutParams svnCheckoutParams = objectMapper.readValue(stageParams, SvnCheckoutParams.class);

        // 根据:URL+分支来定位
        String resource = String.format("%s", svnCheckoutParams.getUrl());
        crc32.update(resource.getBytes());
        long dirName = crc32.getValue();
        String workspaceDir = String.format("%s/%s", svnCheckoutParams.getWorkspaceDir(), dirName);

        File workspaceFileDir = new File(workspaceDir);
        if (workspaceFileDir.exists()) { // 存在目录,则先删了目录
            FileUtils.forceDelete(workspaceFileDir);
        }
        // 强制创建目录
        FileUtils.forceMkdir(workspaceFileDir);

        // 项目名称
        String projectName = svnCheckoutParams.getProjectName();
        if (null == projectName) {
            projectName = getProjectName(svnCheckoutParams.getUrl());
        }

        // 用户名和密码
        String userName = svnCheckoutParams.getUserName();
        String password = svnCheckoutParams.getPassword();
        char[] passwordArray = null;
        if (null != password) {
            passwordArray = password.toCharArray();
        }

        ISVNAuthenticationManager authenticationManager = BasicAuthenticationManager.newInstance(userName, passwordArray);
        SVNURL url = SVNURL.parseURIEncoded(svnCheckoutParams.getUrl());
        SVNRepository repository = SVNRepositoryFactory.create(url, null);
        repository.setAuthenticationManager(authenticationManager);
        // 获取最后一个版本
        long latestRevision = repository.getLatestRevision();

        SVNClientManager clientManager = SVNClientManager.newInstance();
        clientManager.setAuthenticationManager(authenticationManager);

        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);

        SVNRevision svnRevision = SVNRevision.create(latestRevision);
        updateClient.doExport(repository.getLocation(), workspaceFileDir, svnRevision, svnRevision, null, true, SVNDepth.INFINITY);


        ctx.addVar(Constant.CodeRepository.WORKSPACE_DIR, workspaceDir);
        ctx.addVar("projectName", projectName);
        ctx.addVar("url", svnCheckoutParams.getUrl());
        logger.debug("结束插件执行: [{}]", this.getClass().getName());
        return true;
    }

    protected String getProjectName(String urlString) throws Exception {
        try {
            URI uri = new URI(urlString);
            return uri.getPath().substring(1);
        } catch (Exception e) {
            String msg = String.format("从URL:[%s]中,解析出项目名称失败,失败原因:[\n%s]", urlString, e.getMessage());
            logger.error(msg);
            throw new Exception(msg);
        }
    }

    @Override
    public String name() {
        return SVN_CHECKOUT_ACTION;
    }
}