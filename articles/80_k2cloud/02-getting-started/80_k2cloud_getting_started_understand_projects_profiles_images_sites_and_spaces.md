# Understand Projects, Profiles, Images, Sites, and Spaces

## Overview

K2cloud uses a small number of objects to define where and how a K2view Fabric environment is deployed.

For a new K2cloud user, the most important relationship to understand is:

```text
Project + Space Profile + Fabric Image + Site → Space
```

Each object answers a different question:

- **Project** — What K2view implementation is being deployed?
- **Space Profile** — What runtime topology and resources should the Space use?
- **Fabric Image** — What version of Fabric should run?
- **Site** — Where should the Space run?
- **Space** — The resulting deployed K2view environment.

Understanding these objects makes the Space creation process straightforward.

## Project

A **Project** represents a K2view implementation managed through K2cloud.

The Project provides the context used to create and operate its Spaces and associates the implementation with its Git repository.

A Project can have multiple Spaces. For example, the same Project may have development, QA, staging, and production Spaces.

The Project Manager creates the Project before creating its first Space.

For more information, see [Projects](/articles/80_k2cloud/03-projects/README.md).

## Space Profile

A **Space Profile** defines the runtime topology and resources used to create a Space.

Profiles define characteristics such as:

- the type of Space,
- CPU and memory resources,
- the number of Fabric replicas,
- the services included in the Space,
- and how supporting services such as the System Database are provided.

For example, a Studio development Space typically includes Fabric, Fabric Studio, and PostgreSQL, while a Fabric runtime profile may provide multiple Fabric replicas and use an external System Database.

K2view typically recommends appropriate Space Profiles during the initial provisioning and planning of a K2cloud environment.

For the available K2cloud profiles, see [K2cloud Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/profiles.md).

## Fabric Image

A **Fabric Image** identifies the version of the K2view runtime deployed to a Space.

The Fabric Image is selected for a Space Profile. This separates the runtime topology defined by the profile from the version of Fabric running within that topology.

When a newer Fabric release becomes available, the Project Manager can select the newer Fabric Image for the appropriate Space Profile. Existing eligible Spaces using that profile can then be upgraded.

This allows the same Space Profile to continue to define the deployment topology while the Fabric version changes over time.

## Site

A **Site** identifies the runtime infrastructure where a Space is deployed.

For K2cloud SaaS, the runtime infrastructure is managed and operated by K2view.

For K2cloud Self-Hosted, the Site represents customer-operated Kubernetes infrastructure connected to the K2cloud Orchestrator. Most Self-Hosted environments use a hyperscaler-managed Kubernetes service such as Amazon EKS, Azure AKS, or Google Kubernetes Engine (GKE).

Sites can also establish infrastructure and access boundaries such as region, Kubernetes cluster, ingress, and domain configuration.

For more information, see [Sites](/articles/80_k2cloud/05-sites/README.md).

## Space

A **Space** is the deployed K2view environment created from the Project, Space Profile, Fabric Image, and Site.

Conceptually:

```text
Project + Space Profile + Fabric Image + Site → Space
```

The Project identifies the implementation. The Space Profile defines its runtime topology. The Fabric Image determines the runtime version. The Site determines where it runs.

Together, these definitions allow the K2cloud Orchestrator to create and manage the resulting Space.

A Project can have multiple Spaces using different profiles, Fabric Images, and Sites according to the environments required by the implementation.

For more information, see [Spaces](/articles/80_k2cloud/06-spaces/README.md).

## Space URLs

Each deployed Space has its own URL.

A Space URL is distinct from the K2cloud Orchestrator URL:

```text
https://cloud.k2view.com
```

The K2cloud Orchestrator URL is used by Project Managers with the `cloud_user` role to manage Projects and Spaces. A Space URL provides direct access to the applications and services available within that Space.

K2cloud supports two approaches for routing users to Spaces:

- **Context-path-based routing** — multiple Spaces share a common domain and the Space is identified within the URL path.
- **Domain-based routing** — each Space is addressed using a Space-specific hostname.

### Context-Path-Based Space URLs

**Context-path-based routing is the preferred approach for new K2cloud deployments where applicable.**

With context-path-based routing, multiple Spaces can share a common domain and are differentiated through the URL path. This reduces the need for Space-specific hostnames and simplifies supporting infrastructure such as DNS and TLS certificate management as additional Spaces are created.

Conceptually:

```text
https://<site-domain>/<space-context>/...
```


### Domain-Based Space URLs

K2cloud also supports domain-based routing, where the Space name forms part of the hostname.

For example:

```text
https://k2-claude-code-k2se-demos.us.az.presales.cloud.k2view.com/app/studio/
```

In this example:

- `k2-claude-code` is the Space name.
- `k2se-demos.us` is the Site name.

Domain-based routing may be used for existing deployments or where the deployment architecture requires separate Space hostnames. Because each Space introduces a distinct hostname, DNS and certificate requirements should be considered when selecting this approach.

For more information about accessing Spaces, see [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md).

## Related Documentation

- [Create Your First Project](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_create_your_first_project.md)
- [Selecting a Profile, Fabric Image, and Site](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_selecting_a_profile_fabric_image_and_site.md)
- [Create Your First Space](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_create_your_first_space.md)