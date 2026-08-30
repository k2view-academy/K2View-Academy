# Air-Gapped Operational Expectations

## Table of Contents

- [Overview](#overview)
- [Isolation and Operational Ownership](#isolation-and-operational-ownership)
- [Operational Maturity](#operational-maturity)
- [Lifecycle Management](#lifecycle-management)
- [Automation](#automation)
- [Software Distribution](#software-distribution)
- [Observability and Troubleshooting](#observability-and-troubleshooting)
- [Skills and Operational Coverage](#skills-and-operational-coverage)
- [Comparing Air-Gapped and K2cloud Self-Hosted](#comparing-air-gapped-and-k2cloud-self-hosted)
- [Planning for Air-Gapped Operation](#planning-for-air-gapped-operation)
- [Related Documentation](#related-documentation)

## Overview

Air-gapped Fabric deployments provide a high degree of infrastructure and network isolation, but that isolation changes the operational model.

The environment does not depend on the K2cloud Orchestrator control plane. As a result, the customer assumes responsibility for the orchestration, lifecycle management, automation, observability, and operational procedures that K2cloud would otherwise provide.

Organizations considering an air-gapped architecture should evaluate both the isolation requirement and the operational responsibilities that accompany it.

## Isolation and Operational Ownership

Air-gapped deployment may be appropriate when organizational requirements prevent the Fabric runtime environment from depending on an external control plane.

This can provide:

- strict network isolation,
- complete customer control of runtime infrastructure,
- controlled movement of software and artifacts,
- and operation within customer-defined security boundaries.

The corresponding tradeoff is increased customer operational ownership.

Isolation is therefore not only a network or security decision. It also determines who provides and operates the lifecycle-management framework surrounding Fabric.

## Operational Maturity

Organizations operating Fabric without K2cloud should have established capabilities for managing Kubernetes-based application platforms.

This includes the ability to operate and support:

- Kubernetes infrastructure,
- networking and ingress,
- persistent storage and databases,
- container registries,
- identity and access,
- monitoring and logging,
- backup and recovery,
- software distribution,
- and operational automation.

The organization should also have clearly defined ownership for these capabilities.

## Lifecycle Management

Routine Fabric lifecycle activities require documented and repeatable procedures.

These include:

- environment provisioning,
- application deployment,
- runtime restart and recovery,
- topology changes,
- upgrades and rollbacks,
- software and image distribution,
- backup and recovery,
- and environment decommissioning.

Without K2cloud, these procedures become part of the customer's own operational platform.

Organizations should determine how these activities will be standardized and automated before placing production workloads into the environment.

## Automation

Air-gapped environments can be highly automated.

Customers can use their own infrastructure-as-code, CI/CD, Kubernetes, and operational automation frameworks to manage Fabric environments.

However, the customer is responsible for:

- designing the automation,
- implementing it,
- securing it,
- testing it,
- documenting it,
- maintaining it,
- and adapting it as the platform evolves.

Automation reduces repetitive operational effort but does not change the ownership boundary.

## Software Distribution

Air-gapped operation requires a reliable process for introducing software into the isolated environment.

Organizations should establish procedures for:

- obtaining supported Fabric software and images,
- reviewing and approving artifacts,
- transferring them across the security boundary,
- publishing them to internal repositories or registries,
- retaining required versions,
- and making new versions available for maintenance and upgrades.

The ability to move and manage software artifacts should be treated as an ongoing operational requirement rather than only an installation activity.

## Observability and Troubleshooting

Because K2cloud monitoring and diagnostics are not available, the customer must provide sufficient observability to operate the environment independently.

Operational teams should be able to correlate information across:

    K2view application
            ↓
       Fabric runtime
            ↓
        Kubernetes
            ↓
    Customer infrastructure

Monitoring, logging, alerting, and troubleshooting procedures should provide enough information to identify the layer in which a problem originates.

This is also important when escalating Fabric issues to K2view Support, because the customer must provide the relevant diagnostic context from the environment.

## Skills and Operational Coverage

Air-gapped operation typically requires coordination across multiple technical disciplines.

Depending on the architecture, this can include teams responsible for:

- K2view Fabric,
- Kubernetes,
- cloud or data-center infrastructure,
- networking,
- storage and databases,
- security,
- identity,
- DevOps and automation,
- and monitoring.

Organizations should establish clear ownership and escalation paths across these areas.

Production operations should not depend on undocumented knowledge held by individual administrators.

## Comparing Air-Gapped and K2cloud Self-Hosted

Organizations that require customer ownership of runtime infrastructure should distinguish that requirement from a requirement for complete isolation from the K2cloud Orchestrator.

**K2cloud Self-Hosted** already allows the customer to operate its Kubernetes infrastructure, networking, ingress, storage, registries, and infrastructure observability while K2cloud provides centralized Fabric lifecycle orchestration.

**Air-Gapped Fabric** additionally removes the dependency on the K2cloud Orchestrator. The customer consequently assumes responsibility for providing the corresponding orchestration and lifecycle processes.

The architectural choice can therefore be framed as:

    Customer-managed runtime infrastructure required?
                     │
                     ▼
                    Yes
                     │
                     ▼
    Is independence from the K2cloud Orchestrator
              also required?
               │             │
              No            Yes
               │             │
               ▼             ▼
        K2cloud          Air-Gapped
       Self-Hosted         Fabric

Where complete isolation is required, air-gapped deployment provides that operating model.

Where the requirement is customer control of runtime infrastructure rather than independence from the K2cloud Orchestrator, K2cloud Self-Hosted retains that infrastructure control while also providing centralized Fabric-aware orchestration.

## Planning for Air-Gapped Operation

Before adopting an air-gapped Fabric architecture, organizations should be able to answer the following questions:

- What requirement prevents use of the K2cloud Orchestrator control plane?
- Who owns the Kubernetes infrastructure?
- How will Fabric environments be provisioned and reproduced?
- How will application content be promoted between environments?
- How will Fabric software and images enter the isolated environment?
- How will runtime lifecycle operations be performed?
- How will upgrades and rollbacks be executed?
- How will the environment be monitored?
- How will backup and recovery be performed?
- What automation will be required?
- Who maintains that automation?
- How will incidents be diagnosed and escalated?

The answers define the operational platform the customer must establish around Fabric.

## Related Documentation

- [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)
- [Air-Gapped Operational Model](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_operational_model.md)
- [Air-Gapped Runtime Operations](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_runtime_operations.md)
- [Air-Gapped Customer Responsibilities](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_customer_responsibilities.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)