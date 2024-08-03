package help.lixin.starlink.plugin.jenkins.xml.pojo.parameter;

import java.io.Serializable;

public class StringParameterDefinition implements Serializable {
    private String name;
    private String description;
    private String defaultValue;
    private Boolean trim = Boolean.TRUE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(Boolean trim) {
        this.trim = trim;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<hudson.model.StringParameterDefinition>").append("\n");
        builder.append("<name>").append(name).append("</name>").append("\n");
        builder.append("<description>").append(description).append("</description>").append("\n");
        builder.append("<defaultValue>").append(defaultValue).append("</defaultValue>").append("\n");
        builder.append("<trim>").append(trim).append("</trim>").append("\n");
        builder.append("</hudson.model.StringParameterDefinition>").append("\n");
        return builder.toString();
    }
}
