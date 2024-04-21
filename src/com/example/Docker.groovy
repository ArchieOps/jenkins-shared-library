#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buiidDockerImage(String imageName) {
        script.echo "building the Docker image..."
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-cred', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "Docker build -t $imageName ."
            script.sh "echo $script.PASS | Docker login -u $script.USER --password-stdin"
            script.sh "Docker push $imageName"
        }
    }
}
