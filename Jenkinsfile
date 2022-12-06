pipeline {
  agent {
    kubernetes {
      defaultContainer 'maven'
      yamlFile 'jenkins/maven-agent.yml'
    }
  }

  stages {
    stage('Run tests') {
      steps {
        container('maven') {
          sh 'mvn -version'

          dir('fare') {
            sh 'pwd'
            sh 'ls -lah'

            sh '''#!/bin/bash
            export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
            export PATH=$PATH:$JAVA_HOME/bin

            ./gradlew \
                -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                clean test --info 

            ls -la build/reports/tests/test
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

    stage('Code scan') {
      steps {
        container('maven') {
          dir('fare') {
            sh '''#!/bin/bash
            export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
            export PATH=$PATH:$JAVA_HOME/bin

            ./gradlew \
                -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                "-Dhttp.nonProxyHosts=*.rhtw.kubedev.org|localhost" \
                "-Dhttps.nonProxyHosts=*.rhtw.kubedev.org|localhost" \
                clean test --info 
            '''
        }
      }
    }

    stage('Run podman') {
      steps {
        container('podman') {
          sh 'podman version'
        }
      }
    }
  }
}