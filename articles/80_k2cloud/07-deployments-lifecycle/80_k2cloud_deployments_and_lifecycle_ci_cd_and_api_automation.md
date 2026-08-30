# CI/CD and API Automation

## Overview

K2cloud provides built-in deployment operations for promoting environment definitions and Project content from Git to Fabric Spaces.

For many implementations, the built-in workflow is sufficient:

```text
Git
 ↓
Deploy Environment
 ↓
Activate Environment
 ↓
Deploy Project
 ↓
Validate Runtime
```

Organizations with established CI/CD processes can integrate K2cloud deployment operations into their broader delivery workflows.

## When to Use Automation

CI/CD automation can be useful when deployment must be integrated with processes such as:

- automated build and validation,
- approval gates,
- change-management controls,
- coordinated application releases,
- or enterprise deployment pipelines.

The need for CI/CD automation depends on the organization's software delivery requirements.

It is not required simply to promote K2view changes between Spaces.

## Deployment Automation

An automated workflow should preserve the same basic deployment sequence used by the built-in K2cloud workflow:

1. Identify the approved version in Git.
2. Deploy the required environment definition.
3. Activate the appropriate environment.
4. Deploy the approved Project content.
5. Validate the target runtime.

Automation changes how these operations are initiated and coordinated; it does not change the underlying deployment model.

## Git and Version Control

Git remains the source for versioned Project content and environment definitions.

CI/CD pipelines should use defined Git versions, such as approved tags, so that the content promoted to each runtime environment can be identified and controlled.

A typical promotion model is:

```text
Development
    ↓
Git Tag
    ↓
QA
    ↓
Staging
    ↓
Production
```

The exact pipeline, approval process, and promotion strategy are determined by the organization.

## API-Driven Operations

K2cloud lifecycle and deployment operations can be integrated with automation where supported by the available K2cloud APIs.

Organizations implementing API-driven deployment should coordinate the workflow with their K2cloud architecture and operational requirements rather than duplicating the manual Orchestrator workflow independently.

## Built-In Workflow or CI/CD

The built-in K2cloud deployment workflow is appropriate when a straightforward Git-to-Space promotion process meets the organization's needs.

CI/CD automation is appropriate when K2cloud deployment must participate in a broader enterprise delivery process.

Both approaches use the same underlying principles:

- versioned content in Git,
- controlled environment configuration,
- controlled Project deployment,
- and runtime validation.

## Related Documentation

- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md)
- [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md)