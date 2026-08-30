# SaaS Customer Responsibilities

## Overview

K2cloud SaaS removes the need for customers to operate the underlying K2cloud platform and Kubernetes infrastructure.

Customers remain responsible for their K2view implementation and how it is configured, secured, deployed, and used.

At a high level:

```text
K2view
    ↓
Operates K2cloud SaaS and the runtime infrastructure

Customer
    ↓
Owns and operates the K2view implementation
```

## Projects and Source Control

Customers are responsible for their K2cloud Projects and the source content associated with them.

This includes:

- Project configuration,
- application development,
- Git repository ownership,
- repository permissions,
- source-control practices,
- commits and tags,
- and determining which versions are approved for deployment.

K2cloud uses the configured Git repository as part of the deployment workflow but does not manage customer Git users or repository permissions.

## Space Lifecycle

Customers determine when Spaces should be created, operated, upgraded, or removed.

Authorized users are responsible for application-level lifecycle decisions such as:

- creating Spaces,
- selecting the appropriate Site and Space Profile,
- reviewing Space status,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- initiating available Fabric and Studio upgrades or rollbacks,
- and deleting Spaces when they are no longer required.

K2view operates the infrastructure on which these operations execute.

## Application Configuration and Deployment

Customers are responsible for the configuration and deployment of their K2view implementation.

This includes:

- environment definitions,
- environment-specific configuration,
- selecting approved Git tags,
- deploying environments,
- activating the appropriate environment,
- deploying Project content,
- and validating the resulting runtime behavior.

K2view operates the SaaS platform used to perform these operations. The customer determines what application content should be deployed and when.

## Identity and Access

Customers remain responsible for determining who should have access to their K2view implementation and what those users should be permitted to do.

For customer-federated identity, responsibilities include:

- managing enterprise user identities,
- managing IdP group membership,
- maintaining customer-side identity-provider configuration,
- defining required access responsibilities,
- and coordinating changes to federation configuration.

Customers are also responsible for defining the required:

- K2cloud Orchestrator access,
- Fabric roles and permissions,
- TDM permission groups where applicable,
- and Git repository access.

K2view operates the K2cloud identity federation service and supports the required federation mappings.

## Runtime Validation

Customers are responsible for validating that their K2view applications operate as intended.

This includes validating:

- deployments,
- environment configuration,
- application logic,
- APIs,
- integrations,
- data processing,
- runtime access,
- and other implementation-specific behavior.

K2view operates the underlying platform but does not determine whether customer-specific application behavior is functionally correct.

## Monitoring and Troubleshooting

K2cloud SaaS provides monitoring, logging, and Kubernetes diagnostic capabilities that customers can use when operating their Spaces.

Where customers require ERROR and AUDIT information for integration with their own security or monitoring systems, K2view can provide a supported log export capability. The customer remains responsible for its downstream monitoring, analysis, alerting, retention, and incident-response processes.

Customers are responsible for using the available information to investigate issues involving their implementation, including:

- application behavior,
- deployment content,
- environment configuration,
- integrations,
- and runtime authorization.

K2view remains responsible for issues involving the underlying K2cloud SaaS platform and infrastructure.

When investigation indicates that an issue is outside the customer implementation, it should be escalated to K2view.

## Persistence and Data

K2cloud SaaS Spaces use `managed` persistence.

The database and object storage associated with the Space are lifecycle-managed as part of the Space.

Customers should understand that deleting a Space also deletes its associated lifecycle-managed persistence.

Space deletion is not recoverable. Ensure that the implications of deleting the Space and its associated data are understood before performing the operation.

## Operational Governance

Customers should establish operational ownership for their K2view implementation.

This includes defining:

- who manages Projects and Spaces,
- who approves deployments,
- who administers runtime access,
- who validates production changes,
- who investigates application issues,
- and when an issue should be escalated to K2view.

The [Production Readiness Checklist](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_production_readiness_checklist.md) can be used to validate these responsibilities before production use.

## Related Documentation

- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [SaaS Operational Model](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_operational_model.md)
- [SaaS Runtime Operations](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_runtime_operations.md)
- [SaaS Observability and Support](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_observability_and_support.md)
- [Production Readiness Checklist](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_production_readiness_checklist.md)