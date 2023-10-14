pipelineJob('hotspot/build-hotspot-bkservice') { 
	displayName("Build Hotspot Services (JDK8)")
	description("Hotspot Services builder pipeline")

	parameters {
        choice(name: 'ENV', choices: ['dev', 'qa', 'int'], description: 'Choose target environment')
        choice(name: 'NAMESPACE', choices: ['ctad-dev-amp-core', 'ctad-qa-amp-core', 'ctad-int-amp-core'], description: 'Choose namespace')
        choice(name: 'ACTION', choices: ['Scale Up', 'Scale Down'], description: 'Choose scaling action')
        // This choice will be dynamically populated later based on the chosen environment
        choice(name: 'DEPLOYMENT', choices: [], description: 'Choose deployment to scale')
        string(name: 'REPLICAS', defaultValue: '1', description: 'Replica count for scaling up')
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





