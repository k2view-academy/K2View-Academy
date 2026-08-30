# Sites Overview

## Table of Contents

- [Overview](#overview)
- [Sites and Spaces](#sites-and-spaces)
- [What a Site Represents](#what-a-site-represents)
  - [K2cloud SaaS](#k2cloud-saas)
  - [K2cloud Self-Hosted](#k2cloud-self-hosted)
- [What a Site Defines](#what-a-site-defines)
- [Sites as Infrastructure Boundaries](#sites-as-infrastructure-boundaries)
- [Who Manages Sites](#who-manages-sites)
- [Sites and Ingress](#sites-and-ingress)
- [Selecting a Site](#selecting-a-site)
- [Related Documentation](#related-documentation)

## Overview

A **Site** represents a runtime deployment target within K2cloud.

When creating a Space, the selected Site determines where the Space is deployed and connects the K2cloud Orchestrator to the infrastructure on which the Space will run.

A Site can represent characteristics such as:

- a Kubernetes cluster,
- a cloud region,
- a network or security boundary,
- an ingress and DNS configuration,
- and infrastructure-specific services used by Spaces deployed to that Site.

Sites are typically established as part of K2cloud onboarding and infrastructure provisioning rather than created by developers as part of their normal workflow.

## Sites and Spaces

A Space is created using:

```text
Project + Space Profile + Fabric Image + Site → Space
```

Each component has a distinct responsibility:

<table>
<thead>
<tr>
<th>Component</th>
<th>Purpose</th>
</tr>
</thead>
<tbody>
<tr>
<td>Project</td>
<td>Identifies the K2view implementation and its Git repository.</td>
</tr>
<tr>
<td>Space Profile</td>
<td>Defines the deployment topology, resources, and runtime configuration.</td>
</tr>
<tr>
<td>Fabric Image</td>
<td>Defines the Fabric or Studio software version.</td>
</tr>
<tr>
<td>Site</td>
<td>Defines the runtime infrastructure and placement target.</td>
</tr>
</tbody>
</table>

The **Site is selected when the Space is created**. It is not a Project setting.

## What a Site Represents

The exact infrastructure represented by a Site depends on the K2cloud deployment model.

### K2cloud SaaS

With K2cloud SaaS, the Site represents K2view-managed runtime infrastructure.

K2view operates the Kubernetes and supporting infrastructure while the K2cloud Orchestrator provides the application-aware lifecycle management of the Fabric environment.

### K2cloud Self-Hosted

With K2cloud Self-Hosted, the Site represents customer-operated runtime infrastructure connected to the K2cloud Orchestrator.

Most Self-Hosted deployments use a managed Kubernetes service from a major cloud provider, such as:

- Amazon Elastic Kubernetes Service (EKS),
- Azure Kubernetes Service (AKS),
- or Google Kubernetes Engine (GKE).

The customer operates the runtime infrastructure while K2cloud continues to provide centralized orchestration and lifecycle management of the K2view Fabric environments deployed to it.

## What a Site Defines

Site configuration establishes infrastructure-specific characteristics used by Spaces deployed to that Site.

These can include:

- runtime placement,
- ingress configuration,
- DNS behavior,
- network connectivity,
- container registry configuration,
- storage integration,
- and other infrastructure-specific deployment settings.

This allows the deployment topology defined by a Space Profile to remain separate from the infrastructure on which the Space is deployed.

## Sites as Infrastructure Boundaries

Organizations can use Sites to represent meaningful infrastructure or operational boundaries.

For example, separate Sites can be used when Spaces must run:

- in different Kubernetes clusters,
- in different cloud regions,
- across separate network or security boundaries,
- or on infrastructure operated for different purposes.

The appropriate Site structure depends on the organization's runtime infrastructure and connectivity requirements.

## Who Manages Sites

Sites are normally established and configured by K2view and the teams responsible for the runtime infrastructure.

Developers and other Space users generally do not administer Sites.

Instead, a Project Manager selects from the Sites made available when creating a Space.

## Sites and Ingress

A Site can define the ingress model used by Spaces deployed to it.

Centralizing ingress configuration at the Site allows Spaces and Space Profiles to use a consistent routing model without independently defining the same infrastructure configuration.

Where applicable, **context-path-based ingress is preferred for new K2cloud deployments** because multiple Spaces can use a common domain, reducing Space-specific DNS records and simplifying TLS certificate management.

Space Profiles can use the Site's ingress configuration or specify a different ingress mode where required.

For details, see [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md).

## Selecting a Site

The Site is selected as part of Space creation.

A typical workflow is:

```text
Select Project
      ↓
Select Space Profile
      ↓
Select Fabric Image
      ↓
Select Site
      ↓
Create Space
```

K2cloud then uses the selected Site as the infrastructure target for the Space deployment.

## Related Documentation

- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md)
- [Spaces](/articles/80_k2cloud/06-spaces/README.md)