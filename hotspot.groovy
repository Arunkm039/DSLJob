def jobName = 'hotspot-worker'
def jobDisplayName = 'Hotspot Worker'
def repo = 'DevOpsTest'
def includeBranchPattern = 'master feature/*'
def org = 'Arunkm039'
def credentials = 'Github'
def jenkinsfilePath = 'Jenkinsfile'

pipelineJob("hotspot/compute-engine/dev-qa/${jobName}") {

    displayName("${jobDisplayName} DEV QA Build")

    parameters {
		choiceParam {
			name ("ENV")
			description ("Choose target environment")
			choices (['dev', 'qa', 'int'])
		}
		gitParameter {
			name("GIT_BRANCH_TAG")
			description("Available git branches")
			type("PT_BRANCH_TAG")
			defaultValue("")
			branch("*")
			branchFilter(".*")
			tagFilter("*")
			sortMode("DESCENDING_SMART")
			selectedValue("DEFAULT")
			useRepository("hotspot-backend-services.git")
			quickFilterEnabled(true)
			listSize("0")
		}		
		
		booleanParam {		
			name("RUN_TESTS")
			defaultValue(true)
			description("Check to run tests")
		}
		booleanParam {			
			name("SONAR_SCAN")
			defaultValue(true)
			description("Check to run SonarQube scan -> automatically runs tests for coverage report")
		}
	}

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
