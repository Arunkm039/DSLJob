def jobName = 'hotspot-worker'
def jobDisplayName = 'Hotspot Worker'
def repo = 'DevOpsTest'
def includeBranchPattern = 'master feature/*'
def org = 'Arunkm039'
def credentials = 'Github'
def jenkinsfilePath = 'Jenkinsfile'

multibranchPipelineJob("hotspot/compute-engine/dev-qa/${jobName}") {

    displayName("${jobDisplayName} DEV QA Build")
		

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
	    parameters {
                choiceParam('ENV', ['dev', 'qa', 'int'], 'Description for choice parameter')
                gitParameter(name: 'GIT_BRANCH_TAG', type: 'PT_BRANCH', branch: '*', defaultValue: '""', description: 'Git Parameter')  // Assuming you have Git Parameter plugin
                booleanParam('RUN_TESTS', true, 'unit test')
                booleanParam('SONAR_SCAN', false, 'sonar scan')
            }
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(5)
        }
    }

}
