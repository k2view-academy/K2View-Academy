# Air-Gapped Operational Model

## Table of Contents

- [Overview](#overview)
- [Responsibility Model](#responsibility-model)
- [Infrastructure Operations](#infrastructure-operations)
- [Fabric Lifecycle Operations](#fabric-lifecycle-operations)
- [Application Deployment](#application-deployment)
- [Software and Artifact Management](#software-and-artifact-management)
- [Upgrades and Rollbacks](#upgrades-and-rollbacks)
- [Monitoring and Diagnostics](#monitoring-and-diagnostics)
- [Backup and Recovery](#backup-and-recovery)
- [Operational Automation](#operational-automation)
- [Support Boundary](#support-boundary)
- [Related Documentation](#related-documentation)

## Overview

An air-gapped Fabric deployment operates independently of the K2cloud Orchestrator control plane.

K2cloud Sites, Space Profiles, Fabric Images, and Space lifecycle management are not used in this deployment model.

The operational model is therefore fundamentally different from K2cloud SaaS and K2cloud Self-Hosted.

## Responsibility Model

In an air-gapped deployment, the customer assumes responsibility for the complete runtime and operational environment.

<table>
<thead>
<tr>
<th>Area</th>
<th>Responsibility</th>
</tr>
</thead>
<tbody>
<tr>
<td>Kubernetes infrastructure</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure capacity</td>
<td>Customer</td>
</tr>
<tr>
<td>Networking and ingress</td>
<td>Customer</td>
</tr>
<tr>
<td>DNS and certificates</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure identity and access</td>
<td>Customer</td>
</tr>
<tr>
<td>Storage and databases</td>
<td>Customer</td>
</tr>
<tr>
<td>Container registries</td>
<td>Customer</td>
</tr>
<tr>
<td>Software and artifact distribution</td>
<td>Customer</td>
</tr>
<tr>
<td>Fabric deployment and configuration</td>
<td>Customer</td>
</tr>
<tr>
<td>Fabric runtime lifecycle</td>
<td>Customer</td>
</tr>
<tr>
<td>Application deployment</td>
<td>Customer</td>
</tr>
<tr>
<td>Fabric upgrades and rollbacks</td>
<td>Customer</td>
</tr>
<tr>
<td>Monitoring, logging, and alerting</td>
<td>Customer</td>
</tr>
<tr>
<td>Backup and recovery</td>
<td>Customer</td>
</tr>
<tr>
<td>Operational tooling and automation</td>
<td>Customer</td>
</tr>
<tr>
<td>Runtime authorization</td>
<td>Customer</td>
</tr>
</tbody>
</table>

K2view provides the Fabric software and associated product support, but the customer operates the environment in which Fabric is deployed.

## Infrastructure Operations

The customer operates the Kubernetes platform and all supporting infrastructure required by Fabric.

This includes areas such as:

- Kubernetes availability and capacity,
- compute resources,
- networking,
- ingress,
- DNS and certificates,
- storage,
- databases,
- container registries,
- infrastructure identity,
- and infrastructure security.

The customer must establish operational procedures for maintaining these components and managing changes that can affect Fabric.

## Fabric Lifecycle Operations

Without K2cloud Orchestrator, Fabric lifecycle operations are not initiated through K2cloud Spaces or Space Profiles.

The customer must establish its own procedures and tooling for operations such as:

- provisioning Fabric environments,
- configuring runtime topology,
- starting and restarting runtime components,
- managing runtime availability,
- replacing or recovering runtime components,
- and decommissioning environments.

These procedures may use Kubernetes and customer automation, but they remain customer-owned operational processes.

K2view does not prescribe the customer's infrastructure automation framework through K2cloud in this model.

## Application Deployment

The customer is responsible for establishing the process used to move K2view implementation content between environments.

Unlike K2cloud, there is no K2cloud workflow providing:

    Deploy Environment
          ↓
    Activate Environment
          ↓
      Deploy Project

The customer must establish the deployment and change-management procedures appropriate to its environment.

These procedures should address:

- versioned application content,
- environment configuration,
- deployment sequencing,
- validation,
- rollback,
- and promotion between environments.

## Software and Artifact Management

Air-gapped environments commonly restrict or prohibit direct access to external software repositories and registries.

The customer is therefore responsible for establishing a controlled process for making required software and artifacts available within the isolated environment.

This includes maintaining the internal repositories or registries required by the deployment and controlling how approved artifacts are introduced into the environment.

The specific transfer and approval process depends on the customer's security architecture and operational requirements.

## Upgrades and Rollbacks

Fabric upgrades in an air-gapped environment are customer-operated lifecycle activities.

The customer is responsible for:

- obtaining the required Fabric software or images,
- transferring approved artifacts into the isolated environment,
- making those artifacts available to the runtime infrastructure,
- planning the upgrade,
- executing the required lifecycle procedures,
- validating the environment following the upgrade,
- and maintaining an appropriate rollback process.

The K2cloud self-service upgrade workflow does not apply because the environment is not managed by K2cloud Orchestrator.

The detailed installation and upgrade procedure depends on the Fabric deployment architecture and should follow the applicable Fabric installation and upgrade documentation.

## Monitoring and Diagnostics

The customer is responsible for the complete observability framework used by the air-gapped environment.

This includes:

- Kubernetes monitoring,
- infrastructure metrics,
- centralized logging,
- Fabric monitoring,
- alerting,
- security monitoring,
- audit collection,
- SIEM integration,
- and incident response.

K2cloud monitoring and Space Details diagnostics are not available because the deployment does not use K2cloud Orchestrator.

Customers must therefore ensure that their observability environment provides sufficient information to operate and troubleshoot both the infrastructure and Fabric runtime.

## Backup and Recovery

Backup, recovery, and disaster-recovery procedures are customer responsibilities.

Customers should establish procedures appropriate to their architecture for protecting and recovering:

- persistent data,
- databases,
- configuration,
- application content,
- infrastructure configuration,
- and other components required to restore the Fabric environment.

Recovery procedures should be documented and validated as part of the customer's operational model.

## Operational Automation

Air-gapped operation does not necessarily imply manual operation.

Customers can automate provisioning, deployments, lifecycle operations, upgrades, monitoring, and recovery using their own infrastructure and automation frameworks.

However, the customer is responsible for designing, implementing, testing, and maintaining that automation.

This is an important distinction from K2cloud Self-Hosted, where the customer operates the runtime infrastructure but K2cloud continues to provide the Fabric-aware orchestration layer.

## Support Boundary

K2view provides product support for Fabric.

The customer remains responsible for operating and troubleshooting the infrastructure and operational tooling surrounding Fabric.

When escalating an issue to K2view, customers should isolate the issue as far as practical and provide the relevant Fabric, Kubernetes, infrastructure, and operational information required to investigate it.

The absence of K2cloud Orchestrator means that K2view does not have the same control-plane context available for a K2cloud-managed Space.

## Related Documentation

- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
- [Air-Gapped Runtime Operations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_runtime_operations.md)
- [Air-Gapped Customer Responsibilities](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_customer_responsibilities.md)
- [Air-Gapped Operational Expectations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_expectations.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)