import jenkins.model.Jenkins

// Get the Jenkins instance
def jenkinsInstance = Jenkins.instance

// Get all jobs in Jenkins
def allJobs = jenkinsInstance.getAllItems()

// Print the full names of all jobs
allJobs.each { job ->
    println "Job Full Name: ${job.getFullName()}"
}

// Example Pipeline that lists job names in a choice parameter
pipeline {
    agent any
    parameters {
        choice(
            name: 'JENKINS_JOB',
            choices: allJobs.collect { it.getFullName() },
            description: 'Select a Jenkins job'
        )
    }
    stages {
        stage('Echo Selected Job') {
            steps {
                echo "Selected Jenkins job: ${params.JENKINS_JOB}"
            }
        }
    }
}
