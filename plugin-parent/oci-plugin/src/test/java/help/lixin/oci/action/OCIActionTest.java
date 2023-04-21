package help.lixin.oci.action;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.config.ActionsConfig;
import help.lixin.core.pipeline.config.ExpressionConfig;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.oci.config.OCIActionConfig;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;
import java.io.StringWriter;

public class OCIActionTest {

    @Test
    public void testExecute() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ActionsConfig.class, ExpressionConfig.class, OCIActionConfig.class);

        ActionMediator actionMediator = applicationContext.getBean(ActionMediator.class);
        Action action = actionMediator.getAction("oci");

        InputStream resourceAsStream = OCIActionTest.class.getResourceAsStream("/oci.json");
        StringWriter writer = new StringWriter();
        IOUtils.copy(resourceAsStream, writer);
//        String json = writer.toString();
//        System.out.println(json);
        String json = "{   \"from\": {  \"image\": \"openjdk:8u302-jdk-oracle\"  },  \"to\": {  \"image\": \"hub.lixin.help/library/spring-web-boot\",  \"credential\": {  \"username\": \"admin\",  \"password\": \"Li@88888888\"  }  },  \"envs\": {  \"APPLICATION_HOME\": \"/opt/application\",  \"APP\": \"spring-web-demo-1.1.0.jar\",  \"APPLICATION_BIN_DIR\": \"${APPLICATION_HOME}/bin\",  \"APPLICATION_CONF_DIR\": \"${APPLICATION_HOME}/conf\",  \"APPLICATION_LOG_DIR\": \"${APPLICATION_HOME}/logs\",  \"APPLICATION_DUMP_DIR\": \"${APPLICATION_HOME}/dumpfiles\",  \"APPLICATION\": \"${APPLICATION_BIN_DIR}/spring-web-demo-1.1.0.jar\"  },  \"copys\": [  {  \"files\": [  \"/Users/lixin/Workspace/spring-web-demo/target/${APP}\"  ],  \"pathInContainer\": \"${APPLICATION_BIN_DIR}\"  }  ],  \"entrypoints\": [  \"java\",  \"-Xms1024m\",  \"-Xmx1024m\",  \"-XX: HeapDumpOnOutOfMemoryError\",  \"-XX:HeapDumpPath=${APPLICATION_DUMP_DIR}\",  \"-Dfile.encoding=UTF-8\",  \"-Duser.timezone=GMT 8\",  \"-jar\",  \"${APPLICATION}\"  ]  }";
        PipelineContext ctx = new StagePipelineContext();
        ctx.setStageParams(json);

        Boolean res = action.execute(ctx);
        Assert.assertEquals(true, res);
    }
}
