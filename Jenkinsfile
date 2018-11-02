node('master') {
  def workspace = pwd()

  stage 'Git pull'
  git branch: 'develop', 
  credentialsId: 'GIT-HUB',
  url: 'https://github.com/sjupally/apartment-service.git'

  echo workspace
  stage 'Builing'
  def mvnHome = tool name: 'Maven3', type: 'hudson.tasks.Maven$MavenInstallation'
  sh "cd ${workspace}/; ${mvnHome}/bin/mvn install -Pdevelopment"
  sh "cd ${workspace}"
}