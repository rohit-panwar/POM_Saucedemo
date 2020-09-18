pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'jenkins_maven_3_6_3') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage- Smoke Test') {

            steps {
                withMaven(maven : 'jenkins_maven_3_6_3') {
                    bat 'mvn test -DsuiteXmlFile="./smoke_test_testng.xml"'
                }
            }
        }
        
        stage ('Testing Stage- Regression Test') {

            steps {
                withMaven(maven : 'jenkins_maven_3_6_3') {
                    bat 'mvn test -DsuiteXmlFile="./testng.xml"'
                }
            }
        }

    }
        
}