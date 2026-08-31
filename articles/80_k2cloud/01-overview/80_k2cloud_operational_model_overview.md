# Model Overview

## Overview

K2cloud uses a consistent operational model for defining, deploying, and managing K2view Fabric environments.

The primary customer-facing objects are:

- **Projects** — define the application and its source repository.
- **Space Profiles** — define the runtime topology and configuration.
- **Fabric Images** — identify the K2view software version.
- **Sites** — identify where Spaces can be deployed.
- **Spaces** — running K2view environments created from these objects.

Together, these objects separate the application being deployed from its runtime configuration, software version, and deployment location.

## Projects

A **Project** represents a K2view implementation within K2cloud.

The Project provides the context for the source code and configuration used by its Spaces. It associates K2cloud with the Git repository containing the K2view project and provides the context for managing Space access and deployment configuration.

A Project can have multiple Spaces, allowing the same implementation to be deployed into different environments.

## Space Profiles

A **Space Profile** defines the runtime topology and configuration used to create a Space.

Profiles provide reusable runtime definitions for different types of environments. For example, different profiles can be used for Studio development environments and Fabric runtime environments.

A Space Profile is selected when a Space is created.

Space Profiles are managed independently of the Fabric software version, allowing the runtime topology and Fabric Image to be selected separately.

For available profiles and their configurations, see [K2cloud Space Profiles](/articles/98_installation_and_upgrade/Hardware_K8s/05_k2cloud_space_profiles.md).

## Fabric Images

A **Fabric Image** identifies the K2view software version used by a Space.

Fabric Images are managed independently of Projects and Space Profiles. This allows the same Project and runtime topology to be deployed using different available Fabric versions.

A Fabric Image is selected when a Space is created and can subsequently be changed through the supported K2cloud upgrade process.

## Sites

A **Site** represents a runtime deployment target available to K2cloud.

When creating a Space, the Site determines where that Space will run.

In K2cloud SaaS, Sites represent K2view-managed runtime infrastructure.

In K2cloud Self-Hosted, Sites represent customer-managed Kubernetes environments connected to K2cloud. These environments commonly use managed Kubernetes services such as Amazon EKS, Azure AKS, or Google GKE.

A Site establishes an infrastructure and placement boundary and can affect characteristics such as network connectivity, ingress, DNS, and access to the deployed Spaces.

## Spaces

A **Space** is a deployed K2view environment.

A Space combines four primary elements:

- a **Project**,
- a **Space Profile**,
- a **Fabric Image**, and
- a **Site**.

Conceptually:

**Project + Space Profile + Fabric Image + Site → Space**

The Project determines what is deployed. The Space Profile determines the runtime topology. The Fabric Image determines the K2view software version. The Site determines where the environment runs.

Once created, the Space becomes the primary operational unit managed through the K2cloud Orchestrator.

Depending on its purpose, a Space may provide a Studio development environment or a Fabric runtime environment.

## How the Objects Work Together

A typical lifecycle begins with a Project that identifies the K2view implementation and its Git repository.

When creating a Space, the user selects the appropriate Space Profile, Fabric Image, and Site. K2cloud then provisions the Space on the Kubernetes infrastructure associated with that Site.

After creation, the K2cloud Orchestrator provides the application-aware lifecycle operations used to manage the Space.

This object model allows the application, runtime topology, software version, and deployment location to be managed as separate concerns while bringing them together in a deployed Space.

## Related Documentation

- [Architecture Overview](/articles/80_k2cloud/01-overview/80_k2cloud_architecture_overview.md)
- [Roles and Personas](/articles/80_k2cloud/01-overview/80_k2cloud_roles_and_personas.md)
