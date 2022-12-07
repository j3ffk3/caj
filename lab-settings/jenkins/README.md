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

The below will show you how to setup Kubernetes Dynamic agents:

1. First, to install the [Kubernetes Plugin](https://plugins.jenkins.io/kubernetes/) from Jenkins Dashboard's plugin page.
2. Using the OpenShift Command Line Tool (CLI), login to OpenShift as and create a new project called 'devops-system', and then create a new service account called jenkins:

```sh
$ oc new-project devops-system
$ oc -n devops-system create serviceaccount jenkins
$ oc adm policy add-cluster-role-to-user edit system:serviceaccount:devops-system:jenkins
$ oc -n devops-system serviceaccounts get-token jenkins 
```

3. Copy the token, and then do the following steps. Go to `Dashboard > Manage Jenkins > Manage nodes and clouds > Configure Clouds` page.
  * Configure Kubernetes URL and Namespace.

![](https://i.imgur.com/13zqH1R.png)

  * Add a `Secret text` credential for accessing Kubernetes API server. Paste the token into Secret field.

![](https://i.imgur.com/AQNFW2c.png)

  * Test Kubernetes API connection.

![](https://i.imgur.com/6wvE2Ji.png)

  * Configure a Pod template.

![](https://i.imgur.com/TGIRmoh.png)

![](https://i.imgur.com/w4h7qLz.png)

![](https://i.imgur.com/MrH9O4U.png)

  * Create a Pipeline and test the below content:

```groovy
node('openjdk11') {
  stage('Hello') {
    echo 'Hello World'
    sh 'java -version'
  }
}
```

![](https://i.imgur.com/tcgeTwW.png)