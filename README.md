### 1. 项目背景
研发或者运维,在看日志时要用:Kibana,持续集成要用:Jenkins,维护定时任务要用:xxl-job-admin,意味着要在不同的的组件之间进行登录切换,难道就没有一个统一的平台,进行统一管理吗?<br/>
正如其项目名称一样:所有的"组件",因,散落在不同的机器上,想通过该项目,进行统一的管理,而最终形成的结果就像"星链",网住所有的"组件"<br/> 

### 2. 罗列组件
1) 仓库     
   gitlab/git/svn

2) 持续集成     
   jenkins

3) 技术组件          
   nacos/apollo/xxl-job/Skywalking/kibana/rocketmq-console

4) 运维组件          
   Harbor/K8S/Promethues/Grafana/Jumpserver

### 3. 解决思路
实现思路比较简单,通过统一的平台,去管理所有的组件,而不是在不同的组件之间进行登录切换,并且,要做到统一的鉴权中心.     

1) 对各个组件的api进行了解.
2) 对业务模型进行抽象,各个组件只是实现的形式而已,亦方便以后支持更多的组件.    
3) 可以自由选择组件,而非,只支持一种组件(比如:gitlab/git/gitee...).  
4) 实现鉴权(细到数据行),并且,同步到各个组件里(比如:gitlab).   

### 4. 各组件API罗列
1) [Jenkins API](https://www.lixin.help/2022/05/07/Jenkins-Api.html)
2) [Nacos API](https://nacos.io/zh-cn/docs/open-api.html)
3) [Harbor API](https://editor.swagger.io/?url=https://raw.githubusercontent.com/goharbor/harbor/main/api/v2.0/legacy_swagger.yaml)
4) [Gitlab API](https://github.com/gitlab4j/gitlab4j-api)
5) [Git API](https://docs.github.com/zh/enterprise-cloud@latest/rest/guides/getting-started-with-the-rest-api)
6) [Eureka API](https://github.com/Netflix/eureka/wiki/Eureka-REST-operations)

### 5. 模块依赖图纸
![模块依赖图](docs/desgin/module-dependency.jpg)

### 6. 设计图纸
![框架设计图纸](docs/desgin/Pipeline-ClassDiagram.jpg)

### 7. 自定义流水线案例(JSON)

```
{
  "key": "pipelineEngine-test",
  "name": "测试流水线",
  "pipelines": [
    {
      "clazz": "help.lixin.core.definition.impl.PluginDefinition",
      "id": "1",
      "name": "gitlab下载源码",
      "source": null,
      "target": "2",
      "plugin": "gitlab",
      "params": "{  \"url\" : \"ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git\" , \"branch\" : \"main\" }"
    },
    {
      "clazz": "help.lixin.core.definition.impl.SequenceFlowDefinition",
      "id": "2",
      "name": "流水线-1",
      "source": "1",
      "target": "3",
      "params": ""
    },
    {
      "clazz": "help.lixin.core.definition.impl.PluginDefinition",
      "id": "3",
      "name": "jenkins maven 源码编译",
      "source": "2",
      "target": "4",
      "sync": true,
      "plugin": "jenkins",
      "params": "{  \"templateFile\" : \"/Users/lixin/GitRepository/starlink/admin/src/main/resources/java-service-template.ftl\" , \"credentialId\" : \"gitlab\" , \"archiveArtifacts\" : \"target/*.jar\" , \"stages\": [ { \"name\":\"Build\",\"steps\": \" sh  ''' mvn clean install -DskipTests -X '''  \"  } ] }"
    },
    {
      "clazz": "help.lixin.core.definition.impl.SequenceFlowDefinition",
      "id": "4",
      "name": "流水线-2",
      "source": "3",
      "target": "5"
    },
    {
      "clazz": "help.lixin.core.definition.impl.PluginDefinition",
      "id": "5",
      "name": "配置harbor仓库",
      "source": "4",
      "target": "6",
      "sync": true,
      "plugin": "harbor"
    },
    {
      "clazz": "help.lixin.core.definition.impl.SequenceFlowDefinition",
      "id": "6",
      "name": "流水线-3",
      "source": "5",
      "target": "7"
    },
    {
      "clazz": "help.lixin.core.definition.impl.PluginDefinition",
      "id": "7",
      "name": "Docker打包镜像并推送给Harbor仓库",
      "source": "6",
      "target": "8",
      "sync": true,
      "plugin": "shell",
      "params": "{  \"cmds\":[  \" cd ${ARTIFACT_DIR} \" , \" docker build -f ${DOCKER_FILE} --build-arg APP_FILE=${ARTIFACT_NAME}  -t ${projectName}:v${BUILD_NUMBER} . \" , \" docker login ${REPOSITORY_URL} -u ${REPOSITORY_USERNAME} -p ${REPOSITORY_PASSWORD} \" , \" docker tag ${projectName}:v${BUILD_NUMBER}  ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} \" , \" docker push ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} \"  ] }"
    },
    {
      "clazz": "help.lixin.core.definition.impl.SequenceFlowDefinition",
      "id": "8",
      "name": "流水线-4",
      "source": "7",
      "target": "9"
    },
    {
      "clazz": "help.lixin.core.definition.impl.PluginDefinition",
      "id": "9",
      "name": "K8S拉取镜像,并发布",
      "source": "8",
      "target": null,
      "sync": true,
      "plugin": "k8s-deploy",
      "params": "{  \"yamlTemplatePath\":\"/Users/lixin/GitRepository/starlink/admin/src/main/resources/deployment-template.yml\" , \"deployName\":\"${projectName}-deploy\" ,\"podLabelName\":\"app\" ,\"podLabelValue\":\"spring-web-demo-pod\", \"imagePullSecretName\":\"loginharbor\" , \"containerName\":\"${projectName}\", \"port\":\"9091\" }"
    }
  ]
}
```

### 8. 自定义流水线案例(YAML)
```
key: "pipelineEngine-test"
name: "测试流水线"
pipelines:
  - !<help.lixin.core.definition.impl.PluginDefinition>
    id: "1"
    source: null
    target: "2"
    name: "gitlab下载源码"
    clazz: "help.lixin.core.definition.impl.PluginDefinition"
    params: "{  \"url\" : \"ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git\" , \"branch\" : \"main\" }"
    plugin: "gitlab"
    sync: true
  - !<help.lixin.core.definition.impl.SequenceFlowDefinition>
    id: "2"
    source: "1"
    target: "3"
    name: "流水线-1"
    clazz: "help.lixin.core.definition.impl.SequenceFlowDefinition"
    params: ""
    plugin: "flow"
  - !<help.lixin.core.definition.impl.PluginDefinition>
    id: "3"
    source: "2"
    target: "4"
    name: "jenkins maven 源码编译"
    clazz: "help.lixin.core.definition.impl.PluginDefinition"
    params: "{  \"templateFile\" : \"/Users/lixin/GitRepository/starlink/admin/src/main/resources/java-service-template.ftl\" , \"credentialId\" : \"gitlab\" , \"archiveArtifacts\" : \"target/*.jar\" ,  \"stages\": [ { \"name\":\"Build\",\"steps\": \" sh  ''' mvn clean install -DskipTests  -X '''  \"  } ] }"
    plugin: "jenkins"
    sync: true
  - !<help.lixin.core.definition.impl.SequenceFlowDefinition>
    id: "4"
    source: "3"
    target: "5"
    name: "流水线-2"
    clazz: "help.lixin.core.definition.impl.SequenceFlowDefinition"
    params: null
    plugin: "flow"
  - !<help.lixin.core.definition.impl.PluginDefinition>
    id: "5"
    source: "4"
    target: "6"
    name: "配置harbor仓库"
    clazz: "help.lixin.core.definition.impl.PluginDefinition"
    params: null
    plugin: "harbor"
    sync: true
  - !<help.lixin.core.definition.impl.SequenceFlowDefinition>
    id: "6"
    source: "5"
    target: "7"
    name: "流水线-3"
    clazz: "help.lixin.core.definition.impl.SequenceFlowDefinition"
    params: null
    plugin: "flow"
  - !<help.lixin.core.definition.impl.PluginDefinition>
    id: "7"
    source: "6"
    target: "8"
    name: "Docker打包镜像并推送给Harbor仓库"
    clazz: "help.lixin.core.definition.impl.PluginDefinition"
    params: "{  \"cmds\":[  \" cd ${ARTIFACT_DIR} \" , \" docker build -f ${DOCKER_FILE} --build-arg APP_FILE=${ARTIFACT_NAME}  -t ${projectName}:v${BUILD_NUMBER} . \" , \" docker login ${REPOSITORY_URL} -u ${REPOSITORY_USERNAME} -p ${REPOSITORY_PASSWORD} \" , \" docker tag ${projectName}:v${BUILD_NUMBER}  ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} \" , \" docker push ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} \"  ] }"
    plugin: "shell"
    sync: true
  - !<help.lixin.core.definition.impl.SequenceFlowDefinition>
    id: "8"
    source: "7"
    target: "9"
    name: "流水线-4"
    clazz: "help.lixin.core.definition.impl.SequenceFlowDefinition"
    params: null
    plugin: "flow"
  - !<help.lixin.core.definition.impl.PluginDefinition>
    id: "9"
    source: "8"
    target: null
    name: "Docker打包镜像并推送给Harbor仓库"
    clazz: "help.lixin.core.definition.impl.PluginDefinition"
    params: "{  \"yamlTemplatePath\":\"/Users/lixin/GitRepository/starlink/admin/src/main/resources/deployment-template.yml\" , \"deployName\":\"${projectName}-deploy\" ,\"podLabelName\":\"app\" ,\"podLabelValue\":\"spring-web-demo-pod\", \"imagePullSecretName\":\"loginharbor\" , \"containerName\" : \"${projectName}\", \"port\":\"9091\" }"
    plugin: "k8s-deploy"
    sync: true
others: { }
```

### 9. 为什么要自定义流水线
在设计时,有考虑过是否要向Jenkins靠拢,即:把流水进行转换成jenkis中的stage,但是,后来考虑了一下,这样做的话,会太过于依赖jenkins了,后来决定,还是自己做pipline,然后,把jenkins当成流水线中的一个小步骤.  

### 10. 各Action详解
#### 1) Gitlab

Gilab的Action比较简单,只是设置相关变量(项目名称/分支/仓库地址)即可,为什么这么简单?因为:Gitlab提供了大量的Api给前端,由前端辅助用户选择:项目和分支,并将选择结果交给后端保存.  

```
# 这些信息,gitlab里有大量的api,由前端去获取即可.
# projctName: 对应gitlab里的project
# branch : 对应gitlab里的分支
# url    : project具体的位置
{
  "projectName": "spring-web-demo",
  "branch" : "main",
  "url" : "ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git"
}
```


#### 2) Jenkins

Jenkins的Action比较复杂: 

+ 2.1) 根据:项目名称+分支名称,判断在Jenkins中是否存在,如果不存在,则创建Job,并,获了Job的基本信息(JobInfo,其中:nextBuildNumber代表下一次构建的id).
+ 2.2) 如果(项目名称+分支名称)在Jenkins中存在,则,获了项目的基本信息(JobInfo,其中:nextBuildNumber代表下一次构建的id).
+ 2.3) 触发Job构建,在构建从上下文中获得参数(这些参数是在gitlab里设置的:项目名称/分支/仓库地址).
+ 2.4) 拿着JobInfo里的nextBuildNumber轮询去拿到"构建信息(BuildInfo)",在"构建信息"结果,里有一个信息(result),result有三个状态:Null:代表仍在构建中/FAILURE:代表构建失败/SUCCESS:代表构建成功.
+ 2.5) 在"构建信息"里还有一个重要的对象:Artifact,是代表构建成功后的成品位置(这个成品位置要求你自己填写,比如:target/*.jar),通过jenkins api下载"成品"信息保存到磁盘上(建议共享磁盘),并把磁盘位置信息保存到线程上下文里.
+ 2.6) Jenkins Action的参数如下:
```
# templateFile       :   jenkins内部在创建job时,实际是在workspace目录里创建一个文件夹,并为这个文件夹配置一个xml,这个xml存储的就是我们在jenkins界面配置的所有内容,这里的xml是一个模板内容. 
# credentialId       :   凭证id,需要运维预先在jenkins里配置好,我这里代表的是gitlab的账号和密码凭证.  
# cmd                :   要执行的命令
# archiveArtifacts   :   构建成功后,最后的成品库位置.  

{
  "templateFile" : "/Users/lixin/GitRepository/starlink/admin/src/test/resources/java-service-template.xml",
  "credentialId" : "gitlab",
  "cmd" : "mvn clean install -DskipTests -X",
  "archiveArtifacts" : "target/*.jar"
}
```

+ 2.7) Jenkins不足处:
   + 2.7.1) 需要预先在Jenkins里大量的配置(比如:插件安装/凭证配置/jdk/maven/gradle/nodejs/gitlab),运维人员都能参与到这份上了,与我的想法差别还是比较大的,后期的想法是去掉Jenkins自己去拉取代码下来,调用:Docker根据不同的环境(java/android/ios)来编译.
   + 2.7.2) 为什么我要把"成品"下载下来,原因只有一个,我不能让后面的action与jenkins有太大的交互了,比如:后面我需要对"成品"进行二次加工,制作成docker镜像. 

+ 2.8) Jenkins提供可引用的变量

```
ARTIFACT_DIR         :          /Users/lixin/GitRepository/spring-web-demo/target    
ARTIFACT_NAME        :          spring-web-demo-1.1.0.jar 
ARTIFACT_FULL_PATH   :          /Users/lixin/GitRepository/spring-web-demo/target/spring-web-demo-1.1.0.jar
```

#### 3) Harbor
Harbor现在的做法,是把仓库地址/账号/密码塞在上下文里,这些信息,可以在流水线里配置,也可以不用配置,不配置会默认读取application.properties里的.

Harbor提供可引用的变量

```
HTTP_REPOSITORY_URL  :   http://103.215.125.86:3080 
REPOSITORY_URL       :   103.215.125.86:3080
REPOSITORY_USERNAME  :   admin
REPOSITORY_PASSWORD  :   XXXXX
```

#### 4) Shell

Shell的做法会比较简单,你可以传递一组cmd,进行执行,以下为案例. 

```
{
  cmds:[
     " cd ${ARTIFACT_DIR} ",
     " docker login ${REPOSITORY_URL} -u ${REPOSITORY_USERNAME} -p ${REPOSITORY_PASSWORD} ",
     " docker build -f ${DOCKER_FILE} --build-arg APP_FILE=${ARTIFACT_NAME}  -t ${projectName}:v${SECOND} .  ",
     " docker tag ${projectName}:v${SECOND}  ${REPOSITORY_URL}/${projectName}/${projectName}:v${SECOND} ",
     " docker push ${REPOSITORY_URL}/${projectName}/${projectName}:v${SECOND} "
  ]
}
```

#### 5) Gloabl变量

```
YEAR     : 年
MONTH    : 月
DAY      : 日
HOUR     : 小时
MINUTE   : 分钟
SECOND   : 秒钟
DATETIME : yyyy-MM-dd HH:mm:ss
```

### 11. 流水线引擎
为了让框架更加的通用,没有自己研发流水线引擎,而是向"流程引擎"靠拢,
也没有与具体的某一个"流程引擎"厂商绑定,而是:自己定义"流水线引擎操作接口",让各个"流程引擎"实现这个接口,这样就不存在选型问题,目前:只支持Camunda,后续会支持更多(Flowable/Activiti). 

### 12. 流水线执行日志查看
某个Pipline的执行过程会比较耗时,为了增加体验,期望能"实时"的查看每一个Pipline节点,"此时",正在做什么操作.
最初考虑的方案是通过发布事件的方式去收集这些信息,后来发现:代码相当的臃肿且不优美(正常/异常处都要添加发布事件).
最终的解决方案是对Logger进行扩展,并无感知切入(无须在XML配置),只要像以往那样正常打日志,
框架层,会对日志进行落库(DB),最终会把"日志"与"业务Pipline进行关联"(关联思想源码于:zipkin中traceId和spaceId),
该过程是异步的(通过Disruptor生产和消费),允许丢失消息,如果不满足需求,可以扔一个实现进去,让日志与业务强事务(不建议).          

为什么不用AOP来做日志拦截和处理? 
1) AOP只能拦截到方法上,粒度太粗了.  
2) 为了这个日志收集功能,AOP会强制要求:开发必须把每一个细小的操作,都转换成方法,这种约束过强.     
3) 有一定约束和规范有好处,亦有坏处,越是有规范的东西,能减少异同和学习成本,但是,也会局限你,阻碍你的认知,它会给你灌输一种思想,让你从骨子里就认为,形成规范的东西一定是最好的,会影响你对未知的探索.          

### 13. 事件总线
事件总线产生的背景,本系统有一整套管理模型(比如:用户/项目),而其它组件(比如:gitlab/harbor/camunda...)也都有一套模型,期望:在本系统进行管理时,能够自动与其它组件(比如:gitlab/harbor)进行同步,(例如:用户在本系统创建用户时:gitlab也能创建用户),实现方式有以下几种:      

1) 同步调用    
   比如:在本系统创建:用户/角色/项目时,同步调用其它组件(比如:gitlab/harbor),缺点:代码过于耦合,如果,新增一个组件咋办?所以Pass掉.      
2) MQ方案(Kakfa/RocketMQ/Pulsar)       
   不推荐MQ的原因是有两点:    
   2.1) MQ(RocketMQ/Kafka)会自动删除过期的消息,不符合我的业务,例如:用户刚开始使用的组件是:阿里docker仓库,后面(几年后),把docker仓库切换成:harbor(由于,本系统要协助用户自动在harbor创建Project),而此时,如果harbor组件去订阅消息时,发现以前的消息不复存在了.              
   2.2) MQ太重了,因为,这个项目是Plugin模式开发,期望:一个普通开发人员拿下代码后,能直接运行,而且,一旦启动时,依赖其它的外部框架,会给开发人员造成学习成本的增加.    
   2.3) 其实:Pulsar符合我的业务要求.  
4) outbox模式(推荐)         
   针对同步调用的缺点,相应的解决方案是,通过:"事件发布"进行解耦,将消息载体通过DB进行存储,而且,还能,保证在一个事务之内(该思想源于:eventuate-tram-core),这样,就能保证,绝对不会丢失消息,不同的组件(gitlab)在订阅时,记住最后一次同步的id(lastId),一批一批的拉取消息进行处理.             
5) 设计考虑      
   为了应对将来大数据量以及多租户隔离机制,event组件,要求按照面向对象编程的原则(迪米特/开闭/里氏替换/接口隔离),根据使用情况,可切换到其它的开源MQ,比如:Pulsar.            
### 14. Pipeline转换成BPMN效果图
![Pipeline 转换成BPMN后效果图](docs/desgin/pipline-bpmn.png)

### 15. 上面Pipeline转换成Camunda xml的内容
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<definitions xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="definitions_ed4324e7-ae1f-4741-a359-d5bb8454c530" targetNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <process id="pipelineEngine-test" isExecutable="true" name="测试流水线">
    <startEvent id="startEvent_1ea0ff43-9d78-4fce-aba7-c8bf05827554">
      <outgoing>sequenceFlow_cba7c820-c00c-40df-8ecb-67b1222aa034</outgoing>
    </startEvent>
    <serviceTask camunda:topic="gitlab" camunda:type="external" id="serviceTask_f18e889a-531f-499f-92d5-142aa845cb12" name="gitlab下载源码">
      <extensionElements>
        <camunda:inputOutput>
          <camunda:inputParameter name="_params">{  "url" : "ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git" , "branch" : "main" }</camunda:inputParameter>
        </camunda:inputOutput>
      </extensionElements>
      <incoming>sequenceFlow_cba7c820-c00c-40df-8ecb-67b1222aa034</incoming>
      <outgoing>sequenceFlow_abcac0da-58fd-4efd-b89a-1265944c9239</outgoing>
    </serviceTask>
    <sequenceFlow id="sequenceFlow_cba7c820-c00c-40df-8ecb-67b1222aa034" sourceRef="startEvent_1ea0ff43-9d78-4fce-aba7-c8bf05827554" targetRef="serviceTask_f18e889a-531f-499f-92d5-142aa845cb12"/>
    <serviceTask camunda:topic="jenkins" camunda:type="external" id="serviceTask_5a68b5ac-eb1a-4da1-bab8-f53340eb91f2" name="jenkins maven 源码编译">
      <extensionElements>
        <camunda:inputOutput>
          <camunda:inputParameter name="_params">{  "templateFile" : "/Users/lixin/GitRepository/starlink/admin/src/main/resources/java-service-template.ftl" , "credentialId" : "gitlab" , "archiveArtifacts" : "target/*.jar" , "stages": [ { "name":"Build","steps": " sh  ''' mvn clean install -DskipTests -X '''  "  } ] }</camunda:inputParameter>
        </camunda:inputOutput>
      </extensionElements>
      <incoming>sequenceFlow_abcac0da-58fd-4efd-b89a-1265944c9239</incoming>
      <outgoing>sequenceFlow_94561705-1178-4d14-8d47-794518afcef8</outgoing>
    </serviceTask>
    <sequenceFlow id="sequenceFlow_abcac0da-58fd-4efd-b89a-1265944c9239" sourceRef="serviceTask_f18e889a-531f-499f-92d5-142aa845cb12" targetRef="serviceTask_5a68b5ac-eb1a-4da1-bab8-f53340eb91f2"/>
    <serviceTask camunda:topic="harbor" camunda:type="external" id="serviceTask_228efccb-047d-4d4b-a9fb-2e32d94b9fa1" name="配置harbor仓库">
      <incoming>sequenceFlow_94561705-1178-4d14-8d47-794518afcef8</incoming>
      <outgoing>sequenceFlow_18bd9c91-89ef-4c31-89ac-7dc5a87b19fa</outgoing>
    </serviceTask>
    <sequenceFlow id="sequenceFlow_94561705-1178-4d14-8d47-794518afcef8" sourceRef="serviceTask_5a68b5ac-eb1a-4da1-bab8-f53340eb91f2" targetRef="serviceTask_228efccb-047d-4d4b-a9fb-2e32d94b9fa1"/>
    <serviceTask camunda:topic="shell" camunda:type="external" id="serviceTask_5674b6da-8d3f-4477-bbac-c7cfd289c075" name="Docker打包镜像并推送给Harbor仓库">
      <extensionElements>
        <camunda:inputOutput>
          <camunda:inputParameter name="_params">{  "cmds":[  " cd ${ARTIFACT_DIR} " , " docker build -f ${DOCKER_FILE} --build-arg APP_FILE=${ARTIFACT_NAME}  -t ${projectName}:v${BUILD_NUMBER} . " , " docker login ${REPOSITORY_URL} -u ${REPOSITORY_USERNAME} -p ${REPOSITORY_PASSWORD} " , " docker tag ${projectName}:v${BUILD_NUMBER}  ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} " , " docker push ${REPOSITORY_URL}/${projectName}/${projectName}:v${BUILD_NUMBER} "  ] }</camunda:inputParameter>
        </camunda:inputOutput>
      </extensionElements>
      <incoming>sequenceFlow_18bd9c91-89ef-4c31-89ac-7dc5a87b19fa</incoming>
      <outgoing>sequenceFlow_92981ff9-2d33-4d10-8efb-d89b1ef00fbf</outgoing>
    </serviceTask>
    <sequenceFlow id="sequenceFlow_18bd9c91-89ef-4c31-89ac-7dc5a87b19fa" sourceRef="serviceTask_228efccb-047d-4d4b-a9fb-2e32d94b9fa1" targetRef="serviceTask_5674b6da-8d3f-4477-bbac-c7cfd289c075"/>
    <serviceTask camunda:topic="k8s-deploy" camunda:type="external" id="serviceTask_020fa67e-253c-4069-8a20-aad8447bc60e" name="K8S拉取镜像,并发布">
      <extensionElements>
        <camunda:inputOutput>
          <camunda:inputParameter name="_params">{  "yamlTemplatePath":"/Users/lixin/GitRepository/starlink/admin/src/main/resources/deployment-template.yml" , "deployName":"${projectName}-deploy" ,"podLabelName":"app" ,"podLabelValue":"spring-web-demo-pod", "imagePullSecretName":"loginharbor" , "containerName":"${projectName}", "port":"9091" }</camunda:inputParameter>
        </camunda:inputOutput>
      </extensionElements>
      <incoming>sequenceFlow_92981ff9-2d33-4d10-8efb-d89b1ef00fbf</incoming>
      <outgoing>sequenceFlow_bef88dea-628a-4cb3-8a34-59b38cd58dc9</outgoing>
    </serviceTask>
    <sequenceFlow id="sequenceFlow_92981ff9-2d33-4d10-8efb-d89b1ef00fbf" sourceRef="serviceTask_5674b6da-8d3f-4477-bbac-c7cfd289c075" targetRef="serviceTask_020fa67e-253c-4069-8a20-aad8447bc60e"/>
    <endEvent id="endEvent_e436a048-f9ef-4663-988c-6edf9c528d91">
      <incoming>sequenceFlow_bef88dea-628a-4cb3-8a34-59b38cd58dc9</incoming>
    </endEvent>
    <sequenceFlow id="sequenceFlow_bef88dea-628a-4cb3-8a34-59b38cd58dc9" sourceRef="serviceTask_020fa67e-253c-4069-8a20-aad8447bc60e" targetRef="endEvent_e436a048-f9ef-4663-988c-6edf9c528d91"/>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_fb17dfc5-6da8-4f53-9d72-d09eb379c571">
    <bpmndi:BPMNPlane bpmnElement="pipelineEngine-test" id="BPMNPlane_33359969-957b-4ae6-b7e8-13f622ee9bcb">
      <bpmndi:BPMNShape bpmnElement="startEvent_1ea0ff43-9d78-4fce-aba7-c8bf05827554" id="BPMNShape_027afffd-e789-49ef-b23d-ce3db92c6b2b">
        <dc:Bounds height="36.0" width="36.0" x="100.0" y="100.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="serviceTask_f18e889a-531f-499f-92d5-142aa845cb12" id="BPMNShape_33826a0d-58db-4cbb-ae1f-f4a37491cffd">
        <dc:Bounds height="80.0" width="100.0" x="186.0" y="78.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_cba7c820-c00c-40df-8ecb-67b1222aa034" id="BPMNEdge_e4122b00-d231-4b34-8b27-d31778df5225">
        <di:waypoint x="136.0" y="118.0"/>
        <di:waypoint x="186.0" y="118.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="serviceTask_5a68b5ac-eb1a-4da1-bab8-f53340eb91f2" id="BPMNShape_78cb0897-43ee-4fb1-84d0-32612741e8bd">
        <dc:Bounds height="80.0" width="100.0" x="336.0" y="78.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_abcac0da-58fd-4efd-b89a-1265944c9239" id="BPMNEdge_88ff2bcb-4eb9-448c-a673-b1c048ce73d5">
        <di:waypoint x="286.0" y="118.0"/>
        <di:waypoint x="336.0" y="118.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="serviceTask_228efccb-047d-4d4b-a9fb-2e32d94b9fa1" id="BPMNShape_390830be-824e-4c91-abc6-9136ae816d62">
        <dc:Bounds height="80.0" width="100.0" x="486.0" y="78.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_94561705-1178-4d14-8d47-794518afcef8" id="BPMNEdge_184239f6-2601-418f-affa-4aebe1ef50db">
        <di:waypoint x="436.0" y="118.0"/>
        <di:waypoint x="486.0" y="118.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="serviceTask_5674b6da-8d3f-4477-bbac-c7cfd289c075" id="BPMNShape_07cd8b97-c314-4ffc-9bd3-2a9650d1ffd4">
        <dc:Bounds height="80.0" width="100.0" x="636.0" y="78.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_18bd9c91-89ef-4c31-89ac-7dc5a87b19fa" id="BPMNEdge_0509ce09-1e68-46ec-bb81-467f0c7558bf">
        <di:waypoint x="586.0" y="118.0"/>
        <di:waypoint x="636.0" y="118.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="serviceTask_020fa67e-253c-4069-8a20-aad8447bc60e" id="BPMNShape_141b4f4f-8bbb-44c3-88a0-8521bd1cbdf8">
        <dc:Bounds height="80.0" width="100.0" x="786.0" y="78.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_92981ff9-2d33-4d10-8efb-d89b1ef00fbf" id="BPMNEdge_b67e75e4-c104-49ff-8cac-c11cd978f82c">
        <di:waypoint x="736.0" y="118.0"/>
        <di:waypoint x="786.0" y="118.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="endEvent_e436a048-f9ef-4663-988c-6edf9c528d91" id="BPMNShape_6978de87-6de0-4ff6-ba69-289bd8a06a9e">
        <dc:Bounds height="36.0" width="36.0" x="936.0" y="100.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sequenceFlow_bef88dea-628a-4cb3-8a34-59b38cd58dc9" id="BPMNEdge_3711fb64-7394-4c61-a853-09b5b14744dd">
        <di:waypoint x="886.0" y="118.0"/>
        <di:waypoint x="936.0" y="118.0"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```

### 16. 感谢其它开源作者 
1) [Camunda BPMN Converter](https://github.com/lzgabel/camunda-bpmn-converter)
