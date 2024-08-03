package help.lixin.starlink.plugin.jsch.service.impl;

import java.util.List;

import help.lixin.starlink.plugin.jsch.service.IGenerateSshHostsService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.properties.Mode;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/2 下午2:12
 * @Description
 */
public class GenerateSshHostsService implements IGenerateSshHostsService {

    private AbstractServiceFactory jschServiceFactory;

    @Override
    public String generateHosts(String labelKey, List<String> instanceCodes) {
        StringBuilder res = new StringBuilder();
        res.append(String.format("\t[%s]\n", labelKey));

        instanceCodes.forEach(instanceCode -> {
            JschProperties properties = jschServiceFactory.getInstance(instanceCode, JschProperties.class);
            if (properties.getMode().name().equals(Mode.PASSWORD.name())) {
                // 写入用户名密码
                String data = String.format("\t%s\t%s\t%s\n", //
                    properties.getHost(), //
                    properties.getUsername(), //
                    properties.getPassword());
                res.append(data);
            } else {
                // 写入免密内容
                String data = String.format("\t%s\n", //
                    properties.getHost());
                res.append(data);
            }
        });
        return res.toString();
    }

    public GenerateSshHostsService(AbstractServiceFactory jschServiceFactory) {
        this.jschServiceFactory = jschServiceFactory;
    }
}
