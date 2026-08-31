# Deployment Models

## Overview

K2view Fabric can be deployed and operated using different models based on infrastructure ownership, connectivity requirements, and whether K2cloud manages the environment.

K2cloud provides two deployment models:

- **K2cloud SaaS** — K2view manages and operates both the K2cloud Orchestrator and the runtime infrastructure.
- **K2cloud Self-Hosted** — K2view manages and operates the K2cloud Orchestrator, while the customer manages and operates the runtime infrastructure.

The **K2cloud Orchestrator is a SaaS component that K2view manages and operates.** It provides the centralized control plane and operational model used by both K2cloud deployment models.

You can also deploy Fabric **without K2cloud**, including in air-gapped Kubernetes environments. In this model, there is no dependency on the K2cloud Orchestrator and the customer assumes responsibility for the infrastructure as well as the processes, automation, and procedures required to manage the Fabric runtime lifecycle.

This distinction is fundamental: **K2cloud SaaS and K2cloud Self-Hosted are K2cloud deployment models; air-gapped is a Fabric deployment model that does not use K2cloud.**

## K2cloud SaaS

With **K2cloud SaaS**, K2view manages and operates both:

- the K2cloud Orchestrator, and
- the runtime infrastructure on which customer Spaces run.

Customers use the K2cloud Orchestrator to create and manage Projects and Spaces, deploy application changes, perform supported lifecycle operations, and access operational information without having to operate the underlying Kubernetes and supporting infrastructure.

K2cloud SaaS provides the greatest reduction in customer infrastructure responsibility.

The customer remains responsible for its K2view implementation, application configuration, access governance, integrations, deployments, and application-level validation.

For more information, see [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md).

## K2cloud Self-Hosted

With **K2cloud Self-Hosted**, the customer provides and operates the Kubernetes infrastructure on which its Spaces run, while K2view manages and operates the K2cloud Orchestrator.

Most K2cloud Self-Hosted deployments use a managed Kubernetes service from a hyperscaler, such as:

- Amazon Elastic Kubernetes Service (EKS),
- Azure Kubernetes Service (AKS), or
- Google Kubernetes Engine (GKE).

The K2cloud Orchestrator manages the lifecycle of the Fabric environments deployed to that Kubernetes infrastructure. The customer remains responsible for the underlying Kubernetes service and associated infrastructure, including networking, ingress, storage, security, and infrastructure observability.

This creates a deliberate separation of responsibilities:

- **K2view operates the K2cloud orchestration layer.**
- **The customer operates the Kubernetes runtime infrastructure.**

The customer therefore retains control of where Fabric runs and how that infrastructure integrates with its enterprise environment, while K2cloud provides the application-aware operational model used to provision, deploy, upgrade, and manage Fabric Spaces.

## Non-K2cloud Air-Gapped Fabric

An **air-gapped Kubernetes deployment** operates without dependence on the K2cloud Orchestrator control plane.

This model is appropriate where security, regulatory, or organizational requirements require the Fabric environment to operate without the connectivity needed to use the K2view-managed SaaS control plane.

The customer assumes responsibility for the complete operating environment, including the infrastructure and the processes required to deploy, maintain, upgrade, and operate Fabric.

This distinction is important. Removing the dependency on the K2cloud Orchestrator provides additional isolation, but it also removes the operational capabilities provided by the Orchestrator.

The customer must therefore establish its own procedures and automation for lifecycle activities that would otherwise be managed through K2cloud.

Organizations considering an air-gapped deployment should evaluate both the isolation requirement and the additional operational ownership that results from operating without the K2cloud Orchestrator.

For more information, see [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md).

## K2cloud and Air-Gapped Deployment Comparison

The following table compares the two K2cloud deployment models with an air-gapped Fabric deployment that does not use K2cloud.

<table>
<thead>
<tr>
<th>Area</th>
<th>K2cloud SaaS</th>
<th>K2cloud Self-Hosted</th>
<th>Non-K2cloud Air-Gapped</th>
</tr>
</thead>
<tbody>
<tr>
<td>K2cloud Orchestrator</td>
<td>K2view-managed and operated SaaS component</td>
<td>K2view-managed and operated SaaS component</td>
<td>Not used</td>
</tr>
<tr>
<td>Runtime infrastructure</td>
<td>K2view operated</td>
<td>Customer operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>K2view operated</td>
<td>Customer operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Runtime lifecycle orchestration</td>
<td>K2cloud Orchestrator</td>
<td>K2cloud Orchestrator</td>
<td>Customer-defined processes and automation</td>
</tr>
<tr>
<td>Space lifecycle management</td>
<td>K2cloud Orchestrator</td>
<td>K2cloud Orchestrator</td>
<td>Customer-defined processes and automation</td>
</tr>
<tr>
<td>Application deployment</td>
<td>K2cloud deployment workflows or customer CI/CD</td>
<td>K2cloud deployment workflows or customer CI/CD</td>
<td>Customer-defined deployment process</td>
</tr>
<tr>
<td>Fabric upgrades</td>
<td>Managed through K2cloud</td>
<td>Managed through K2cloud</td>
<td>Customer-defined upgrade process</td>
</tr>
<tr>
<td>Infrastructure observability</td>
<td>K2view operated</td>
<td>Customer operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Operational isolation</td>
<td>K2view SaaS operating model</td>
<td>Customer-controlled runtime with K2view SaaS control plane</td>
<td>No dependency on the K2cloud Orchestrator control plane</td>
</tr>
</tbody>
</table>

## Selecting a Deployment Model

The appropriate deployment model depends on the customer's requirements for infrastructure ownership, network connectivity, security, regulatory compliance, and operational responsibility.

Choose **K2cloud SaaS** when K2view should operate both the orchestration layer and the runtime infrastructure.

Choose **K2cloud Self-Hosted** when the runtime must remain within customer-controlled infrastructure while retaining the centralized lifecycle and operational capabilities of the K2cloud Orchestrator.

Where an organization cannot use the K2cloud Orchestrator because of isolation, security, regulatory, or connectivity requirements, Fabric can instead be deployed in an **air-gapped environment without K2cloud**. The organization must then be prepared to assume the corresponding infrastructure and Fabric runtime lifecycle responsibilities.

Air-gapped should therefore not be viewed simply as a more isolated version of K2cloud Self-Hosted. The models differ in an important architectural respect: **K2cloud Self-Hosted retains a K2view-managed orchestration layer; air-gapped does not.**


## Related Documentation

- [K2cloud SaaS vs K2cloud Self-Hosted](/articles/80_k2cloud/01-overview/80_k2cloud_overview_k2cloud_saas_vs_self_hosted.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
