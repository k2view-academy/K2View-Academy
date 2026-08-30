# Sites and Space Profiles in Self-Hosted Environments

## Overview

In K2cloud Self-Hosted, **Sites** and **Space Profiles** connect the K2cloud deployment model to customer-managed runtime infrastructure.

A **Site** identifies the infrastructure where a Space will run.

A **Space Profile** defines the deployment topology, resources, and runtime configuration K2cloud uses when creating that Space.

Together, they separate two important concerns:

- **Site — where the Space runs**
- **Space Profile — how the Space is configured**

This allows the same K2cloud orchestration model to be used across customer-managed Kubernetes environments.

## Sites in Self-Hosted Environments

A Self-Hosted Site represents a customer-managed runtime infrastructure boundary connected to the K2cloud Orchestrator.

A Site can correspond to infrastructure such as:

- an Amazon EKS cluster,
- an Azure AKS cluster,
- a Google GKE cluster,
- or another supported customer-managed Kubernetes environment.

The Site provides the infrastructure-specific configuration required for K2cloud to deploy and operate Spaces in that environment.

Depending on the deployment, this can include configuration associated with:

- Kubernetes connectivity,
- networking,
- ingress,
- DNS and certificates,
- container registry access,
- storage integration,
- and infrastructure identity and access.

The customer is responsible for configuring and operating these infrastructure components.

For more information about Sites, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Sites and Runtime Placement

The Site is selected when a Space is created.

It is not a Project setting.

This allows a Project to be used to create Spaces in different Sites where the customer's architecture requires separate runtime environments.

For example, Sites may represent different:

- Kubernetes clusters,
- cloud regions,
- network boundaries,
- ingress boundaries,
- or customer environments.

The Site therefore establishes the runtime placement and infrastructure boundary for the Space.

## Connectivity and Ingress

Because the customer operates the runtime infrastructure, the customer is responsible for the Site's network and ingress configuration.

This includes the infrastructure required for users and systems to reach deployed Spaces.

Where applicable, context-path-based ingress is preferred for new K2cloud deployments because it can simplify DNS and TLS certificate management and reduce the proliferation of Space-specific hostnames.

The specific ingress model depends on the customer's infrastructure and network architecture.

For more information, see [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md).

## Space Profiles in Self-Hosted Environments

Space Profiles define the topology and runtime configuration used when K2cloud creates a Space.

A Space Profile can define characteristics such as:

- the type of Space,
- runtime resources,
- Fabric topology,
- replica configuration,
- persistence model,
- and other runtime configuration.

The Fabric or Fabric-Studio version is selected separately through the Fabric Image associated with the profile.

This separation allows the profile to define **how the runtime is deployed** without permanently tying the profile to a specific Fabric version.

For more information, see [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md).

## Infrastructure Capacity

The customer is responsible for ensuring that the Site has sufficient infrastructure capacity for the Space Profiles and workloads deployed to it.

K2cloud uses the selected Space Profile to create and manage the required K2view runtime topology.

The customer therefore does not manually construct the Fabric topology in Kubernetes, but the underlying Kubernetes environment must have sufficient resources to support it.

This is an important responsibility boundary:

> **K2cloud defines and manages the K2view runtime topology. The customer provides and operates the infrastructure capacity required to run it.**

## Persistence

Self-Hosted deployments can use different persistence models depending on the selected Space Profile and architecture.

With a `noSdb` profile, the Space uses externally configured database and object-storage resources. These resources have a lifecycle independent of the Space.

Deleting a `noSdb` Space therefore removes the Space but does not delete the external database or object storage.

With a `managed` profile, persistence is associated with the lifecycle of the Space. Deleting the Space also deletes the lifecycle-managed persistence associated with it.

The persistence model should therefore be understood before creating or deleting a Space.

For the available K2cloud Space Profiles and their naming conventions, see [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md).

## Fabric Images

The Space Profile and Fabric Image are separate resources.

The Space Profile defines the deployment topology and configuration.

The Fabric Image identifies the Fabric or Fabric-Studio runtime version to use with that profile.

For Self-Hosted Sites, the required Fabric and Fabric-Studio container images must be available from the customer-managed container registry configured for the Site.

K2view publishes supported Fabric releases, while the customer is responsible for making the required images available in its registry.

When a Project Manager selects a Fabric Image for a Space Profile, K2cloud uses the corresponding image when creating or upgrading eligible Spaces.

For detailed upgrade and image-preparation procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Creating a Space

When creating a Self-Hosted Space, the Project Manager combines the standard K2cloud resources:

**Project + Space Profile + Fabric Image + Site → Space**

The **Project** identifies the implementation.

The **Space Profile** defines the deployment topology and runtime configuration.

The **Fabric Image** identifies the runtime version.

The **Site** determines the customer-managed infrastructure where the Space will run.

K2cloud then orchestrates creation of the Space within that Site.

## Related Documentation

- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Self-Hosted Operational Model](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_operational_model.md)
- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md)
- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md)
- [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md)