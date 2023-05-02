package help.lixin.svn.action;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.impl.StagePipelineContext;
import org.junit.Assert;
import org.junit.Test;

public class SvnCheckoutActionTest {

    @Test
    public void testAction() throws Exception {
        StagePipelineContext ctx = new StagePipelineContext();
        ctx.setStageParams("{ \"url\" : \"svn://192.168.8.16/spring_web_demo\" , \"userName\" : \"admin\" , \"password\" : \"88888888\"  }");
//        Action action = new SvnCheckoutAction();
//        boolean execute = action.execute(ctx);
//        Assert.assertEquals(true, execute);
    }
}
