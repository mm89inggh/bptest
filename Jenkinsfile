pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker { 
			image 'maven:3-alpine' 
		}
            }
            steps {
                sh 'mvn -B -DskipTests -f spring-server clean package'
            }
        }
	stage('Test') {
            agent {
                docker { 
			image 'maven:3-alpine' 
		}
            }
            steps {
                sh 'mvn -f spring-server test'
            }
            post {
                always {
                    junit 'spring-server/target/surefire-reports/*.xml'
                }
            }
        }
	stage('Image') {
            docker.build("mm89ingdk/devops:1.0.0")
	}	
    }
}
