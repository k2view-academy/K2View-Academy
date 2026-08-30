# Self-Hosted Runtime Operations

## Overview

K2cloud Self-Hosted provides the same Space-oriented operational model used throughout K2cloud while the Spaces themselves run within customer-managed Kubernetes infrastructure.

Project Managers and Space Owners perform supported application-level operations through the K2cloud Orchestrator.

The customer infrastructure team remains responsible for operating the Kubernetes environment and its supporting services.

This separation allows routine K2view operations to be performed through K2cloud without requiring users to manage Fabric deployments directly through Kubernetes.

## Space Lifecycle Operations

K2cloud manages the lifecycle of Spaces deployed to a Self-Hosted Site.

Depending on the type of Space, supported operations include:

- creating Spaces,
- opening Spaces,
- refreshing Space status,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- viewing Space Details,
- and deleting Spaces.

These operations are initiated through the K2cloud Orchestrator and executed against the customer-managed runtime environment.

For more information, see [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md).

## Creating Spaces

A Project Manager creates a Self-Hosted Space using the standard K2cloud deployment model:

**Project + Space Profile + Fabric Image + Site → Space**

The Site determines the customer-managed infrastructure where the Space is deployed.

The Space Profile defines the deployment topology and runtime configuration, while the Fabric Image identifies the Fabric or Fabric-Studio version.

K2cloud orchestrates creation of the required runtime components within the target Site.

For more information, see [Create a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_create_a_space.md).

## Deploying Environments and Project Content

Application deployment follows the standard K2cloud workflow.

Customers use Git as the source for versioned Project and environment content and use K2cloud to deploy that content to the target Fabric Space.

The typical workflow is:

    Git
      ↓
    Deploy Environment
      ↓
    Activate Environment
      ↓
    Deploy Project
      ↓
    Runtime Validation

The customer determines what content to deploy and when to deploy it.

For more information, see:

- [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md)
- [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md)

## Fabric and Studio Upgrades

Fabric and Studio version lifecycle operations are also managed through K2cloud.

K2view publishes supported Fabric releases. The Project Manager can then select an available Fabric Image for the appropriate Space Profile.

For Self-Hosted Sites, the corresponding Fabric or Fabric-Studio container image must first be available in the customer-managed container registry configured for the Site.

Once the required image is available and the newer Fabric Image has been selected, eligible Spaces can be upgraded through K2cloud.

K2cloud performs the upgrade against the customer-managed Kubernetes environment. For multi-replica Fabric Spaces, upgrades can use rolling behavior so that runtime pods are replaced progressively.

Customers remain responsible for validating their applications following an upgrade and should use appropriate maintenance and release-management practices.

For detailed image preparation, upgrade, and rollback procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Runtime Access

Access to the K2cloud Orchestrator and access to deployed Spaces are separate.

Users who require Orchestrator lifecycle capabilities need the appropriate K2cloud role.

Users who only need to work within an authorized Studio or Fabric Space can access that Space directly using its Space URL without requiring access to the K2cloud Orchestrator.

Runtime authorization within the Space remains governed by Fabric roles and, where applicable, TDM permission groups.

For more information, see [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md).

## Runtime Diagnostics

K2cloud provides Kubernetes-level diagnostics for Self-Hosted Spaces through **Space Details**.

Authorized users can inspect information such as:

- pod state,
- pod details,
- pod logs,
- resource information,
- restart information,
- and Kubernetes events.

These diagnostics provide visibility into the Kubernetes resources associated with a Space and can help identify runtime issues.

They do not replace the customer's infrastructure observability framework.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Monitoring and Logging

The K2cloud SaaS Metrics and Logs monitoring components are not provided for Self-Hosted Spaces.

Because the customer operates the runtime infrastructure, the customer is responsible for its own:

- infrastructure monitoring,
- centralized logging,
- alerting,
- security monitoring,
- audit integration,
- and SIEM integration.

These capabilities should be integrated with the customer's existing operational and security processes.

The Kubernetes diagnostics available through Space Details provide additional Space-specific information but are distinct from this infrastructure monitoring model.

## Infrastructure Operations

K2cloud lifecycle operations depend on the health and capacity of the underlying customer-managed infrastructure.

The customer infrastructure team remains responsible for areas such as:

- Kubernetes availability,
- infrastructure capacity,
- networking and connectivity,
- ingress,
- DNS and certificates,
- storage infrastructure,
- container registry availability,
- and infrastructure monitoring.

An infrastructure problem can therefore affect a K2cloud operation even when the operation itself is initiated correctly through the K2cloud Orchestrator.

When troubleshooting, customers should determine whether an issue concerns the K2view implementation, the Space runtime, or the underlying infrastructure.

## Deleting Spaces

Deleting a Space is not recoverable.

Before deleting a Self-Hosted Space, customers should understand the persistence model associated with its Space Profile.

For a `noSdb` Space, externally managed databases and object storage have a lifecycle independent of the Space and are not deleted with it.

For a `managed` Space, lifecycle-managed persistence associated with the Space is deleted when the Space is deleted.

For more information, see [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md).

## Related Documentation

- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Self-Hosted Operational Model](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_operational_model.md)
- [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md)
- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md)