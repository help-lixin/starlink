package help.lixin.starlink.plugin.jenkins.xml.pojo.parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametersDefinitionProperty implements Serializable {
    private List<StringParameterDefinition> parameterDefinitions = new ArrayList<>(0);

    public void add(StringParameterDefinition stringParameterDefinition) {
        if (null == parameterDefinitions) {
            synchronized (this) {
                if (null == parameterDefinitions) {
                    parameterDefinitions = new ArrayList<>(0);
                }
            }
        }
        this.parameterDefinitions.add(stringParameterDefinition);
    }

    public List<StringParameterDefinition> getParameterDefinitions() {
        return parameterDefinitions;
    }

    public void setParameterDefinitions(List<StringParameterDefinition> parameterDefinitions) {
        this.parameterDefinitions = parameterDefinitions;
    }

    @Override
    public String toString() {
        if (null == parameterDefinitions || parameterDefinitions.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("<parameterDefinitions>").append("\n"); //
            getParameterDefinitions().forEach(item -> {
                builder.append(item.toString()).append("\n"); //
            });//
            builder.append("</parameterDefinitions>").append("\n"); //
            return builder.toString();
        }
    }
}
