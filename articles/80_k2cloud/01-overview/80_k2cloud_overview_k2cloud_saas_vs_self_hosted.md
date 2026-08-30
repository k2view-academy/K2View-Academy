# K2cloud SaaS vs K2cloud Self-Hosted


## Overview

K2cloud SaaS and K2cloud Self-Hosted both use the **K2cloud Orchestrator**, a SaaS component managed and operated by K2view, as the centralized control plane for deploying, managing, and operating K2view Fabric environments.

The primary difference between the two models is **who operates the runtime infrastructure**.

- With **K2cloud SaaS**, K2view operates the runtime infrastructure.
- With **K2cloud Self-Hosted**, the customer operates the runtime infrastructure in its own environment.

In both models, customers benefit from the same K2cloud operational model and lifecycle capabilities provided by the K2cloud Orchestrator. The customer does not need to reproduce those capabilities through infrastructure-level procedures and DevOps automation.

This document explains the distinction at a decision-making level. Detailed responsibilities and operational requirements are covered separately.


## K2cloud SaaS

With K2cloud SaaS, K2view operates both the K2cloud Orchestrator and the infrastructure on which customer Spaces run.

Customers use the K2cloud Orchestrator to manage their Projects and Spaces without operating the underlying Kubernetes and supporting runtime infrastructure.

K2view operates the SaaS platform infrastructure, including the infrastructure required to run customer Spaces.

Customers remain responsible for their K2view implementation and activities such as:

- managing Projects and application configuration,
- managing and governing user access,
- configuring and validating application integrations,
- deploying and validating application changes,
- and coordinating application lifecycle activities.

K2cloud SaaS therefore reduces the customer's infrastructure responsibilities without eliminating the customer's responsibility for its K2view implementation.

## K2cloud Self-Hosted

With K2cloud Self-Hosted, the customer provides and operates the runtime infrastructure on which its Spaces run.

The **K2cloud Orchestrator remains a K2view-operated SaaS control plane** and provides the centralized orchestration and lifecycle management used to create and operate Spaces.

The customer is responsible for the infrastructure and services required by the runtime environment. Depending on the implementation, these responsibilities can include:

- Kubernetes infrastructure,
- networking and connectivity,
- ingress and DNS,
- certificates,
- persistent storage,
- external PostgreSQL and blob storage,
- monitoring and infrastructure observability,
- backup and recovery,
- and the operational readiness of the runtime environment.

K2cloud Self-Hosted is therefore not a separately operated copy of K2cloud. It combines **customer-operated runtime infrastructure with the centralized K2cloud Orchestrator**.

## Comparison

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
<td>K2view-operated SaaS service</td>
<td>K2view-operated SaaS service</td>
</tr>
<tr>
<td>Runtime infrastructure</td>
<td>K2view operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>K2view operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Runtime networking</td>
<td>K2view-managed service model</td>
<td>Customer infrastructure responsibility</td>
</tr>
<tr>
<td>Ingress and DNS</td>
<td>K2view-managed service model</td>
<td>Customer infrastructure responsibility</td>
</tr>
<tr>
<td>Infrastructure monitoring</td>
<td>K2view operated</td>
<td>Customer operated</td>
</tr>
<tr>
<td>Infrastructure backup and recovery</td>
<td>K2view-operated service model</td>
<td>Customer responsibility</td>
</tr>
<tr>
<td>Project and application lifecycle</td>
<td>Customer responsibility with K2view platform support</td>
<td>Customer responsibility with K2view platform support</td>
</tr>
<tr>
<td>Primary benefit</td>
<td>Reduced infrastructure ownership</td>
<td>Control of runtime infrastructure and network boundaries</td>
</tr>
</tbody>
</table>

## Choosing Between the Models

**K2cloud SaaS** is generally appropriate when the customer wants K2view to operate the runtime infrastructure and reduce the infrastructure responsibilities associated with running Fabric.

**K2cloud Self-Hosted** is generally appropriate when the customer needs Fabric to run within customer-controlled infrastructure because of network architecture, infrastructure standards, security requirements, regulatory requirements, or other operational considerations.

The choice changes how infrastructure responsibilities are divided. It does not change the fundamental K2cloud operating model: Projects and Spaces continue to be managed through the K2cloud Orchestrator.

## What About Air-Gapped Deployments?

Air-gapped deployments are a separate operating model.

Unlike K2cloud SaaS and K2cloud Self-Hosted, an air-gapped deployment operates **without dependence on the K2cloud Orchestrator control plane**. This may be necessary when organizational or security requirements prohibit the connectivity required to use a SaaS control plane.

That isolation has an operational consequence.

Without the K2cloud Orchestrator, the customer must provide the processes, automation, and operational procedures needed to manage the Fabric runtime lifecycle. Capabilities that K2cloud otherwise provides through a consistent operational model must instead be addressed within the customer's own platform and DevOps practices.

Examples include:

- structuring and managing runtime environments,
- provisioning and managing Fabric deployments,
- managing Space lifecycle operations,
- coordinating Fabric upgrades,
- implementing rolling runtime updates,
- promoting application code and configuration between environments,
- integrating deployment activities with CI/CD where required,
- and providing the operational procedures and automation needed to perform these activities consistently.

The K2cloud Orchestrator provides these capabilities through a K2view-managed SaaS control plane and presents them through a purpose-built management interface rather than requiring customers to operate primarily at the Kubernetes infrastructure level.

Organizations evaluating an air-gapped architecture should therefore consider both sides of the decision: **the isolation gained by removing the SaaS control-plane dependency and the additional operational responsibility created by doing so.**

Where security and connectivity policies permit it, K2cloud Self-Hosted provides an alternative model: the Fabric runtime remains within customer-controlled infrastructure while the K2cloud Orchestrator provides centralized lifecycle management.

Air-gapped deployments should therefore not be treated simply as K2cloud Self-Hosted environments without Internet connectivity.


## Related Documentation

- [Deployment Models](/articles/80_k2cloud/01-overview/80_k2cloud_overview_deployment_models.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
