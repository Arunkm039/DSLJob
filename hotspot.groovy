def jobName = 'hotspot-worker'
def jobDisplayName = 'Hotspot Worker'
def repo = 'DevOpsTest'
def includeBranchPattern = 'master feature/*'
def org = 'Arunkm039'
def credentials = 'Github'
def jenkinsfilePath = 'Jenkinsfile'

multibranchPipelineJob("hotspot/compute-engine/dev-qa/${jobName}") {

    displayName("${jobDisplayName} DEV QA Build")

    parameters {
		choiceParam {
			name ("ENV")
			description ("Choose target environment")
			choices (['dev', 'qa', 'int'])
		}		

		choiceParam {
			name ("COMPONENT")
			description("Choose component")
			choices (['scheduler', 'worker', 'manager', 'model-worker'])
		}

		choiceParam {
			name ("NAMESPACE")
			description ("Choose namespace")
			choices (['ctad-dev-amp-core', 'ctad-qa-amp-core', 'ctad-int-amp-core'])
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
