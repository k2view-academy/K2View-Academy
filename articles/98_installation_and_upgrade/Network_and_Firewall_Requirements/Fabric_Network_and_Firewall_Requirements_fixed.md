# Fabric Network and Firewall Requirements

## Table of Contents

1. [Overview](#overview)
2. [Network Design Principles](#network-design-principles)
3. [Fabric Application Ports](#fabric-application-ports)
4. [Fabric on Linux with Fabric Web Studio on Docker](#fabric-on-linux-with-fabric-web-studio-on-docker)
5. [Containerized Fabric Deployments](#containerized-fabric-deployments)
6. [Infrastructure and External Service Connectivity](#infrastructure-and-external-service-connectivity)
7. [Recommended Firewall Configuration](#recommended-firewall-configuration)
8. [TLS and HTTPS](#tls-and-https)
9. [Related Documentation](#related-documentation)



## Overview

This article describes the network ports and firewall requirements for K2view Fabric deployments.

It provides a central reference for planning network connectivity for:

- Fabric installed directly on Linux;
- Fabric Web Studio deployed using Docker Compose or Podman;
- Containerized Fabric deployments such as Kubernetes;
- Fabric clusters with multiple Fabric nodes;
- Supporting services such as PostgreSQL and external data sources; and
- Cassandra and Kafka are also included but only used for specific use cases. 

Not every port listed in this article must be exposed through a firewall. Required connectivity depends on the deployment architecture and the services enabled in the environment.

> **Security principle:** Open only the ports required for the deployment and restrict each port to the systems or network zones that require access. Internal Fabric and infrastructure services should not normally be exposed to untrusted or public networks.



## Network Design Principles

When defining firewall rules for Fabric, distinguish between the following types of connectivity:

1. **User and application access** — browsers, API clients, applications, load balancers, and ingress controllers accessing Fabric.
2. **Administrative access** — administrators, deployment tools, CI/CD systems, and management stations.
3. **Internal platform communication** — communication between Fabric and supporting platform services.
4. **External data connectivity** — outbound connections from Fabric to databases, APIs, message brokers, object stores, identity providers, and other enterprise services.

A firewall rule is required only when traffic crosses a network boundary where that traffic is restricted.

For example, a port used between containers within the same Kubernetes cluster does not necessarily need to be opened on the Kubernetes worker nodes or exposed outside the cluster.



## Fabric Application Ports

The following are the primary Fabric application ports.

<table>
<thead>
<tr>
<th>Port</th>
<th>Protocol</th>
<th>Purpose</th>
<th>Typical Access</th>
<th>External Exposure</th>
</tr>
</thead>
<tbody>
<tr>
<td>3213</td>
<td>TCP / HTTP</td>
<td>Default Fabric Web/API listener</td>
<td>Users, applications, deployment tools, or reverse proxy/load balancer</td>
<td>As required</td>
</tr>
<tr>
<td>8443</td>
<td>TCP / HTTPS</td>
<td>Common Fabric HTTPS listener when TLS is configured directly in Fabric</td>
<td>Users, applications, deployment tools, or reverse proxy/load balancer</td>
<td>As required</td>
</tr>
<tr>
<td>443</td>
<td>TCP / HTTPS</td>
<td>Standard HTTPS endpoint when exposed through a reverse proxy, load balancer, ingress, or when Fabric is configured to bind directly to port 443</td>
<td>Users and applications</td>
<td>Typically yes when Fabric services are externally accessible</td>
</tr>
<tr>
<td>5124</td>
<td>TCP / Fabric JDBC</td>
<td>Fabric JDBC listener</td>
<td>Fabric JDBC clients and authorized deployment or administrative components that require JDBC connectivity</td>
<td>Normally no; expose only when JDBC access is required</td>
</tr>
<tr>
<td>9443</td>
<td>TCP</td>
<td>Fabric management/administrative service where applicable</td>
<td>Authorized administrative components</td>
<td>Normally no</td>
</tr>
</tbody>
</table>

### Port 3213 — Fabric Web and API

Port `3213/TCP` is the default Fabric HTTP listener used to access Fabric web services and APIs.

For example:

```text
http://fabric-host:3213/
```

Fabric REST APIs use the same Fabric web endpoint.

In secured production environments, clients should normally access Fabric using HTTPS rather than exposing the default HTTP listener directly.

Depending on the architecture, TLS can terminate:

- directly at Fabric;
- at a reverse proxy;
- at a load balancer; or
- at a Kubernetes ingress controller.

When TLS terminates upstream of Fabric, port 3213 may remain an internal endpoint between the proxy or ingress layer and Fabric.

Configured in Fabric's config.ini

```text
## Web service non-secure port. To turn off, activate secure port or set port to 0 (requires restart)
#WEB_SERVICE_PORT=3213

## Web service secure port. By default (commented) or when set to 0, https is disabled. When turned on (uncomment), the unsecure port will be turned off. (requires restart)
#WEB_SERVICE_SECURE_PORT=8443
```

### HTTPS — Ports 443 and 8443

Fabric can be configured with a secure HTTPS listener. Port `8443` is commonly used when you configure HTTPS directly in Fabric for the Web service secure port.

Port `443` can instead be presented as the standard external HTTPS endpoint, particularly when Fabric is accessed through a reverse proxy, load balancer, or Kubernetes ingress.

Binding an application directly to TCP ports below 1024, including port 443, may require additional operating-system privileges or configuration on Linux. For this reason, it is common to terminate HTTPS on port 443 at a reverse proxy or load balancer and forward traffic to the Fabric application port.

See [Fabric API and UI Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md) for configuring HTTPS.

### Port 5124 — Fabric JDBC

Port `5124/TCP` is the default Fabric JDBC listener.

The listener is configured in `config.ini`:

```ini
[jdbc-server]

## Listener port for Fabric driver (requires restart)
#PORT=5124

## Turn on TLS for the Fabric driver protocol (requires restart)
#SECURE=true
```

The default JDBC connection syntax is:

```text
jdbc:fabric://<fabric-host>:5124
```

Port 5124 does **not** normally need to be exposed to end users or public networks.

Allow access only from systems that require the Fabric JDBC protocol, such as:

- authorized Fabric JDBC clients;
- approved integration or BI systems using the Fabric JDBC driver; or
- deployment or administrative tooling when configured to use the JDBC endpoint.

When Fabric is deployed as a cluster, firewall rules should permit required Fabric services between relevant trusted cluster components without unnecessarily exposing those services outside the Fabric environment.

TLS is disabled by default for the Fabric JDBC protocol.



## Fabric Web Studio on Docker 

A common deployment architecture runs Fabric Web Studio on a Linux, Windows, or Mac host using Docker Compose or Podman.

In this architecture, there are two distinct network boundaries:

```text
Developer Browser
       |
    HTTPS
       |
       v
+-----------------------+
| Fabric Web Studio     |
| Docker / Podman Host  |
+-----------------------+
       |
       | Fabric deployment/API connectivity
       v
+-----------------------+
| Fabric                |
| Linux Server          |
+-----------------------+
       |
       | Database/API/etc.
       v
+-----------------------+
| Enterprise Data       |
| Sources and Services  |
+-----------------------+
```

### Fabric Web Studio Host

Fabric Web Studio uses a reverse proxy to provide browser access to Studio and its Fabric Spaces.

For a secured deployment, browser traffic should normally enter through HTTPS on port `443`.

You don't need to expose all internal container ports through the host firewall. Docker or Podman networking provides communication between containers within the Web Studio runtime.

If JDBC access to a Web Studio Fabric Space is required, you can explicitly expose port 5124 for that Space. This is optional and should only be configured when JDBC connectivity is required.

### Web Studio to the Fabric Deployment Environment

Fabric Web Studio or the deployment tooling must be able to reach the target Fabric environment using the configured deployment protocol.

Where remote deployment uses the Fabric HTTP/HTTPS API, allow connectivity from the Web Studio/deployment environment to the applicable Fabric API endpoint.

For example:

```text
Web Studio / Deployment Host
        |
        | HTTPS
        v
Built-in Traefik 
        |
        v
      Fabric
```

The firewall should restrict this connectivity to the Web Studio, CI/CD, or other authorized deployment systems.

There is generally no reason to expose the Fabric JDBC listener to developer workstations merely because Fabric Web Studio is being used.

## Fabric on Linux

Fabric Server can be installed directly on Linux as either:

- a **single Fabric node**; or
- a **multi-node Fabric cluster** for scalability and high availability.

Each Fabric node runs the Fabric services and requires connectivity to the System DB and other infrastructure services used by the deployment. Multi-node deployments additionally require Kafka for Fabric Pub/Sub.

Firewall requirements therefore depend on whether Fabric is deployed as a single node or a cluster and on the System DB and other services selected for the environment.

### Single-Node Fabric

A single-node Fabric deployment consists of one Fabric Server and its configured System DB.

A typical production deployment using PostgreSQL is:

```text
Users / Applications
        |
        | HTTPS :443 or :8443
        | HTTP  :3213 when enabled
        v
+-----------------------+
| Fabric Server         |
| Linux                 |
+-----------------------+
        |
        | TCP :5432
        v
+-----------------------+
| PostgreSQL System DB  |
+-----------------------+

Fabric Server
     |
     +----------> Source / Target Systems
     |
     +----------> External APIs and Services
```

The primary firewall requirements are:

- clients requiring Fabric Web/API access must be able to reach the configured Fabric HTTP or HTTPS endpoint;
- systems requiring Fabric JDBC access must be able to reach TCP port `5124`;
- Fabric must be able to reach its configured System DB;
- Fabric must be able to reach the source systems, target systems, APIs, and other external services configured for the project.

If PostgreSQL is used as the System DB, Fabric requires connectivity to the PostgreSQL server or cluster endpoint, typically TCP port `5432`.

Port `5124` does not need to be exposed unless a system requires Fabric JDBC connectivity.

### Multi-Node Fabric Cluster

A Fabric cluster consists of two or more Fabric Server nodes. Each node is installed on a Linux server and configured as part of the same Fabric environment.

Fabric nodes share platform state through the System DB. When a Fabric node starts, it registers with the cluster and obtains the deployed project from the System DB.

Kafka is required for multi-node Fabric deployments and provides the durable Pub/Sub service the Fabric cluster uses.

A typical deployment is:

```text
                  Users / Applications
                          |
                          | HTTPS :443
                          v
                 +-------------------+
                 | Load Balancer     |
                 +-------------------+
                    |             |
                    |             |
                    v             v
             +------------+  +------------+
             | Fabric     |  | Fabric     |
             | Node 1     |  | Node 2     |
             +------------+  +------------+
                    |             |
                    +------+------+
                           |
              +------------+------------+
              |                         |
              v                         v
      +---------------+          +---------------+
      | PostgreSQL    |          | Kafka         |
      | System DB     |          | Cluster       |
      +---------------+          +---------------+

        Fabric Nodes
             |
             +----------> Source / Target Systems
             |
             +----------> External APIs and Services
```

For a multi-node deployment, each Fabric node must be able to reach:

- the System DB;
- the Kafka brokers;
- source and target systems required by deployed projects; and
- other configured external services.

If PostgreSQL is used as the System DB, each Fabric node requires access to the PostgreSQL server, cluster VIP, or load-balancer endpoint, typically on TCP port `5432`.

Each Fabric node must also reach the configured Kafka brokers on the Kafka listener port. The Kafka port depends on the Kafka deployment and security configuration.

> **Important:** A multi-node Fabric cluster does not need to expose Fabric application ports such as 3213 or 5124 publicly. Firewall rules should distinguish client access to Fabric from the backend connectivity required by the Fabric nodes.

### Client Access to a Fabric Cluster

In a production cluster, client traffic is typically directed through a load balancer or other enterprise network endpoint rather than directly to an individual Fabric node.

For example:

```text
Client
   |
   | HTTPS :443
   v
Load Balancer
   |
   | Fabric Web/API
   v
Fabric Nodes
```

The externally exposed port can therefore be different from the port on which Fabric itself listens.

For example, an enterprise load balancer can accept HTTPS on TCP port `443` and forward traffic to the Fabric Web/API listener on TCP port `3213`.

Alternatively, Fabric can provide HTTPS directly using its configured secure Web service port.

Port `5124` should be reachable only when Fabric JDBC clients require it. It does not need to be exposed through the Web/API load balancer unless that load balancer is specifically configured to provide JDBC connectivity.

### Fabric Cluster Connectivity

Fabric nodes require connectivity to the shared services that make up the Fabric environment, but this does not imply that every Fabric application port must be opened between every Fabric server.

Firewall rules should be based on the actual traffic flow:

<table>
<thead>
<tr>
<th>Source</th>
<th>Destination</th>
<th>Default Port</th>
<th>Purpose</th>
</tr>
</thead>
<tbody>
<tr>
<td>Users / applications / load balancer</td>
<td>Fabric nodes</td>
<td>3213, 8443, or configured port</td>
<td>Fabric Web/API access</td>
</tr>
<tr>
<td>Authorized JDBC clients</td>
<td>Fabric nodes</td>
<td>5124</td>
<td>Fabric JDBC access</td>
</tr>
<tr>
<td>Fabric nodes</td>
<td>PostgreSQL System DB</td>
<td>5432</td>
<td>Shared Fabric System DB</td>
</tr>
<tr>
<td>Fabric nodes</td>
<td>Kafka brokers</td>
<td>Configured Kafka listener port</td>
<td>Fabric Pub/Sub for multi-node deployments</td>
</tr>
<tr>
<td>Fabric nodes</td>
<td>Source / target systems</td>
<td>System-specific</td>
<td>Project data connectivity</td>
</tr>
<tr>
<td>Fabric nodes</td>
<td>External APIs and services</td>
<td>Service-specific</td>
<td>Project and platform integrations</td>
</tr>
</tbody>
</table>

The same rules must apply to every Fabric node that can execute the corresponding workload.

For example, if any node in the cluster can execute a Fabric interface that connects to a source database, the network configuration must allow the applicable Fabric nodes to reach that database.

### Scaling a Fabric Cluster

When you scale out a Fabric cluster, the new Fabric node must have the same required network connectivity as the existing nodes.

Before adding a node, verify that it can reach:

- the System DB;
- Kafka in a multi-node deployment;
- required source and target systems; and
- other external services required by the deployed Fabric projects.

The node must also be reachable through any load balancer or other network service intended to distribute Fabric client traffic to that node.

Adding a Fabric node should therefore include review of the applicable firewall rules, security groups, load-balancer configuration, and allowlists.

For additional information, see:

- [Fabric 8 Installation Guide](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md)
- [Fabric 8 with PostgreSQL Setup](/articles/98_installation_and_upgrade/Install_on_Linux/02.2_Fabric_8.x.x_PG_setup.md)
- [Fabric Cluster Scaling](/articles/98_installation_and_upgrade/Install_on_Linux/03_fabric_scale.md)

## Containerized Fabric Deployments

For Fabric deployed in Kubernetes or another containerized environment, distinguish between:

- container ports;
- Kubernetes Service ports;
- Ingress/load-balancer ports; and
- ports exposed on the underlying worker nodes.

A Fabric container listening on port 3213 or 5124 does not mean that the same port must be exposed outside the Kubernetes cluster.

### Recommended Pattern

Externally accessible Fabric Web/API traffic should normally follow this path:

```text
Client
   |
 HTTPS :443
   |
   v
Ingress / Load Balancer
   |
   v
Kubernetes Service
   |
   v
Fabric Pod :3213
```

Only the ingress or load-balancer endpoint needs to be reachable by external clients.

Fabric JDBC access on port 5124 should only be exposed through a Kubernetes Service or load balancer when an external JDBC client requires it.

Internal services should remain cluster-internal whenever possible.

### Network Policies and Firewalls

In Kubernetes environments, network controls may exist at several layers:

- enterprise network firewalls;
- cloud security groups or network security groups;
- Kubernetes NetworkPolicies;
- ingress controllers;
- service meshes; and
- load balancers.

The same least-privilege principle applies at each layer: permit only the source, destination, port, and protocol required for the application flow.



## Infrastructure and External Service Connectivity

Fabric may require connectivity to additional infrastructure depending on the deployment architecture.

These ports are **not automatically required for every Fabric installation**.

<table>
<thead>
<tr>
<th>Default Port</th>
<th>Service</th>
<th>Typical Direction</th>
<th>Required When</th>
</tr>
</thead>
<tbody>
<tr>
<td>22</td>
<td>SSH / SCP</td>
<td>Management → Linux host</td>
<td>SSH-based administration or file transfer is permitted</td>
</tr>
<tr>
<td>5432</td>
<td>PostgreSQL</td>
<td>Fabric → PostgreSQL</td>
<td>PostgreSQL is used as the Fabric System DB or by another configured component</td>
</tr>
<tr>
<td>9042 / 9142</td>
<td>Cassandra</td>
<td>Fabric → Cassandra</td>
<td>Cassandra is part of the selected architecture</td>
</tr>
<tr>
<td>9093 / configured broker port</td>
<td>Kafka</td>
<td>Fabric → Kafka</td>
<td>Kafka is configured as a Fabric messaging/pub-sub service</td>
</tr>
<tr>
<td>3000 / 9090</td>
<td>Grafana / Prometheus</td>
<td>Management/monitoring network</td>
<td>These monitoring components are deployed and network access is required</td>
</tr>
<tr>
<td>443</td>
<td>HTTPS</td>
<td>Fabric → external service</td>
<td>Fabric accesses HTTPS APIs, identity providers, cloud services, object storage endpoints, Git services, or other HTTPS resources</td>
</tr>
<tr>
<td>Database-specific</td>
<td>Source/target databases</td>
<td>Fabric → data source/target</td>
<td>The corresponding interface is configured in the Fabric project</td>
</tr>
</tbody>
</table>

The actual port used for an external system is determined by that system's configuration. For example, a database configured on a non-default port requires that configured port, not the database vendor's default port.

### System Database

When an external PostgreSQL database is used as the Fabric System DB, Fabric nodes require network access to PostgreSQL, typically on TCP port `5432`.

The PostgreSQL server does not need general inbound access from users or developer workstations. Limit access to the Fabric components and administrative systems that require it.

### Source and Target Systems

Fabric projects connect to customer data sources and target systems through configured interfaces.

Firewall rules must therefore also permit the Fabric runtime to reach the required:

- relational databases;
- APIs and web services;
- message brokers;
- file services;
- object storage;
- identity providers;
- secrets managers; and
- other project-specific enterprise services.

These connections are project- and environment-specific and are not represented by a single fixed set of Fabric ports.



## Recommended Firewall Configuration

A typical secured deployment should follow these principles:

1. **Expose only the application entry point.**  
   Use HTTPS, normally TCP `443`, as the externally accessible endpoint for Fabric APIs and user-facing services.

2. **Keep Fabric HTTP internal when TLS terminates upstream.**  
   If a load balancer, reverse proxy, or ingress terminates TLS, TCP `3213` can remain accessible only within the trusted application network.

3. **Restrict JDBC access.**  
   TCP `5124` should only be reachable from systems that require the Fabric JDBC protocol.

4. **Do not expose infrastructure databases publicly.**  
   PostgreSQL, Cassandra, Kafka, and other platform services should be accessible only by the Fabric components and management systems that require them.

5. **Restrict administrative access.**  
   SSH and other management interfaces should be limited to approved administrative networks or bastion hosts.

6. **Allow required outbound connectivity.**  
   Fabric must be able to connect to the source systems, target systems, APIs, identity services, repositories, object stores, and other external services configured for the project.

7. **Apply the same principles to containers.**  
   A port exposed by a container does not automatically need to be exposed outside the container host or Kubernetes cluster.

### Example

For a Fabric cluster accessed through an HTTPS load balancer, a typical network design might be:

```text
Users / Applications
        |
        | TCP 443
        v
+-------------------+
| Load Balancer     |
+-------------------+
        |
        | TCP 3213
        v
+-------------------+
| Fabric Nodes      |
+-------------------+
   |       |       |
   |       |       +----> External APIs / Services
   |       |
   |       +------------> System DB :5432
   |
   +--------------------> Source / Target Systems
```

If no external JDBC client requires access to Fabric, port `5124` remains restricted to the trusted Fabric/application network and does not need to be exposed through the external load balancer.



## TLS and HTTPS

K2view recommends protecting Fabric interfaces using TLS when traffic crosses network or trust boundaries.

Fabric supports TLS for both:

- Fabric Web/API services; and
- the Fabric JDBC protocol.

For the Fabric Web/API interface, HTTPS can terminate directly in Fabric or at an enterprise reverse proxy, load balancer, or Kubernetes ingress.

For the Fabric JDBC listener, TLS is controlled in the `[jdbc-server]` section of `config.ini`:

```ini
[jdbc-server]

#PORT=5124
#SECURE=true
```

For configuration instructions, certificate requirements, and API/UI hardening, see:

[Fabric API and UI Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)



## Related Documentation

- [System Requirements, Installation, and Upgrade Overview](/articles/98_installation_and_upgrade/Hardware/README.md)
- [Fabric Studio Installation System Requirements](/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md)
- [Fabric 8 Installation Guide](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md)
- [Fabric Web Studio 2.1](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.md)
- [Fabric API and UI Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)



> **Note:** Port numbers documented here are K2view defaults. Some ports are configurable. Always validate the deployed configuration and the architecture of the specific environment before implementing firewall rules.