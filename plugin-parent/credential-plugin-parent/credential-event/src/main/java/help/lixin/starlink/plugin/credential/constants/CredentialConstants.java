package help.lixin.starlink.plugin.credential.constants;

import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueCreateCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueUpdateCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialUpdateEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/8 下午4:35
 * @Description
 */
public class CredentialConstants {

    public static final String CREATE_TOKEN_EVENT_TOPIC =
        SysTokenCredentialCreateEvent.class.getName().replaceAll("\\.", "_");
    public static final String UPDATE_TOKEN_EVENT_TOPIC = SysTokenCredentialUpdateEvent.class.getName().replaceAll("\\.","_");
    public static final String DELETE_TOKEN_EVENT_TOPIC =
        SysTokenCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");

    public static final String CREATE_TLS_EVENT_TOPIC =
        SysTLSCredentialCreateEvent.class.getName().replaceAll("\\.", "_");
    public static final String UPDATE_TLS_EVENT_TOPIC =
        SysTLSCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");
    public static final String DELETE_TLS_EVENT_TOPIC =
        SysTLSCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");

    public static final String CREATE_OPAQUE_EVENT_TOPIC = SysOpaqueCreateCredentialEvent.class.getName().replaceAll("\\.","_");
    public static final String UPDATE_OPAQUE_EVENT_TOPIC = SysOpaqueUpdateCredentialEvent.class.getName().replaceAll("\\.","_");
    public static final String DELETE_OPAQUE_EVENT_TOPIC =
        SysOpaqueUpdateCredentialEvent.class.getName().replaceAll("\\.", "_");

    public static final String CREATE_SSH_EVENT_TOPIC = SysSshCredentialCreateEvent.class.getName().replaceAll("\\.","_");
    public static final String UPDATE_SSH_EVENT_TOPIC = SysSshCredentialUpdateEvent.class.getName().replaceAll("\\.","_");
    public static final String DELETE_SSH_EVENT_TOPIC =
        SysSshCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");

    public static final String CREATE_SECRET_EVENT_TOPIC = SysSecretCredentialCreateEvent.class.getName().replaceAll("\\.","_");
    public static final String UPDATE_SECRET_EVENT_TOPIC = SysSecretCredentialUpdateEvent.class.getName().replaceAll("\\.","_");
    public static final String DELETE_SECRET_EVENT_TOPIC =
        SysSecretCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");

    public static final String CREATE_PWD_EVENT_TOPIC = SysUserNamePasswordCredentialCreateEvent.class.getName().replaceAll("\\.","_");
    public static final String UPDATE_PWD_EVENT_TOPIC = SysUserNamePasswordCredentialUpdateEvent.class.getName().replaceAll("\\.","_");
    public static final String DELETE_PWD_EVENT_TOPIC =
        SysUserNamePasswordCredentialUpdateEvent.class.getName().replaceAll("\\.", "_");

    public static final String TOKEN = "TOKEN";
    public static final String USERNAME_PASSWORD = "USERNAME_PASSWORD";
    public static final String TEXT = "TEXT";
    public static final String SECRET = "SECRET";
    public static final String SSH = "SSH";
    public static final String TLS = "TLS";
    public static final String OPAQUE = "OPAQUE";

    public static final String ANNOTATION_FLAB = "starlink.lixin.help";



}
