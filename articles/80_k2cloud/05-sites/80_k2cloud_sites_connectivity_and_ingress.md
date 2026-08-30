# Connectivity and Ingress

## Table of Contents

- [Overview](#overview)
- [Site and Ingress Relationship](#site-and-ingress-relationship)
- [Ingress Models](#ingress-models)
- [DNS and TLS](#dns-and-tls)
- [Connectivity Models](#connectivity-models)
- [Operational Considerations](#operational-considerations)
- [Related Documentation](#related-documentation)

## Overview

A Site defines infrastructure-specific connectivity and ingress characteristics used by Spaces deployed to that Site.

This can include:

- ingress behavior,
- DNS integration,
- TLS configuration,
- network connectivity,
- and the infrastructure through which traffic reaches deployed Spaces.

The exact configuration depends on the deployment model and the infrastructure represented by the Site.

## Site and Ingress Relationship

A Site establishes the baseline ingress configuration for Spaces deployed to that infrastructure.

This can include:

- ingress infrastructure,
- DNS integration,
- TLS certificate strategy,
- and routing behavior.

A Space Profile can use the ingress configuration defined by the Site or specify a different ingress mode where required.

Centralizing ingress configuration at the Site allows Spaces deployed to the same infrastructure to use a consistent routing model.

## Ingress Models

K2cloud supports **context-path** and **subdomain-based** ingress.

### Context Path

With context-path ingress, multiple Spaces can share a common domain.

Where applicable, context-path ingress is preferred for new K2cloud deployments because it:

- simplifies DNS management,
- simplifies TLS certificate management,
- reduces the proliferation of Space-specific hostnames,
- and makes it easier to add Spaces without creating additional DNS records.

### Subdomain

With subdomain-based ingress, each Space uses a Space-specific hostname.

This model can be used where the deployment requires separate Space hostnames or where an existing K2cloud deployment already uses subdomain-based routing.

Because each Space has its own hostname, the DNS and TLS strategy must accommodate additional Space hostnames as Spaces are created.



## DNS and TLS

The Site configuration establishes the DNS and TLS approach required for the selected ingress model.

Depending on the deployment infrastructure, TLS can terminate through components such as:

- a Kubernetes ingress controller,
- a cloud load balancer,
- or another ingress service.

The specific implementation is established as part of Site provisioning.

The choice between context-path and subdomain-based ingress directly affects DNS and certificate management. This is one reason context-path ingress is preferred where applicable.

## Connectivity Models

A Site also reflects how users and external systems connect to the deployed environment.

Depending on the deployment and network architecture, connectivity can include:

- public network access,
- private network connectivity,
- VPN connectivity,
- cloud-provider private connectivity,
- or proxy-based access.

For K2cloud Self-Hosted, these connectivity requirements are established with the customer as part of the infrastructure and Site configuration.

Different Sites can represent different network and security boundaries and therefore do not need to use the same connectivity model.

## Operational Considerations

Ingress and connectivity decisions affect several aspects of operating K2cloud Spaces, including:

- DNS administration,
- TLS certificate management,
- network security boundaries,
- access to deployed Spaces,
- and connectivity between Fabric and external systems.

These decisions should therefore be established as part of the Site architecture rather than independently for each Space.

Where a common ingress policy applies to a Site, Space Profiles should generally use the **Site configuration** rather than independently overriding the ingress model.

## Related Documentation

- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [Space Profile General Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_general_settings.md)