String gitCredentials = 'gitId'
String repoUrl = 'https://github.com/Lakkahutta/gatling_training.git'
node {

        stage('pulLatestCode'){

                git branch: 'main',

                 credentialsId: gitCredentials,

                 url: repoUrl

        }


        stage("Build Maven") {
        
                sh 'mvn -B clean package'
        
        }
        
        stage("Run Gatling") {
        
                sh 'mvn clean gatling:test -DTrainingTaskUsers=100'
        }

         stage("Archive Load Test Results") {
        
                    gatlingArchive()

        }

}

