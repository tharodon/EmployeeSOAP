pipeline {
    agent {docker {image 'maven:3.6.3-jdk-8'}}
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -version'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
