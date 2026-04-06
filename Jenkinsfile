pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'make.bat build'
            }
        }

        stage('Docker Image Build') {
            steps {
                bat 'make.bat docker-build'
            }
        }

        stage('Deploy Docker Container') {
            steps {
                // Example of running the container; usually you'd deploy it to a remote host/registry
                bat 'make.bat docker-run'
            }
        }
    }
}
