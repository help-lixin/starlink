### 1. 背景

对于jenkins是又爱又恨,爱是因为,这个工具:帮你实现了CI/CD,恨是因为:这东西没有走向智能化,而我的流水线开发,仅仅只是用到了,jenkins进行了源码编译(
但是,却需要部署一个jenkins,并配置jdk/maven/gitlab...),所以,干脆下载源码后,让容器加载源码目录,让容器帮忙打包.

### 2. maven加速器

为了加速maven下载依赖的过程,阿里提供了下载加速器(并且,做成了镜像),所以,自己也懒得自建Dockerfile了,直接拷贝官网的下来,为什么要拷贝一份下来?因为:
这份Dockerfile实际是模板文件,原因是:很多公司会自建maven仓库来着的,需要自行根据Dockerfile去修改仓库地址. 

https://github.com/AliyunContainerService/maven-image

### 3. 实现思路

1. 基于阿里提供的maven加速器,通过Dockerfile构建出一个基础镜像. 
2. 创建API调用Docker,加载源码目录,进行编译即可. 
3. 前置要求是:下载源码的目录,需要是共享的(比如:nfs/s3),Docker宿主机要能挂载这个目录. 

```
docker run -it --rm \
--name my-maven-project \
-v "$(pwd)":/usr/src/mymaven \
-w /usr/src/mymaven \
registry.cn-hangzhou.aliyuncs.com/acs/maven \
mvn clean install
```
