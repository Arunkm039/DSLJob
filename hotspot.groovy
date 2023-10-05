def jobName = 'hotspot-worker'
def jobDisplayName = 'Hotspot Worker'
def repo = 'DevOpsTest'
def includeBranchPattern = 'master feature/*'
def org = 'Arunkm039'
def credentials = 'Github'
def jenkinsfilePath = 'Jenkinsfile'

multibranchPipelineJob("hotspot/compute-engine/dev-qa/${jobName}") {

    displayName("${jobDisplayName} DEV QA Build")

    properties([
    parameters([
	    	choice(name: 'ENV', choices: ['dev', 'qa', 'int'], description: 'Choose target environment'),	    
			
	    	booleanParam ( 			
			name: 'RUN_TESTS',
			defaultValue: 'true',
			description: 'Check to run test'
		),
		booleanParam ( 			
			name: 'SONAR_SCAN',
			defaultValue: 'true',
			description: 'Check to run SonarQube scan -> automatically runs tests for coverage report'
		)        
        
    	])
])	

    branchSources {

        github {
            id("${credentials}")
            apiUri("https://api.github.com")
            scanCredentialsId("${credentials}")
            repoOwner("${org}")
            repository("${repo}")
            includes("${includeBranchPattern}")
            buildOriginPRMerge(true)
        }

    }

    description("Multibranch pipeline build for ${jobDisplayName}")

    factory {
        workflowBranchProjectFactory {
            scriptPath("${jenkinsfilePath}")
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(5)
        }
    }

}
