pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps{
                git branch: 'EmployeePdf',
                    url: 'https://github.com/tharodon/EmployeeSOAP.git'
                }
        }
        stage('Build docker image') {
            steps{
                    sh 'docker build -t tharodon/employee:0.1 .'
            }
        }
        stage('Push docker image to DockerHub') {
            steps{
                withDockerRegistry(credentialsId: 'docker-hub', url: 'https://index.docker.io/v1/') {
                    sh '''
                        docker push tharodon/employee:0.1
                    '''
                }
            }
        }
        stage('Deploy') {
                    steps {
                  sshagent(['ubuntu-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no tharodon@192.168.1.7 bash pull_run_mage.sh'
                  }
              }
                }
    }
}