<?xml version='1.1' encoding='UTF-8'?>
<flow-definition plugin="workflow-job@1268.v6eb_e2ee1a_85a">
    <actions>
        <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction plugin="pipeline-model-definition@2.2118.v31fd5b_9944b_5"/>
        <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction plugin="pipeline-model-definition@2.2118.v31fd5b_9944b_5">
            <jobProperties/>
            <triggers/>
            <parameters>
                <string>branch</string>
                <string>url</string>
            </parameters>
            <options/>
        </org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction>
    </actions>
    <description></description>
    <keepDependencies>false</keepDependencies>
    <properties>
        <#if CODE_REPOSITORY == "gitlab" >
            <com.dabsquared.gitlabjenkins.connection.GitLabConnectionProperty plugin="gitlab-plugin@1.7.6">
                <gitLabConnection>${_jenkins.credentialId}</gitLabConnection>
                <jobCredentialId></jobCredentialId>
                <useAlternativeCredential>false</useAlternativeCredential>
            </com.dabsquared.gitlabjenkins.connection.GitLabConnectionProperty>
        </#if>

        <hudson.model.ParametersDefinitionProperty>
            <parameterDefinitions>
                <hudson.model.StringParameterDefinition>
                    <name>branch</name>
                    <description>分支名称</description>
                    <defaultValue>main</defaultValue>
                    <trim>false</trim>
                </hudson.model.StringParameterDefinition>
                <hudson.model.StringParameterDefinition>
                    <name>url</name>
                    <description>url</description>
                    <defaultValue>ssh://git@103.215.125.86/order-group/spring-web-demo.git</defaultValue>
                    <trim>false</trim>
                </hudson.model.StringParameterDefinition>
            </parameterDefinitions>
        </hudson.model.ParametersDefinitionProperty>
    </properties>
    <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps@3618.v13db_a_21f0fcf">
        <script>
         pipeline {
                agent any

                parameters {
                    string(name: "branch", defaultValue: "main", description: "分支名称")
                    string(name: "url", defaultValue: "ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git", description: "url")
                }

                tools {
                    jdk   "jdk8"
                    maven "maven"
                }

                stages {
                    <#if CODE_REPOSITORY == "gitlab" >
                        stage("Gitlab") {
                            steps {
                                git branch: "${r'${params.branch}'}" , url: "${r'${params.url}'}"
                            }
                         // end Git stage
                        }
                    </#if>

                    <#list _jenkins.stages as stageVar >
                        stage("${stageVar.name}") {
                            steps {
                                ${stageVar.steps}
                            }
                        // end stage
                        }
                    </#list>

                    stage("PrintEnv") {
                        steps {
                            sh "printenv"
                        }
                    // end PrintEnv
                    }
                // end stages
                }

                post {
                    success {
                        archiveArtifacts "${_jenkins.archiveArtifacts}";
                      // end success
                    }
                // end post
                }

          // end   pipeline
          }
        </script>
        <sandbox>true</sandbox>
    </definition>
    <triggers/>
    <disabled>false</disabled>
</flow-definition>