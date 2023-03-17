package help.lixin.shell.service.impl;

import help.lixin.shell.service.IContextCustomizer;
import help.lixin.shell.service.IExpressionService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BeetlExpressionService implements IExpressionService {

    private StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();

    public IContextCustomizer contextCustomizer;

    public void setContextCustomizer(IContextCustomizer contextCustomizer) {
        this.contextCustomizer = contextCustomizer;
    }

    public IContextCustomizer getContextCustomizer() {
        return contextCustomizer;
    }

    @Override
    public String prase(String template, Map<String, Object> context) {
        // 拷贝一份上下文出来
        Map<String, Object> tempContext = new HashMap<>(context);
        try {
            // 允许你扩展自定义的属性(比如:把spring里的变量全部追加进来)
            if (null != contextCustomizer) {
                contextCustomizer.customizer(tempContext);
            }

            GroupTemplate gt = new GroupTemplate(resourceLoader, Configuration.defaultConfiguration());
            Template t = gt.getTemplate(template);
            t.binding(tempContext);
            String newString = t.render();
            return newString;
        } catch (IOException e) {
            return template;
        }
    }
}
