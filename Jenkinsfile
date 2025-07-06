pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                bat "docker build -t=diwakar15/sauced ."
            }
        }
        
        stage('Push Image'){
			enviorment{}
            steps{
                bat "docker push diwakar15/sauced:latest"
                bat "docker tag diwakar15/sauced:latest diwakar15/sauced:${env.BUILD_NUMBER}"
                bat "docker push diwakar15/sauced:${env.BUILD_NUMBER}"
            }
        }

    }

}