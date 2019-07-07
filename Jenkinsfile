pipeline {
    agent {
    	label 'docker' 
    }
    agent {
        docker {
	    label 'docker'
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
	stage('Build') {
	      agent {
		docker {
		  label 'docker'
		  image 'maven:3-alpine'
		  args '-v /root/.m2:/root/.m2' // list any args
		}
	      }
	      steps {
                sh 'mvn -B -DskipTests clean package'
              }
	 }



  
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
    }
}
