# What is K2cloud?

## Overview

K2cloud is K2view’s platform for deploying, managing, and operating K2view Fabric environments.

At the center of K2cloud is the **K2cloud Orchestrator**, a SaaS control plane that provides a centralized interface for managing the lifecycle of Fabric environments. It coordinates the Projects, Space Profiles, Fabric Images, Sites, and Spaces used to deploy and operate K2view environments.

K2cloud separates orchestration from the runtime infrastructure on which Fabric executes. Depending on the deployment model, that infrastructure may be operated by K2view or by the customer.

## What K2cloud Provides

K2cloud provides a consistent operational model for creating and managing K2view environments.

Using the K2cloud Orchestrator, authorized users can:

- create and manage Projects,
- configure the Git repository associated with a Project,
- create Spaces using available Space Profiles, Fabric Images, and Sites,
- manage the lifecycle of deployed Spaces,
- deploy versioned environments and projects,
- view Space status and operational information,
- access deployed Fabric and Studio environments,
- and perform supported operational and upgrade activities.

K2cloud does not replace Fabric. **Fabric is the runtime platform. K2cloud provides the orchestration and lifecycle management used to deploy and operate Fabric environments.**

## Core K2cloud Objects

K2cloud uses a small set of objects to define what is deployed and where it runs.

### Project

A **Project** associates K2cloud with the source code and configuration used to create and operate Spaces.

A Project provides the context for the Git repository, Space access, deployment configuration, and Spaces associated with a K2view implementation.

### Space Profile

A **Space Profile** defines the runtime topology and configuration used when creating a Space.

Profiles provide reusable configuration for different types of Spaces, such as Studio development environments and Fabric runtime environments.

### Fabric Image

A **Fabric Image** identifies the version of the K2view software used by a Space.

Available Fabric Images are managed independently of Space Profiles and are selected when creating or upgrading a Space.

### Site

A **Site** is the runtime deployment target available for creating Spaces.

A Site determines where a Space is deployed and may represent K2view-managed infrastructure or customer-managed infrastructure, depending on the deployment model. Sites also establish infrastructure boundaries that affect connectivity, ingress, and access to deployed Spaces.

### Space

A **Space** is a deployed K2view environment.

A Space combines a **Project**, **Space Profile**, **Fabric Image**, and **Site** into a running environment. Depending on its purpose, a Space may provide a Studio development environment or a Fabric runtime environment.

## K2cloud Deployment Models

K2cloud supports different operating models based primarily on who operates the runtime infrastructure and whether the K2cloud Orchestrator is used as the control plane.

With **K2cloud SaaS**, K2view operates both the K2cloud Orchestrator and the runtime infrastructure used by customer Spaces.

With **K2cloud Self-Hosted**, the customer operates the runtime infrastructure while the K2cloud Orchestrator remains the centralized SaaS control plane used to manage Spaces.

**Air-gapped deployments** operate without dependence on the K2cloud Orchestrator control plane. The customer assumes responsibility for operating the isolated environment and managing the associated software and infrastructure lifecycle.

The deployment model therefore changes the division of operational responsibility, while the underlying K2view runtime remains Fabric.

For more information, see:

- [K2cloud SaaS vs K2cloud Self-Hosted](/articles/80_k2cloud/01-overview/80_k2cloud_overview_k2cloud_saas_vs_self_hosted.md)
- [Deployment Models](/articles/80_k2cloud/01-overview/80_k2cloud_overview_deployment_models.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)

## Related Documentation

- [Operational Model Overview](/articles/80_k2cloud/01-overview/80_k2cloud_operational_model_overview.md)
- [Architecture Overview](/articles/80_k2cloud/01-overview/80_k2cloud_architecture_overview.md)
- [Roles and Personas](/articles/80_k2cloud/01-overview/80_k2cloud_roles_and_personas.md)

