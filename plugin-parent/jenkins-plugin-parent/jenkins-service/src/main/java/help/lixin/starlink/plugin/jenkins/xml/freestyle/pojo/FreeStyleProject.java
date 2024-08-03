package help.lixin.starlink.plugin.jenkins.xml.freestyle.pojo;

import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.AbstractBuildWrapper;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.JenkinsProperties;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.AbstractScm;
import help.lixin.starlink.plugin.jenkins.xml.pojo.setup.AbstractSetup;
import help.lixin.starlink.plugin.jenkins.xml.pojo.trigger.AbstractTrigger;

import java.util.ArrayList;
import java.util.List;

public class FreeStyleProject {
    private JenkinsProperties properties;
    private AbstractScm scm;
    private String description;
    private Boolean keepDependencies = Boolean.FALSE;
    private Boolean canRoam = Boolean.FALSE;
    private Boolean disabled = Boolean.FALSE;
    private Boolean blockBuildWhenDownstreamBuilding = Boolean.FALSE;
    private Boolean blockBuildWhenUpstreamBuilding = Boolean.FALSE;
    private String jdk;
    private Boolean concurrentBuild = Boolean.FALSE;

    private List<AbstractTrigger> triggers = new ArrayList<>(0);
    private List<AbstractSetup> builders = new ArrayList<>(0);
    private List<AbstractBuildWrapper> buildWrappers = new ArrayList<>(0);


    public void addTrigger(AbstractTrigger trigger) {
        if (null != trigger)
            this.triggers.add(trigger);
    }

    public void addSetup(AbstractSetup setup) {
        if (null != setup)
            this.builders.add(setup);
    }

    public void addBuildWrapper(AbstractBuildWrapper buildWrapper) {
        if (null != buildWrapper)
            this.buildWrappers.add(buildWrapper);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getKeepDependencies() {
        return keepDependencies;
    }

    public void setKeepDependencies(Boolean keepDependencies) {
        this.keepDependencies = keepDependencies;
    }

    public Boolean getCanRoam() {
        return canRoam;
    }

    public void setCanRoam(Boolean canRoam) {
        this.canRoam = canRoam;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getBlockBuildWhenDownstreamBuilding() {
        return blockBuildWhenDownstreamBuilding;
    }

    public void setBlockBuildWhenDownstreamBuilding(Boolean blockBuildWhenDownstreamBuilding) {
        this.blockBuildWhenDownstreamBuilding = blockBuildWhenDownstreamBuilding;
    }

    public Boolean getBlockBuildWhenUpstreamBuilding() {
        return blockBuildWhenUpstreamBuilding;
    }

    public void setBlockBuildWhenUpstreamBuilding(Boolean blockBuildWhenUpstreamBuilding) {
        this.blockBuildWhenUpstreamBuilding = blockBuildWhenUpstreamBuilding;
    }

    public String getJdk() {
        return jdk;
    }

    public void setJdk(String jdk) {
        this.jdk = jdk;
    }

    public Boolean getConcurrentBuild() {
        return concurrentBuild;
    }

    public void setConcurrentBuild(Boolean concurrentBuild) {
        this.concurrentBuild = concurrentBuild;
    }

    public List<AbstractSetup> getBuilders() {
        return builders;
    }

    public void setBuilders(List<AbstractSetup> builders) {
        this.builders = builders;
    }

    public List<AbstractBuildWrapper> getBuildWrappers() {
        return buildWrappers;
    }

    public void setBuildWrappers(List<AbstractBuildWrapper> buildWrappers) {
        this.buildWrappers = buildWrappers;
    }

    public JenkinsProperties getProperties() {
        return properties;
    }

    public void setProperties(JenkinsProperties properties) {
        this.properties = properties;
    }

    public AbstractScm getScm() {
        return scm;
    }

    public void setScm(AbstractScm scm) {
        this.scm = scm;
    }

    public List<AbstractTrigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<AbstractTrigger> triggers) {
        this.triggers = triggers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version='1.1' encoding='UTF-8'?>").append("\n");
        builder.append("<project>").append("\n");

        // TODO lixin <actions/>

        descriptionToXml(builder);
        keepDependenciesToXml(builder);
        propertiesToXml(builder);

        scmToXml(builder);

        canRoamToXml(builder);
        disabledToXml(builder);
        blockBuildWhenDownstreamBuildingToXml(builder);
        blockBuildWhenUpstreamBuildingToXml(builder);
        jdkToXml(builder);

        triggersToXml(builder);

        buildersToXml(builder);
        buildWrappersToXml(builder);


        builder.append("</project>");
        return builder.toString();
    }

    protected void descriptionToXml(StringBuilder builder) {
        if (null != description) {
            // <description></description>
            builder.append("<description>") //
                    .append(description) //
                    .append("</description>").append("\n");
        }
    }

    protected void keepDependenciesToXml(StringBuilder builder) {
        if (null != keepDependencies) {
            // <keepDependencies>false</keepDependencies>
            builder.append("<keepDependencies>")//
                    .append(keepDependencies) //
                    .append("</keepDependencies>").append("\n");
        }
    }

    protected void propertiesToXml(StringBuilder builder) {
        if (null != properties) {
            builder.append(properties);
        }
    }

    protected void scmToXml(StringBuilder builder) {
        if (null != scm) {
            builder.append(scm);
        }
    }

    protected void canRoamToXml(StringBuilder builder) {
        if (null != canRoam) {
            // <canRoam>true</canRoam>
            builder.append("<canRoam>")//
                    .append(canRoam) //
                    .append("</canRoam>").append("\n");
        }
    }


    //
    protected void disabledToXml(StringBuilder builder) {
        if (null != disabled) {
            // <disabled>false</disabled>
            builder.append("<disabled>")//
                    .append(disabled) //
                    .append("</disabled>").append("\n");
        }
    }

    protected void blockBuildWhenDownstreamBuildingToXml(StringBuilder builder) {
        if (null != blockBuildWhenDownstreamBuilding) {
            // <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
            builder.append("<blockBuildWhenDownstreamBuilding>")//
                    .append(blockBuildWhenDownstreamBuilding) //
                    .append("</blockBuildWhenDownstreamBuilding>").append("\n");
        }
    }

    protected void blockBuildWhenUpstreamBuildingToXml(StringBuilder builder) {
        if (null != blockBuildWhenUpstreamBuilding) {
            // <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
            builder.append("<blockBuildWhenUpstreamBuilding>")//
                    .append(blockBuildWhenUpstreamBuilding) //
                    .append("</blockBuildWhenUpstreamBuilding>").append("\n");
        }
    }

    protected void jdkToXml(StringBuilder builder) {
        if (null != jdk) {
            // <jdk>jdk.1.8</jdk>
            builder.append("<jdk>")//
                    .append(jdk) //
                    .append("</jdk>").append("\n");
        }
    }


    protected void concurrentBuildToXml(StringBuilder builder) {
        if (null != concurrentBuild) {
            // <concurrentBuild>false</concurrentBuild>
            builder.append("<concurrentBuild>")//
                    .append(concurrentBuild) //
                    .append("</concurrentBuild>").append("\n");
        }
    }

    protected void triggersToXml(StringBuilder builder) {
        if (null != getTriggers() && !getTriggers().isEmpty()) {
            builder.append("<triggers>").append("\n");
            getTriggers() //
                    .stream() //
                    .filter(item -> null != item)//
                    .forEach(item -> {
                        builder.append(item);
                    });
            builder.append("</triggers>").append("\n");
        } else {
            builder.append("<triggers/>").append("\n");
        }
    }

    protected void buildersToXml(StringBuilder builder) {
        // builders
        if (null != getBuilders() || !getBuilders().isEmpty()) {
            builder.append("<builders>").append("\n");
            getBuilders() //
                    .stream() //
                    .filter(item -> null != item) //
                    .forEach(item -> {
                        builder.append(item);
                    });
            builder.append("</builders>").append("\n");
        } else {
            builder.append("<builders/>").append("\n");
        }
    }

    protected void buildWrappersToXml(StringBuilder builder) {
        // buildWrappers
        if (null != getBuildWrappers() && !getBuildWrappers().isEmpty()) {
            builder.append("<buildWrappers>").append("\n");
            getBuildWrappers() //
                    .stream() //
                    .filter(item -> null != item) //
                    .forEach(item -> {
                        builder.append(item);
                    });
            builder.append("</buildWrappers>").append("\n");
        } else {
            builder.append("<buildWrappers/>").append("\n");
        }
    }

}
