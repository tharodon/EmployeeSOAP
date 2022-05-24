pipeline {
    agent none
    stages {
        stage('Build') {
            agent { docker 'maven:3.6.3-jdk-1.8' }
            steps {
                echo 'Hello, Maven'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Run') {
            agent { docker 'openjdk:8.0.0-jdk' }
            steps {
                echo 'Hello, JDK'
                sh 'java -jar target/EmployeeSOAP-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}