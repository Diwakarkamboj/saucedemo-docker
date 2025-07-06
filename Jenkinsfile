pipeline {
    agent any

    environment {
        DOCKER_HUB = credentials('dockerhub-creds')
    }

    stages {
        stage('Build Jar') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                bat 'docker build -t diwakar15/sauced:latest .'
            }
        }
<<<<<<< HEAD
        
        stage('Push Image'){
			enviorment{
				DOCKER_HUB = credentials('dockerhub-creds')
			}
            steps{
				bat "docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%"
                bat "docker push diwakar15/sauced:latest"
=======

        stage('Push Image') {
            steps {
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat 'docker push diwakar15/sauced:latest'
>>>>>>> 60a1486ab82694b1a729e106ba381919d82f5caf
                bat "docker tag diwakar15/sauced:latest diwakar15/sauced:${env.BUILD_NUMBER}"
                bat "docker push diwakar15/sauced:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            bat 'docker logout'
        }
    }
}
