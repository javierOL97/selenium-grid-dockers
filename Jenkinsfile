pipeline {

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                bat "docker build -t=javierol97/selenium-grid-docker"
            }
        }

        stage('Push Image'){
            steps{
                bat "docker push javierol97/selenium-grid-docker"
            }
        }
    }

    post{
        always {
            echo "doing clean"
        }
    }

}