pipeline {
    environment {
      IMAGE_BASE = 'demo'
      IMAGE_TAG = "v$BUILD_NUMBER"
      DOCKER_REGISTRY_LOGIN = "exploit243"
      IMAGE_NAME = "${env.DOCKER_REGISTRY_LOGIN}/${env.IMAGE_BASE}:${env.IMAGE_TAG}"
      IMAGE_NAME_LATEST = "${env.IMAGE_BASE}:latest"
      DOCKERFILE_NAME = "Dockerfile"
    }
    agent none
    stages {
        stage("Prepare container") {
          agent {
            docker {
              image 'openjdk:11.0.5-slim'
              args '-v $HOME/.m2:/root/.m2'
            }
          }
          stages {
            stage('Build') {
              steps {
                checkout scm
                sh './mvnw compile'
              }
            }
            stage('Package') {
              steps {
                sh './mvnw package -DskipTests'
              }
            }
          }
        }
        stage('Push images') {
          agent any
    //       when {
    //         branch 'master'
    //       }
          steps {
            script {
              def dockerImage = docker.build("${env.IMAGE_NAME}", "-f ${env.DOCKERFILE_NAME} .")
              docker.withRegistry('https://docker.io/exploit243/demo', 'docker') {
                dockerImage.push()
                dockerImage.push("latest")
              }
              echo "Pushed Docker Image: ${env.IMAGE_NAME}"
            }
            sh "docker rmi ${env.IMAGE_NAME} ${env.IMAGE_NAME_LATEST}"
          }
        }
    }

}