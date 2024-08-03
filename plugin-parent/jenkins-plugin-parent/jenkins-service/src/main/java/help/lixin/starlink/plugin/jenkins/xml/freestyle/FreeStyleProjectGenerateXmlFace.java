package help.lixin.starlink.plugin.jenkins.xml.freestyle;

import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.pojo.FreeStyleProject;
import help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers.AbstractBuildWrapper;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.JenkinsProperties;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.ParametersDefinitionProperty;
import help.lixin.starlink.plugin.jenkins.xml.pojo.parameter.StringParameterDefinition;
import help.lixin.starlink.plugin.jenkins.xml.pojo.scm.AbstractScm;
import help.lixin.starlink.plugin.jenkins.xml.pojo.setup.AbstractSetup;
import help.lixin.starlink.plugin.jenkins.xml.pojo.trigger.AbstractTrigger;
import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * FreeStyleProject 生成xml门面模式
 */
public class FreeStyleProjectGenerateXmlFace {

    private IBuildDependencyDelegate buildDependencyDelegate;
    private IBuildWrapperDelegate buildWrapperDelegate;
    private IJavaDelegate javaDelegate;
    private IParamsDelegate paramsDelegate;
    private IScmDelegate scmDelegate;
    private ISetupDelegate setupDelegate;

    public FreeStyleProjectGenerateXmlFace(IBuildDependencyDelegate buildDependencyDelegate, //
                                           IBuildWrapperDelegate buildWrapperDelegate, //
                                           IJavaDelegate javaDelegate, //
                                           IParamsDelegate paramsDelegate, //
                                           IScmDelegate scmDelegate, //
                                           ISetupDelegate setupDelegate) {
        this.buildDependencyDelegate = buildDependencyDelegate;
        this.buildWrapperDelegate = buildWrapperDelegate;
        this.javaDelegate = javaDelegate;
        this.paramsDelegate = paramsDelegate;
        this.scmDelegate = scmDelegate;
        this.setupDelegate = setupDelegate;
    }

    public String generateXml(JenkinsJobFormDTO form) {
        final FreeStyleProject project = new FreeStyleProject();
        // params
        if (null != form.getParams() && //
                form.getParams().size() > 0) {
            ParametersDefinitionProperty parametersDefinitionProperty = new ParametersDefinitionProperty();
            List<StringParameterDefinition> parameterDefinitions = paramsDelegate.apply(form.getParams());
            parametersDefinitionProperty.getParameterDefinitions().addAll(parameterDefinitions);
            JenkinsProperties properties = new JenkinsProperties();
            properties.setParametersDefinitionProperty(parametersDefinitionProperty);
            project.setProperties(properties);
        }

        // scm
        if (null != form.getScm()) {
            AbstractScm scm = scmDelegate.apply(form.getScmType(), form.getInstanceCode(), form.getScm());
            project.setScm(scm);
        }

        // jdk
        String jdkId = form.getJdkId();
        if (null != jdkId) {
            String jdkName = javaDelegate.apply(form.getInstanceCode(), jdkId);
            project.setJdk(jdkName);
        }

        // <triggers>
        List<String> buildDependencys = form.getBuildDependencys();
        if (null != buildDependencys && buildDependencys.size() > 0) {
            List<AbstractTrigger> triggers = buildDependencyDelegate.apply(form.getInstanceCode(), buildDependencys);
            project.getTriggers().addAll(triggers);
        }


        List<JenkinsPipelineSetupCommonDTO> setupList = form.getSetups();

        // 创建一个 Comparator 对象
        Comparator<JenkinsPipelineSetupCommonDTO> comparator = Comparator.comparingInt(JenkinsPipelineSetupCommonDTO::getSequence);
        // 使用 sort 方法对 List 中的对象进行排序
        Collections.sort(setupList, comparator);

        if (null != setupList && setupList.size() > 0) {
            List<AbstractSetup> setups = setupDelegate.apply(form.getInstanceCode(), setupList);
            project.getBuilders().addAll(setups);
            List<AbstractBuildWrapper> buildWrappers = buildWrapperDelegate.apply(form.getInstanceCode(), setupList);
            project.getBuildWrappers().addAll(buildWrappers);
        }

        return project.toString();
    }
}
