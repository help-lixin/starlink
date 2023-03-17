package help.lixin.admin.service.impl;

import help.lixin.admin.service.IPipelineParseStrategy;
import help.lixin.core.definition.PipelineDefinition;

import java.util.HashMap;
import java.util.Map;

public class PipelineParseFaceService {

    private Map<String, IPipelineParseStrategy> parses = new HashMap<>();

    public PipelineParseFaceService(Map<String, IPipelineParseStrategy> parses) {
        if (null != parses) {
            this.parses = parses;
        }
    }


    public PipelineDefinition parse(String body) throws Exception {
        String supportType = PipelineParseForYamlStrategy.SUPPORT_TYPE;
        if (body.startsWith("{")) {
            supportType = PipelineParseForJsonStrategy.SUPPORT_TYPE;
        }
        IPipelineParseStrategy pipelineParseStrategy = parses.get(supportType);
        if (null == pipelineParseStrategy) {
            throw new NullPointerException("not found support type");
        }
        return pipelineParseStrategy.parse(body);
    }
}
