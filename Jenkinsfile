pipeline {
    agent none
    stages {
        stage('Build') {
        agent any
            steps {
                echo 'Hello, Maven'
                sh 'mvn -B -DskipTests clean package'
                sh 'docker-compose up --build'
                }
            }
        }
}

