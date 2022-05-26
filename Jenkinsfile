pipeline {
    agent any

    stages {
        agent {docker}
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build .'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}