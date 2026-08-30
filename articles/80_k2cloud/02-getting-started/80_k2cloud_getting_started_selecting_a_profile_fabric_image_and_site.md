# Selecting a Profile, Fabric Image, and Site

## Overview

When creating a Space, several selections determine how and where the Space is deployed:

- **Space Profile** — defines the runtime topology and resources.
- **Fabric Image** — defines the Fabric version.
- **Site** — defines where the Space is deployed.

These selections work together with the Project to define the resulting Space:

```text
Project + Space Profile + Fabric Image + Site → Space
```

## Select a Space Profile

The **Space Profile** defines the runtime topology and resources used to create the Space.

Profiles define characteristics such as:

- the type of Space,
- CPU and memory resources,
- the number of Fabric replicas,
- the services included in the Space,
- and how supporting services such as the System Database are provided.

A Project can have multiple Space Profiles available for different purposes. For example, a Project may use a Studio profile for development and a Fabric runtime profile for staging or production.

K2view typically recommends the appropriate Space Profiles during initial provisioning and planning.

For the available profiles, see [K2cloud Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/profiles.md).


## Select a Fabric Image

The **Fabric Image** determines the version of Fabric used by the Space.

The Fabric Image is selected for the Space Profile. This separates the runtime version from the topology and resources defined by the profile.

When K2view publishes a newer Fabric release, the Project Manager can select that Fabric Image for the appropriate Space Profile. Existing eligible Spaces using the profile can then be upgraded separately.

This allows the Space Profile to remain stable while the Fabric version changes over time.

![K2cloud Fabric Image selection](/articles/80_k2cloud/images/02_select_fabric_image.png)

## Select a Site

The **Site** determines the runtime infrastructure where the Space is deployed.

For **K2cloud SaaS**, the Site represents K2view-managed runtime infrastructure.

For **K2cloud Self-Hosted**, the Site represents customer-operated Kubernetes infrastructure connected to the K2cloud Orchestrator. Self-Hosted environments commonly use managed Kubernetes services such as Amazon EKS, Azure AKS, or Google Kubernetes Engine (GKE).

The Site also establishes infrastructure characteristics that affect the Space, including its placement and ingress configuration.

Select the Site appropriate for the environment being created.


## Putting the Selections Together

When creating a Space, the selections answer four basic questions:

- **Project** — What K2view implementation is being deployed?
- **Space Profile** — What runtime topology and resources should it use?
- **Fabric Image** — What version of Fabric should run?
- **Site** — Where should it run?

K2cloud uses these definitions to provision and manage the resulting Space.

```text
Project + Space Profile + Fabric Image + Site → Space
```

## Next Step

After selecting the appropriate Space Profile, Fabric Image, and Site, you are ready to create the Space.

See [Create Your First Space](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_create_your_first_space.md).