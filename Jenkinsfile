pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                bat 'docker build -t=diwakar15/sauced .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                // There might be a warning.
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
               //the below step will work in linux only so ignore warning in windows
               // bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
                bat 'docker push diwakar15/sauced'
            }
        }

    }

    post {
        always {
            bat 'docker logout'
        }
    }

}