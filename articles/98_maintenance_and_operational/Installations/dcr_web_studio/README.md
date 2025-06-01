
# About K2view Fabric Web Studio

**K2view Fabric Web Studio** provides developers, architects, and data teams with a unified environment to design, build, manage, and deploy data-driven solutions on the K2view Fabric platform. It enables rapid development of complex integration, transformation, and orchestration use cases through a visual, model-driven interface that fully leverages Fabric’s runtime capabilities.

Fabric Web Studio simplifies the process of building data products, logical units, APIs, business logic, and orchestrations, while embedding real-time integration, validation, and testing capabilities directly into the development workflow.

Developers benefit from:

- **Multi-space development** — easily create isolated development environments (Spaces) for individual or team workstreams.
- **Real-time integration** — build, debug, and test integrations interactively as development progresses.
- **Data orchestration** — visually design end-to-end flows across multiple data sources and systems.
- **Unified deployment model** — transition seamlessly from development to production while preserving environment isolation.
- **Secure collaboration** — integrate with source control systems (e.g., Git) to enable team-based parallel development and controlled releases.
- **Flexible runtime options** — deploy Fabric Web Studio on multiple container engines (Docker Compose or Podman Compose).

---

## Versions

**Links**

<ul>      
<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/README.md">Version 2.0</a></li>
     
<li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2.1/README.md">Version 2.1</a></li>
</ul>

**Versioning and Compatibility**

Fabric Web Studio runtime packaging is versioned independently of Fabric core versions. Please consult the installation guides for version-specific compatibility and runtime prerequisites.

- Current versions:
  - Docker Compose Runtime (e.g., **2.0**, **2.1**)
  - Podman Compose Runtime (added in **2.1**)

---

## Supported Runtime Options

Fabric Web Studio is designed to be deployed flexibly across a variety of runtime environments based on organizational needs and IT standards:

### Docker Compose Runtime

Fabric Web Studio supports deployment using Docker Compose, which orchestrates the Studio runtime, embedded Fabric engine, and Traefik reverse proxy within a Docker-based container environment. This configuration enables multi-space development while leveraging Docker’s mature ecosystem.

### Podman Compose Runtime

Fabric Web Studio also supports deployment using Podman Compose, offering a daemonless, enterprise-grade alternative to Docker. Podman Compose provides compatibility with standard Compose YAML files while aligning with Red Hat Enterprise Linux and related distributions (RHEL, AlmaLinux, Rocky Linux, CentOS Stream), supporting customers who operate in strict enterprise Linux environments.

---

## Fabric Web Studio Runtime Components

Regardless of container runtime, the following core components are deployed:

- **Fabric Web Studio Application** — the primary web-based IDE for development, testing, and orchestration.
- **Fabric Runtime Engine** — embedded data engine for local development and real-time execution.
- **System DB Options** — multiple profile options allow Web Studio to embed SQLite, PostgreSQL, Cassandra, or combined PostgreSQL + Cassandra for broader TDM (Test Data Management) use cases.
- **Traefik Reverse Proxy** — supports multi-space URL routing and optional TLS configuration.
- **Git Integration** — integrates directly with Git repositories for project storage, version control, and team collaboration.

---

## Key Use Cases

- Data product development
- Logical Unit and micro-database modeling
- API orchestration and transformation
- Real-time and batch data integration
- Test data generation and management
- Multi-environment development pipelines
- Collaborative team development with Git version control




