package help.lixin.oci;

import com.google.cloud.tools.jib.api.*;
import com.google.cloud.tools.jib.api.buildplan.AbsoluteUnixPath;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JibTest {
    public static void main(String[] args) throws Exception {
        String toImage = "hub.lixin.help/spring-web-demo/spring-web-demo:v2";

        String app = "spring-web-demo-1.1.0.jar";
        String appHome = "/opt/application";
        String binDir = String.format("%s/%s", appHome, "bin");
        String confDir = String.format("%s/%s", appHome, "conf");
        String logDir = String.format("%s/%s", appHome, "logs");
        String dumpDir = String.format("%s/%s", appHome, "dumpfiles");
        String appPath = String.format("%s/%s", binDir, app);

        Map<String, String> envs = new HashMap<>();
        envs.put("APPLICATION_HOME", appHome);
        envs.put("APPLICATION_BIN_DIR", binDir);
        envs.put("APPLICATION_CONF_DIR", confDir);
        envs.put("APPLICATION_LOG_DIR", logDir);
        envs.put("APPLICATION_DUMP_DIR", dumpDir);
        envs.put("APPLICATION", appPath);

        JibContainer containerize = Jib.from("openjdk:8u302-jdk-oracle")
                .setEnvironment(envs)
                .addLayer(
                        Collections.singletonList(Paths.get("/Users/lixin/Workspace/spring-web-demo/target/spring-web-demo-1.1.0.jar")),
                        AbsoluteUnixPath.get("/opt/application/bin"))
                .setEntrypoint( // 注意:不允许有空格来着的
                        "java", //
                        "-Xms1024m", //
                        "-Xmx1024m", //
                        "-XX:+HeapDumpOnOutOfMemoryError", //
                        "-XX:HeapDumpPath=" + envs.getOrDefault("APPLICATION_DUMP_DIR", "/tmp"), //
                        "-Dfile.encoding=UTF-8", //
                        "-Duser.timezone=GMT+8", //
                        "-jar", //
                        envs.get("APPLICATION"))
                .containerize(
                        Containerizer.to(TarImage.at(Paths.get("spring-web-demo-1.1.0.tar.gz"))
                                        .named("spring-web-demo"))
                                .setAllowInsecureRegistries(true)
                );
        System.out.println(containerize.getTargetImage());
    }
}