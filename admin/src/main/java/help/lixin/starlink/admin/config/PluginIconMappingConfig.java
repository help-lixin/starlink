package help.lixin.admin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PluginIconMappingConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置访问路径与资源的映射关系
        // /icons/**  --> classpath:/
        registry.addResourceHandler("/icons/**").addResourceLocations("classpath:/");//
    }
}
