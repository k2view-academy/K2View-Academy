# **Fabric Devops Security**

## General Perspective 

DevOps Information security, as we know it, is struggling considerably to keep up with today's fast-paced DevOps world. DevOps security refers to the discipline and practice of safeguarding an entire DevOps environment through strategies, policies, processes and technology. 
DevOps has seen a decade of growth and success in many software organizations. DevOps security enables the development and delivery pipeline to move faster, and enhances overall throughput to the system, in a safe manner. 

Security in DevOps should be built into every part of its lifecycle. Instead of functioning as a blocker to innovation, or being an outsider, security must be adapted to being an integral part of software delivery efforts throughout the entire process, rather than just at the very end.

K2View has embraced the DevOps perception and has implemented security throughout the DevOps pipeline from the initial design of a project up to its deployment.

## Fabric DevOps Targets 

Fabric includes the following servers: 

- Fabric
- Kafka
- Cassandra

Each server can be configured as a single node. However, to ensure high availability and for resiliency purposes, it is better to configure each server in a cluster mode.
To achieve and maintain compliance with a plethora of international regulations and standards, and to reduce cyber security risks, we strongly recommend that the following security hardening best practices are implemented. 

Fabric requires these to be in place in order for the systems to operate optimally and to operate together.

For the purpose of clarity, we assume that each Operating System on which these server instances are installed, be hardened based on the most up-to-date standards and policy. 

The following steps describe Fabric hardening on each server and their involved functionalities.

Note that this section only addresses Devops security hardening. For information about Fabric security from an architectural perspective, click [here](/articles/26_fabric_security/01_fabric_security_overview.md).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/02_fabric_environments.md) 
