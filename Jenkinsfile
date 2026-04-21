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
                script {
                    if (isUnix()) {
                        sh 'make build'
                    } else {
                        bat 'make.bat build'
                    }
                }
            }
        }

        stage('Docker Image Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'make docker-build'
                    } else {
                        bat 'make.bat docker-build'
                    }
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                // Example of running the container; usually you'd deploy it to a remote host/registry
                script {
                    if (isUnix()) {
                        sh 'make docker-run'
                    } else {
                        bat 'make.bat docker-run'
                    }
                }
            }
        }
    }
}
