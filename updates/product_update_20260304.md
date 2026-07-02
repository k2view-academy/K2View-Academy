### The K2cloud January 2026 Release

You can now configure a custom Certificate Authority (CA) certificate for your Git repository directly at the project level in K2cloud Orchestrator. 

This is required if your Git repository (GitHub, GitLab, Bitbucket, or private Git server) uses:

* An internal CA
* A self-signed certificate
* A corporate PKI What this enables 

Once configured 

* Git operations (clone, pull, deploy) work automatically
* The certificate is applied to all spaces in the project
* You configure it once per project, not per environment 

No manual truststore changes or per-space workarounds are required. 

<img src="images/img.png" alt="image" style="zoom: 70%;" />
