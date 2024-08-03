package help.lixin.service.impl;

import help.lixin.service.IContextCustomizer;
import help.lixin.service.IExpressionService;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.env.Environment;
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
    private static final String EXPRESSION_PREFIX = "#{";

    private static final String EXPRESSION_SUFFIX = "}";

    private Environment environment;

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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String prase(String expressionString, Map<String, Object> context) {
        if (null == expressionString) {
            return null;
        }
        // 拷贝一份上下文出来
        Map<String, Object> tempContext = new HashMap<>(context);
        // 允许你扩展自定义的属性(比如:把spring里的变量全部追加进来)
        if (null != contextCustomizer) {
            contextCustomizer.customizer(tempContext);
        }

        // 1. 先从参数里去读信息
        StandardEvaluationContext evaluationCtx = new StandardEvaluationContext(context);
        evaluationCtx.setPropertyAccessors(Collections.singletonList(new MapAccessor()));
        Expression expression = parser.parseExpression(expressionString, parserContext);
        String result = null;
        try {
            result = expression.getValue(evaluationCtx, String.class);
        } catch (Exception ignore) {
            result = expressionString;
        }

        // 2. 仍然是表达式的情况下,再从系统环境里读信息
        if (result.startsWith(EXPRESSION_PREFIX)) {
            result = environment.resolvePlaceholders(expressionString);
        }
        return result;
    }
}
