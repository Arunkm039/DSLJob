pipelineJob('hotspot/build-hotspot-bkservice') { 
	displayName("Build Hotspot Services (JDK8)")
	description("Hotspot Services builder pipeline")	

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





