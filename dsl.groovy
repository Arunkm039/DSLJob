multibranchPipelineJob('my-multibranch-pipeline') {
    branchSources {
        git {
            id('Github') // unique id
            remote('https://github.com/Arunkm039/DevOpsTest.git')
            credentialsId('https://github.com/Arunkm039/DevOpsTest.git') // Optional, if you have credentials
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(30) 
        }
    }
}
