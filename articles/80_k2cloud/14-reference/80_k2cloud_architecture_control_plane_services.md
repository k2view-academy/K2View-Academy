# Control Plane Services Architecture

## Table of Contents

- [Overview](#overview)
- [K2cloud Orchestrator](#k2cloud-orchestrator)
- [Control Plane and Runtime Environments](#control-plane-and-runtime-environments)
- [Projects](#projects)
- [Sites](#sites)
- [Space Profiles and Fabric Images](#space-profiles-and-fabric-images)
- [Spaces](#spaces)
- [Deployment and Lifecycle Operations](#deployment-and-lifecycle-operations)
- [Operational Visibility](#operational-visibility)
- [Identity and Access](#identity-and-access)
- [SaaS and Self-Hosted](#saas-and-self-hosted)
- [Air-Gapped Fabric](#air-gapped-fabric)
- [Related Documentation](#related-documentation)

## Overview

The K2cloud Orchestrator is the centralized SaaS control plane used to manage the lifecycle of K2view Fabric environments.

The Orchestrator is managed and operated by K2view. Customers interact with it through the **K2cloud Orchestrator console** at:

`https://cloud.k2view.com`

K2cloud separates management of the K2view environment from management of the Kubernetes infrastructure on which that environment runs.

At a high level:

    K2cloud Orchestrator
             │
             │ lifecycle and deployment operations
             ▼
            Site
             │
             ▼
           Space
             │
             ▼
    Kubernetes runtime

Kubernetes manages containers and infrastructure resources. K2cloud manages K2view Fabric environments.

This separation applies to both K2cloud SaaS and K2cloud Self-Hosted. The primary difference between the two models is who operates the runtime infrastructure.

## K2cloud Orchestrator

The K2cloud Orchestrator maintains the K2cloud resources and workflows used to manage Fabric environments.

These include:

- Projects,
- Sites,
- Space Profiles,
- Fabric Images,
- Spaces,
- deployment operations,
- lifecycle operations,
- and access to runtime status and diagnostics.

The Orchestrator provides an application-aware management layer above Kubernetes. Users perform K2view lifecycle operations through K2cloud rather than directly managing the underlying Kubernetes resources for those operations.

This allows the runtime infrastructure and the K2view application lifecycle to remain separate operational concerns.

## Control Plane and Runtime Environments

The K2cloud Orchestrator is separate from the Kubernetes runtime environments it manages.

The control plane maintains the K2cloud model and initiates operations against the appropriate runtime environment.

The runtime environment hosts the actual K2view workload.

Conceptually:

    K2cloud Orchestrator
             │
             ├── Project
             ├── Site
             ├── Space Profile
             └── Fabric Image
                    │
                    ▼
                  Space
                    │
                    ▼
             Kubernetes runtime

A Space is therefore not simply a Kubernetes workload. It is a K2cloud-managed K2view environment created from a defined combination of K2cloud resources.

## Projects

A Project associates the K2view implementation with the Git repository and the Space Profiles used to create runtime environments for that implementation.

The Project provides the application context used by K2cloud deployment workflows.

Git remains the source-control system. K2cloud does not replace Git or manage developer repository permissions.

For more information, see [Projects Overview](/articles/80_k2cloud/03-projects/80_k2cloud_projects_projectoverview.md).

## Sites

A Site represents the runtime placement and infrastructure boundary into which a Space is created.

A Site can represent characteristics such as:

- a Kubernetes cluster,
- a cloud or infrastructure environment,
- a geographic region,
- or an ingress and access boundary.

The Site is selected when the Space is created. It is not a persistent setting of the Project.

In K2cloud SaaS, K2view operates the infrastructure associated with the Site.

In K2cloud Self-Hosted, the customer operates the Kubernetes infrastructure and supporting services associated with the Site.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Space Profiles and Fabric Images

A Space Profile defines the runtime topology and resource configuration used for a Space.

The Fabric Image defines the Fabric or Fabric-Studio software version used by that runtime.

Keeping these concepts separate allows the runtime topology to remain stable while the Fabric version changes.

Project Managers can select the appropriate Fabric Image for a Space Profile. Eligible Spaces can then be upgraded using the K2cloud lifecycle workflow.

For more information, see:

- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Fabric Versions and Upgrades](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_upgrading_and_requesting_a_fabric_image_version.md)

## Spaces

A Space is a K2view runtime environment managed through the K2cloud Orchestrator.

A Space is created from:

**Project + Space Profile + Fabric Image + Site → Space**

The selected resources determine:

- the K2view implementation,
- runtime topology and resources,
- Fabric software version,
- and runtime placement.

Depending on its purpose, a Space can provide a Studio development environment or a Fabric runtime environment.

Once created, the Space becomes the primary unit through which K2cloud performs lifecycle and deployment operations.

For more information, see [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_overview.md).

## Deployment and Lifecycle Operations

The K2cloud Orchestrator provides workflows for operating K2view environments without requiring users to perform the corresponding application lifecycle operations directly against Kubernetes.

Space lifecycle operations include creating, deleting, pausing, resuming, upgrading, and rolling back Spaces where those operations are applicable to the Space type.

K2cloud also provides Git-backed application deployment workflows.

These include:

- **Deploy Environments** — deploy an environment definition from a selected Git tag.
- **Deploy Project** — deploy the Project or selected Logical Units from a selected Git tag.

The typical application deployment lifecycle is:

    Git
     │
     ▼
    Deploy Environment
     │
     ▼
    Activate Environment
     │
     ▼
    Deploy Project
     │
     ▼
    Runtime Validation

Lifecycle operations such as restarting a Space or viewing Space Details are K2cloud operational actions and are not Git-backed deployment operations.

For more information, see [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md).

## Operational Visibility

The K2cloud Orchestrator provides information about the state of the Spaces it manages.

**Space Details** provides Kubernetes runtime diagnostics, including:

- pod information,
- pod state and readiness,
- pod restart information,
- CPU and memory information,
- pod logs,
- Kubernetes events,
- and pod definition information.

These diagnostics are available through the K2cloud management model and are distinct from the broader monitoring and logging framework used for the runtime environment.

K2cloud SaaS also provides K2cloud monitoring components such as **Metrics** and **Logs**.

K2cloud Self-Hosted customers use their own infrastructure monitoring, centralized logging, alerting, and security-monitoring framework while retaining the K2cloud Space diagnostics provided through Space Details.

For more information, see:

- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)

## Identity and Access

K2cloud separates access to the Orchestrator from access to the runtime Spaces it manages.

A user who requires Project and Space lifecycle-management capabilities can be granted an appropriate K2cloud role such as `cloud_user`.

Runtime users can access an authorized Space directly through its Space URL without requiring access to the K2cloud Orchestrator.

Within a Space, Fabric and TDM enforce their own authorization models.

For federated customers, identity-provider groups can be mapped through K2cloud identity federation to the appropriate Fabric roles and, where applicable, TDM permission groups.

The control plane therefore participates in the overall identity architecture without replacing the authorization model enforced by the runtime applications.

For more information, see [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md).

## SaaS and Self-Hosted

K2cloud supports two deployment models: **K2cloud SaaS** and **K2cloud Self-Hosted**.

In both models, the K2cloud Orchestrator is a centralized SaaS control plane managed and operated by K2view.

The difference is the ownership of the runtime infrastructure.

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
<td>Runtime Kubernetes infrastructure</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>K2view Space lifecycle</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
</tr>
<tr>
<td>Application deployment</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
</tr>
<tr>
<td>Infrastructure monitoring</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>K2cloud SaaS Metrics and Logs</td>
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

Most K2cloud Self-Hosted environments use hyperscaler-managed Kubernetes services such as Amazon EKS, Azure Kubernetes Service (AKS), or Google Kubernetes Engine (GKE).

For more information, see:

- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)

## Air-Gapped Fabric

Air-gapped Fabric is not a K2cloud deployment model.

An air-gapped Fabric environment operates without dependence on the K2cloud Orchestrator control plane. The customer therefore assumes responsibility for the orchestration and lifecycle capabilities that K2cloud otherwise provides.

This includes responsibility for areas such as:

- Fabric deployment,
- runtime lifecycle,
- application deployment,
- software and image distribution,
- upgrades and rollbacks,
- monitoring and diagnostics,
- operational automation,
- and recovery procedures.

The architectural distinction is important:

    K2cloud SaaS / Self-Hosted
    
    K2cloud Orchestrator
             │
             ▼
       K2view Space
             │
             ▼
    Kubernetes runtime


    Air-Gapped Fabric
    
    Customer operational tooling
             │
             ▼
       K2view Fabric
             │
             ▼
    Customer-managed runtime

For more information, see [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md).

## Related Documentation

- [What is K2cloud?](/articles/80_k2cloud/01-overview/80_k2cloud_overview_what_is_k2cloud.md)
- [Projects Overview](/articles/80_k2cloud/03-projects/80_k2cloud_projects_overview.md)
- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_overview.md)
- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)