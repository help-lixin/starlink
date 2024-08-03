package help.lixin.starlink.core.pipeline.interceptor.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.pipeline.constants.Constant;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.interceptor.IActionExecuteInterceptor;
import help.lixin.transport.client.Client;
import help.lixin.transport.client.model.Message;

public class PushActiveNodeInterceptor implements IActionExecuteInterceptor {
    private static final String SUPER_ADMIN = "super-admin";
    private static final String SYSTEM = "starlink-service";
    private static final String MODULE = "pipeline";
    private static final String BUSSINESS_MODULE = "pipeline-active-node";

    private Client client;

    public PushActiveNodeInterceptor(Client client) {
        this.client = client;
    }

    @Override
    public void beforeExecute(PipelineContext ctx) throws Exception {
        String instnaceId = (String)ctx.getVar(Constant.Pipeline.PIPELINE_INSTANCE_ID);
        String taskId = (String)ctx.getVar(Constant.Pipeline.PIPELINE_NODE_TASK_ID);
        String nodeId = (String)ctx.getVar(Constant.Pipeline.PIPELINE_NODE_ID);
        // Object nodeName = ctx.getVar(Constant.Pipeline.PIPELINE_NODE_NAME);
        // Object actionName = ctx.getVar(Constant.Common.ACTION_NAME);
        // Object userId = ctx.getVar(Constant.Common.USER_ID);
        String userName = (String)ctx.getVar(Constant.Common.USER_NAME);
        // 业务主键ID
        String businessId = (String)ctx.getVar(Constant.Common.BUSINESS_ID);

        if (null != businessId && //
            null != userName && //
            null != instnaceId && //
            null != taskId && //
            null != nodeId) {

            Map<String, Object> body = new HashMap<>(0);
            body.put("businessId", businessId);
            body.put("instnaceId", instnaceId);
            body.put("taskId", taskId);
            body.put("nodeId", nodeId);

            try {
                ObjectMapper mapper = new ObjectMapper();
                String bodyJson = mapper.writeValueAsString(body);
                client.send(Message.newBuilder() //
                    .withTo(userName) //
                    .withSendTime(new Date()) //
                    .withFrom(SUPER_ADMIN) //
                    .withSystem(SYSTEM) //
                    .withSystemModule(MODULE) //
                    .withBusinessModule(BUSSINESS_MODULE) //
                    .withType(0) // 系统消息(底层用于通信的消息,不会对外进行展示)
                    .withBody(bodyJson) //
                    .build());
            } catch (JsonProcessingException ignore) {
            }
        }
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
