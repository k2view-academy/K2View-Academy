# Deploy Environments

## Overview

Use **Deploy Environments** to deploy an environment definition from Git to a Fabric Space.

Environment definitions are stored and versioned with the Project in Git. The Git tag identifies the version of the environment definition to deploy.

After deployment, the appropriate environment must be **activated in Fabric Admin** before deploying the Project content that will use it.

## Before You Begin

Before deploying an environment:

- confirm that the required environment definition has been committed to Git,
- identify the Git tag containing the version to deploy,
- confirm the target Fabric Space,
- and identify the environment designation to activate, such as `prod`.

## Deploy the Environment

1. Open the **Spaces** page in the K2cloud Orchestrator.
2. Locate the target Fabric Space.
3. Open the Space action menu (**...**).
4. Select **Deploy Environments**.
5. Select the required **Git Tag**.
6. Start the deployment.

K2cloud retrieves the selected version of the environment definition from the Project Git repository and deploys it to the Fabric Space.

## Git Tag

The **Git Tag** identifies the version of the environment definition stored in Git.

Using a tag allows the environment configuration deployed to a runtime Space to correspond to a known version of the Project repository.

The Git tag is selected as part of the deployment operation; it is not a persistent Project or Space Profile setting.

## Environment Designation

An environment definition can contain configuration for different runtime environments.

The environment designation identifies the configuration that should be used by the target Fabric Space.

For example:

```text
dev
qa
staging
prod
```

The appropriate designation depends on the environment definitions established for the Project.

## Activate the Environment

Deploying the environment definition makes it available to the Fabric Space. It does **not** by itself make that environment active.

After deployment:

1. Open **Fabric Admin** in the target Space.
2. Select the appropriate environment.
3. Select **Activate**.
4. Confirm that the expected environment is active.

Fabric then uses the configuration associated with the active environment.

For more information about Fabric environments, see [Environments Overview](/articles/25_environments/01_environments_overview.md).

## Next Step

After the correct environment has been deployed and activated, deploy the required Project content.

See [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md).

## Related Documentation

- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md)
- [Environments Overview](/articles/25_environments/01_environments_overview.md)
