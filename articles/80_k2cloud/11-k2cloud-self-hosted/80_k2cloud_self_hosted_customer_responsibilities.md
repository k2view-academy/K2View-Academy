# Self-Hosted Customer Responsibilities

## Table of Contents

- [Overview](#overview)
- [Kubernetes and Infrastructure](#kubernetes-and-infrastructure)
- [Sites](#sites)
- [Projects and Source Control](#projects-and-source-control)
- [Space Lifecycle](#space-lifecycle)
- [Persistence and Data](#persistence-and-data)
- [Container Images](#container-images)
- [Deployments and Runtime Configuration](#deployments-and-runtime-configuration)
- [Identity and Access](#identity-and-access)
- [Monitoring and Troubleshooting](#monitoring-and-troubleshooting)
- [Fabric and Studio Version Lifecycle](#fabric-and-studio-version-lifecycle)
- [Operational Governance](#operational-governance)
- [Related Documentation](#related-documentation)

## Overview

K2cloud Self-Hosted gives customers control of the Kubernetes infrastructure where their K2view Spaces run.

K2view operates the K2cloud Orchestrator, while the customer operates the runtime infrastructure and remains responsible for its K2view implementation and day-to-day use of K2cloud.

This article summarizes the primary customer responsibilities in a Self-Hosted deployment.

## Kubernetes and Infrastructure

The customer is responsible for operating the Kubernetes environment and the supporting infrastructure required by each K2cloud Site.

Responsibilities include:

- Kubernetes availability and capacity,
- networking and connectivity,
- ingress infrastructure,
- DNS and certificates,
- infrastructure identity and access,
- storage infrastructure,
- container registry availability,
- and infrastructure monitoring and alerting.

Where a managed Kubernetes service such as Amazon EKS, Azure AKS, or Google GKE is used, some infrastructure functions are operated by the cloud provider. The customer remains responsible for the configuration and operation of its environment and for ensuring that it satisfies K2cloud requirements.

## Sites

The customer is responsible for the infrastructure associated with its Self-Hosted Sites.

This includes maintaining the connectivity and infrastructure services required for K2cloud to deploy and operate Spaces within the Site.

Changes to networking, ingress, DNS, certificates, registries, storage, infrastructure identity, or Kubernetes configuration can affect the Spaces deployed there.

Infrastructure changes should therefore be managed with consideration for their impact on K2cloud operations.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Projects and Source Control

Customers are responsible for their K2view Projects and source repositories.

This includes:

- maintaining the Git repository,
- managing source-control access,
- committing and versioning implementation content,
- maintaining environment definitions,
- managing Git tags used for deployment,
- and ensuring that required content is available before deployment.

K2cloud uses Git as the source for deployment content but does not replace the customer's source-control governance.

## Space Lifecycle

Customers decide when to create, operate, upgrade, and delete their Spaces.

This includes selecting the appropriate:

- Project,
- Space Profile,
- Fabric Image,
- and Site.

Customers are also responsible for understanding the operational effect of lifecycle actions.

In particular, deleting a Space is not recoverable and can affect persistence depending on the Space Profile.

For more information, see [Space Lifecycle](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_lifecycle.md).

## Persistence and Data

Customers are responsible for the persistence architecture used by their Self-Hosted Spaces and the infrastructure supporting it.

For `noSdb` configurations, the Space uses externally managed database and object-storage resources. These resources have a lifecycle independent of the Space and are not deleted when the Space is deleted.

For `managed` configurations, lifecycle-managed persistence is associated with the Space and is deleted when the Space is deleted.

Customers should understand the persistence model and its lifecycle implications before creating or deleting Spaces.

## Container Images

For Self-Hosted Sites, the required Fabric and Fabric-Studio container images must be available in the customer-managed container registry configured for the Site.

K2view publishes supported releases, while the customer is responsible for making the required images available at the registry location used by the Site.

If the required image is not available, the corresponding Space creation or lifecycle operation cannot successfully use that image.

For detailed image preparation and upgrade procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Deployments and Runtime Configuration

Customers own the K2view implementation deployed to their Spaces.

Responsibilities include:

- selecting the appropriate Git tag,
- deploying environment definitions,
- activating the appropriate environment,
- deploying Project content,
- maintaining application configuration,
- validating integrations,
- and validating runtime behavior after deployment.

K2cloud provides the deployment and lifecycle workflows, but the customer determines what application content and configuration are deployed.

## Identity and Access

Customers are responsible for designing and maintaining access to their K2view implementation.

This includes:

- assigning K2cloud access appropriately,
- controlling the highly privileged `cloud_user` role,
- managing Space authorization,
- defining Fabric roles and permissions,
- managing customer-defined roles,
- maintaining identity-provider groups and mappings where federation is used,
- and managing TDM permission groups where applicable.

Creating a Space does not automatically make its creator a Space Admin.

Access should be based on operational responsibilities and least privilege rather than assigning broad administrative access to individual users.

For more information, see [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md).

## Monitoring and Troubleshooting

Self-Hosted customers are responsible for the observability framework associated with their runtime infrastructure.

This includes:

- infrastructure monitoring,
- centralized logging,
- alerting,
- security monitoring,
- audit integration,
- SIEM integration,
- and operational response.

The K2cloud SaaS Metrics and Logs monitoring components are not provided for Self-Hosted Spaces.

K2cloud provides Kubernetes-level diagnostics through **Space Details**, including pod information, pod logs, and Kubernetes events. Customers can use these diagnostics together with their own infrastructure observability when investigating runtime issues.

Customers are responsible for determining whether an issue concerns:

- their K2view implementation,
- a Space runtime,
- customer-managed infrastructure,
- or the K2cloud control plane.

Issues involving the customer implementation or infrastructure remain the customer's responsibility. Issues determined to involve the K2cloud Orchestrator should be escalated to K2view.

## Fabric and Studio Version Lifecycle

Customers are responsible for planning and validating Fabric and Studio upgrades for their Spaces.

This includes:

- making required images available in the customer registry,
- selecting the appropriate Fabric Image for the Space Profile,
- deciding when to perform an upgrade,
- validating the application after the upgrade,
- and maintaining appropriate release and maintenance practices.

K2cloud provides the supported upgrade and rollback workflow.

For detailed procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Operational Governance

Customers should establish operational procedures appropriate to their organization for:

- Space creation and deletion,
- deployments,
- upgrades and rollbacks,
- infrastructure changes,
- access changes,
- monitoring and incident response,
- persistence and recovery,
- and escalation to K2view.

K2cloud provides the application-aware orchestration layer for K2view Spaces, while the customer remains responsible for governing both its implementation and the infrastructure on which those Spaces run.

## Related Documentation

- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Self-Hosted Operational Model](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_operational_model.md)
- [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md)
- [Self-Hosted Runtime Operations](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_runtime_operations.md)
- [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md)
- [Shared Responsibility Model](/articles/80_k2cloud/01-overview/80_k2cloud_overview_shared_responsibility_model.md)