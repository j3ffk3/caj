pipeline {
  agent {
    kubernetes {
      defaultContainer 'maven'
      yamlFile 'jenkins/maven-agent.yml'
    }
  }

  stages {
    stage('Run Tests') {
      when {
        expression { params.SKIP_TESTS == false }
      }
      steps {
        container('maven') {
          sh 'mvn -version'

          dir("${PROJECT_NAME}") {
            sh '''#!/bin/bash
            export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
            export PATH=$PATH:$JAVA_HOME/bin

            ./gradlew \
                -Dhttp.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttp.proxyPort=3128 \
                -Dhttps.proxyHost=proxy.penguin.rhtw.kubedev.org -Dhttps.proxyPort=3128 \
                clean test
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
      when {
        expression { params.SKIP_CODE_SCAN == false }
      }
      steps {
        container('maven') {
          dir("${PROJECT_NAME}") {
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
                    -Dsonar.host.url=${SONARQUBE_URL} \
                    -Dsonar.login=${USERNAME} \
                    -Dsonar.password=${PASSWORD} \
                    -Dsonar.projectName=${STUDENT_ID}-${PROJECT_NAME} \
                    -Dsonar.projectKey=${STUDENT_ID}-${PROJECT_NAME}
              '''
            }
          }
        }
      }
    }

    stage('Build Artifact') {
      steps {
        container('maven') {
          dir("${PROJECT_NAME}") {
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
          dir("${PROJECT_NAME}") {
            sh 'podman version'
            sh 'podman build -f Dockerfile-openjdk -t ${REGISTRY_URL}/${REGISTRY_NAMESPACE}/${PROJECT_NAME}:latest .'
            sh 'podman images'
          }
        }
      }
    }

    stage('Image Scan') {
      when {
        expression { params.SKIP_IMAGE_SCAN == false }
      }
      steps {
        container('podman') {
          sh '''#!/bin/bash
          cd
          curl -L https://github.com/aquasecurity/trivy/releases/download/v0.35.0/trivy_0.35.0_Linux-64bit.tar.gz -o - | tar -xz
          
          podman save ${REGISTRY_URL}/${REGISTRY_NAMESPACE}/${PROJECT_NAME}:latest > ${PROJECT_NAME}-latest.tar.gz
          ./trivy --debug image --timeout 15m0s --severity HIGH,CRITICAL --input ${PROJECT_NAME}-latest.tar.gz
          '''
        }
      }
    }

    stage('Push Image') {
      steps {
        container('podman') {
          withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDS_ID}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            sh 'podman login docker.io -u ${USERNAME} -p ${PASSWORD}'
          }
         
          sh 'podman push ${REGISTRY_URL}/${REGISTRY_NAMESPACE}/fare:latest'
        }
      }
    }

    stage('Deploy to OCP4') {
      steps {
        container('ose-cli') {
          sh '''#!/bin/bash
          set -eu

          oc project ${STUDENT_ID}
          # oc set image deploy/caj-${PROJECT_NAME} caj-${PROJECT_NAME}=${REGISTRY_URL}/${REGISTRY_NAMESPACE}/${PROJECT_NAME}:latest
          oc rollout restart deploy/caj-${PROJECT_NAME}
          '''
        }
      }
    }
  }
}