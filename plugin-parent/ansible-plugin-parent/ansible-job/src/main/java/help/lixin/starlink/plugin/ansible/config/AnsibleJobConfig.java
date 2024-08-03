package help.lixin.starlink.plugin.ansible.config;

import help.lixin.starlink.plugin.ansible.job.UpdateAnsibleHostsJob;
import help.lixin.starlink.plugin.ansible.job.service.IUpdateAnsibleHostsService;
import help.lixin.starlink.plugin.ansible.job.service.impl.UpdateAnsibleHostsService;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:43
 * @Description
 */
@Configuration
public class AnsibleJobConfig {

    @Bean
    public UpdateAnsibleHostsJob ansibleCreateHostsEventJob(IUpdateAnsibleHostsService downloadKnownHostsService){
        return new UpdateAnsibleHostsJob(downloadKnownHostsService);
    }


    @Bean
    public IUpdateAnsibleHostsService downloadKnownHostsService(AnsibleHostManageMapper ansibleHostManageMapper,
                                                                DomainEventPublisher domainEventPublisher){
        return new UpdateAnsibleHostsService(ansibleHostManageMapper,domainEventPublisher);
    }

}
