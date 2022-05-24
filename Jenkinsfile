pipeline {
	agent any
	stages {
		stage("stage1-build") {
			steps {
				mvn clean install; java -jar target/*.jar;
			}
		}
	}
}