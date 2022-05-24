pipeline {
	agent any
	stages {
		stage('Run') {
		agent{docker 'openjdk:8'}
			steps {
				sh 'java -jar target/EmployeeSOAP-0.0.1-SNAPSHOT.jar'
			}
		}
	}
}