
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