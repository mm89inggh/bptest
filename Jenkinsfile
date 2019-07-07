pipeline {
    agent none
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
        }	
    }
}
