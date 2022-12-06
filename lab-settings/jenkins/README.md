# Jenkins Server Settings

To run Jenkins LTS server via Podman:

```sh
$ yum install -y podman
$ podman run -d \
  -v /var/lib/jenkins:/var/jenkins_home \
  -p 8080:8080 \
  -p 50000:50000 \
  -e LC_CTYPE=n_US.UTF-8 -e PYTHONIOENCODING=UTF-8 \
  -e JAVA_OPTS=-Dfile.encoding=UTF8 -e JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8 \
  -e LC_ALL=en_US.UTF-8 -e LANG=en_US.UTF-8 \
  --name jenkins-server \
  docker.io/jenkins/jenkins:lts-jdk11

$ podman generate systemd jenkins-server > /etc/systemd/system/container-jenkins.service
$ systemctl enable container-jenkins --now
```

Next, go to plugin manager page, and then install the [Role-based Authorization Strategy](https://plugins.jenkins.io/role-strategy/) plugin on Jenkins server. And then execute as the following scripts in Jenkins script console.

1. Create student's user via `create-users.groovy`.
2. Create student's folder via `create-folders.groovy`.
3. Create roles and assign it to users via `create-assign-roles.groovy`.

Check the following screenshots:

![](https://i.imgur.com/MwbkPC7.png)

![](https://i.imgur.com/w8GK8mq.png)