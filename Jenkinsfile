pipeline {
  agent {
    kubernetes {
      defaultContainer 'maven'
      yamlFile 'jenkins/maven-agent.yml'
    }
  }

  stages {
    stage('Run Tests') {
      steps {
        container('maven') {
          sh 'mvn -version'

          dir('fare') {
            sh '''#!/bin/bash
            export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
            export PATH=$PATH:$JAVA_HOME/bin

            ./gradlew \
                -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                clean test --info 
            '''

            publishHTML(target: [
              allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: true,
              reportDir: 'build/reports/tests/test',
              reportFiles: 'index.html',
              reportName: "Test Report"
            ])
          }
        }
      }
    }

    stage('Code Scan') {
      steps {
        container('maven') {
          dir('fare') {
            withCredentials([usernamePassword(credentialsId: 'sonarqube-basic-auth', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
              sh '''#!/bin/bash
              export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
              export PATH=$PATH:$JAVA_HOME/bin

              ./gradlew \
                  -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                  -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                  "-Dhttp.nonProxyHosts=*.rhtw.kubedev.org|localhost" \
                  "-Dhttps.nonProxyHosts=*.rhtw.kubedev.org|localhost" \
                  sonarqube \
                    -Dsonar.host.url=http://sonarqube-speaker02.apps.penguin.rhtw.kubedev.org \
                    -Dsonar.login=${USERNAME} \
                    -Dsonar.password=${PASSWORD} \
                    -Dsonar.projectName=${STUDENT_ID}-fare \
                    -Dsonar.projectKey=${STUDENT_ID}-fare
              '''
            }
          }
        }
      }
    }

    stage('Build Artifact') {
      steps {
        container('maven') {
          dir('fare') {
            sh '''#!/bin/bash
            export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
            export PATH=$PATH:$JAVA_HOME/bin

            ./gradlew \
                -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                clean build
            '''
          }
        }
      }
    }

    stage('Build Image') {
      steps {
        container('podman') {
          dir('fare') {
            sh 'podman version'
            sh 'podman build -f Dockerfile-openjdk -t docker.io/kairen/fare:latest .'
            sh 'podman images'
          }
        }
      }
    }

    stage('Image Scan') {
      steps {
        container('podman') {
          sh '''#!/bin/bash
          cd
          wget -c https://github.com/aquasecurity/trivy/releases/download/v0.35.0/trivy_0.35.0_Linux-64bit.tar.gz -O - | tar -xz

          ./trivy image docker.io/kairen/fare:latest
          '''
        }
      }
    }
  }
}