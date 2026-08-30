# Spaces Overview

## Table of Contents

- [Overview](#overview)
- [K2cloud Orchestrator and Runtime Spaces](#k2cloud-orchestrator-and-runtime-spaces)
- [Projects and Spaces](#projects-and-spaces)
- [Studio Spaces](#studio-spaces)
- [Fabric Spaces](#fabric-spaces)
- [Space Cards](#space-cards)
- [Space Operations](#space-operations)
- [Related Documentation](#related-documentation)

## Overview

A **Space** is a deployed K2view runtime environment managed by the K2cloud Orchestrator.

A Space combines:

```text
Project + Space Profile + Fabric Image + Site → Space
```

The Project provides the K2view implementation, the Space Profile defines the deployment topology, the Fabric Image defines the software version, and the Site determines where the Space is deployed.

Once created, the Space becomes the primary operational object through which users access, deploy, monitor, and manage the environment.

## K2cloud Orchestrator and Runtime Spaces

The K2cloud Orchestrator provides the control plane for managing Spaces.

The workloads themselves run on Kubernetes infrastructure:

- With **K2cloud SaaS**, the runtime infrastructure is managed by K2view.
- With **K2cloud Self-Hosted**, the runtime infrastructure is operated by the customer and connected to the K2cloud Orchestrator.

In both models, the Orchestrator provides application-aware lifecycle and operational management of the deployed K2view environments.

## Projects and Spaces

Each Space belongs to a Project.

A Project can have multiple Spaces representing different stages or purposes within the implementation, such as development, QA, staging, and production.

For example:

```text
Project
 ├── Development Space
 ├── QA Space
 └── Production Space
```

The Project remains the common Git-backed implementation, while each Space is an independently deployed runtime environment.

## Studio Spaces

A **Studio Space** is a development-oriented environment.

A typical Studio Space includes:

- Fabric,
- Fabric Studio,
- and PostgreSQL.

Studio Spaces provide the environment in which developers build, test, debug, and commit implementation changes to Git.

Opening a Studio Space provides access to the Studio IDE and the Fabric applications available within that environment.

Fabric Studio and its applications are documented separately in the Fabric documentation.

## Fabric Spaces

A **Fabric Space** is a runtime-oriented environment.

Fabric Spaces are commonly used for:

- QA,
- staging,
- production,
- APIs,
- and other runtime workloads.

A Fabric Space typically runs Fabric without the Studio development environment.

Persistent services can also be externalized from the Space. For example, profiles using the `noSdb` model use an externally managed System Database and object storage whose lifecycle is independent from the Space.

## Space Cards

The K2cloud Orchestrator presents deployed Spaces as cards on the **Spaces** page.

A Space card provides the information needed to identify the deployment and understand its current operational state, including information such as:

- Space name,
- Project,
- Space Profile,
- Site,
- current status,
- creation information,
- and recent status changes.

The available actions depend on the Space type and its current state.

The articles that follow describe these operations and the additional runtime information available through the Space.

## Space Operations

Depending on the Space type and state, K2cloud provides operations for activities such as:

- opening a Space,
- deploying environments and Project content,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- viewing Space details,
- reviewing runtime status and logs,
- and deleting a Space.

Deployment workflows are covered separately under **Deployments and Lifecycle**.

## Related Documentation

- [Create a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_create_a_space.md)
- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)
- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)