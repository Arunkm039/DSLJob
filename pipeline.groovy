pipelineJob('hotspot/build-hotspot-bkservice') { 
	displayName("Build Hotspot Services (JDK8)")
	description("Hotspot Services builder pipeline")

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

	definition{
		cpsScm {
			scm {
				git {			
					branch ('master')
					remote {
						credentials ('Github')
						url('https://github.com/Arunkm039/DevOpsTest.git')
					}
				}
				scriptPath("Jenkinsfile")
			}	
		}  	
	}
}	




