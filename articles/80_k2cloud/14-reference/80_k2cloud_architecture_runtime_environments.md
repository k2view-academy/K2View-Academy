# Runtime Environments Architecture

## Table of Contents

- [Overview](#overview)
- [Runtime Environment Model](#runtime-environment-model)
- [Sites and Runtime Placement](#sites-and-runtime-placement)
- [Spaces](#spaces)
- [Runtime Topology](#runtime-topology)
- [Space Profiles](#space-profiles)
- [Persistence](#persistence)
- [Networking and Ingress](#networking-and-ingress)
- [Runtime Isolation](#runtime-isolation)
- [Lifecycle Management](#lifecycle-management)
- [Observability and Diagnostics](#observability-and-diagnostics)
- [SaaS and Self-Hosted Runtime Environments](#saas-and-self-hosted-runtime-environments)
- [Related Documentation](#related-documentation)

## Overview

K2cloud runtime environments host the K2view workloads managed through the K2cloud Orchestrator.

The Orchestrator provides the application-aware management layer, while Kubernetes provides the underlying container orchestration and infrastructure resource management.

Conceptually:

    K2cloud Orchestrator
             │
             ▼
            Site
             │
             ▼
           Space
             │
             ▼
    Kubernetes runtime
             │
             ▼
      K2view workloads

The runtime architecture is similar for K2cloud SaaS and K2cloud Self-Hosted. The primary difference is who operates the Kubernetes infrastructure and its supporting services.

## Runtime Environment Model

A K2cloud runtime environment consists of the infrastructure and K2view components required to operate a Space.

Depending on the Space type and configuration, these components can include:

- Fabric,
- Web Studio,
- PostgreSQL,
- application APIs,
- TDM capabilities,
- and supporting runtime services.

Not every Space contains the same components.

For example, a Studio development Space includes the components required for development, while a Fabric runtime Space is intended to run the deployed implementation.

The exact runtime topology is defined through the Space Profile.

## Sites and Runtime Placement

A **Site** represents the runtime placement and infrastructure boundary into which Spaces are created.

A Site can correspond to characteristics such as:

- Kubernetes infrastructure,
- cloud or infrastructure environment,
- geographic region,
- networking boundary,
- and ingress configuration.

When creating a Space, the user selects the Site on which the Space will run.

In K2cloud SaaS, K2view operates the runtime infrastructure represented by the Site.

In K2cloud Self-Hosted, the Site represents customer-operated Kubernetes infrastructure connected to the K2cloud Orchestrator.

Most Self-Hosted environments use managed Kubernetes services such as Amazon EKS, Azure Kubernetes Service (AKS), or Google Kubernetes Engine (GKE).

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Spaces

A **Space** is the primary K2view runtime environment managed by K2cloud.

A Space is created from:

**Project + Space Profile + Fabric Image + Site → Space**

These resources determine:

- the K2view implementation,
- runtime topology and resources,
- Fabric or Fabric-Studio version,
- and runtime placement.

The Space is then managed as a K2view environment through K2cloud lifecycle and deployment workflows.

The underlying Kubernetes resources support the Space but are not themselves the K2cloud management abstraction.

For more information, see [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_overview.md).

## Runtime Topology

K2cloud supports different runtime topologies depending on the purpose of the Space.

### Studio Development Space

A Studio Space provides the environment used for K2view development.

Its runtime includes the components required to support Studio, Fabric, and associated development services.

Conceptually:

    Studio Space
         │
         ├── Web Studio
         ├── Fabric
         └── PostgreSQL

Developers access the Studio Space directly through its Space URL.

Source code is maintained in the Project's Git repository.

### Fabric Runtime Space

A Fabric Space provides the runtime environment for a deployed K2view implementation.

Conceptually:

    Fabric Space
         │
         └── Fabric
              │
              ├── APIs
              ├── Logical Units
              └── Runtime services

Depending on the deployment model and Space Profile, persistent services can be managed as part of the Space or provided through external services.

## Space Profiles

A **Space Profile** defines the runtime topology and resources used to create a Space.

The profile determines characteristics such as:

- runtime components,
- CPU and memory allocation,
- number of Fabric replicas,
- and persistence configuration.

The Fabric Image is selected separately from the Space Profile. This separates runtime topology from the Fabric software version and allows the Fabric version to change without redefining the underlying profile.

For the current Space Profile definitions and naming conventions, see [K2cloud Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md).

## Persistence

Persistence differs according to the deployment model and Space Profile.

### K2cloud SaaS

K2cloud SaaS uses **managed** persistence.

The persistent database and storage associated with the Space are managed as part of the SaaS environment and follow the lifecycle of that Space.

Deleting a Space that uses managed persistence deletes the persistence associated with that Space.

### K2cloud Self-Hosted

K2cloud Self-Hosted can use **managed** or **noSdb** persistence models, depending on the Space Profile and architecture.

With **managed** persistence, the database and storage are associated with the Space and follow its lifecycle.

With **noSdb**, persistence is external to the Space. The customer configures the required database and blob storage and provides the required identity and access.

Because these resources are external to the Space lifecycle, deleting the Space does not delete the external database or blob storage.

This distinction is particularly important when planning production Self-Hosted environments.

For more information, see:

- [Persistence and Data Lifecycle](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_persistence_and_data_lifecycle.md)
- [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md)

## Networking and Ingress

The Site establishes the runtime networking and ingress context for its Spaces.

Space access can use:

- context-path-based addressing, or
- domain-based addressing.

Where applicable, context-path-based Spaces are preferred for new K2cloud deployments because they simplify DNS and TLS certificate management and reduce the proliferation of Space-specific hostnames.

The exact infrastructure implementation differs between SaaS and Self-Hosted environments.

In K2cloud SaaS, K2view operates the underlying networking and ingress infrastructure.

In K2cloud Self-Hosted, the customer operates the infrastructure required for networking, ingress, DNS, certificates, and connectivity.

For more information, see [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md).

## Runtime Isolation

Spaces provide separate K2view runtime environments.

Each Space has its own runtime identity within K2cloud and is managed independently through its Space lifecycle.

The Site provides the broader infrastructure and placement boundary, while the Space represents the individual K2view environment operating within that boundary.

This allows multiple Spaces to be managed through a common Site while retaining independent:

- lifecycle state,
- runtime configuration,
- Fabric Image,
- application deployment,
- and authorization.

Infrastructure-level isolation is determined by the architecture of the Site and its underlying Kubernetes environment.

## Lifecycle Management

K2cloud manages the lifecycle of the K2view runtime environment.

Depending on the Space type and operation, lifecycle actions can include:

- creating a Space,
- deleting a Space,
- pausing and resuming a Studio Space,
- restarting a Space,
- upgrading the Fabric Image,
- and rolling back to a previous image.

Kubernetes performs the underlying container scheduling and runtime operations, while K2cloud provides the K2view-aware workflow used to initiate and manage the lifecycle operation.

For multi-replica Fabric Spaces, upgrades can use rolling behavior in which runtime pods are replaced progressively.

Detailed upgrade procedures are documented separately under [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Observability and Diagnostics

Runtime visibility depends on the deployment model.

### Space Details

For both SaaS and Self-Hosted Spaces, **Space Details** provides Kubernetes runtime diagnostics such as:

- pod state,
- readiness,
- restart information,
- CPU and memory information,
- pod logs,
- Kubernetes events,
- and pod definition information.

These diagnostics provide visibility into the Kubernetes resources supporting the Space.

### K2cloud SaaS

K2cloud SaaS also provides K2cloud monitoring capabilities through **Metrics** and **Logs**.

These capabilities provide operational visibility without requiring the customer to operate the underlying infrastructure-monitoring environment.

### K2cloud Self-Hosted

Self-Hosted customers operate their own infrastructure observability environment, including monitoring, centralized logging, alerting, and security monitoring as appropriate.

K2cloud Space Details remains available for Space-level Kubernetes diagnostics.

This creates an important troubleshooting distinction:

    K2cloud Space diagnostics
              +
    Customer infrastructure observability
              =
    Self-Hosted runtime visibility

For more information, see:

- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md)

## SaaS and Self-Hosted Runtime Environments

The K2view runtime architecture is managed through the same K2cloud control plane in both deployment models, but infrastructure ownership differs.

<table>
<thead>
<tr>
<th>Area</th>
<th>K2cloud SaaS</th>
<th>K2cloud Self-Hosted</th>
</tr>
</thead>
<tbody>
<tr>
<td>K2cloud Orchestrator</td>
<td>K2view-operated</td>
<td>K2view-operated</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Site infrastructure</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Space lifecycle</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
</tr>
<tr>
<td>Space Profile</td>
<td>Defines runtime topology</td>
<td>Defines runtime topology</td>
</tr>
<tr>
<td>Persistence</td>
<td>Managed</td>
<td>Managed or noSdb</td>
</tr>
<tr>
<td>Infrastructure observability</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>K2cloud Metrics and Logs</td>
<td>Available</td>
<td>Not available</td>
</tr>
<tr>
<td>Space Details diagnostics</td>
<td>Available</td>
<td>Available</td>
</tr>
</tbody>
</table>

The operational model can therefore be summarized as:

**K2cloud SaaS**

    K2view operates:
    Orchestrator + Kubernetes infrastructure

**K2cloud Self-Hosted**

    K2view operates:
    Orchestrator

    Customer operates:
    Kubernetes infrastructure

In both cases, K2cloud remains the application-aware orchestration layer used to manage K2view Spaces.

## Related Documentation

- [Control Plane Services Architecture](/articles/80_k2cloud/14-reference/80_k2cloud_architecture_control_plane_services.md)
- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_overview.md)
- [Persistence and Data Lifecycle](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_persistence_and_data_lifecycle.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)