pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Hello, Maven'
                'docker-compose up --build'
            }
        }
    }
}