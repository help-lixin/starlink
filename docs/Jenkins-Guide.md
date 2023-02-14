
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
jenkinsci/blueocean
```
