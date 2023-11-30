pipelineJob('cta/prismascan-crui-services') {
    displayName("Prisma Scan - CRUI Services")
    description("CRUI Services security scanner")

   

    definition {
        cpsScm {
            scm {
                git {
                    branch('master')
                    remote {
                        credentials('Github')
                        url('https://github.com/Arunkm039/DevOpsTest.git')
                    }
                    scriptPath(Jenkinsfile.prismascan")
                }
            }
        }
    }
}
