pipeline {
    agent none
    stages {
        stage('Build') {
            agent { docker 'maven:3.6.3-jdk-8' }
            steps {
                echo 'Hello, Maven'
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}