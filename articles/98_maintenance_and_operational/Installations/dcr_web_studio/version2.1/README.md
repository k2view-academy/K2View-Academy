# Fabric Web Studio 2.1 Overview

K2view Fabric Web Studio version 2.1 introduces expanded capabilities for flexible, containerized deployment across enterprise environments. It enables development teams to build, manage, and test K2view Fabric projects quickly and securely using isolated development Spaces.

Fabric Web Studio continues to support **multi-space development**, allowing multiple developers or teams to work concurrently, each within its isolated runtime environment on developer machines and shared servers.

Version 2.1 introduces several important enhancements, most notably:

- **Support for Podman Compose runtime**, providing a daemonless, enterprise-grade alternative to Docker Compose.
- **Upgraded Fabric version 8.2.1_46** bundled with the distribution.
- **Heap size increased to 4GB by default**, configurable per Space.
- **PROJECT_NAME parameter introduced** to decouple project name from Space name.
- **Per-Space configuration files** (`.env`, `compose.yaml`, `.config`) allow for easier customization.
- **JDBC access via port 5124** exposed for optional direct database integration.
- **Improved healthcheck behavior** for better runtime monitoring.
- **New release packaging**:
  - Docker distribution: `Studio-Docker-latest.zip`  TODO
  - Podman distribution: `Studio-Podman-latest.zip`  TODO

---

## Documentation Structure

TODO
For full deployment, configuration, and operational details, refer to:

- [Installation](link-to-version2.1/Installation.html)
- [Operating Fabric Web Studio](link-to-version2.1/Operating.html)
- [Troubleshooting](link-to-version2.1/Troubleshooting.html)
- [Upgrading Fabric Web Studio](link-to-version2.1/Upgrading.html)
- [Docker Compose Runtime Guide](link-to-version2.1/Docker-Compose.html)
- [Podman Runtime Guide](link-to-version2.1/Podman.html)

<ul>      
<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/About.md">About</a></li>
  
<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Installation.md">Installing Fabric Web Studio</a></li>

<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Operating.md">Operating</a></li>


<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.md">Troubleshooting</a></li>

<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/6-Docker-Compose.md">Docker and Docker Compose Installation</a></li>

<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Upgrading.md">Upgrading</a></li>

</ul>
---

## Supported Deployment Options

You can install Fabric Web Studio using either of the following container runtimes:

### Docker Compose Runtime

Fabric Web Studio can be deployed using Docker Compose for customers operating on platforms where Docker is already present. Docker Compose coordinates Studio containers, embedded Fabric runtime, Traefik reverse proxy, and multiple developer Spaces.

TODO

Refer to [Docker Compose Installation Instructions](link-to-version2.1/Docker-Compose.html).

### Podman Compose Runtime

Fabric Web Studio can also be deployed using Podman Compose, offering a fully daemonless container engine aligned with modern Enterprise Linux distributions (e.g., RHEL, AlmaLinux, Rocky Linux, CentOS Stream).

TODO 

Refer to [Podman Installation Instructions](link-to-version2.1/Podman.html).

Both runtimes offer identical functional capabilities within Fabric Web Studio.

---

## Key Fabric Web Studio Features

- Visual development environment for data products, APIs, integrations, orchestration, and transformations.
- Real-time interactive testing, deployment, and validation.
- Embedded Fabric runtime engine.
- Multiple database options per profile: SQLite, PostgreSQL, Cassandra or a hybrid of Cassandra & PostgreSQL
- Secure integration with Git for source control and team collaboration.
- Traefik reverse proxy for flexible URL-based Space routing and SSL management.
- Per-Space configuration isolation for greater flexibility across environments.
- Multiple authentication providers: Fabric local, LDAP, Active Directory, and SAML-based identity federation.

---

## Available Profiles

During Fabric Web Studio space creation, you select one of four available runtime profiles:

| Profile | Description |
|---------|-------------|
| `studio` | Default profile using embedded SQLite for System DB. |
| `studio_pg` | Studio with PostgreSQL for System DB and TDM usage. |
| `studio_cass` | Studio with Cassandra for System DB and TDM usage. |
| `studio_pg_cass` | Studio with both Cassandra and PostgreSQL for TDM operations. |


---

## Installation Package

The installation package is available for download via K2view's Nexus Image Repository or can be obtained via [K2view's GitHub Studio Blueprint]([url](https://github.com/k2view/blueprints/tree/main/Studio)). We recommend downloading the installation package from K2view's Nexus Image Repository

  - Docker distribution: `Studio-Docker-latest.zip`  TODO
  - Podman distribution: `Studio-Podman-latest.zip`  TODO

Either path will require that you obtain an account for K2view's Nexus Image Repository. If you do not have an account, please request one from your K2view representative. 

---

## What’s Included in the Installation Package

- **README.md** – Installation reference guide (per runtime).
- **k2space.sh** – Command-line tool to create, start, stop, list, and destroy Fabric Spaces.
- **.env / .env-[spacename] files** – Runtime configuration parameters.
- **common.config / [spacename].config files** – Fabric runtime configuration overrides.
- **compose.yaml / compose-[spacename].yaml** – Container composition definitions.
- **Traefik configuration files** for proxy and TLS management.
- **SSL certificates directory** to allow insertion of organization-specific certificates.


---

## Version Compatibility

Fabric Web Studio 2.1 packages are validated and certified against Fabric version `8.2.1_46`. Contact your K2view representative if you wish to employ a later version of Fabric and TDM.


