# Air-Gapped Runtime Operations

## Overview

In an air-gapped Fabric deployment, runtime operations are performed using customer-managed infrastructure, procedures, and automation.

There is no K2cloud Orchestrator managing Spaces or translating application-level lifecycle actions into Kubernetes operations.

The customer must therefore establish repeatable operational procedures for managing the Fabric runtime throughout its lifecycle.

## Runtime Lifecycle

The customer is responsible for the complete lifecycle of each Fabric environment.

This includes procedures for:

- provisioning the runtime environment,
- configuring Fabric,
- starting and restarting runtime components,
- maintaining the required runtime topology,
- managing runtime availability,
- recovering failed components,
- scaling the supporting infrastructure as required,
- and decommissioning environments.

These procedures should be documented and automated where appropriate.

## Runtime Topology

K2cloud Space Profiles are not used in an air-gapped deployment.

The customer must establish the Fabric runtime topology required for each environment and ensure that the Kubernetes infrastructure provides the resources required to support it.

This includes maintaining the deployment configuration used to reproduce the environment consistently.

Changes to runtime topology should be managed through the customer's infrastructure and change-management processes.

## Application Deployment

Application deployment is also customer-operated.

The customer must establish procedures for moving versioned K2view implementation content and environment configuration between development, test, staging, and production environments as applicable.

The deployment process should define:

- the source of approved application content,
- environment-specific configuration,
- version identification,
- deployment sequencing,
- validation,
- rollback,
- and promotion controls.

Organizations may implement these procedures manually or through their own CI/CD and automation framework.

## Runtime Restarts

Restarting Fabric in an air-gapped environment is a customer-operated procedure.

The customer should establish a documented restart process appropriate to the runtime topology and availability requirements of the environment.

For multi-replica environments, operational procedures should consider service availability and the sequencing of runtime component restarts.

Unlike K2cloud-managed Spaces, there is no K2cloud **Restart Space** operation coordinating this lifecycle action.

## Upgrades and Rollbacks

The customer operates the Fabric upgrade lifecycle.

This includes:

- obtaining approved Fabric software or container images,
- transferring them into the isolated environment,
- publishing them to the appropriate internal repository or registry,
- planning the upgrade,
- performing the required runtime changes,
- validating Fabric and the customer implementation,
- and executing rollback procedures when required.

Upgrade procedures should account for the topology and availability requirements of the environment.

The K2cloud self-service upgrade and rollback workflow does not apply to an air-gapped Fabric deployment.

Customers should follow the applicable Fabric installation and upgrade documentation for their deployment architecture.

## Software and Image Distribution

Air-gapped environments require a controlled mechanism for introducing software into the isolated environment.

The customer is responsible for:

- obtaining approved software and container images,
- scanning or approving them according to organizational policy,
- transferring them across the security boundary,
- publishing them to internal repositories or registries,
- maintaining required versions,
- and controlling access to those artifacts.

The process should support both initial installation and subsequent Fabric maintenance and upgrades.

## Monitoring and Logging

All runtime monitoring and logging are customer-operated.

Customers should provide appropriate capabilities for:

- Fabric logs,
- Kubernetes logs,
- infrastructure metrics,
- runtime health,
- alerting,
- audit information,
- security monitoring,
- and operational troubleshooting.

These capabilities should integrate with the customer's existing monitoring and incident-management processes where appropriate.

K2cloud Metrics, Logs, Space status, Space Details, and Kubernetes diagnostics exposed through the K2cloud Orchestrator are not available in an air-gapped deployment.

## Troubleshooting

Troubleshooting an air-gapped Fabric environment may require correlating information across multiple layers, including:

    K2view application
            ↓
       Fabric runtime
            ↓
        Kubernetes
            ↓
    Customer infrastructure

The customer should establish procedures for collecting and correlating information from these layers.

A typical investigation may include:

- reviewing application behavior,
- reviewing Fabric logs,
- reviewing Kubernetes pod state and logs,
- reviewing Kubernetes events,
- checking infrastructure capacity,
- validating networking and storage,
- reviewing recent deployments or configuration changes,
- and correlating findings with customer monitoring systems.

## Backup and Recovery

Customers are responsible for operating and validating backup and recovery procedures appropriate to their Fabric architecture.

Operational procedures should identify:

- what must be backed up,
- where backups are maintained,
- how configuration is reproduced,
- how persistent data is restored,
- how the runtime environment is rebuilt,
- and how application content is restored and validated.

Recovery procedures should be tested rather than treated only as documented procedures.

## Operational Runbooks

Because routine lifecycle operations are customer-operated, air-gapped environments should have documented runbooks for recurring and high-impact operations.

At minimum, organizations should consider runbooks for:

- environment provisioning,
- application deployment,
- Fabric restart,
- runtime recovery,
- upgrades and rollbacks,
- software and image distribution,
- backup and recovery,
- troubleshooting,
- and environment decommissioning.

Automation can reduce the operational burden, but the customer remains responsible for creating, validating, and maintaining that automation.

## Related Documentation

- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
- [Air-Gapped Operational Model](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_model.md)
- [Air-Gapped Customer Responsibilities](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_customer_responsibilities.md)
- [Air-Gapped Operational Expectations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_expectations.md)