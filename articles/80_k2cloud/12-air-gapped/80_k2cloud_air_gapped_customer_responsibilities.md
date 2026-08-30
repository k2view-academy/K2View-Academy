# Air-Gapped Customer Responsibilities

## Table of Contents

- [Overview](#overview)
- [Infrastructure](#infrastructure)
- [Fabric Deployment and Configuration](#fabric-deployment-and-configuration)
- [Runtime Lifecycle](#runtime-lifecycle)
- [Application Deployment](#application-deployment)
- [Software and Artifact Management](#software-and-artifact-management)
- [Fabric Upgrades and Rollbacks](#fabric-upgrades-and-rollbacks)
- [Identity and Access](#identity-and-access)
- [Monitoring and Troubleshooting](#monitoring-and-troubleshooting)
- [Persistence, Backup, and Recovery](#persistence-backup-and-recovery)
- [Operational Procedures and Automation](#operational-procedures-and-automation)
- [Support](#support)
- [Related Documentation](#related-documentation)

## Overview

In an air-gapped Fabric deployment, the customer owns and operates the complete runtime and operational environment.

Because the deployment does not depend on the K2cloud Orchestrator control plane, the customer is responsible not only for the underlying infrastructure but also for the deployment, lifecycle, automation, monitoring, and operational processes required to run Fabric.

This article summarizes those customer responsibilities.

## Infrastructure

The customer is responsible for the infrastructure on which Fabric runs.

This includes:

- Kubernetes,
- compute capacity,
- networking,
- ingress,
- DNS and certificates,
- storage,
- databases,
- container registries,
- infrastructure identity and access,
- and infrastructure security.

The customer is responsible for maintaining the availability, capacity, configuration, and security of these components.

## Fabric Deployment and Configuration

The customer is responsible for deploying and configuring Fabric within its infrastructure.

This includes maintaining the deployment configuration required to establish and reproduce the runtime environment.

Without K2cloud, there are no Sites or Space Profiles defining runtime placement and topology through the K2cloud Orchestrator.

The customer must therefore maintain the infrastructure and deployment configuration required for each Fabric environment.

## Runtime Lifecycle

The customer is responsible for Fabric runtime lifecycle operations.

This includes establishing procedures for:

- provisioning environments,
- starting and restarting runtime components,
- maintaining runtime topology,
- recovering failed components,
- maintaining runtime availability,
- and decommissioning environments.

These operations may be automated, but the customer is responsible for implementing, testing, and maintaining that automation.

## Application Deployment

The customer owns the process used to deploy K2view implementation content and environment configuration.

Responsibilities include:

- maintaining versioned implementation content,
- managing environment-specific configuration,
- defining deployment procedures,
- controlling promotion between environments,
- validating deployments,
- and maintaining rollback procedures.

The K2cloud Deploy Environment and Deploy Project workflows are not available in an air-gapped deployment.

## Software and Artifact Management

The customer is responsible for making required software and artifacts available within the air-gapped environment.

This includes:

- obtaining approved Fabric software and container images,
- transferring artifacts into the isolated environment,
- maintaining internal repositories or registries,
- controlling artifact versions,
- and maintaining the organizational processes required to introduce new software.

These processes should support both initial deployment and ongoing maintenance.

## Fabric Upgrades and Rollbacks

The customer is responsible for planning and executing Fabric upgrades and rollbacks.

Responsibilities include:

- obtaining the required software or images,
- introducing them into the air-gapped environment,
- making them available to the runtime infrastructure,
- planning the upgrade,
- executing the applicable upgrade procedure,
- validating Fabric and the customer implementation,
- and maintaining an appropriate rollback process.

The K2cloud self-service upgrade and rollback workflow does not apply.

Customers should use the applicable Fabric installation and upgrade documentation for their deployment architecture.

## Identity and Access

The customer is responsible for identity and access across both the infrastructure and K2view runtime.

This includes:

- infrastructure identity and permissions,
- Kubernetes access,
- Fabric authentication,
- Fabric roles and permissions,
- identity-provider integration where applicable,
- and TDM permissions where applicable.

Access should be designed according to least privilege and the customer's security and operational requirements.

## Monitoring and Troubleshooting

The customer is responsible for the complete monitoring and troubleshooting framework.

This includes:

- infrastructure monitoring,
- Kubernetes monitoring,
- Fabric logging,
- centralized logging,
- alerting,
- audit collection,
- security monitoring,
- SIEM integration,
- and incident response.

K2cloud monitoring, Space status, and Space Details diagnostics are not available.

Customers must therefore maintain sufficient observability to investigate issues across the K2view application, Fabric runtime, Kubernetes platform, and supporting infrastructure.

## Persistence, Backup, and Recovery

The customer is responsible for persistent data and its lifecycle.

This includes establishing appropriate procedures for:

- database operation,
- object storage where applicable,
- backup,
- retention,
- recovery,
- disaster recovery,
- and validation of restored environments.

Backup and recovery procedures should include both persistent data and the configuration required to reproduce the Fabric environment.

## Operational Procedures and Automation

The customer is responsible for establishing the operational model used to manage the environment.

Documented procedures should cover areas such as:

- provisioning,
- deployment,
- runtime lifecycle,
- restart and recovery,
- software distribution,
- upgrades and rollbacks,
- monitoring and troubleshooting,
- backup and recovery,
- access changes,
- and decommissioning.

Customers can automate these activities using their preferred infrastructure and operational tooling.

However, the automation itself becomes part of the customer-operated platform and must be maintained accordingly.

## Support

K2view provides product support for Fabric, while the customer remains responsible for the infrastructure and operational framework surrounding the product.

Before escalating an issue, customers should investigate the environment sufficiently to determine whether the problem concerns Fabric or a customer-managed infrastructure or operational component.

Relevant Fabric, Kubernetes, infrastructure, configuration, and diagnostic information should be provided when escalating an issue to K2view.

## Related Documentation

- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
- [Air-Gapped Operational Model](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_model.md)
- [Air-Gapped Runtime Operations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_runtime_operations.md)
- [Air-Gapped Operational Expectations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_expectations.md)