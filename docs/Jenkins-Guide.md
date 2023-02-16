
### 1. 安装Jenkins
```
# 1. 创建磁盘目录
lixin-macbook:~ lixin$ mkdir -p /opt/docker/jenkins/jenkins-data

# 2. 运行jenkins
lixin-macbook:~ lixin$ docker run \
-u root \
-d \
-p 2080:8080 \
-p 50000:50000 \
-v /opt/docker/jenkins/jenkins-data:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
--name jenkins \
--restart always \
--privileged=true \
jenkinsci/blueocean
```
### 2. 解锁Jenkins
```
# 进入容器内部
[root@lixin ~]# docker exec -it jenkins /bin/bash

# 查看初始化密码
bash-5.1# cat /var/jenkins_home/secrets/initialAdminPassword
72f5e1a3b7fd4a48b0fddb8bf3b66ede
```

![解锁Jenkins](jenkins/jenkins-unlock.png)

### 2. 自定义Jenkins
> 在这里我选择:安装推荐插件

![自定义Jenkins](jenkins/jenkins-customer.png)


### 3. Jenkins安装推荐插件
![Jenkins安装推荐插件](jenkins/jenkins-plugin-install.png)

### 4. Jenkins创建管理员账号
![Jenkins创建管理员账号](jenkins/jenkins-create-admin.png)

### 5. Jenkins实例配置
![Jenkins实例配置](jenkins/jenkins-instance-config.png)

### 6. 安装常见错误
> 无法连接到Jenkins,重启下jenkins,然后,重新登录即可. 

![无法连接到Jenkins](jenkins/jenkins-connection-repository-error.png)


