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
			name ("NAMESPACE")
			description ("Choose namespace")
			choices (['ctad-dev-amp-core', 'ctad-qa-amp-core', 'ctad-int-amp-core'])
		}

		choiceParam {
			name ("ACTION")
			description ("Choose action")
			choices (['Scale Up', 'Scale Down'])
		}

		stringParam {
			name ("REPLICAS")
			description ("Choose replica count")
			defaultValue ('1')			
		}

		stringParam {
			name ("DEPLOYMENT")
			description ("Choose replica count")
			defaultValue ('')
			description ("Choose deployment. Dev: [hotspot-api-dask-scheduler, hotspot-api-worker], QA: [hotspot-mq-scheduler, hotspot-mqworker-maxaccumulation], Int: [hotspot-mqworker-scenario, hotspot-mqworker-visualization]")

		}

		/*

		choiceParam {
		    name ("DEPLOYMENT_DEV")
		    description ("Choose deployment for Dev environment")
		    choices (['hotspot-api-dask-scheduler', 'hotspot-api-worker', 'dev-worker'])
		}

		choiceParam {
		    name ("DEPLOYMENT_QA")
		    description ("Choose deployment for QA environment")
		    choices (['hotspot-mq-scheduler', 'hotspot-mqworker-maxaccumulation', 'qa-worker'])
		}
		
		choiceParam {
		    name ("DEPLOYMENT_INT")
		    description ("Choose deployment for Int environment")
		    choices (['hotspot-mqworker-scenario', 'hotspot-mqworker-visualization', 'int-worker'])
		}
  		*/
		

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
				scriptPath("Jenkinsfile.scale")
			}	
		}  	
	}
}	





