pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
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
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }	
    }
}
