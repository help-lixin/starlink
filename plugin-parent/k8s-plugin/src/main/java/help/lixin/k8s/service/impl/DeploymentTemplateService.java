package help.lixin.k8s.service.impl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Map;

public class DeploymentTemplateService {

    private Logger logger = LoggerFactory.getLogger(DeploymentTemplateService.class);

    private static final String K8S_DEPLOYMENT_TEMPLATE_NAME = "K8S_DEPLOYMENT_TEMPLATE_NAME";

    private Configuration configuration = null;

    private StringTemplateLoader templateLoader = null;

    public DeploymentTemplateService() {
        templateLoader = new StringTemplateLoader();

        configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(templateLoader);
    }

    public String process(String templateString, Map<String, Object> ctx) {
        String result = null;
        try {
            if (null == templateLoader.findTemplateSource(K8S_DEPLOYMENT_TEMPLATE_NAME)) {
                synchronized (templateLoader) {
                    if (null == templateLoader.findTemplateSource(K8S_DEPLOYMENT_TEMPLATE_NAME)) {
                        templateLoader.putTemplate(K8S_DEPLOYMENT_TEMPLATE_NAME, templateString);
                    }
                }
            }
            Template template = configuration.getTemplate(K8S_DEPLOYMENT_TEMPLATE_NAME);
            StringWriter stringWriter = new StringWriter();
            template.process(ctx, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            String msg = String.format("模板内容:[\n%s]\n渲染出现错误,异常信息如下:[\n%s\n]", templateString, e.getMessage());
            logger.error(msg);
        }
        return result;
    }
}
