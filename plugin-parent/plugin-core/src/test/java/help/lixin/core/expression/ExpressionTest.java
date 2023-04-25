package help.lixin.core.expression;


import help.lixin.core.constants.Constant;
import org.junit.Test;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {

    @Test
    public void testExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'hello'.concat('world')");
        String value = exp.getValue(String.class);
        System.out.println(value);
    }

    @Test
    public void testExpressionUser() {
        User user = new User();
        user.setName("张三");
        user.setAge(25);

        ExpressionParser parser = new SpelExpressionParser();
        // 改变解析上下文中的内容
        ParserContext parserContext = new TemplateParserContext("${", "}");
        StandardEvaluationContext context = new StandardEvaluationContext(user);
        String name = (String) parser.parseExpression("${name}", parserContext).getValue(context);

//        Expression expression = parser.parseExpression("${name}-${age}", parserContext);
//        String name = expression.getValue(user, String.class);
        System.out.println(name);
    }


    @Test
    public void testExpressionMap() {
        Map<String, Object> vars = new HashMap<>();
        vars.put(Constant.Artifact.ARTIFACT_NAME, "spring-web.demo.jar");


        ExpressionParser parser = new SpelExpressionParser();
        // 改变解析上下文中的内容
        ParserContext parserContext = new TemplateParserContext("${", "}");
        StandardEvaluationContext evaluationCtx = new StandardEvaluationContext(vars);
        evaluationCtx.setPropertyAccessors(Collections.singletonList(new MapAccessor()));

        Expression expression = parser.parseExpression("${ARTIFACT_NAME}", parserContext);
        String name = expression.getValue(evaluationCtx, String.class);
        System.out.println(name);
    }

}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
