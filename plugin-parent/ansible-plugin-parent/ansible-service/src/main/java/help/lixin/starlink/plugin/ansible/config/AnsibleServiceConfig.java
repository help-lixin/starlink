package help.lixin.starlink.plugin.ansible.config;

import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleInventoryMapper;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleLabelMapper;
import help.lixin.starlink.plugin.ansible.mq.provider.DeleteAnsibleHostsHandler;
import help.lixin.starlink.plugin.ansible.mq.provider.UpdateAnsibleHostsHandler;
import help.lixin.starlink.plugin.ansible.service.IAnsibleHostService;
import help.lixin.starlink.plugin.ansible.service.IAnsibleLabelService;
import help.lixin.starlink.plugin.ansible.service.impl.AnsibleHostService;
import help.lixin.starlink.plugin.ansible.service.impl.AnsibleLabelService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 下午12:09
 * @Description
 */
@Configuration
public class AnsibleServiceConfig {
    @Bean
    @ConditionalOnMissingBean(name = "ansibleLabelService")
    public IAnsibleLabelService ansibleLabelService(AnsibleLabelMapper ansibleLabelMapper,
                                                    AnsibleInventoryMapper ansibleInventoryMapper,
                                                    UpdateAnsibleHostsHandler updateAnsibleHostsHandler,
                                                    DeleteAnsibleHostsHandler deleteAnsibleHostsHandler,
                                                    QueryTemplate queryTemplate){
        return new AnsibleLabelService(
                ansibleLabelMapper,
                ansibleInventoryMapper,
                updateAnsibleHostsHandler,
                deleteAnsibleHostsHandler,
                queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ansibleHostService")
    public IAnsibleHostService ansibleHostService(AnsibleHostManageMapper ansibleHostManageMapper,
                                                  QueryTemplate queryTemplate){
        return new AnsibleHostService(
                ansibleHostManageMapper,
                queryTemplate);
    }

}
