pipeline {
    agent none
    stages {
        stage('Build') {
        agent {docker{image 'maven:3.6.3-jdk-8'}}
            steps {
                echo 'Hello, Maven'
                sh 'mvn -B -DskipTests clean package'
                sh 'docker-compose up --build'
                }
            }
        }
        stage('Run') {
        agent any
        steps {
                sh 'docker-compose up --build'
         }
     }
   }

