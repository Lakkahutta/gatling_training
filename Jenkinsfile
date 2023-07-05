String gitCredentials = 'gitId'
String repoUrl = 'https://github.com/Lakkahutta/lighthouse_training.git'
node {

stage("Build Maven") {

        sh 'mvn -B clean package'

}

stage('pullLatestCode'){

         git branch: 'main',

         credentialsId: gitCredentials,

         url: repoUrl

}

stage("Run Gatling") {

    steps {

        sh 'mvn clean gatling:test -DTrainingTaskUsers=100'

    }

    post {

        always {

            gatlingArchive()

        }

    }

}

}

