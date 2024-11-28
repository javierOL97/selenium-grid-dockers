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
                bat "docker build -t=javierol97/selenium-grid-docker ."
            }
        }

        stage('Push Image'){
        	environment{
        		DOCKER_HUB = credentials('dockerhub-creds')
        	}
            steps{
            	bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push javierol97/selenium-grid-docker"
            }
        }
    }

    post{
        always {
            bat "docker logout"
        }
    }

}