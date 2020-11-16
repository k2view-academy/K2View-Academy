# Fabric Environments

Fabric will require the following environments for each one of the lifecycle phases of the project from its required between inception to its delivery and maintenance.

## Development Environment

This environment should contain:
- Fabric server for each developer
- Windows for installing and running the Fabric studio.
- SVN/GIT for version control

Please note that the access to this server and SVN/GIT should be limited to the relevant developers to avoid uncontrolled access to external sources or any other project confidential information.

<img src="/articles/99_fabric_infras/devops/images/01_devop-devEnv.png">




## QA Environment

This environment should be separate from the Dev Env and contain the following components:

- Fabric servers cluster (minimum 3 for check)
- Windows Terminal server for installing and running Fabric studio.
- Access to Fabric WS (API) should be done via load balancer (LB).
- Access to SVN/GIT for version control. (The access should be limited to the relevant users.)
- Source Databases including data that should have the same structure as in the Production environment.


Note that all connection methods and protocols must be the same type as defined in the Production environment. 
All access to servers described above should be limited to the QA team only.

<img src="/articles/99_fabric_infras/devops/images/02_devop-QAEnv.png">


## Pre-Production Environment

This environment should be isolated from the Dev & QA and be the same as the Production environment:

- Fabric servers cluster, equal as much as possible to the Prod Env (minimum 3 on each DC).
- Win Terminal server for installing and running the Fabric studio (access to this server should be limited to the relevant admin team only).
- Access to the Fabric WS (API) should be done via load balancer (LB).
- Access to SVN/GIT for version control. (limited to the relevant users).
- Source Databases including data that should have the same structure as in the Production environment.


Note that:
- All connection methods and protocols must be the same type as defined in the Production environment. 
- All access to servers described above should be limited to the QA team only.
- The connection method and protocol must be the same as is used in Prod. 
- This environment should be sufficiently strong to be able to run load testing. 


<img src="/articles/99_fabric_infras/devops/images/03_devop-preProdEnv.png">


## Production Environment
This environment should be isolated from the Dev & QA and be the same as the Pre-Production environment:

- Fabric servers cluster, according to the production sizing definitions.
- Win Terminal server for installing and running the Fabric studio. (access to this server should be limited to the relevant admin team only).
- Access to the Fabric WS (API) should be done via load balancer (LB).
- Access to SVN/GIT for version control. (access should be limited to the relevant users)

<img src="/articles/99_fabric_infras/devops/images/04_devop-prodEnv.png">



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/03_fabric_and_cassandra_hardening.md)
