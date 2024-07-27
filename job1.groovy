pipeline{
    
    agent any
    
    
    stages{
        
        stage('Clone the repo')
        {
            steps{
                git branch: 'main', url: 'https://github.com/tuxwow/caltech-CMAT-CEP-1.git'
            }
        }
        
        stage('Execute playbook-install')
        {
            steps{
              ansiblePlaybook credentialsId: 'ansible1', disableHostKeyChecking: true, installation: 'myansible', inventory: 'dev.inv', playbook: 'playbook-Install.yml', vaultTmpPath: '' 
            }
        }
        
          stage('Execute playbook-CICD')
        {
            steps{
              ansiblePlaybook credentialsId: 'ansible1', disableHostKeyChecking: true, installation: 'myansible', inventory: 'dev.inv', playbook: 'playbook-CICD.yml', vaultTmpPath: ''
            }
           
        }
    }
    
}
