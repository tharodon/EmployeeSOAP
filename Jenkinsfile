pipeline {
    agent any
    stages {
        stage('Build') {
            agent any { docker 'maven:3.6.3-jdk-8' }
            steps {
                echo 'Hello, Maven'
            }
        }
    }
}