
### 1. Harbor安装
[Harbor安装参考地址](https://www.lixin.help/2021/01/10/Docker-Harbor.html)

### 2. 配置Docker

```
lixin-macbook:~ lixin$ cat ~/.docker/daemon.json
{
  "insecure-registries":["103.215.125.86:3080"]
}
```
### 3. 登录Harbor

```
lixin-macbook:~ lixin$ docker login 103.215.125.86:3080 -u admin
Password:
Login Succeeded

```

### 4. 对本地镜像打标签
```
# 1. 查看本地所有的镜像
lixin-macbook:target lixin$ docker images
REPOSITORY        TAG       IMAGE ID       CREATED       SIZE
spring-web-demo   v1.0.0    56ab506cf75d   4 hours ago   376MB

# 2. 对本地的镜像打标签
# 103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0.0
# 103.215.125.86:3080         : harbor服务器地址
# spring-web-demo             : harbor定义的项目名称
# spring-web-demo:v1.0.0      : 镜像:版本
lixin-macbook:target lixin$ docker tag spring-web-demo:v1.0.0  103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0.0

# 3. 再次查看所有的镜像
lixin-macbook:target lixin$ docker images
REPOSITORY                                            TAG       IMAGE ID       CREATED       SIZE
103.215.125.86:3080/spring-web-demo/spring-web-demo   v1.0.0    56ab506cf75d   5 hours ago   376MB
spring-web-demo                                       v1.0.0    56ab506cf75d   5 hours ago   376MB

# 4. 上传镜像
lixin-macbook:target lixin$ docker push 103.215.125.86:3080/spring-web-demo/spring-web-demo:v1.0.0
The push refers to repository [103.215.125.86:3080/spring-web-demo/spring-web-demo]
1c51cbfc75b4: Pushing [=====>                                             ]  1.772MB/16.65MB
24ec311900d3: Pushed
098af476827f: Pushing [==>                                                ]  10.86MB/209MB
eb109653b21a: Pushing [==>                                                ]  2.119MB/39.63MB
54b5e2b53cf2: Pushing [=>                                                 ]  2.746MB/110.4MB
```

### 5. Harbor查看镜像
