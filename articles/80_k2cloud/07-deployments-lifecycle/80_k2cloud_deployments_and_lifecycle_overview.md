# Deployments and Lifecycle Overview

## Overview

K2cloud Orchestrator provides a built-in workflow for moving Project changes from development into Fabric runtime Spaces.

The workflow combines:

- Fabric Studio for development,
- Git for versioned Project content and environment definitions,
- and K2cloud Orchestrator for deployment to Fabric Spaces.

A typical deployment flow is:

```text
Studio Space
    ↓
Develop and validate
    ↓
Commit and tag in Git
    ↓
Deploy Environment
    ↓
Activate Environment
    ↓
Deploy Project
    ↓
Validate Runtime
```

This provides a straightforward deployment path without requiring an external CI/CD pipeline.

## Git as the Source of Deployment Content

K2cloud Projects are Git-backed.

Git provides the versioned source for:

- Project content,
- environment definitions,
- and version history.

K2cloud does not replace Git as the source of deployment content. Instead, the K2cloud Orchestrator coordinates deployment from Git into Fabric runtime Spaces.

Git tags allow the operator to identify the version of the content to deploy.

## Studio and Runtime Spaces

Studio and Fabric Spaces have different roles in the deployment lifecycle.

**Studio Spaces** are primarily used to:

- develop Project content,
- configure environments,
- test changes,
- and commit changes to Git.

**Fabric Spaces** are runtime-oriented and are commonly used for:

- QA,
- staging,
- production,
- and other runtime environments.

Deployment operations are available from the Fabric Space action menu.

## Deploy the Environment

Before deploying Project content, deploy the appropriate environment definition to the Fabric Space.

The operator selects the Git tag containing the version of the environment definition to deploy.

After the environment definition has been deployed, the environment is selected and **activated in Fabric Admin**.

This establishes the runtime environment configuration that Fabric will use.

For the detailed procedure, see [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md).

## Deploy the Project

After the environment is active, Project content can be deployed from Git.

The operator selects the Git tag and can deploy:

- the complete Project,
- or selected Logical Units (LUs).

This allows the deployment scope to be controlled based on the changes being promoted.

For the detailed procedure, see [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md).

## Validate the Runtime

After deployment, validate that the runtime environment is operating as expected.

Validation can include:

- confirming the active environment,
- verifying the deployed Project or Logical Units,
- testing APIs and integrations,
- and reviewing runtime behavior.

K2cloud SaaS customers can also use the K2cloud monitoring and log capabilities for additional runtime visibility.

K2cloud Self-Hosted customers use the observability and monitoring capabilities provided by their own infrastructure environment.

## CI/CD and Automation

The built-in K2cloud deployment workflow is appropriate for organizations that want a direct operational path from Git to Fabric runtime Spaces.

Organizations with more elaborate software delivery requirements can also integrate deployment into CI/CD and API-driven workflows.

See [CI/CD and API Automation](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_ci_cd_and_api_automation.md).

## Related Documentation

- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md)
- [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
