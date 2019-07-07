pipeline {
    agent any
    environment {
    	registry = "mm89ingdk/bp-devops"
    	registryCredential = '82687000-e270-4d1d-a4cb-787a73b93faa'
    }
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
            steps {
		script {
          		docker.build -f spring-server registry + ":$BUILD_NUMBER"
        	}
	    }
	}	
    }
}
