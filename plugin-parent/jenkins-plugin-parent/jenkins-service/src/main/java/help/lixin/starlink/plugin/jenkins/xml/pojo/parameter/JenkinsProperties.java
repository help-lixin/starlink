package help.lixin.starlink.plugin.jenkins.xml.pojo.parameter;

import java.io.Serializable;

public class JenkinsProperties implements Serializable {
    private ParametersDefinitionProperty parametersDefinitionProperty;

    public ParametersDefinitionProperty getParametersDefinitionProperty() {
        return parametersDefinitionProperty;
    }

    public void setParametersDefinitionProperty(ParametersDefinitionProperty parametersDefinitionProperty) {
        this.parametersDefinitionProperty = parametersDefinitionProperty;
    }

    @Override
    public String toString() {
        String properties = "<properties/>.append(\"\\n\")";
        if (null == parametersDefinitionProperty) {
            return properties;
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("<properties>").append("\n")//
                    .append("<hudson.model.ParametersDefinitionProperty>").append("\n") //
                    .append(getParametersDefinitionProperty().toString()).append("\n")//
                    .append("</hudson.model.ParametersDefinitionProperty>").append("\n") //
                    .append("</properties>").append("\n");
            return builder.toString();
        }
    }
}
