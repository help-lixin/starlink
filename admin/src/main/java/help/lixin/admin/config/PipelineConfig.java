package help.lixin.admin.config;

import help.lixin.admin.service.IPipelineParseStrategy;
import help.lixin.admin.service.impl.PipelineParseFaceService;
import help.lixin.admin.service.impl.PipelineParseForJsonStrategy;
import help.lixin.admin.service.impl.PipelineParseForYamlStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PipelineConfig {

    @Bean
    public IPipelineParseStrategy pipelineParseForJsonStrategy() {
        return new PipelineParseForJsonStrategy();
    }

    @Bean
    public IPipelineParseStrategy pipelineParseForYamlStrategy() {
        return new PipelineParseForYamlStrategy();
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineParseFaceService pipelineParseFaceService(List<IPipelineParseStrategy> parseList) {
        Map<String, IPipelineParseStrategy> parses = new HashMap<>();
        for (IPipelineParseStrategy parseService : parseList) {
            parses.put(parseService.supportType(), parseService);
        }
        PipelineParseFaceService pipelineParseFaceService = new PipelineParseFaceService(parses);
        return pipelineParseFaceService;
    }
}
