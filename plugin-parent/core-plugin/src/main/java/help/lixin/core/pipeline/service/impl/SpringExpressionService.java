package help.lixin.core.pipeline.service.impl;

import help.lixin.core.pipeline.service.IContextCustomizer;
import help.lixin.core.pipeline.service.IExpressionService;
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

public class SpringExpressionService implements IExpressionService {
    private static final String EXPRESSION_PREFIX = "${";

    private static final String EXPRESSION_SUFFIX = "}";


    private IContextCustomizer contextCustomizer;

    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParserContext parserContext = new TemplateParserContext(EXPRESSION_PREFIX, EXPRESSION_SUFFIX);

    @Override
    public void setContextCustomizer(IContextCustomizer contextCustomizer) {
        this.contextCustomizer = contextCustomizer;
    }

    @Override
    public IContextCustomizer getContextCustomizer() {
        return contextCustomizer;
    }

    @Override
    public String prase(String expressionString, Map<String, Object> context) {
        if (null == expressionString) {
            return null;
        }
        // 拷贝一份上下文出来
        Map<String, Object> tempContext = new HashMap<>(context);
        try {
            // 允许你扩展自定义的属性(比如:把spring里的变量全部追加进来)
            if (null != contextCustomizer) {
                contextCustomizer.customizer(tempContext);
            }

            StandardEvaluationContext evaluationCtx = new StandardEvaluationContext(context);
            evaluationCtx.setPropertyAccessors(Collections.singletonList(new MapAccessor()));

            Expression expression = parser.parseExpression(expressionString, parserContext);
            String value = expression.getValue(evaluationCtx, String.class);
            return value;
        } catch (Exception e) {
            return expressionString;
        }
    }
}
