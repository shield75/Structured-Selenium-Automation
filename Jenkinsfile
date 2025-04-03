pipeline{

	agent any

    stages{

		stage('Build Jar'){
			steps{
				sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
			steps{
				sh 'docker build -t=tonu75/selenium-docker:latest .'
            }
        }

        stage('Push Image'){
			environment{
				DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
				sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push tonu75/selenium-docker:latest'
                sh "docker tag tonu75/selenium-docker:latest tonu75/selenium-docker:${env.BUILD_NUMBER}"
                sh "docker push tonu75/selenium-docker:${env.BUILD_NUMBER}"
            }
        }

    }

    post {
		always {
			sh 'docker logout'
        }
    }

}