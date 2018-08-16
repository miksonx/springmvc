node {

    checkout scm

    env.DOCKER_API_VERSION="1.23"
    
    sh "git rev-parse --short HEAD > commit-id"

    tag = readFile('commit-id').replace("\n", "").replace("\r", "")
    appName = "todos"
    registryHost = "127.0.0.1:30400/"
    imageName = "${registryHost}${appName}:${tag}"
    env.BUILDIMG=imageName


    stage('Build application'){
    	build 'SpringPackage'
    }
    stage('Build Docker') {
    	sh "docker build -t ${imageName} ."
	}
   
    stage "Push"

        sh "docker push ${imageName}"

    stage "Deploy"
        
        sh "sed 's#127.0.0.1:30400/todos:latest#'$BUILDIMG'#' deployment.yaml | kubectl apply -f -"
        
}