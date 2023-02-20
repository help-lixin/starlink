### 1. 项目背景
大概是在三年前,公司派我和一同事去负责一个项目,我们在谈论微服务之道时,他提出了一个质疑我觉得非常好,微服务之后:<br/>
看日志要用:Kibana,持续集成要用:Jenkins,维护定时任务要用:xxl-job-admin... <br/>
难道就没有一个统一的开源平台,进行统一管理吗?而,该项目就是解决这个问题.  

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

### 5. 设计图纸
![框架设计图纸](docs/desgin/Pipeline-ClassDiagram.jpg)

### 6. 流水线定义案例
> 后期可以像阿里云效平台那样,运维通过画布组合大量的组件,实现任务的流水线的编排.   

```
[
  {
    "clazz": "help.lixin.core.definition.impl.PluginDefinition",
    "id": "1",
    "name": "gitlab下载源码",
    "source": null,
    "target": "2",
    "plugin": "gitlab",
    "params": "{ \"branch\" : \"main\", \"url\" : \"ssh://git@103.215.125.86:2222/order-group/spring-web-demo.git\" }"
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
    "params": "{  \"templateFile\" : \"/Users/lixin/GitRepository/spider-web-platform/admin/src/test/resources/java-service-template.xml\" , \"credentialId\" : \"zhangsan\" , \"compile\" : \"maven\" , \"cmd\" : \"mvn clean install -DskipTests -X\" , \"archiveArtifacts\" : \"target/*.jar\" }"
  },
  {
    "clazz": "help.lixin.core.definition.impl.SequenceFlowDefinition",
    "id": "4",
    "name": "流水线-2",
    "source": "3",
    "target": null
  }
]
```

### 7. 为什么要自定义流水线
在开发时,有考虑过向Jenkins靠扰,即:把流水进行转换向jenkis靠拢,但是,后来考虑了一下,这样做的话,会太过于依赖jenkins了,还是自己做pipline,然后,把jenkins当成流水线中的一个小步骤而已. 

### 8. 项目结构
```
lixin-macbook:spider-web-platform lixin$ tree -L 2
.
├── LICENSE
├── README.md
├── actions                     # action组件集合(jenins/gitlab/harbor....)
│   ├── gitlab-action
│   ├── harbor-action
│   ├── jenkins-action
│   └── pom.xml
├── admin                       # 将来的后台系统,统一界面. 
│   ├── pom.xml
│   ├── src
│   └── target
├── api-parent                   # 在第一阶段,用于api测试的代码,到最后,action完成后,这些api测试代码会移除
│   ├── docker-api
│   ├── eureka-api
│   ├── gitlab-api
│   ├── harbor-api
│   ├── jenkins-api
│   ├── nacos-api
│   ├── pom.xml
│   └── xxl-job-api
├── core                        # pipline的核心代码定义
│   ├── pom.xml
│   ├── src
│   └── target
├── docs                        # 所有软件的安装以及配置过程
│   ├── GitLab-Guide.md
│   ├── Harbor-Install.md
│   ├── Jenkins-Guide.md
│   ├── Nacos-Guide.md
│   ├── desgin
│   ├── gitlab
│   └── jenkins
└── pom.xml
```
