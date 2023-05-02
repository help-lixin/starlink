package help.lixin.core.simple.engine.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.model.PipelineDeploy;
import help.lixin.core.engine.service.IPipelineDeployService;
import org.apache.tomcat.util.security.MD5Encoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.zip.CRC32;

public class PipelineDeployService implements IPipelineDeployService {

    // TODO lixin
    // 临时测试存储
    private static final ConcurrentMap<String, PipelineDeploy> DEPLOYS = new ConcurrentHashMap<>();

    @Override
    public PipelineDeploy deploy(PipelineDefinition pipeline) {
        PipelineDeploy pipelineDeploy = new PipelineDeploy();
        pipelineDeploy.setId(genId(pipeline));
        pipelineDeploy.setKey(pipeline.getKey());
        pipelineDeploy.setName(pipeline.getName());
        pipelineDeploy.setVersion(1);
        pipelineDeploy.setPipelineDefinition(pipeline);
        DEPLOYS.putIfAbsent(pipelineDeploy.getId(), pipelineDeploy);
        return pipelineDeploy;
    }


    protected String genId(PipelineDefinition pipeline) {
        try {
            // body md5
            ObjectMapper mapper = new ObjectMapper();
            String pipelineString = mapper.writeValueAsString(pipeline);
            byte[] bytes = pipelineString.getBytes("UTF-8");
            CRC32 crc32 = new CRC32();
            crc32.update(bytes);
            long value = crc32.getValue();
            return String.valueOf(value);
        } catch (Exception e) {
            // random id
            return UUID.randomUUID().toString();
        }
    }


    @Override
    public PipelineDeploy get(String deployId) {
        return DEPLOYS.get(deployId);
    }
}
