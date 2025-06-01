# About K2view Fabric Web Studio 2.1

K2view Fabric Web Studio provides developers, architects, and data teams with a unified environment to design, build, manage, and deploy data-driven solutions across a variety of integration, data product, orchestration, and transformation use cases.

Fabric Web Studio enables:

- Model-driven design of Logical Units and micro-databases.
- Real-time development, testing, validation, and orchestration.
- Flexible data integration across multiple data sources and platforms.
- Visual creation and management of APIs, flows, business logic, and transformations.
- Rapid creation of reusable data products for internal and external consumption.

With **multi-space development**, Fabric Web Studio allows multiple developers or teams to work concurrently within isolated, independently configurable Spaces — supporting collaborative workflows, faster iterations, and simplified testing environments.

---

## What’s New in Version 2.1

Fabric Web Studio 2.1 introduces several important enhancements provided [here](TODO) TODO

## Supported Deployment Runtimes

Fabric Web Studio supports deployment across two container orchestration environments:

### Docker Compose Runtime

Docker Compose provides a widely adopted container orchestration layer to run Web Studio, embedded Fabric engine, and Traefik reverse proxy. Docker Compose allows full multi-space development on Docker-based platforms across Linux, macOS, or Windows (using WSL).

TODO
See: [Docker Compose Installation Instructions](link-to-version2.1/Docker-Compose.html)

### Podman Compose Runtime

Podman Compose introduces daemonless, enterprise-grade containerization ideal for Red Hat Enterprise Linux (RHEL), AlmaLinux, Rocky Linux, and CentOS Stream distributions. It delivers native Linux orchestration fully compatible with Fabric Web Studio’s Compose YAML deployment model.

TODO
See: [Podman Installation Instructions](link-to-version2.1/Podman.html)

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

## Supported Profiles

At deployment, administrators select one of the following runtime profiles depending on database requirements:

| Profile          | Description                                                  |
| ---------------- | ------------------------------------------------------------ |
| `studio`         | Default profile using embedded SQLite for System DB.         |
| `studio_pg`      | Uses PostgreSQL for System DB and TDM functionality.         |
| `studio_cass`    | Uses Cassandra for System DB and TDM functionality.          |
| `studio_pg_cass` | Hybrid profile combining PostgreSQL (TDM) and Cassandra (System DB). |

---

## Prerequisites

Before installing Fabric Web Studio 2.1, please review the following system prerequisites. Some requirements are common, while others depend on whether you deploy using Docker Compose or Podman Compose.

### Host Machine Requirements

- **Architecture**: AMD64 (x86-64) architecture is required.  
  > ARM-based processors are not supported.

- **Memory**: Minimum 32GB RAM recommended to support Web Studio, Fabric Spaces, and associated services.
  - Each Fabric Space allocates 4GB JVM heap by default (overridable in configuration).

- **Disk Space**: Sufficient local storage for persistent data directories, Fabric images (~2GB per image), and logs.

### Operating System Requirements

| Runtime | Supported Platforms |
|---------|----------------------|
| **Docker Compose** | Linux (preferred), macOS, or Windows (via WSL2 and Linux distributions) |
| **Podman Compose** | Enterprise Linux distributions: RHEL, AlmaLinux, Rocky Linux, CentOS Stream |

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
  - Docker Desktop includes Docker Compose plugin by default.
  - Use the native Docker Compose plugin (not the legacy `docker-compose` Python utility).

TODO

> Please refer to the installation instructions provided in the <a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2.1/6-Docker-Compose.html">Installing Docker and Docker Compose </a> topic.

- **WSL2 (Windows Subsystem for Linux 2)** *(Windows only)*  
  - Recommended for Windows users.
  - Install WSL2 and a supported Linux distribution (e.g., Ubuntu) to ensure full compatibility.

TODO

To install Docker Compose Runtime for K2view Fabric Web Studio on Microsoft Windows, you will need to use Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. When installing on Microsoft Windows with WSL, you need to install a Linux distribution rather than use the default distribution bundled by Microsoft's WSL. Using the Microsoft-provided Linux distribution will cause the Docker Compose Runtime for Fabric Services to fail to run correctly. Instructions are provided in the <a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2.1/6-Docker-Compose.html">Installing Docker and Docker Compose </a> topic. 

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

| Service | Purpose |
|---------|---------|
| `https://github.com` | (optional) Clone K2view Blueprints |
| `https://docker.share.cloud.k2view.com` | Access Fabric images |
| `https://nexus.share.cloud.k2view.com` | Download Studio distributions |
| `https://exchange.k2view.com` | (if TDM is used) Access Exchange packages |

---

> After verifying prerequisites, proceed to the Installation Guide for your chosen runtime (Docker or Podman).
