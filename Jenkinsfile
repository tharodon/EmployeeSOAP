pipeline {
    agent any
    stages {
        stage('Build') {
        agent{docker 'maven:3.6.3-jdk-8'}
            steps {
                echo 'Building..'
                sh 'mvn clean package'
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