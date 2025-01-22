# Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0
This document describes the Docker Compose Runtime used for hosting K2view Fabric Web Studio. It covers setup, components, installation options and features. 

## About K2view Fabric Web Studio

K2view Fabric Web Studio provides developers with a unified platform for designing, building and managing data-driven solutions. The Docker Compose Runtime's multi-space capability enables the creation of data management applications supporting multiple developers. Developers benefit from robust data management and orchestration tools for real-time integration, seamless testing, and debugging features. These accelerate project completions and deliver collaborative functionality that supports multiple users. 

Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0, supports multiple-space creation and provides a simplified URL for accessing Fabric spaces using a URL context rather than a subdomain-based URL. 

## The Components

1. **Docker Compose Runtime**: Fabric Web Studio can be installed within a Docker Compose Runtime environment. Docker and its Compose plugin provide the ability to run Web Studio for which profiles can be selected, an embedded Fabric engine, and a Traefik reverse proxy - that when combined - provide the means to create multiple Fabric spaces within the Docker Compose Runtime. 
2. **Fabric Image**: The Docker Compose Runtime is certified to run specific Fabric releases that you can download from K2view's Nexus Container Registry.
3. **K2view Fabric Web Studio** - available with four profiles, where each embeds Fabric.
  - **studio.config**. The default Web Studio profile that embeds SQLite for its System DB.
  - **studio_pg.config**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
  - **studio_cass.config**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
  - **studio_pg_cass.config**. A TDM profile that incorporates Apache Cassandra for its System DB and PostgreSQL for TDM tasks.
4. **Traefik Reverse Proxy** - allows you to route requests to your various running Fabric spaces within your Docker Compose Runtime at http(s)://[host]/[spacename]/. 
