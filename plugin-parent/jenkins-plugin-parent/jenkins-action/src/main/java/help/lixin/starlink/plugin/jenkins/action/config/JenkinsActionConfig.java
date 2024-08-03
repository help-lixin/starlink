package help.lixin.starlink.plugin.jenkins.action.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.action.JenkinsAction;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsJobService;

@Configuration
public class JenkinsActionConfig {

    // 源代码目录
    @Value("${source.directory:/mnt/data/jenkins/workspace}")
    private String sourceDirectory;

    @Bean
    @ConditionalOnMissingBean(name = "jenkinsAction")
    public Action jenkinsAction(@Autowired @Qualifier("jenkinsServiceFactory") //
    AbstractServiceFactory jenkinsServiceFactory, //
        IJenkinsJobService jenkinsJobService, //
        IExpressionService expressionService) { //
        if (null != sourceDirectory && !sourceDirectory.endsWith("/")) {
            sourceDirectory = sourceDirectory + "/";
        }
        return new JenkinsAction(sourceDirectory, jenkinsServiceFactory, jenkinsJobService, expressionService);
    }
}
