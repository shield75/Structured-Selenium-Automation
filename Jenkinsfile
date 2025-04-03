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
				bat 'docker build -t=tonu75/selenium-docker:latest .'
            }
        }

        stage('Push Image'){
			environment{
				DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
				bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                bat 'docker push tonu75/selenium-docker:latest'
                bat "docker tag tonu75/selenium-docker:latest tonu75/selenium-docker:${env.BUILD_NUMBER}"
                bat "docker push tonu75/selenium-docker:${env.BUILD_NUMBER}"
            }
        }

    }

    post {
		always {
			bat 'docker logout'
        }
    }

}