### 1. docker拉取gitlab
```
lixin-macbook:~ lixin$ docker pull gitlab/gitlab-ce
```
### 2. docker运行gitlab
```
# 1. 创建挂载目录
lixin-macbook:~ lixin$  mkdir -p /opt/docker/gitlab/etc/gitlab && mkdir -p /opt/docker/gitlab/var/log && mkdir -p /opt/docker/gitlab/var/opt 	

# 2. 运行gitlab
lixin-macbook:~ lixin$ docker run \
-itd  \
-p 8443:443 \
-p 1080:80 \
-p 2222:22 \
-v /opt/docker/gitlab/etc/gitlab:/etc/gitlab  \
-v /opt/docker/gitlab/var/log:/var/log/gitlab \
-v /opt/docker/gitlab/var/opt:/var/opt/gitlab \
--restart always \
--privileged=true \
--name gitlab \
gitlab/gitlab-ce
```

### 3. 进入容器内部修改配置(gitlab.rb)
```
# 进容器内部
lixin-macbook:~ lixin$ docker exec -it gitlab /bin/bash
 
# 修改gitlab.rb
root@339d2c526e93:/# vi /etc/gitlab/gitlab.rb
 
#加入如下
# gitlab访问地址,如果端口不写的话默认为80端口
external_url 'http://103.215.125.86'
# ssh主机ip
gitlab_rails['gitlab_ssh_host'] = '103.215.125.86'
# ssh连接端口
gitlab_rails['gitlab_shell_ssh_port'] = 2222
 
# 让配置生效
root@339d2c526e93:/# gitlab-ctl reconfigure
```

### 4. 进入容器内部修改配置(gitlab.yml)
```
# 修改http和ssh配置
root@339d2c526e93:/# vi /opt/gitlab/embedded/service/gitlab-rails/config/gitlab.yml
  gitlab:
    host: 103.215.125.86
    port: 1080 # 这里改为1080
    https: false
    
    
# 重启gitlab 
root@339d2c526e93:/# gitlab-ctl restart
# 退出容器 
root@339d2c526e93:/# exit    
```

### 5. 进入容器内部修改root密码
```
# 进入容器内部
lixin-macbook:~ lixin$ docker exec -it gitlab /bin/bash
 
# 进入控制台
root@339d2c526e93:/# gitlab-rails console -e production

--------------------------------------------------------------------------------
 Ruby:         ruby 2.7.7p221 (2022-11-24 revision 168ec2b1e5) [x86_64-linux]
 GitLab:       15.8.1 (383efe57adf) FOSS
 GitLab Shell: 14.15.0
 PostgreSQL:   13.8
-----------------------------------------------------------[ booted in 167.07s ]
Loading production environment (Rails 6.1.6.1)

# 查询id为1的用户，id为1的用户是超级管理员
irb(main):015:0> user=User.where(id:1).first
=> #<User id:1 @root>
# 修改密码
irb(main):016:0> user.password='123456'
=> "123456"
# 确认密码
irb(main):017:0> user.password_confirmation='123456'
=> "123456"

# 保存
irb(main):018:0> user.save!
=> true

# 退出
irb(main):019:0> exit

# 退出容器
root@339d2c526e93:/# exit
```

### 6. 创建token
```
preferences --> access tokens 
```

### 7. 创建token
