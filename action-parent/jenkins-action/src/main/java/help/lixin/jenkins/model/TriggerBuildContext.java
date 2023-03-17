package help.lixin.jenkins.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 触发构建上下文
 */
public class TriggerBuildContext {
    private String optionalFolderPath;
    private String jobName;
    private Map<String, List<String>> properties = new HashMap<>();


    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private TriggerBuildContext ctx = new TriggerBuildContext();


        public Builder optionalFolderPath(String optionalFolderPath) {
            ctx.setOptionalFolderPath(optionalFolderPath);
            return this;
        }

        public Builder jobName(String jobName) {
            ctx.setJobName(jobName);
            return this;
        }

        public Builder properties(Map<String, List<String>> properties) {
            if (null != properties) {
                ctx.getProperties().putAll(properties);
            }
            return this;
        }

        public Builder properties(String name, String value) {
            if (null != name && null != value) {
                ctx.getProperties().put(name, Arrays.asList(value));
            }
            return this;
        }

        public TriggerBuildContext build() {
            return ctx;
        }
    }


    public String getOptionalFolderPath() {
        return optionalFolderPath;
    }

    public void setOptionalFolderPath(String optionalFolderPath) {
        this.optionalFolderPath = optionalFolderPath;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Map<String, List<String>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, List<String>> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "TriggerBuildContext{" +
                "optionalFolderPath='" + optionalFolderPath + '\'' +
                ", jobName='" + jobName + '\'' +
                ", properties=" + properties +
                '}';
    }
}
