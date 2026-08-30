# Deploy Project

## Overview

Use **Deploy Project** to deploy Project content from Git to a Fabric Space.

The operator selects the Git tag containing the version to deploy and can deploy either:

- the complete Project, or
- selected Logical Units (LUs).

Before deploying Project content, ensure that the correct environment has been deployed and activated in the target Fabric Space.

## Before You Begin

Before deploying the Project:

- confirm that the required Project changes have been committed to Git,
- identify the Git tag containing the version to deploy,
- confirm the target Fabric Space,
- and verify that the correct environment is active.

For environment deployment and activation, see [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md).

## Deploy the Project

1. Open the **Spaces** page in the K2cloud Orchestrator.
2. Locate the target Fabric Space.
3. Open the Space action menu (**...**).
4. Select **Deploy Project**.
5. Select the required **Git Tag**.
6. Specify the LUs to deploy.
7. Start the deployment.

K2cloud retrieves the selected Project version from Git and deploys it to the target Fabric Space.

## Select the Git Tag

The **Git Tag** identifies the version of the Project content to deploy.

Using Git tags provides a defined version of the Project that can be promoted between runtime environments.

For example, the same tagged Project version can be deployed through:

```text
QA → Staging → Production
```

The Git tag is selected for the deployment operation; it is not a persistent Project or Space Profile setting.

## Select the Deployment Scope

The Project can be deployed in full or limited to selected Logical Units.

### Deploy the Complete Project

Deploy the complete Project when the entire tagged Project version should be applied to the target Space.

### Deploy Selected Logical Units

When only specific Logical Units need to be deployed, provide the required LU names as a comma-delimited list.

For example:

```text
Customer,Order,Product
```

This limits the deployment to the specified Logical Units.

## Validate the Deployment

After deployment completes, validate the target runtime.

Validation should confirm that:

- the expected Project version was deployed,
- the expected Logical Units are available,
- the correct environment remains active,
- and the affected runtime functionality operates as expected.

The specific validation depends on the Project and the changes being promoted.

For K2cloud SaaS, the built-in monitoring and log capabilities can also be used when reviewing runtime behavior after deployment.

## Deployment Sequence

The built-in K2cloud deployment sequence is:

```text
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

This sequence keeps the runtime environment configuration and deployed Project content aligned with versioned content in Git.

## Related Documentation

- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)