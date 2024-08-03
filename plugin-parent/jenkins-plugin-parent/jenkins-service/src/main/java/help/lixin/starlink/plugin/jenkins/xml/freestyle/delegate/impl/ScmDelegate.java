package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.mapper.SysCredentialCommonMapper;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.IScmDelegate;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.AbstractScm;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.GitScm;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.SvnScm;

public class ScmDelegate implements IScmDelegate {

    private IJenkinsPluginService jenkinsPluginService;

    private SysCredentialCommonMapper sysCredentialCommonMapper;


    private static final String PLUGIN_NAME_GIT = "git";
    private static final String PLUGIN_NAME_SVN = "subversion";

    public ScmDelegate(SysCredentialCommonMapper sysCredentialCommonMapper, //
                       IJenkinsPluginService jenkinsPluginService) {
        this.sysCredentialCommonMapper = sysCredentialCommonMapper;
        this.jenkinsPluginService = jenkinsPluginService;
    }

    @Override
    public AbstractScm apply(ScmType scmType,//
                             String instanceCode, //
                             JenkinsPipelineScmDTO scmDto) {
        AbstractScm scm = null;
        if (scmType.equals(ScmType.GIT)) {
            scm = new GitScm();
            GitScm gitScm = (GitScm) scm;
            String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_GIT);
            gitScm.setPlugin(plugin);
            gitScm.setUrl(scmDto.getUrl());
            gitScm.setBranch(scmDto.getBranch());
        }

        if (scmType.equals(ScmType.SVN)) {
            scm = new SvnScm();
            SvnScm svnScm = (SvnScm) scm;
            String plugin = jenkinsPluginService.queryPluginInfo(instanceCode, PLUGIN_NAME_SVN);
            svnScm.setPlugin(plugin);
            svnScm.setRemote(scmDto.getUrl());
        }

        if (null != scm && //
                null != scmDto.getCredentialsId()) {
            // 由于凭证id是数据库的唯一id,所以,在这里就不用再加上instanceCode了
            SysCredentialCommon sysCredential = sysCredentialCommonMapper.selectById(scmDto.getCredentialsId());
            if (null != sysCredential) {
                scm.setCredentialsId(sysCredential.getCredentialKey());
            }
        }
        return scm;
    }

}
