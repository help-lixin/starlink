package help.lixin.starlink.plugin.svn.api.config;

import help.lixin.starlink.plugin.svn.api.properties.SvnProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class SvnConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public SvnProperties svnProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        SvnProperties svnProperties = Binder.get(environment)//
                .bind(prefix, SvnProperties.class)//
                .get();
        return svnProperties;
    }
}
