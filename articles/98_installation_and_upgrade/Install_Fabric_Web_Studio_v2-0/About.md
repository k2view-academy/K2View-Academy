# Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0
This document describes the Docker Compose Runtime used for hosting K2view Fabric Web Studio. It covers setup, components, installation options, and features. 

## Deprecation Notice
K2view Fabric Web Studio, Version 2.0, is now deprecated and has been replaced with Version 2.1. Please use to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">Fabric Web Studio 2.1 release.</a>

If you need version 2.0, please contact K2view support, who can make arrangements to make it available to you.

## About K2view Fabric Web Studio

K2view Fabric Web Studio provides developers a unified platform for designing, building, and managing data-driven solutions. The Docker Compose Runtime's multi-space capability enables the creation of data management applications supporting multiple developers. Developers benefit from robust data management and orchestration tools for real-time integration, seamless testing, and debugging features. These accelerate project completions and deliver collaborative functionality that supports multiple users. 

Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0, supports:
 - multiple-space creation and
 - provides a simplified URL for accessing Fabric spaces using a URL context rather than a subdomain-based URL. 

## The Components

1. **Docker Compose Runtime**: Fabric Web Studio can be installed within a Docker Compose Runtime environment. Docker and its Compose plugin provide the ability to run Web Studio for which profiles can be selected, an embedded Fabric engine, and a Traefik reverse proxy - that, when combined, provide the means to create multiple Fabric spaces within the Docker Compose Runtime. Installation instructions are provided in the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-0/6-Docker-Compose.md">Installing Docker and Docker Compose </a> topic.
2. **Fabric Image**: The Docker Compose Runtime is certified to run specific Fabric releases that you can download from K2view's Nexus Container Registry.
3. **K2view Fabric Web Studio** - available with four profiles, each of which embeds Fabric.
  - **studio.config**. The default Web Studio profile embeds SQLite for its System DB.
  - **studio_pg.config**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
  - **studio_cass.config**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
  - **studio_pg_cass.config**. A TDM profile that incorporates Apache Cassandra for its System DB and PostgreSQL for TDM tasks.
4. **Traefik Reverse Proxy** - allows you to route requests to your various running Fabric spaces within your Docker Compose Runtime at http(s)://[host]/[spacename]/. 


## Prerequisites

The Docker Compose Runtime for Fabric Services has specific prerequisites. 

### Host Machine

Fabric requires an AMD64 processor architecture. When running on Apple Silicon or ARM64 processor architectures, AMD64 emulation will be performed if your Docker installation's configuration is enabled.

The amount of RAM you need will depend on your use case. 32GB of memory should suffice to run your Docker Compose Runtime for Fabric Web Studio. A 2GB heap size per Fabric Space is allocated by default, which can be overridden. 

### 3rd Party Software

1. You need to install a Git client on the computer by downloading and installing it. You can download it from https://git-scm.com/downloads and follow the instructions provided at https://git-scm.com/book/en/v2/Getting-Started-Installing-Git.

2. You need to install and run Docker. You also need to install the Docker Compose plugin. If you install Docker Desktop, then Docker Compose is bundled. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. The installation links are:
   - Install Docker Desktop: https://docs.docker.com/desktop/
   - Install Docker: https://docs.docker.com/engine/install/
   - Install Docker Compose Plugin: https://docs.docker.com/compose/install/

> Please refer to the installation instructions provided in the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-0/6-Docker-Compose.md">Installing Docker and Docker Compose </a> topic.
     
3. To install Docker and Docker Compose, a platform that will host the Docker Compose Runtime for Fabric Services, you need to have administrative rights on the machine:
   - Linux: Root or sudo access grants you administrative rights
   - Windows: Administrator rights are required on your machine

4. To install Docker Compose Runtime for K2view Fabric Web Studio on Microsoft Windows, you will need to use Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. When installing on Microsoft Windows with WSL, you need to install a Linux distribution instead of using the default distribution bundled by Microsoft's WSL. Using the Microsoft-provided Linux distribution will cause the Docker Compose Runtime for Fabric Services to fail to run correctly. Instructions are provided in the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-0/6-Docker-Compose.md">Installing Docker and Docker Compose </a> topic. 

### K2view Software

1. It is presumed that you have Internet access for the installation to obtain Fabric images from the K2view Nexus Container Registry and perform a Git clone on your machine. 
2. To obtain a Fabric Studio docker image, a K2view Nexus account is required. Your K2view representative can arrange this for you. 

### Internet Access is Required

Internet access is required to perform this installation. You will need access to:

1. Github.com to clone K2view’s blueprints at https://github.com/k2view/blueprints.git
2. K2view’s Nexus Docker Image repository at https://docker.share.cloud.k2view.com
3. If you plan to install TDM, you need access to K2view’s Exchange.
