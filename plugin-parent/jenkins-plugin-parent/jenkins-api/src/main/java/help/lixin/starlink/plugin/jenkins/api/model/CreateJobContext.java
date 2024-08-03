package help.lixin.starlink.plugin.jenkins.api.model;

public class CreateJobContext {
    private String optionalFolderPath;
    private String jobName;
    private String configXML;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private CreateJobContext ctx = new CreateJobContext();

        public Builder optionalFolderPath(String optionalFolderPath) {
            ctx.setOptionalFolderPath(optionalFolderPath);
            return this;
        }

        public Builder jobName(String jobName) {
            ctx.setJobName(jobName);
            return this;
        }

        public Builder configXML(String configXML) {
            ctx.setConfigXML(configXML);
            return this;
        }

        public CreateJobContext build() {
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

    public String getConfigXML() {
        return configXML;
    }

    public void setConfigXML(String configXML) {
        this.configXML = configXML;
    }
}
