# Shared Responsibility Model

## Table of Contents

- [Overview](#overview)
- [K2cloud SaaS Responsibilities](#k2cloud-saas-responsibilities)
- [K2cloud Self-Hosted Responsibilities](#k2cloud-self-hosted-responsibilities)
- [Responsibility Comparison](#responsibility-comparison)
- [Shared Operational Responsibilities](#shared-operational-responsibilities)
- [Air-Gapped Environments](#air-gapped-environments)
- [Related Documentation](#related-documentation)

## Overview

The K2cloud shared responsibility model defines how operational responsibilities are divided between K2view and the customer.

The division of responsibility depends primarily on the K2cloud deployment model:

- With **K2cloud SaaS**, K2view manages and operates both the K2cloud Orchestrator and the runtime infrastructure.
- With **K2cloud Self-Hosted**, K2view manages and operates the K2cloud Orchestrator, while the customer manages and operates the Kubernetes runtime infrastructure.

In both models, the customer remains responsible for its K2view implementation, application configuration, access governance, integrations, and application lifecycle.

Fabric can also be deployed in an **air-gapped environment without K2cloud**. In that case, the customer assumes responsibility for both the infrastructure and the operational processes and automation that K2cloud would otherwise provide.

## K2cloud SaaS Responsibilities

With K2cloud SaaS, K2view operates the K2cloud platform and the infrastructure used to run customer Spaces.

K2view responsibilities include:

- operating the K2cloud Orchestrator,
- operating the Kubernetes infrastructure,
- managing the supporting cloud infrastructure,
- managing the K2cloud service infrastructure,
- monitoring the infrastructure and platform services,
- and maintaining the infrastructure required to operate customer Spaces.

The customer remains responsible for its implementation, including:

- application design and development,
- Project configuration,
- application configuration,
- user and access governance,
- customer data and application integrations,
- environment configuration,
- deployment and validation of application changes,
- and application-level testing and operational validation.

K2cloud SaaS reduces the customer's infrastructure responsibilities but does not transfer responsibility for the customer's K2view implementation to K2view.

## K2cloud Self-Hosted Responsibilities

With K2cloud Self-Hosted, K2view operates the K2cloud Orchestrator while the customer operates the Kubernetes infrastructure on which Fabric Spaces run.

Most K2cloud Self-Hosted deployments use a managed Kubernetes service from a hyperscaler, such as Amazon EKS, Azure AKS, or Google GKE.

K2view is responsible for:

- operating the K2cloud Orchestrator,
- providing the K2cloud orchestration and lifecycle management capabilities,
- and maintaining the K2cloud services used to manage customer Spaces.

The customer is responsible for the runtime infrastructure, including:

- the Kubernetes service and cluster,
- cloud infrastructure associated with the runtime environment,
- networking and connectivity,
- ingress and DNS,
- certificates associated with customer infrastructure,
- persistent storage,
- external databases and blob storage where applicable,
- infrastructure security,
- infrastructure monitoring and observability,
- backup and recovery of customer-managed infrastructure and data,
- and maintaining the infrastructure required for K2cloud to manage the deployed Spaces.

The customer also retains the same implementation responsibilities it has with K2cloud SaaS, including application development, configuration, access governance, integrations, deployments, testing, and validation.

The important boundary is that **the customer operates the Kubernetes runtime infrastructure, while K2view operates the K2cloud orchestration layer used to manage Fabric on that infrastructure.**

## Responsibility Comparison

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
<td>K2view</td>
<td>K2view</td>
</tr>
<tr>
<td>Runtime Kubernetes infrastructure</td>
<td>K2view</td>
<td>Customer</td>
</tr>
<tr>
<td>Cloud infrastructure supporting the runtime</td>
<td>K2view</td>
<td>Customer</td>
</tr>
<tr>
<td>Runtime networking</td>
<td>K2view</td>
<td>Customer</td>
</tr>
<tr>
<td>Ingress and DNS</td>
<td>K2view</td>
<td>Customer</td>
</tr>
<tr>
<td>Customer-managed databases and storage</td>
<td>As applicable</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure monitoring</td>
<td>K2view</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure backup and recovery</td>
<td>K2view-managed service model</td>
<td>Customer</td>
</tr>
<tr>
<td>Fabric lifecycle orchestration</td>
<td>K2cloud Orchestrator</td>
<td>K2cloud Orchestrator</td>
</tr>
<tr>
<td>Application development and configuration</td>
<td>Customer</td>
<td>Customer</td>
</tr>
<tr>
<td>Application integrations</td>
<td>Customer</td>
<td>Customer</td>
</tr>
<tr>
<td>User and access governance</td>
<td>Customer</td>
<td>Customer</td>
</tr>
<tr>
<td>Application deployment and validation</td>
<td>Customer</td>
<td>Customer</td>
</tr>
</tbody>
</table>

## Shared Operational Responsibilities

Some activities require coordination between K2view and the customer rather than belonging exclusively to one party.

For example, in a K2cloud Self-Hosted deployment, K2cloud can manage a Fabric Space only when the customer-managed Kubernetes environment and its supporting services are available and correctly configured.

Similarly, troubleshooting may cross the responsibility boundary. A Space-level issue may require investigation through K2cloud and Fabric, while an infrastructure issue may require investigation of the customer's Kubernetes cluster, networking, storage, or cloud services.

The responsibility model therefore defines ownership, but it does not eliminate the need for coordination when an issue crosses the boundary between K2cloud and customer-managed infrastructure.

## Air-Gapped Environments

The K2cloud shared responsibility model does not apply in the same way to an air-gapped Fabric deployment because the environment does not use the K2cloud Orchestrator.

In an air-gapped environment, the customer assumes responsibility for the runtime infrastructure and for establishing the operational processes and automation required to manage the Fabric lifecycle.

This includes responsibilities that K2cloud would otherwise provide through its application-aware orchestration layer.

The distinction is therefore more than infrastructure ownership:

- **K2cloud Self-Hosted** gives the customer control of the Kubernetes infrastructure while retaining K2view-managed Fabric orchestration.
- **Air-gapped without K2cloud** gives the customer control of the complete environment while also transferring the corresponding orchestration and lifecycle responsibilities to the customer.

For more information, see [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md).

## Related Documentation

- [Deployment Models](/articles/80_k2cloud/01-overview/80_k2cloud_overview_deployment_models.md)
- [K2cloud SaaS vs K2cloud Self-Hosted](/articles/80_k2cloud/01-overview/80_k2cloud_overview_k2cloud_saas_vs_self_hosted.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
