# K2cloud Reference Architectures

## Table of Contents

- [Overview](#overview)
- [K2cloud SaaS](#k2cloud-saas)
- [K2cloud Self-Hosted](#k2cloud-self-hosted)
- [Air-Gapped Fabric](#air-gapped-fabric)
- [Architecture Comparison](#architecture-comparison)
- [Choosing an Architecture](#choosing-an-architecture)
- [Related Documentation](#related-documentation)

## Overview

K2cloud supports two deployment models:

- **K2cloud SaaS**
- **K2cloud Self-Hosted**

In both models, the **K2cloud Orchestrator** is a centralized SaaS control plane managed and operated by K2view. The primary architectural difference is who operates the Kubernetes runtime infrastructure.

**Air-Gapped Fabric** is a separate Fabric deployment architecture. It operates without dependence on the K2cloud Orchestrator control plane and is included here for architectural comparison.

The three architectures can be summarized as:

    K2cloud SaaS
    K2view Orchestrator + K2view runtime infrastructure

    K2cloud Self-Hosted
    K2view Orchestrator + customer runtime infrastructure

    Air-Gapped Fabric
    Customer operational tooling + customer runtime infrastructure

## K2cloud SaaS

K2cloud SaaS provides the K2cloud Orchestrator and the runtime infrastructure as K2view-operated services.

Conceptually:

    K2cloud Orchestrator
       K2view-operated
              │
              ▼
            Site
              │
              ▼
            Space
              │
              ▼
    Kubernetes Infrastructure
       K2view-operated

K2view operates:

- the K2cloud Orchestrator,
- Kubernetes infrastructure,
- Site infrastructure,
- networking and ingress infrastructure,
- managed persistence,
- container image distribution,
- infrastructure monitoring,
- and the supporting SaaS platform.

Customers use K2cloud to manage their Projects and Spaces, deploy their K2view implementation, perform supported Space lifecycle operations, and manage application-level configuration and authorization.

K2cloud SaaS also provides K2cloud monitoring capabilities such as Metrics and Logs, in addition to the Kubernetes diagnostics available through Space Details.

This model minimizes the infrastructure responsibilities placed on the customer while retaining customer control over the K2view implementation and its application lifecycle.

For more information, see [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md).

## K2cloud Self-Hosted

K2cloud Self-Hosted combines the K2view-managed K2cloud Orchestrator with customer-managed Kubernetes runtime infrastructure.

Conceptually:

    K2cloud Orchestrator
       K2view-operated
              │
              ▼
       K2cloud Agent
              │
              ▼
            Site
              │
              ▼
            Space
              │
              ▼
    Kubernetes Infrastructure
       Customer-operated

The customer operates the runtime infrastructure, including:

- Kubernetes,
- infrastructure capacity,
- networking,
- ingress,
- DNS,
- certificates,
- storage,
- container registry,
- and infrastructure observability.

Most Self-Hosted environments use hyperscaler-managed Kubernetes services such as Amazon EKS, Azure Kubernetes Service (AKS), or Google Kubernetes Engine (GKE).

K2cloud remains the application-aware orchestration layer used to manage K2view environments on that infrastructure.

Customers therefore retain infrastructure control without having to build their own orchestration model for K2view Space lifecycle management.

K2cloud provides:

- Projects,
- Sites,
- Space Profiles,
- Fabric Images,
- Spaces,
- application deployment workflows,
- Space lifecycle operations,
- upgrades and rollbacks,
- Space status,
- and Space Details diagnostics.

Self-Hosted customers use their own monitoring, logging, alerting, and security-monitoring environment for the underlying infrastructure.

For more information, see [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md).

## Air-Gapped Fabric

Air-Gapped Fabric is not a K2cloud deployment model.

It is a customer-operated Fabric architecture that has no dependence on the K2cloud Orchestrator control plane.

Conceptually:

    Customer Operational Tooling
              │
              ▼
       K2view Fabric
              │
              ▼
    Kubernetes Infrastructure
       Customer-operated

Because K2cloud Orchestrator is not part of the architecture, K2cloud resources and lifecycle workflows are not available.

There are no K2cloud:

- Sites,
- Space Profiles,
- Fabric Images,
- Spaces,
- deployment workflows,
- Space lifecycle operations,
- Metrics and Logs,
- or Space Details diagnostics.

The customer operates the infrastructure and assumes responsibility for the orchestration capabilities that K2cloud would otherwise provide.

This includes:

- Fabric deployment and configuration,
- runtime topology,
- lifecycle operations,
- application deployment,
- software and image distribution,
- upgrades and rollbacks,
- monitoring and diagnostics,
- operational automation,
- backup and recovery,
- and associated operational procedures.

Air-Gapped Fabric provides the greatest separation from external services, but that isolation also transfers the operational responsibility for orchestration to the customer.

For more information, see [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md).

## Architecture Comparison

<table>
<thead>
<tr>
<th>Area</th>
<th>K2cloud SaaS</th>
<th>K2cloud Self-Hosted</th>
<th>Air-Gapped Fabric</th>
</tr>
</thead>
<tbody>
<tr>
<td>Deployment model</td>
<td>K2cloud</td>
<td>K2cloud</td>
<td>Fabric without K2cloud</td>
</tr>
<tr>
<td>K2cloud Orchestrator</td>
<td>K2view-operated</td>
<td>K2view-operated</td>
<td>Not used</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Site</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
<td>Not applicable</td>
</tr>
<tr>
<td>Space Profile</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
<td>Not applicable</td>
</tr>
<tr>
<td>Fabric Image selection</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
<td>Customer-managed</td>
</tr>
<tr>
<td>Space lifecycle</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
<td>Not applicable</td>
</tr>
<tr>
<td>Application deployment</td>
<td>K2cloud workflows available</td>
<td>K2cloud workflows available</td>
<td>Customer-managed</td>
</tr>
<tr>
<td>Container image distribution</td>
<td>K2view-operated</td>
<td>Customer-managed registry</td>
<td>Customer-managed</td>
</tr>
<tr>
<td>Persistence</td>
<td>Managed</td>
<td>Managed or noSdb</td>
<td>Customer-managed</td>
</tr>
<tr>
<td>Infrastructure observability</td>
<td>K2view-operated</td>
<td>Customer-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>K2cloud Metrics and Logs</td>
<td>Available</td>
<td>Not available</td>
<td>Not applicable</td>
</tr>
<tr>
<td>Space Details diagnostics</td>
<td>Available</td>
<td>Available</td>
<td>Not applicable</td>
</tr>
<tr>
<td>Upgrade orchestration</td>
<td>K2cloud workflow</td>
<td>K2cloud workflow</td>
<td>Customer-managed</td>
</tr>
</tbody>
</table>

The fundamental architectural distinction is therefore not whether Kubernetes is customer-managed.

Both K2cloud Self-Hosted and Air-Gapped Fabric can use customer-managed Kubernetes infrastructure.

The distinction is whether the environment uses the K2cloud Orchestrator:

    Customer-managed Kubernetes
              │
              ├── With K2cloud Orchestrator
              │       → K2cloud Self-Hosted
              │
              └── Without K2cloud Orchestrator
                      → Air-Gapped Fabric

This determines whether the customer can use the K2cloud resource model and application-aware lifecycle workflows or must provide those operational capabilities independently.

## Choosing an Architecture

The appropriate architecture depends primarily on infrastructure ownership, connectivity requirements, isolation requirements, and the amount of operational responsibility the customer intends to assume.

**K2cloud SaaS** is appropriate when the customer wants K2view to operate both the K2cloud control plane and the runtime infrastructure.

**K2cloud Self-Hosted** is appropriate when the customer requires control of the runtime infrastructure while retaining centralized K2cloud orchestration and Space lifecycle management.

**Air-Gapped Fabric** is appropriate when the environment must operate without dependence on the K2cloud Orchestrator control plane and the customer is prepared to operate the resulting Fabric lifecycle independently.

The choice between Self-Hosted and Air-Gapped should therefore consider more than infrastructure location.

Both can place Fabric on customer-operated infrastructure. The architectural tradeoff is whether the customer retains K2cloud as the application-aware orchestration layer or assumes responsibility for providing those operational capabilities itself.

## Related Documentation

- [What is K2cloud?](/articles/80_k2cloud/01-overview/80_k2cloud_overview_what_is_k2cloud.md)
- [Deployment Models](/articles/80_k2cloud/01-overview/80_k2cloud_overview_deployment_models.md)
- [Control Plane Services Architecture](/articles/80_k2cloud/14-reference/80_k2cloud_architecture_control_plane_services.md)
- [Runtime Environments Architecture](/articles/80_k2cloud/14-reference/80_k2cloud_architecture_runtime_environments.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)