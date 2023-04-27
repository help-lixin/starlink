package help.lixin.jib.action.entity;

import java.util.*;

/**
 * 可参考: https://www.cnblogs.com/nm666/p/15325164.html
 */
public class JibActionParams {
    // FROM centos:latest
    private FromImage from = new FromImage();
    // hub.lixin.help/library/centos:latest
    private TargetImage to = new TargetImage();
    // ENV APPLICATION_HOME      /opt/application
    private String workDir;
    // root:root
    private String user;
    private Map<String, String> envs = new HashMap<>(0);
    // COPY ${APP_FILE}  ${APPLICATION}
    private List<Copy> copys = new ArrayList<>(0);
    // ENTRYPOINT java ${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${APPLICATION_DUMP_DIR} -Dfile.encoding=UTF-8 -Duser.timezone=GMT+8 -jar ${APPLICATION}
    private List<String> entrypoints = new ArrayList<>(0);
    //
    private Map<String, String> labels = new HashMap<>(0);
    // EXPOSE 6379
    private Set<Port> exposedPorts = new HashSet<>(0);
    // VOLUME /share/data
    private List<String> volumes = new ArrayList<>(0);
    // args
    List<String> args = new ArrayList<>(0);

    public FromImage getFrom() {
        return from;
    }

    public void setFrom(FromImage from) {
        this.from = from;
    }

    public TargetImage getTo() {
        return to;
    }

    public void setTo(TargetImage to) {
        this.to = to;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    public Map<String, String> getEnvs() {
        return envs;
    }

    public void setEnvs(Map<String, String> envs) {
        this.envs = envs;
    }

    public List<Copy> getCopys() {
        return copys;
    }

    public void setCopys(List<Copy> copys) {
        this.copys = copys;
    }

    public List<String> getEntrypoints() {
        return entrypoints;
    }

    public void setEntrypoints(List<String> entrypoints) {
        this.entrypoints = entrypoints;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<Port> getExposedPorts() {
        return exposedPorts;
    }

    public void setExposedPorts(Set<Port> exposedPorts) {
        this.exposedPorts = exposedPorts;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}