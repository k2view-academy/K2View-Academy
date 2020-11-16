# **Fabric Devops Security**

## General Perspective 

DevOps Information security, as we know it, is struggling considerably to keep up with today's fast-paced DevOps world. DevOps security refers to the discipline and practice of safeguarding the entire DevOps environment through strategies, policies, processes, and technology. 
DevOps has seen a decade of growth and success in many software organizations. DevOps security enables the development and delivery pipeline to move faster, and enhances overall throughput to the system, in a safe manner. 

Security in DevOps should be built into every part of its lifecycle: instead of functioning as a blocker to innovation, or being an outsider, security must be adapted to being an integral part of software delivery efforts throughout the entire process, rather than just at the very end.

K2View has embraced DevOps perception and implemented security along the DevOps pipeline from the design phase and up to the deployment phase.


## Fabric DevOps Targets 

Fabric product includes the following servers: 

- Fabric
- Kafka
- Cassandra

Each one of these servers can be configured as a single node, but to ensure high availability and resiliency purposes, it is better to configure each one as in a cluster mode.
To achieve and maintain compliance with a plethora of international regulations and standards, and to reduce cyber security risks, we strongly recommend that the following security hardening best practices are implemented. 

Fabric will require these to be in place in order for the systems to operate optimally or even to operate all together.

For the purpose of clarity we assume that each Operating System on which these servers instances are installed should be hardened based on the most up-to-date standards and policy. 

The following steps describe Fabric hardening for each one of the servers and functionality involved.

Please note that this section deals only with devops security concerns. An entire section dealing with all the aspects of Fabric security from an architecture perspective is available [here](/articles/26_fabric_security/01_fabric_security_overview.md).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/02_fabric_environments.md) 
