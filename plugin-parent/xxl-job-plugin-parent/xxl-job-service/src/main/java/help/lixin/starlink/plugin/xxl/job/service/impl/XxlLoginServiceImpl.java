package help.lixin.starlink.plugin.xxl.job.service.impl;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.LoginService;
import help.lixin.starlink.plugin.xxl.job.service.IXxlLoginService;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 11:19 上午
 * @Description
 */
public class XxlLoginServiceImpl extends InstanceService<LoginService> implements IXxlLoginService {
    @Override
    public void login(String instanceName, String userName, String password) {
        LoginService loginService = getApi(instanceName);
        loginService.login(userName,password);
    }

    public XxlLoginServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }
}
