### 1. 项目背景
大概是在三年前,公司派我和一同事去负责一个项目,我们在谈论微服务之道时,他提出了一个质疑我觉得非常好,微服务之后:<br/>
看日志要用:Kibana,持续集成要用:Jenkins,维护定时任务要用:xxl-job-admin... <br/>
难道就没有一个统一的开源平台,过行统一管理吗?而,该项目就是解决这个问题.  

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
实现思路比较简单,通过统一的平台,去管理所有的组件,而不是在不同的组件之间进行切换.

1) 对各个组件的api进行了解.
2) 对业务模型进行抽象,各个组件只是实现的形式而已,亦方便以后支持更多的组件.    
3) 可以自由选择组件,而非,只支持一种组件(比如:gitlab/git/gitee...) 

### 4. 各组件API介绍
1) [Jenkins API](https://www.lixin.help/2022/05/07/Jenkins-Api.html)
2) [Nacos API](https://nacos.io/zh-cn/docs/open-api.html)
3) [Harbor API](https://editor.swagger.io/?url=https://raw.githubusercontent.com/goharbor/harbor/main/api/v2.0/legacy_swagger.yaml)
4) [Gitlab API](https://github.com/gitlab4j/gitlab4j-api)