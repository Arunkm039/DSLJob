pipelineJob('cta-crui/tools/prismascan-crui-services') {
    displayName("Prisma Scan - CRUI Services")
    description("CRUI Services security scanner")

    parameters {
        imageTag {
            name('VERSION')
            description("Select CRUI Services version to scan.")
            image('crui/crui-services')
            filter('.*')
            registry('https://prodcdata5pwl.azurecr.io')
            credentialId('adp-tools-cta-devops-team-azuretadacredential')
        }
		imageTag {
            name('VERSION2')
            description("Select CRUI Services version to scan.")
            image('crui/crui-services2')
            filter('.*')
            registry('https://prodcdata5pwl.azurecr.io')
            credentialId('adp-tools-cta-devops-team-azuretadacredential')
        }
    }

    definition {
        cpsScm {
            scm {
                git {
                    branch('jenkins')
                    remote {
                        credentials('github')
                        url('https://github.com/Arunkm039/DevOpsTest.git')
                    }
                    scriptPath("Jenkinsfile")
                }
            }
        }
    }
}
