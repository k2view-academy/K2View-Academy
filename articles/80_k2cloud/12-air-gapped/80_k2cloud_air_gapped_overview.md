# Air-Gapped Fabric Deployments

## Overview

An air-gapped deployment is a customer-operated K2view Fabric deployment without dependence on the K2cloud Orchestrator control plane.

Air-gapped deployment is **not a K2cloud deployment model**. K2cloud provides two deployment models:

- **K2cloud SaaS**, where K2view operates both the K2cloud Orchestrator and the runtime infrastructure.
- **K2cloud Self-Hosted**, where K2view operates the K2cloud Orchestrator and the customer operates the runtime infrastructure.

In an air-gapped architecture, the customer operates Fabric independently of the K2cloud Orchestrator and assumes responsibility for the infrastructure, deployment, orchestration, lifecycle, monitoring, and operational procedures required to operate the environment.

## Architectural Model

K2cloud Self-Hosted separates the K2view-managed control plane from customer-managed runtime infrastructure:

    K2cloud Orchestrator
    K2view-managed SaaS control plane
              │
              ▼
       K2cloud Agent / Site
              │
              ▼
    Customer-managed Kubernetes
              │
              ▼
         K2view Fabric

An air-gapped deployment removes the dependency on the K2cloud Orchestrator:

    Customer operational tooling
              │
              ▼
    Customer-managed Kubernetes
              │
              ▼
         K2view Fabric

The customer must therefore provide the operational tooling and procedures required to manage the Fabric deployment.

## Why Use an Air-Gapped Architecture

Organizations may require an air-gapped architecture when security, regulatory, network, or organizational requirements prevent the runtime environment from depending on an external control plane.

This can include environments that require:

- highly restricted or isolated networks,
- no outbound connectivity to external SaaS services,
- complete customer control of operational infrastructure,
- customer-controlled movement of software and artifacts,
- or infrastructure and operational processes that must remain within the customer's security boundary.

Where these requirements apply, an air-gapped architecture provides the required isolation.

## The Operational Tradeoff

Removing dependence on the K2cloud Orchestrator also removes the application-aware orchestration capabilities it provides.

With K2cloud, users work with resources such as:

- Projects,
- Sites,
- Space Profiles,
- Fabric Images,
- and Spaces.

K2cloud uses these resources to provide standardized workflows for operations such as:

- creating and deleting Spaces,
- managing Fabric runtime topology,
- deploying environments and Project content,
- restarting Fabric Spaces,
- managing Fabric and Studio version upgrades and rollbacks,
- and inspecting Kubernetes runtime diagnostics.

In an air-gapped deployment, these K2cloud resources and workflows are not available.

The customer must establish its own procedures, tooling, and automation for the corresponding Fabric lifecycle operations.

> **The decision to remove the K2cloud Orchestrator dependency is also a decision to assume responsibility for the orchestration it provides.**

## Customer Operational Ownership

Air-gapped deployments require complete customer ownership of the runtime environment.

This includes responsibility for areas such as:

- Kubernetes infrastructure,
- networking and ingress,
- DNS and certificates,
- storage,
- databases,
- container registries,
- infrastructure identity and access,
- Fabric deployment,
- Fabric configuration,
- runtime lifecycle operations,
- software and image distribution,
- upgrades and rollbacks,
- monitoring and logging,
- troubleshooting,
- backup and recovery,
- and operational automation.

The customer determines the technologies and procedures used to provide these capabilities.

## Air-Gapped Compared with K2cloud Self-Hosted

K2cloud Self-Hosted and air-gapped Fabric deployments both allow the customer to operate the runtime infrastructure.

The key difference is the control plane.

<table>
<thead>
<tr>
<th>Area</th>
<th>K2cloud Self-Hosted</th>
<th>Air-Gapped Fabric</th>
</tr>
</thead>
<tbody>
<tr>
<td>Runtime infrastructure</td>
<td>Customer-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Kubernetes</td>
<td>Customer-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>K2cloud Orchestrator</td>
<td>K2view-operated SaaS control plane</td>
<td>Not used</td>
</tr>
<tr>
<td>Fabric lifecycle orchestration</td>
<td>Provided through K2cloud</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Infrastructure observability</td>
<td>Customer-operated</td>
<td>Customer-operated</td>
</tr>
<tr>
<td>Operational tooling and automation</td>
<td>K2cloud plus customer infrastructure tooling</td>
<td>Customer-provided</td>
</tr>
</tbody>
</table>

For organizations that require customer control of the runtime infrastructure but do not require complete isolation from the K2cloud Orchestrator, K2cloud Self-Hosted retains centralized Fabric lifecycle orchestration while the runtime infrastructure remains customer-operated.

For more information, see [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md).

## Operational Model

Air-gapped environments should be approached as independently operated Fabric platforms.

Organizations adopting this model should establish documented procedures for:

- provisioning and configuring Fabric environments,
- deploying application content,
- performing runtime lifecycle operations,
- distributing and managing software artifacts,
- performing upgrades and rollbacks,
- monitoring platform and application health,
- troubleshooting failures,
- backup and recovery,
- and managing operational changes.

These procedures replace the centralized lifecycle and deployment workflows that would otherwise be provided through K2cloud.

The remaining articles in this section describe these operational responsibilities and expectations.

## Related Documentation

- [Air-Gapped Operational Model](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_model.md)
- [Air-Gapped Runtime Operations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_runtime_operations.md)
- [Air-Gapped Customer Responsibilities](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_customer_responsibilities.md)
- [Air-Gapped Operational Expectations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_expectations.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)