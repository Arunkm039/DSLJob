multibranchPipelineJob('my-multibranch-pipeline') {
    branchSources {
        git {
            id('Github') // unique id
            remote('https://github.com/Arunkm039/DevOpsTest.git')
            credentialsId('https://github.com/Arunkm039/DevOpsTest.git') // Optional, if you have credentials

            traits {
                gitHubBranchDiscovery {
                    strategyId(3) // Discover branches, but omit PRs
                }
                gitHubPullRequestDiscovery {
                    strategyId(1) // Discover PRs and merge them with the current target branch
                }
                gitHubPullRequestFilter {
                    excludeDraftRequest(true)
                }
                cron("H/2 * * * *") // Poll SCM every 2 minutes
            }
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(30) 
        }
    }
}
