
### 1. 

```
//下载harbor压缩包
[root@harbor_node01 ~]# wget https://github.com/goharbor/harbor/releases/download/v2.4.3/harbor-offline-installer-v2.4.3.tgz
//把harbor解压至/usr/local目录下
[root@harbor_node01 ~]# tar xf harbor-offline-installer-v2.4.3.tgz -C /opt
//进入harbor目录
[root@harbor_node01 ~]# cd /opt/harbor/
[root@harbor_node01 harbor]# ls
common.sh  harbor.v2.4.3.tar.gz  harbor.yml.tmpl  install.sh  LICENSE  prepare
//把配置文件模板拷贝一份出来
root@harbor_node01 harbor]# cp harbor.yml.tmpl harbor.yml
//查看当前主机的主机名全称
[root@harbor_node01 harbor]# hostname
harbor_node01.example.com
[root@harbor_node01 harbor]# vim harbor.yml
..........
hostname: harbor_node01.example.com		//这里的主机名换成本机的主机名或IP地址
.........
#https:									//把https这行及子行都注释掉，因为没有弄ssl证书加密
  # https port for harbor, default is 443
  #port: 443
  # The path of cert and key files for nginx
  #certificate: /your/certificate/path
  #private_key: /your/private/key/path
.........

//启动harbor
[root@harbor_node01 harbor]# ./install.sh
..........启动过程略.............
✔ ----Harbor has been installed and started successfully.----	 //显示此行说明启动成功！
//查看harbor运行起来的容器
[root@harbor_node01 harbor]# docker ps
CONTAINER ID   IMAGE                                COMMAND                  CREATED              STATUS                    PORTS                                   NAMES
1602065b6558   goharbor/nginx-photon:v2.4.3         "nginx -g 'daemon of…"   About a minute ago   Up 51 seconds (healthy)   0.0.0.0:80->8080/tcp, :::80->8080/tcp   nginx
807abe81e1e8   goharbor/harbor-jobservice:v2.4.3    "/harbor/entrypoint.…"   About a minute ago   Up 52 seconds (healthy)                                           harbor-jobservice
525886fff9b0   goharbor/harbor-core:v2.4.3          "/harbor/entrypoint.…"   About a minute ago   Up 53 seconds (healthy)                                           harbor-core
9d40e1cf4131   goharbor/harbor-db:v2.4.3            "/docker-entrypoint.…"   About a minute ago   Up 54 seconds (healthy)                                           harbor-db
e7405e5ba6b1   goharbor/registry-photon:v2.4.3      "/home/harbor/entryp…"   About a minute ago   Up 54 seconds (healthy)                                           registry
a86ae3d1848e   goharbor/redis-photon:v2.4.3         "redis-server /etc/r…"   About a minute ago   Up 54 seconds (healthy)                                           redis
814e2bfc178e   goharbor/harbor-registryctl:v2.4.3   "/home/harbor/start.…"   About a minute ago   Up 54 seconds (healthy)                                           registryctl
340de1da037f   goharbor/harbor-portal:v2.4.3        "nginx -g 'daemon of…"   About a minute ago   Up 54 seconds (healthy)                                           harbor-portal
c3eceadcb92b   goharbor/harbor-log:v2.4.3           "/bin/sh -c /usr/loc…"   About a minute ago   Up 59 seconds (healthy)   127.0.0.1:1514->10514/tcp               harbor-log
//查看启用的端口
[root@harbor_node01 harbor]# ss -anlt
```
