# About K2view Fabric Web Studio for Docker Compose or Podman, version 2.1

## Table of Contents

1. [About K2view Fabric Web Studio 2.1](#about-k2view-fabric-web-studio-21)
2. [Previous Versions](#previous-versions)
2. [What’s New in Version 2.1](#whats-new-in-version-21)
3. [Supported Deployment Runtimes](#supported-deployment-runtimes)
    - [Docker Compose Runtime](#docker-compose-runtime)
    - [Podman Compose Runtime](#podman-compose-runtime)
4. [Fabric Web Studio Runtime Components](#fabric-web-studio-runtime-components)
5. [Key Fabric Web Studio Features](#key-fabric-web-studio-features)
6. [Supported Profiles](#supported-profiles)
7. [Prerequisites](#prerequisites)
    - [Host Machine Requirements](#host-machine-requirements)
    - [Operating System Requirements](#operating-system-requirements)
    - [Required 3rd Party Software](#required-3rd-party-software)
        - [Common Requirements (Both Docker and Podman)](#common-requirements-both-docker-and-podman)
        - [Docker Compose Specific](#docker-compose-specific)
        - [Podman Compose Specific](#podman-compose-specific)
    - [Network Access](#network-access)
8. [Installation Package](#installation-package)
9. [What’s Included in the Installation Package](#whats-included-in-the-installation-package)
10. [Version Compatibility](#version-compatibility)


---

## About K2view Fabric Web Studio for Docker Compose or Podman, version 2.1

K2view Fabric Web Studio provides developers, architects, and data teams with a unified environment for designing, building, managing, and deploying data-driven solutions across various integration, data product, orchestration, and transformation use cases.

Fabric Web Studio enables:

- Model-driven design of Logical Units and micro-databases.
- Real-time development, testing, validation, and orchestration.
- Flexible data integration across multiple data sources and platforms.
- Visual creation and management of APIs, flows, business logic, and transformations.
- Rapid creation of reusable data products for internal and external consumption.

With **multi-space development**, Fabric Web Studio allows multiple developers or teams to work concurrently within isolated, independently configurable Spaces — supporting collaborative workflows, faster iterations, and simplified testing environments.

## Previous Versions
<ul>
<li><a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-0/About.md">Previous Version: 2.0</a></li>
</ul>

## What’s New in Fabric Web Studio for Docker Compose or Podman, Version 2.1

Version 2.1 introduces several important enhancements, most notably:

- **Support for Podman Compose runtime**, providing a daemonless, enterprise-grade alternative to Docker Compose.
- **Upgraded Fabric version 8.2.1_46** bundled with the distribution.
- **Heap size increased to 4GB by default**, configurable per Space.
- **PROJECT_NAME parameter introduced** to decouple project name from Space name.
- **Per-space configuration files** (`.env`, `compose.yaml`, `.config`) enable easier customization.
- **JDBC access via port 5124** is exposed for optional direct database integration.
- **Improved healthcheck behavior** for better runtime monitoring.
- **New release packaging**:
  - Docker distribution: `Studio-Docker-latest.zip` 
  - Podman distribution: `Studio-Podman-latest.zip` 

## Supported Deployment Runtimes

Fabric Web Studio supports deployment across two container orchestration environments:

### Docker Compose Runtime

Docker Compose provides a widely adopted container orchestration layer to run Web Studio, an embedded Fabric engine, and Traefik reverse proxy. Docker Compose allows full multi-space development on Docker-based platforms across Linux, macOS, or Windows (using WSL).

See: [Docker Compose Installation Instructions](https://support.k2view.com/Academy/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.html)


### Podman Compose Runtime

Podman Compose introduces daemonless, enterprise-grade containerization ideal for Red Hat Enterprise Linux (RHEL), AlmaLinux, Rocky Linux, and CentOS Stream distributions. It delivers native Linux orchestration fully compatible with Fabric Web Studio’s Compose YAML deployment model.

See: [Podman Installation Instructions](https://support.k2view.com/Academy/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.html)

> Both runtimes provide equivalent capabilities. Selection depends on enterprise standards and operational preferences.

---

## Fabric Web Studio Runtime Components

Regardless of runtime choice, each Fabric Web Studio deployment includes:

- **Fabric Web Studio IDE** — browser-based environment for model-driven development.
- **Embedded Fabric Runtime Engine** — executes real-time orchestration and data management tasks.
- **Traefik Reverse Proxy** — handles Space-based routing via URL path contexts and manages SSL/TLS certificates.
- **System DB Profiles** — support SQLite, PostgreSQL, Cassandra, or hybrid combinations for embedded and TDM use cases.
- **Git Integration** — allows full source control, team collaboration, and CI/CD alignment.
- **Multi-Space Isolation** — enables creation of fully isolated developer environments on a shared runtime host.

---

## Key Fabric Web Studio Features

- Visual development environment for data products, APIs, integrations, orchestration, and transformations.
- Real-time interactive testing, deployment, and validation.
- Embedded Fabric runtime engine.
- Multiple database options per profile: SQLite, PostgreSQL, Cassandra, or a hybrid of Cassandra & PostgreSQL
- Secure integration with Git for source control and team collaboration.
- Traefik reverse proxy for flexible URL-based Space routing and SSL management.
- Per-Space configuration isolation for greater flexibility across environments.
- Multiple authentication providers: Fabric local, LDAP, Active Directory, and SAML-based identity federation.

---

## Supported Profiles

At deployment, administrators select one of the following runtime profiles depending on database requirements:

<table>
  <thead>
    <tr>
      <th>Profile</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>studio</code></td>
      <td>Default profile using embedded SQLite for System DB.</td>
    </tr>
    <tr>
      <td><code>studio_pg</code></td>
      <td>Uses PostgreSQL for System DB and TDM functionality.</td>
    </tr>
    <tr>
      <td><code>studio_cass</code></td>
      <td>Uses Cassandra for System DB and TDM functionality.</td>
    </tr>
    <tr>
      <td><code>studio_pg_cass</code></td>
      <td>Hybrid profile combining PostgreSQL (TDM) and Cassandra (System DB).</td>
    </tr>
  </tbody>
</table>

---

## Prerequisites

Before you install Fabric Web Studio 2.1, please have a look at the following system prerequisites. Some requirements are standard, while others depend on whether you deploy using Docker Compose or Podman Compose.

### Host Machine Requirements

- **Architecture**: AMD64 (x86-64) architecture is required.  
  > ARM-based processors are not directly supported. Certain operating systems provide AMD64 emulation capabilities for their ARM-based processors.

- **Memory**: A minimum of 32GB RAM is recommended to support Web Studio, Fabric Spaces, and associated services.
  - Each Fabric Space allocates a 4GB JVM heap by default (this can be overridden in the configuration).

- **Disk Space**: Sufficient local storage for persistent data directories, Fabric images (~2GB per image), and logs.

### Operating System Requirements

<table>
  <thead>
    <tr>
      <th>Runtime</th>
      <th>Supported Platforms</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Docker Compose</strong></td>
      <td>Linux (preferred), macOS, or Windows (via WSL2 and Linux distributions)</td>
    </tr>
    <tr>
      <td><strong>Podman Compose</strong></td>
      <td>Enterprise Linux distributions: RHEL, AlmaLinux, Rocky Linux, CentOS Stream</td>
    </tr>
  </tbody>
</table>

> For best performance on Windows, Docker Compose should run within WSL2 using a native Linux distribution.

---

### Required 3rd Party Software

#### Common Requirements (Both Docker and Podman)

- **Git Client**  
  - Install latest Git client: [https://git-scm.com/downloads](https://git-scm.com/downloads)

- **Internet Access**  
  Required to:
  - Download Fabric Web Studio distribution packages
  - Clone K2view Blueprints (optional)
  - Access K2view Nexus Container Registry for Fabric images
  - Access K2view Exchange (if using TDM)

- **K2view Nexus Container Registry Account**  
  - Required to authenticate and pull Fabric images.
  - Contact your K2view representative to obtain credentials.

---

#### Docker Compose Specific

- **Docker Engine and Compose Plugin**  
  - Install Docker Engine: [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)
  - Install Docker Compose: [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/)
  - Docker Desktop includes the Docker Compose plugin by default.
  - Use the native Docker Compose plugin (not the legacy `docker-compose` Python utility).

> Please refer to the installation instructions provided in the <a href="https://support.k2view.com/Academy/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.html">Installing Docker and Docker Compose </a> topic.

- **WSL2 (Windows Subsystem for Linux 2)** *(Windows only)*  
  - Recommended for Windows users.
  - Install WSL2 and a supported Linux distribution (e.g., Ubuntu) to ensure full compatibility.

To install Docker Compose Runtime for K2view Fabric Web Studio on Microsoft Windows, you will need to use Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. When installing on Microsoft Windows with WSL, you need to install a Linux distribution instead of using the default distribution bundled by Microsoft's WSL. Using the Microsoft-provided Linux distribution will cause the Docker Compose Runtime for Fabric Services to fail to run correctly. Instructions are provided in the <a href="https://support.k2view.com/Academy/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.html">Installing Docker and Docker Compose </a> topic. 

---

#### Podman Compose Specific

- **Podman Engine**  
  - Install Podman via distribution package manager:  
    - `sudo dnf install -y podman` *(RHEL, AlmaLinux, Rocky)*  
  - Installation guide: [https://podman.io/getting-started/installation](https://podman.io/getting-started/installation)

- **Podman Compose Plugin**  
  - Install Podman Compose using either:
    - DNF: `sudo dnf install -y podman-compose`
    - Or pip3: `pip3 install --user podman-compose`

> **Note**:  Podman Compose provides native compatibility for Compose YAML files with Podman pods.

- **Rootless Podman Socket**  
  - Ensure Podman socket is enabled and running for rootless operation:
    ```
    systemctl --user enable --now podman.socket
    loginctl enable-linger $USER
    ```

---

### Network Access

Ensure the following external services are reachable from the host machine:

<table>
  <thead>
    <tr>
      <th>Service</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>https://github.com</code></td>
      <td>(optional) Clone K2view Blueprints</td>
    </tr>
    <tr>
      <td><code>https://docker.share.cloud.k2view.com</code></td>
      <td>Access Fabric images</td>
    </tr>
    <tr>
      <td><code>https://nexus.share.cloud.k2view.com</code></td>
      <td>Download Studio distributions</td>
    </tr>
    <tr>
      <td><code>https://exchange.k2view.com</code></td>
      <td>(if TDM is used) Access Exchange packages</td>
    </tr>
  </tbody>
</table>

---

> After verifying prerequisites, proceed to the Installation Guide for your chosen runtime (Docker or Podman).

---

## Installation Package

The installation package is available for download via K2view's Nexus Container Registry or can be obtained via [K2view's GitHub Studio Blueprint](https://github.com/k2view/blueprints/tree/main/Studio). We recommend downloading the installation package from K2view's Nexus Container Registry.

  - Docker distribution: `Studio-Docker-latest.zip` at [https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Podman-Studio-latest.zip](https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Docker-Studio-latest.zip)
  - Podman distribution: `Studio-Podman-latest.zip` at [https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Podman-Studio-latest.zip](https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Podman-Studio-latest.zip)

Either path will require that you obtain an account for K2view's Nexus Container Registry. If you do not have an account, please request one from your K2view representative. 

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

Fabric Web Studio 2.1 packages are validated and certified against Fabric version `8.2.4_3`. Please contact your K2view representative if you'd like to use a later version of Fabric and TDM.

