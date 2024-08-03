package help.lixin.starlink.plugin.jsch.api.config;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;
import help.lixin.starlink.plugin.jsch.api.properties.Mode;
import help.lixin.starlink.plugin.jsch.api.service.ICommandExecuteTemplateService;
import help.lixin.starlink.plugin.jsch.api.service.ISecureCopyService;
import help.lixin.starlink.plugin.jsch.api.service.IShellExecuteTemplateService;
import help.lixin.starlink.plugin.jsch.api.service.impl.CommandExecuteTemplateService;
import help.lixin.starlink.plugin.jsch.api.service.impl.SecureCopyService;
import help.lixin.starlink.plugin.jsch.api.service.impl.ShellExecuteTemplateService;

public class JschConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    private Logger logger = LoggerFactory.getLogger(JschConfig.class);

    @Bean
    public JschProperties jschProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        JschProperties jschProperties = Binder.get(environment)//
            .bind(prefix, JschProperties.class)//
            .get();
        return jschProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public JSch jsch(JschProperties jschProperties) throws Exception {
        JSch jsch = new JSch();
        if (StringUtils.isNotBlank(jschProperties.getPrivateKey()) //
            && StringUtils.isNotBlank(jschProperties.getPublicKey())) {
            jschProperties.setMode(Mode.SECRET_KEY);

            String name = "_jsch_" + new Random().nextInt();
            byte[] passphrase = null;
            // 私钥密钥配置
            if (StringUtils.isNotBlank(jschProperties.getPrivateKeyPassphrase())) {
                passphrase = jschProperties.getPrivateKeyPassphrase().getBytes(StandardCharsets.ISO_8859_1);
            }

            jsch.addIdentity(name, //
                jschProperties.getPrivateKey().getBytes(StandardCharsets.ISO_8859_1), //
                jschProperties.getPublicKey().getBytes(StandardCharsets.ISO_8859_1), //
                passphrase);
        }
        return jsch;
    } // end

    @Bean
    @ConditionalOnMissingBean
    public Session session(JschProperties jschProperties, //
        JSch jsch) throws Exception {
        Session session = jsch.getSession(jschProperties.getUsername(), //
            jschProperties.getHost(), //
            jschProperties.getPort());
        // 第一次访问服务器时不用输入yes
        session.setConfig("StrictHostKeyChecking", "no");
        // 有密码的情况下,配置密码
        if (StringUtils.isNotBlank(jschProperties.getPassword())) {
            jschProperties.setMode(Mode.PASSWORD);
            session.setPassword(jschProperties.getPassword());
        }
        // 认证方式
        // publickey:基于公共密钥的安全验证方式（public key authentication method）,通过生成一组密钥（public key/private key）来实现用户的登录验证.
        // keyboard-interactive:基于键盘交互的验证方式（keyboard interactive authentication
        // method）,通过服务器向客户端发送提示信息,然后由客户端根据相应的信息通过手工输入的方式发还给服务器端.
        // password:基于口令的验证方式（password authentication method）,通过输入用户名和密码的方式进行远程机器的登录验证.
        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
        session.setTimeout(jschProperties.getTimeout());
        logger.info("准备SSH主机:[{}],连接", jschProperties.getHost());
        // TODO lixin
        // 免密钥登录时,偶尔会出现:如下情况:
        // 经过N轮测试,通过其它机器与该主机进行免密钥之后,产生了:/home/app/.ssh/authorized_keys,则可以正常连接.
        // Caused by: com.jcraft.jsch.JSchException: Auth fail
        // at com.jcraft.jsch.Session.connect(Session.java:519)
        // at com.jcraft.jsch.Session.connect(Session.java:183)
        // at help.lixin.jsch.api.config.JschConfig.session(JschConfig.java:90)
        // at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        // at java.base/java.lang.reflect.Method.invoke(Method.java:577)
        // at
        // org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154)
        // ... 44 more
        session.connect();
        logger.info("连接SSH主机:[{}],成功", jschProperties.getHost());
        return session;
    } // end

    @Bean
    @ConditionalOnMissingBean
    public ICommandExecuteTemplateService commandExecuteTemplateService(Session session) {
        return new CommandExecuteTemplateService(session);
    } // end

    @Bean
    @ConditionalOnMissingBean
    public IShellExecuteTemplateService shellExecuteTemplateService(Session session) {
        return new ShellExecuteTemplateService(session);
    }

    @Bean
    @ConditionalOnMissingBean
    public ISecureCopyService secureCopyService(JschProperties jschProperties, //
        Session session) {
        return new SecureCopyService(jschProperties, session);
    } // end
}
