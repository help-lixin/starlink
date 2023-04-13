### 1. 查看系统信息
```
[app@gitlab ~]$ uname -a
Linux gitlab 3.10.0-1160.el7.x86_64 #1 SMP Mon Oct 19 16:18:59 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
```
### 2. 安装yum-config-manager
```
[app@gitlab ~]$ sudo  yum install -y yum-utils device-mapper-persistent-data lvm2
```
### 3. 设置yum源
```
[app@gitlab ~]$ sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
```
### 4. 查看仓库中所有docker版本
```
[app@gitlab ~]$ yum list docker-ce --showduplicates | sort -r
已加载插件：fastestmirror
可安装的软件包
 * updates: mirrors.aliyun.com
Loading mirror speeds from cached hostfile
 * extras: mirrors.aliyun.com
docker-ce.x86_64            3:23.0.3-1.el7                      docker-ce-stable
docker-ce.x86_64            3:23.0.2-1.el7                      docker-ce-stable
docker-ce.x86_64            3:23.0.1-1.el7                      docker-ce-stable
docker-ce.x86_64            3:23.0.0-1.el7                      docker-ce-stable
docker-ce.x86_64            3:20.10.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.24-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.23-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.22-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.21-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.20-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.19-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.18-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.17-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.16-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.15-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.14-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:20.10.13-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.12-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.11-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.10-3.el7                    docker-ce-stable
docker-ce.x86_64            3:20.10.0-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.15-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.14-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:19.03.13-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.12-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.11-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.10-3.el7                    docker-ce-stable
docker-ce.x86_64            3:19.03.0-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.9-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.8-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.7-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.6-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.5-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.4-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.3-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.2-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.1-3.el7                     docker-ce-stable
docker-ce.x86_64            3:18.09.0-3.el7                     docker-ce-stable
docker-ce.x86_64            18.06.3.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.2.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.1.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.06.0.ce-3.el7                    docker-ce-stable
docker-ce.x86_64            18.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            18.03.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.12.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.09.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.06.0.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.3.ce-1.el7                    docker-ce-stable
docker-ce.x86_64            17.03.2.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.1.ce-1.el7.centos             docker-ce-stable
docker-ce.x86_64            17.03.0.ce-1.el7.centos             docker-ce-stable
```
### 5. 我这里选择:18.06.3.ce
```
[app@gitlab ~]$ sudo yum -y install docker-ce-18.06.3.ce
```
### 6. 启动docker
```
[app@gitlab ~]$ sudo systemctl start docker
[app@gitlab ~]$ sudo systemctl enable docker
```
### 7. 验证docker
```
[app@gitlab ~]$ sudo docker version
Client:
 Version:           18.06.3-ce
 API version:       1.38
 Go version:        go1.10.3
 Git commit:        d7080c1
 Built:             Wed Feb 20 02:26:51 2019
 OS/Arch:           linux/amd64
 Experimental:      false

Server:
 Engine:
  Version:          18.06.3-ce
  API version:      1.38 (minimum version 1.12)
  Go version:       go1.10.3
  Git commit:       d7080c1
  Built:            Wed Feb 20 02:28:17 2019
  OS/Arch:          linux/amd64
  Experimental:     false
```
### 8. 免sudo
```
# 把当前用户(app)加入到docker组
[app@gitlab ~]$ sudo gpasswd -a app docker

# 重启docker
[app@gitlab ~]$ sudo systemctl restart docker

# 赋矛访问docker的权限
[app@gitlab ~]$ sudo chmod a+rw /var/run/docker.sock
```
### 9. 总结  
至此,在CentOS上把Docker安装完成. 